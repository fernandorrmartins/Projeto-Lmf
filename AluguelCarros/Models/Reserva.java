package AluguelCarros.Models;

import AluguelCarros.Services.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.sql.Date;

/* Objeto responsável por representar as Reservas de Clientes */
public class Reserva {
	
	/* Atributos da classe */
	// Representa o Tipo de Carro que a Reserva solicitou
	private String TipoCarro = null;
	// Quantidade máxima de passageiros para aquele carro solicitado pela reserva
	private Integer QtdPassageiros = null;
	// As datas dos dias da reserva
	private String[] Datas = null;
	// Atributo responsável para possíveis erros
	private String Erro = null;
	
	/*
	 * Método Construtor - Sobrecarga
	 * Sobrescreve o método construtor padrão. Esta classe só é criada a partir de uma linha do arquivo reserva.txt
	 * Recebe a linha e separa em suas respectivas partes: Tipo de Carro, Quantidades de Pessoas por Carro e Datas
	 * Em seguida preenche os atributos da Classe com os seus respectivos valores
	 */
	private Reserva(String pedido){
		// Divide a linha em campos utilizando o token ':'
		String[] p = pedido.split(":");
		/*
		 * A linha possui 3 campos. Se possuir mais ou menos, o formato esta incorreto.
		 * Se houver quantidade de campos diferentes de 3, informa o erro ao usuário.
		 */
		if(p.length != 3) 
		{
			this.Erro =
			"\nFormato de entrada incorreto!\n\nFormato Correto:\n<Tipo_Carro>:<Quantidade_Passageiros>:<Data1>,<Data2>,<Data3>,...\n";
			
		/*
		 * Se não houver erro preenche os atributos da Classe com seus respectivos valores.
		 */
		} else {
			this.TipoCarro = p[0].toLowerCase();
			this.QtdPassageiros = Integer.valueOf(p[1]);
			this.Datas = p[2].split(",");
			//System.out.println(this.TipoCarro + " " + this.QtdPassageiros + " " + Arrays.toString(Datas));
		}
	}
	
	/* Método estático responsável por ler o arquivo de Reservas na Area de Trabalho e restornar a Lista de Reservas. */
	public static List<Reserva> CarregarPedidos() {
		// Caminho para o Arquivo de Reservas na Area de Trabalho
		String pathDesktop = System.getProperty("user.home") + "\\Desktop\\reserva.txt";
		
		// Cria a lista que sera preenchida
		List<Reserva> reservas = new ArrayList<Reserva>();
		// Criar a váriavel responsável por ler o arquivo
		BufferedReader reader;
		
		try {
			// Instancia o objeto responsável por ler o arquivo utilizando o caminho para Area de Trabalho
			reader = new BufferedReader(new FileReader(pathDesktop));
			// Objeto a ser adicionado a lista durante o loop
			Reserva reserva = null;
			// Variável responsável por armazenar cada linha do arquivo durante o while
			String line = null;
			// Contador de linhas para analise de problemas(Arquivo vazio, formato incorreto e afins...)
			int count = 0;
			/* Loop Responsável por ler cada linha do arquivo de Reservas */
			while((line = reader.readLine()) != null) {
				// Incrementa contador de linhas
				count++;
				// Instancia o objeto Reserva utilizando método de sobrecarga com a linha do arquivo
				reserva = new Reserva(line);
				// Verifica possíveis erros
				if(reserva.getErro() != null){
					count = -1;
					reservas.clear();
					System.out.println(reserva.getErro());
					break;
				}
				reservas.add(reserva);
			}
			// Verifica se o arquivo esta vazio e informa ao usuário
			if(count == 0) { System.out.println("Arquivo reserva.txt nao possui nenhum reserva!\n"); }
			// Verifica se o arquivo foi carregado com sucesso e informa ao usuário
			else if(count > 0) { System.out.println("Arquivo de reservas carregado com sucesso!\n"); }
			
		} catch (Exception e) {
			/*
			 * Caso ocorra um erro, explica onde deve estar o arquivo de Reservas:
			 *	-O nome que o arquivo deve ter;
			 *	-Formato de cada linha;
			 *	-Os tipos de carros que podem ser definidos
			 *	-Apresenta exemplos de linha com seus respectivos formatos;
			 */
			System.out.println("\nNao foi possivel ler ou localizar o arquivo de reserva na area de trabalho!\nO arquivo deve possuir o nome: reserva.txt\nCada linha do arquivo deve ser um reserva.\nO formato da linha deve ser: <Tipo_Carro>:<Quantidade_Passageiros>:<Data1>,<Data2>,<Data3>,...\n\nOs tipos de carros devem ser: compacto, esportivo, suv.\n\nExemplos:\ncompacto:3:16/05/2019,01/07/2020\nesportivo:1:13/06/2019\nsuv:6:23/08/2020,28/10/2025,01/01/2030");
		}
		
		// Retorna lista de reservas
		return reservas;
	}
	
