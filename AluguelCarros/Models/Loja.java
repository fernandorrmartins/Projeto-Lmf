package AluguelCarros.Models;

import java.util.HashMap;
import java.util.Arrays;

import java.io.File;
import java.io.FileInputStream;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import AluguelCarros.Models.*;

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
	// Guarda as opções de carros oferecidas pela Loja
	private String[] Carros;
	
	/* Método Construtor Padrão */
	public Loja(){}
	/* Método Construtor - Sobrecarga ao passar todos os valores por parâmetro formal */
	public Loja(String Nome, double[] ValorSemana, double[] ValorFds, String[] Carros){
		/* Definindo valores durante construção do objeto */
		this.Nome = Nome;
		this.ValorSemana = ValorSemana;
		this.ValorFds = ValorFds;
		this.Carros = Carros;
	}
	
	/* Método Estático Responsável por Carregar Informações das Lojas da Empresa a partir de um Json Criado pelo Instalador */
	public static HashMap<String, Loja> CriarLojas() throws Exception {
		String nomeJson = "Lojas.json";
		
		File file = new File(nomeJson);
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		
		String json = new String(data, "UTF-8");
		
		HashMap<String, Loja> map = new HashMap<String, Loja>();
		
		JSONArray jsonArray = new JSONArray(json);
		for(int i=0; i < jsonArray.length(); i++){
			JSONObject obj = jsonArray.getJSONObject(i);
			
			double[] ValorSemana = new double[obj.getJSONArray("ValorSemana").length()];
			for(int j = 0; j < obj.getJSONArray("ValorSemana").length(); j++){               
				ValorSemana[j] = obj.getJSONArray("ValorSemana").getDouble(j);               
			}
			
			double[] ValorFds = new double[obj.getJSONArray("ValorFds").length()];
			for(int j = 0; j < obj.getJSONArray("ValorFds").length(); j++){               
				ValorFds[j] = obj.getJSONArray("ValorFds").getDouble(j);               
			}
			
			String[] Carros = new String[obj.getJSONArray("Carros").length()];
			for(int j = 0; j < obj.getJSONArray("Carros").length(); j++){               
				Carros[j] = obj.getJSONArray("Carros").getString(j);               
			}
			
			Loja loja = new Loja(obj.getString("Nome"), ValorSemana, ValorFds, Carros);
			map.put(obj.getString("Nome"), loja);
		}
		
		return map;
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
	
	public String[] getCarros(){ return Carros; }
}