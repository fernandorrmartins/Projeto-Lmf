package AluguelCarros.Models;

/* Objeto responsável por representar as Loja da Empresa */
public class Loja {
	
	/* Atributos da classe */
	// Nome da Loja
	private String Nome;
	/*
	 * Valor Semanal de Aluguel para Aquela loja
	 * Indice = 0 -> Valor de Clientes Regulares durante a Semana
	 * Indice = 1 -> Valor de Clientes Premium durante a Semana
	 */
	private double[] ValorSemana;
	/*
	 * Valor do Fim de Semana de Aluguel para Aquela loja
	 * Indice = 0 -> Valor de Clientes Regulares durante o fim de semana
	 * Indice = 1 -> Valor de Clientes Premium durante o fim de semana
	 */
	private double[] ValorFds;
	
	/* Método Construtor Padrão */
	public Loja(){}
	/* Método Construtor - Sobrecarga ao passar todos os valores por parâmetro formal */
	public Loja(String Nome, double[] ValorSemana, double[] ValorFds){
		/* Definindo valores durante construção do objeto */
		this.Nome = Nome;
		this.ValorSemana = ValorSemana;
		this.ValorFds = ValorFds;
	}
	
	/*
	 * Métodos Set/Get da Classe
	 */
	public void setNome(String Nome){ this.Nome = Nome;}
	public String getNome(){ return Nome; }
	
	public void setValorSemana(double[] ValorSemana){ this.ValorSemana = ValorSemana; }
	public double getValorSemana(int index){ return ValorSemana[index]; }
	
	public void setValorFds(double[] ValorFds){ this.ValorFds = ValorFds; }
	public double getValorFds(int index){ return ValorFds[index]; }
}