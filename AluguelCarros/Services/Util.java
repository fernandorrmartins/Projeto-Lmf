package AluguelCarros.Services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/*
 * Objeto responsável por Métodos Uteis Genericos.
 * Todas suas funções são estáticas.
*/
public class Util {
	public static Date StringParaData(String data) throws Exception {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		return new Date(simpleDateFormat.parse(data).getTime());
	}
	
	
	public static String DataParaString(Date data) throws Exception {
		String pattern = "ddMMMyyyy(E)";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		return simpleDateFormat.format(data);
	}
	
	public static Integer PegarDiaDaSemanaComoInt(Date d) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);
		
		return cal.get(java.util.Calendar.DAY_OF_WEEK);
	}
}