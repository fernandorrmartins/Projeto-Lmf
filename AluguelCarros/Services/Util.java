package AluguelCarros.Services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/*
 * Objeto responsável por Métodos Uteis Genericos.
 * Todas suas funções são estáticas.
*/
public class Util {
	/* Método Estático que recebe uma string com formato brasileiro e retorna um Objeto Date. */
	public static Date StringParaData(String data) throws Exception {
		// Padrão brasileiro
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		return new Date(simpleDateFormat.parse(data).getTime());
	}
	
	/* Método Estático que recebe um Objeto Date formata da forma solicitada para o teste e retorna uma string. */
	public static String DataParaString(Date data) throws Exception {
		// Padrão solicitado para apresentar como mensagem para o teste
		String pattern = "ddMMMyyyy(E)";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		return simpleDateFormat.format(data);
	}
	
	/*
	 * Método Estático que recebe um Objeto do tipo Date e retorna do Número do dia Correspondente ao Dia da Semana.
	 * 1 = Domingo
	 * 2 = Segunda
	 * 3 = Terça
	 * 4 = Quarta
	 * 5 = Quinta
	 * 6 = Sexta
	 * 7 = Sábado
	 */
	public static Integer PegarDiaDaSemanaComoInt(Date d) {
		// Cria um Objeto do Tipo Calendário Gregoriano
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);
		
		return cal.get(java.util.Calendar.DAY_OF_WEEK);
	}
}