package AluguelCarros.Services;

import java.util.HashMap;

import java.io.File;
import java.io.FileInputStream;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import AluguelCarros.Models.*;

public class Util {
	private static String nomeJson = "Lojas.json";
	
	public static HashMap<String, Loja> CriarLojas() throws Exception {
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
			
			Loja loja = new Loja(obj.getString("Nome"), ValorSemana, ValorFds);
			map.put(obj.getString("Nome"), loja);
		}
		
		return map;
	}
}