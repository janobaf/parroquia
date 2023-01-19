package com.inagro.springboot.backend.apirest.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UtilFormat {

	public static String formatearNumero(BigDecimal totalizado,String Pattern) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.UK);
		DecimalFormat decimalFormat = new DecimalFormat(Pattern,symbols); 
		return decimalFormat.format(totalizado);
	}
	public static String formatearFechaCadena(String pattern,Date fecha) {
		return new SimpleDateFormat(pattern).format(fecha);
	}
	public static String formatearFechaNombreDiaMes(Date value) {
        String fecha = new SimpleDateFormat("EEEE dd 'de' MMMM 'del' YYYY").format(value);
		return fecha;
	}
	
	public static String obtenerNombreDelDia(Date value) {
        String fecha = new SimpleDateFormat("EEEE").format(value);
		return fecha;
	}
}