	/* Método estático responsável por analisar e apresentar ao usuário os melhores valores para sua necessidade. */
	public static void ApresentarMelhoresPropostas(List<Reserva> reservas, HashMap<String, Loja> map){
		try {
			// Contador de Reservas
			int contador = 0;
			for(Reserva reserva : reservas) {
				// Implementa a Reserva
				contador++;
				// Apresenta Número da Reserva
				System.out.println("\t\t\tReserva: " + contador);
				
				if(!VerificarSeQtdCondizComTipoDeCarro(reserva)) { return; }
				if(VerificarSeDataJaPassou(reserva.getDatas())) { return; }
				
				// Lista de Booleanos que os Indices representam se o Indice do Array de Datas é ou não Dia da Semana
				List<Boolean> diaSemana = new ArrayList<Boolean>();
				
				// Percorrer o Array de Datas para Preencher a Lista de Booleans
				for(int i = 0; i < reserva.getDatas().length; i++){
					diaSemana.add(PegarSeEFDS(Util.PegarDiaDaSemanaComoInt(Util.StringParaData(reserva.getDatas()[i]))));
				}
				
				// Recupera a Loja pelo Tipo de Carro
				Loja loja = (reserva.getTipoCarro().equals("compacto") ? map.get("SouthCar")
						  : (reserva.getTipoCarro().equals("esportivo") ? map.get("WestCar")
						  : map.get("NorthCar")));
				
				// Representa o Preço Total das Reservas
				Double precoTotal[] = {0.00, 0.00};
				// Mensagem Formatada para Apresentar a Reserva e seus Detalhes
				String mensagem = "__________________________________________________________________________________________________________________";
				// Tipos de Carros Oferecidos pela Loja
				mensagem += "\n| Opcoes de Carro: "
						 +  "\n|\t" + Arrays.toString(loja.getCarros()).replace('[',' ').replace(']',' ');
				// Quantidade de Pessoas para a Reserva
				mensagem += "\n| Quantidade de Pessoas: " + reserva.getQtdPassageiros();
				// Apresenta as Datas Solicitadas e seus Valores
				mensagem += "\n| Datas e Valores:";
				// Percorre as Datas
				for(int i = 0; i < reserva.getDatas().length; i++) {
					mensagem += "\n|\tData: " + Util.DataParaString(Util.StringParaData(reserva.getDatas()[i]))
							 +  " | Dia de Semana: " + (diaSemana.get(i) ? "Sim" : "Nao")
							 +  " | Preco Regular: R$" 
									+ String.format("%.2f", (diaSemana.get(i) == true ? loja.getValorSemana(0) : loja.getValorFds(0)))
							 +  " | Preco Premium: R$" 
									+ String.format("%.2f", (diaSemana.get(i) == true ? loja.getValorSemana(1) : loja.getValorFds(1)));
					// Soma os Valores para Apresentar o Valor Total no Fim
					precoTotal[0] += (diaSemana.get(i) == true ? loja.getValorSemana(0) : loja.getValorFds(0));
					precoTotal[1] += (diaSemana.get(i) == true ? loja.getValorSemana(1) : loja.getValorFds(1));
				}
				mensagem += "\n__________________________________________________________________________________________________________________";
				mensagem += "\n| Valor Total Regular: R$" + String.format("%.2f", precoTotal[0])
						 +  " | Valor Total Premium: R$" + String.format("%.2f", precoTotal[1]);
				mensagem += "\n__________________________________________________________________________________________________________________\n";
				
				System.out.println("\n" + mensagem + "\n");
			}
		} catch(Exception e) {
			System.out.println("Não foi possível avaliar a reserva! Verifique o formato das reservas!");
		}
	}
	
	/* Método Estático que Verifica se a Quantidade de Pessoas informada condiz com o Tipo do Carro Solicitado */
	private static boolean VerificarSeQtdCondizComTipoDeCarro(Reserva reserva){
		boolean condiz = false;
		
		if(reserva.getQtdPassageiros() < 0 || reserva.getQtdPassageiros() > 7){
			System.out.println("\n______________________________________________________________"
							 + "\n| Quantidade de pessoas invalida!"
							 + "\n______________________________________________________________");
		} else if((reserva.getTipoCarro().equals("compacto") && reserva.getQtdPassageiros() > 4)
			||(reserva.getTipoCarro().equals("esportivo") && reserva.getQtdPassageiros() > 2)
			||(reserva.getTipoCarro().equals("suv") && reserva.getQtdPassageiros() > 7)){
			System.out.println("______________________________________________________________"
							+  "\n| Tipo de carro nao condiz com quantidade de passageiros.\n| Segue lista de quantidade de pessoas por Tipo de Carro:\n| Esportivo: 2 pessoas\n| Compacto: 4 pessoas\n| SUV: 7 pessoas"
							+ "\n______________________________________________________________");
		} else if(!reserva.getTipoCarro().equals("compacto")
				&& !reserva.getTipoCarro().equals("esportivo")
				&& !reserva.getTipoCarro().equals("suv")) {
			System.out.println("\n______________________________________________________________"
							 + "\n| Tipo de carro incorreto!"
							 + "\n______________________________________________________________");
		} else {
			condiz = true;
		}
		
		return condiz;
	}
	
	/* Método Estático que Verifica se a Data solicitada já passou ou ainda não */
	private static boolean VerificarSeDataJaPassou(String[] datas) throws Exception {
		boolean passou = false;
		for(String d : datas) {
			Date hoje = new Date(System.currentTimeMillis());
			Date data = Util.StringParaData(d);
			
			if(hoje.after(data)) {
				passou = true;
				System.out.println("\n______________________________________________________________"
								+ "\n| A data " + Util.DataParaString(data) + " e invalida!\n| Por favor defina uma nova data!"
								+ "\n______________________________________________________________");
				break;
			}
		}
		
		return passou;
	}
	
	/* Método Estático que Verifica se o Dia é ou não Fim de Semana pelo seu Número */
	private static boolean PegarSeEFDS(int dia) {
		boolean fds = true;
		
		if(1 < dia && dia < 7) { fds = false; }
		
		return fds;
	}
	
	/*
	 * Métodos Get da Classe.
	 * Por ser uma classe que só é criada a partir de uma linha, não possui métodos Set.
	 * Seus atributos são imutaveis.
	 */
	private String getTipoCarro(){ return this.TipoCarro; }
	
	private Integer getQtdPassageiros(){ return this.QtdPassageiros; }
	
	private String[] getDatas(){ return this.Datas; }
	
	private String getErro(){ return this.Erro; }
}