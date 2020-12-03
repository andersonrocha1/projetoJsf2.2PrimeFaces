package br.com.project.report.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils implements Serializable {


	private static final long serialVersionUID = 1L;
	
	public static String getDateAtualReportName() {
		
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		
		return dateFormat.format(Calendar.getInstance().getTime());
		
	}
	
	public static String formatDateSql(Date data) {
		
		StringBuffer retorno = new StringBuffer();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		retorno.append(dateFormat.format(data));
		retorno.append("'");
		
		return retorno.toString();
	
	}
	
public static String formatDateSqlSimple(Date data) {
		
		StringBuffer retorno = new StringBuffer();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		retorno.append(dateFormat.format(data));
	
		return retorno.toString();
	
	}

}
