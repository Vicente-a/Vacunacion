package com.vacunas.utilitario;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.google.common.base.Strings;

public class FechasUtil {

    protected static final Map<Integer, String> CATALOGO_MESES = new HashMap<Integer, String>();

    private FechasUtil() {
    }
    
    public static final Timestamp getFechaTimestamp() {
    	Date fecha = new Date();
		Timestamp timestamp = new Timestamp(fecha.getTime());
		return timestamp;
    }

    public static final Date formateadorStringAFecha(String patron, String cadenaTexto) {
        SimpleDateFormat formateador = new SimpleDateFormat(patron, ComunEnum.LOCALE_ECUADOR);
        try {
            return formateador.parse(cadenaTexto);
        } catch (ParseException e) {
            Log.error(FechasUtil.class.getSimpleName(), "No se pudo convertir la cadena de texto a la fecha dada con el patron " + patron, e);
            return null;
        }
    }

    public static String obtenerFechaTexto(Date fecha) {
        Integer[] array = FechasUtil.obtenerArrayFecha(fecha);
        return array[2] + " de " + CATALOGO_MESES.get(array[1]).toLowerCase() + " de " + array[0];
    }

    public static final String formateadorfechaAString(String patron, Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat(patron, ComunEnum.LOCALE_ECUADOR);
        return formateador.format(fecha);
    }

    public static final Date obtenerPrimerDiaDelMes() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.getActualMinimum(5), cal.getMinimum(11), cal.getMinimum(12), cal.getMinimum(13));
        return cal.getTime();
    }

    public static final Date obtenerPrimerDiaDelMesActualPorAnio(int numeroAniosConSigno) {
        Calendar cal = Calendar.getInstance();
        cal.add(1, numeroAniosConSigno);
        return cal.getTime();
    }

    public static final Date obtenerPrimerDiaDelMes(Date fecha, int sumarDias) {
        Calendar cal = Calendar.getInstance(ComunEnum.TIMEZONE_ECUADOR, ComunEnum.LOCALE_ECUADOR);
        cal.setTime(fecha);
        cal.set(cal.get(1), cal.get(2), cal.getActualMinimum(5), cal.getMinimum(11), cal.getMinimum(12), cal.getMinimum(13));
        cal.add(5, sumarDias);
        return cal.getTime();
    }
    
    public static final boolean validarEmisionCierre(int anio, int mes ){
        Calendar cal = Calendar.getInstance(ComunEnum.TIMEZONE_ECUADOR, ComunEnum.LOCALE_ECUADOR);
        cal.set(anio, mes - 1, 1);
        return estaEntreDosFecha(cal.getTime(), obtenerUltimoDiaDelMes(cal.getTime()), new Date());
    }

    public static Date obtenerUltimoDiaDelMes() {
        Calendar cal = Calendar.getInstance(ComunEnum.TIMEZONE_ECUADOR, ComunEnum.LOCALE_ECUADOR);
        cal.set(cal.get(1), cal.get(2), cal.getActualMaximum(5), cal.getMaximum(11), cal.getMaximum(12), cal.getMaximum(13));
        return cal.getTime();
    }
    
    public static String obtenerDatosFecha(Date fecha, int tipo ) {
    	String respuesta = "";
        Calendar cal = Calendar.getInstance(ComunEnum.TIMEZONE_ECUADOR, ComunEnum.LOCALE_ECUADOR);
        cal.setTime(fecha);
        switch (tipo) {
		case 1:
			respuesta = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			break;
		case 2:
			respuesta = String.valueOf(cal.get(Calendar.MONTH) + 1);
			break;
		case 3:
			respuesta = String.valueOf(cal.get(Calendar.YEAR));
			break;
		default:
			break;
		}
        return Strings.padStart(respuesta, 2, '0');
    }

    public static final Date obtenerUltimoDiaDelMes(Date fecha) {
        Calendar cal = Calendar.getInstance(ComunEnum.TIMEZONE_ECUADOR, ComunEnum.LOCALE_ECUADOR);
        cal.setTime(fecha);
        cal.set(cal.get(1), cal.get(2), cal.getActualMaximum(5), cal.getMaximum(11), cal.getMaximum(12), cal.getMaximum(13));
        return cal.getTime();
    }

    public static final Date obtenerUltimoDiaDelMes(Date fecha, int restarDias) {
        Calendar cal = Calendar.getInstance(ComunEnum.TIMEZONE_ECUADOR, ComunEnum.LOCALE_ECUADOR);
        cal.setTime(fecha);
        cal.set(cal.get(1), cal.get(2), cal.getActualMaximum(5), cal.getMaximum(11), cal.getMaximum(12), cal.getMaximum(13));
        cal.add(7, -restarDias);
        return cal.getTime();
    }

    public static final Date obtenerUltimoDiaDelMesAnterior() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.getActualMaximum(5), cal.getMaximum(11), cal.getMaximum(12), cal.getMaximum(13));
        cal.add(7, -1);
        return cal.getTime();
    }

    public static final Timestamp convertirStringATimestampSql(Object value, String patron) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat(patron);
        if (value == null) {
            return null;
        }
        if (value instanceof Timestamp) {
            return (Timestamp) value;
        }
        if (value instanceof String) {
            if ("".equals((String) value)) {
                return null;
            }
            return new Timestamp(formateador.parse((String) value).getTime());
        }
        return new Timestamp(formateador.parse(value.toString()).getTime());
    }

    public static final double obtenerDiferenciaDias(Date fechaInicio, Date fechaFin) {
        long fechaInicial = fechaInicio.getTime();
        long fechaFinal = fechaFin.getTime();
        long diferencia = fechaFinal - fechaInicial;
        return new Double(diferencia / 86400000) + 1.0;
    }

    public static final Date obtenerFechaSinTiempo(Date fechaEliminarTiempo) {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(fechaEliminarTiempo);
        fecha.set(13, 0);
        fecha.set(14, 0);
        fecha.set(10, 0);
        fecha.set(12, 0);
        return fecha.getTime();
    }

    public static final int dias360(Date fechaInicio, Date fechaFin) {
    	Calendar cini = Calendar.getInstance();   
    	  cini.setTime(fechaInicio);   
    	  Calendar cfin = Calendar.getInstance();   
    	  cfin.setTime(fechaFin);   
    	  int anos = cfin.get(Calendar.YEAR) - cini.get(Calendar.YEAR);  
    	  int meses = cfin.get(Calendar.MONTH) - cini.get(Calendar.MONTH);  
    	  int dia1=0, dia2=0;  
    	  if(cini.get(Calendar.DATE)==31){  
    	    dia1=30;  
    	  } else if(cini.get(Calendar.MONTH)==Calendar.FEBRUARY && cini.get(Calendar.DATE)>=28){  
    	    dia1=30;  
    	  }else{  
    	    dia1=cini.get(Calendar.DATE);  
    	  }   
    	  if(cfin.get(Calendar.DATE)==31){  
    	    dia2=30;  
    	  } else if(cfin.get(Calendar.MONTH)==Calendar.FEBRUARY && cfin.get(Calendar.DATE)>=28){  
    	    dia2=30;  
    	  } else{  
    	    dia2=cfin.get(Calendar.DATE);  
    	  }   
    	  int dias = dia2-dia1;   
    	  int diasLab = anos*360+meses*30+dias;   
    	  return diasLab;  
    }
    public static final int compararFechas(Date fechaInicio, Date fechaFin, boolean compararTiempo) {
        Calendar fecha1 = Calendar.getInstance();
        Calendar fecha2 = Calendar.getInstance();
        if (compararTiempo) {
            fecha1.setTime(FechasUtil.obtenerFechaSinTiempo(fechaInicio));
            fecha2.setTime(FechasUtil.obtenerFechaSinTiempo(fechaFin));
        } else {
            fecha1.setTime(fechaInicio);
            fecha2.setTime(fechaFin);
        }
        return fecha2.compareTo(fecha1);
    }

    public static final int obtenerAnios(Date fechaNacimiento) {
        int edadnumero = 0;
        Date ahora = new Date();
        GregorianCalendar calendarioUtil = new GregorianCalendar();
        if (fechaNacimiento != null) {
            calendarioUtil.setTimeInMillis(ahora.getTime() - fechaNacimiento.getTime());
            edadnumero = calendarioUtil.get(1) - 1970;
        }
        return edadnumero;
    }

    public static final Integer[] obtenerArrayFecha(Date fecha) {
        String[] lista = FechasUtil.formateadorfechaAString(ComunEnum.PATRON_FECHA1.getDescripcion(), fecha).split("/");
        Integer[] resultado = new Integer[lista.length];
        for (int i = 0; i < lista.length; ++i) {
            resultado[i] = Integer.valueOf(lista[i]);
        }
        return resultado;
    }

    public static final boolean estaEntreDosFecha(Date fechaInicio, Date fechaHasta, Date fecha) throws RuntimeException {
        try {
            //if (fecha.after(fechaHasta) || fecha.before(fechaInicio)) {
        	if (fecha.after(fechaInicio) && (fecha.before(fechaHasta) || fecha.equals(fechaHasta))) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static final Date calcularMesSiguiente(Date fecha) throws RuntimeException {
        GregorianCalendar calendario = new GregorianCalendar();
        try {
            calendario.setTime(fecha);
            calendario.add(2, 1);
            calendario.set(5, 1);
        } catch (RuntimeException e) {
            throw new RuntimeException("ERROR AL CALCULAR MES SIGUIENTE. \\ \n" + e.getMessage(), e);
        }
        return calendario.getTime();
    }

    public static final int diasCumplidos(Date fechaDesde, Date fechaHasta) {
    	int dias = 0;
    	if((fechaDesde.getTime() - fechaHasta.getTime()) != 0) {
    		dias = FechasUtil.dateDiff(fechaDesde, fechaHasta).get(6);
    	}
        return dias;
    }

    public static final int mesesCumplidos(Date fechaDesde, Date fechaHasta) {
    	int meses = 0;
    	if((fechaDesde.getTime() - fechaHasta.getTime()) != 0) {
    		meses = FechasUtil.dateDiff(fechaDesde, fechaHasta).get(2);
    		if(meses == 0) {
    			meses = 1;
    		}
    	}
    	return meses;
    }

    public static final int aniosCumplidos(Date fechaDesde, Date fechaHasta) {
        return FechasUtil.dateDiff(fechaDesde, fechaHasta).get(1) - 1970;
    }

    public static final Calendar dateDiff(Date fechaDesde, Date fechaHasta) {
        GregorianCalendar dateDiff = new GregorianCalendar();
        dateDiff.setTimeInMillis(fechaHasta.getTime() - fechaDesde.getTime());
        return dateDiff;
    }

    public static int getDiffDates(Date fechaInicio, int tipo) {
        LocalDate fechaNac = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNac, ahora);
        int returnValue = -1;
        switch (tipo) {
            case 0:
                // Total Años
                returnValue = periodo.getYears();
                break;
            case 1:
                // Total Meses
                returnValue = periodo.getMonths();
                break;
            case 2:
                // Total Dias (se calcula a partir de los milisegundos por día)
                returnValue = periodo.getDays();
                break;
            default:
                break;
        }
        return returnValue;

    }
    
    public static int getDiffDatesDesdeHasta(Date fechaInicio, Date fechaHasta, int tipo) {
        LocalDate fechaNac = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ahora = fechaHasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period periodo = Period.between(fechaNac, ahora);
        int returnValue = -1;
        switch (tipo) {
            case 0:
                // Total Años
                returnValue = periodo.getYears();
                break;
            case 1:
                // Total Meses
                returnValue = periodo.getMonths();
                break;
            case 2:
                // Total Dias (se calcula a partir de los milisegundos por día)
                returnValue = periodo.getDays();
                break;
            default:
                break;
        }
        return returnValue;

    }

    public static int getAnioMes(int tipo) {
        Calendar cal= Calendar.getInstance();
        int valor = 0;
        switch (tipo) {
            case 0:
                valor = cal.get(Calendar.YEAR);
            break;
            case 1:
                valor = cal.get(Calendar.MONTH) + 1;
            break;
        }
        return valor;
    }
    public static final Date obtenerFechaDesde(Date fechaDesde) {
        Calendar fechaDesdeAux = Calendar.getInstance();
        fechaDesdeAux.setTime(fechaDesde);
        fechaDesdeAux.set(11, 0);
        fechaDesdeAux.set(12, 0);
        fechaDesdeAux.set(13, 0);
        fechaDesdeAux.set(14, 0);
        return fechaDesdeAux.getTime();
    }

    public static final Date obtenerFechaHasta(Date fechaHasta) {
        Calendar fechaHastaAux = Calendar.getInstance();
        fechaHastaAux.setTime(fechaHasta);
        fechaHastaAux.set(11, 23);
        fechaHastaAux.set(12, 59);
        fechaHastaAux.set(13, 59);
        fechaHastaAux.set(14, 0);
        return fechaHastaAux.getTime();
    }

    public static final long obtenerFechaUnixEpoch() {
        return System.currentTimeMillis() / 1000;
    }

    public static String devuelveFechaEnLetrasSinHora(Date fechaParametro) {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(fechaParametro);
        return FechasUtil.devuelveDiaSemana(fecha.get(7)) + fecha.get(5) + " de " + FechasUtil.devuelveMes(fecha.get(2)) + " de " + fecha.get(1);
    }
	private static String devuelveDiaSemana(int dia) {
        switch (dia) {
            case 1: {
                return "Domingo ";
            }
            case 2: {
                return "Lunes ";
            }
            case 3: {
                return "Martes ";
            }
            case 4: {
                return "Mi\u00e9rcoles ";
            }
            case 5: {
                return "Jueves ";
            }
            case 6: {
                return "Viernes ";
            }
            case 7: {
                return "S\u00e1bado ";
            }
        }
        return "";
    }



    private static String devuelveMes(int mes) {
        switch (mes) {
            case 0: {
                return "Enero";
            }
            case 1: {
                return "Febrero";
            }
            case 2: {
                return "Marzo";
            }
            case 3: {
                return "Abril";
            }
            case 4: {
                return "Mayo";
            }
            case 5: {
                return "Junio";
            }
            case 6: {
                return "Julio";
            }
            case 7: {
                return "Agosto";
            }
            case 8: {
                return "Septiembre";
            }
            case 9: {
                return "Octubre";
            }
            case 10: {
                return "Noviembre";
            }
            case 11: {
                return "Diciembre";
            }
        }
        return "";
    }

    public static int diferenciaFechas(Date fecha1, Date fecha2, int valor) {
        int retorno = 0;
        Calendar cal1 = null;
        cal1 = Calendar.getInstance();
        Calendar cal2 = null;
        cal2 = Calendar.getInstance();
        cal1.setTime(fecha1);
        long ldate1 = fecha1.getTime() + (long) cal1.get(15) + (long) cal1.get(16);
        cal2.setTime(fecha2);
        long ldate2 = fecha2.getTime() + (long) cal2.get(15) + (long) cal2.get(16);
        int hr1 = (int) (ldate1 / 3600000);
        int hr2 = (int) (ldate2 / 3600000);
        int days1 = hr1 / 24;
        int days2 = hr2 / 24;
        int dateDiff = days2 - days1;
        int yearDiff = cal2.get(1) - cal1.get(1);
        int monthDiff = yearDiff * 12 + cal2.get(2) - cal1.get(2);
        if (valor == 1) {
            if (dateDiff < 0) {
                dateDiff *= -1;
            }
            retorno = dateDiff;
        } else if (valor == 2) {
            if (monthDiff < 0) {
                monthDiff *= -1;
            }
            retorno = monthDiff;
        } else if (valor == 3) {
            if (yearDiff < 0) {
                yearDiff *= -1;
            }
            retorno = yearDiff;
        }
        return retorno;
    }

    public static final String formatoFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        int inicio = fecha.indexOf("-");
        String anio = null, mes = null, dia = null;
        if(inicio == 2) {
        	anio = fecha.substring(6, 10);
        	mes = fecha.substring(3, 5);
        	dia = fecha.substring(0, 2);
        }else if((inicio == 4)){
        	anio = fecha.substring(0, 4);
        	mes = fecha.substring(5, 7);
        	dia = fecha.substring(8, 10);
        }
        fecha = anio+"-"+mes+"-"+dia;
        String fechaFinal = "";
        try {
            Date fechaA = formato.parse(fecha);
            fechaFinal = formato.format(fechaA);
        } catch (ParseException e) {
            return null;
        }
        return fechaFinal;
    }
    
    public static final String formatoHora(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm", Locale.US);
        String fechaFinal = "";
        try {
            Date fechaA = formato.parse(fecha);
            fechaFinal = formato.format(fechaA);
        } catch (ParseException e) {
            return null;
        }
        return fechaFinal;
    }

    public static final Date operarFecha(Date fechaASumar, int anios, int meses, int dias) {
        Calendar calendar = Calendar.getInstance(ComunEnum.TIMEZONE_ECUADOR, ComunEnum.LOCALE_ECUADOR);
        calendar.setTime(fechaASumar);
        calendar.add(6, dias);
        calendar.add(2, meses);
        calendar.add(1, anios);
        return calendar.getTime();
    }

    static {
        CATALOGO_MESES.put(1, "ENERO");
        CATALOGO_MESES.put(2, "FEBRERO");
        CATALOGO_MESES.put(3, "MARZO");
        CATALOGO_MESES.put(4, "ABRIL");
        CATALOGO_MESES.put(5, "MAYO");
        CATALOGO_MESES.put(6, "JUNIO");
        CATALOGO_MESES.put(7, "JULIO");
        CATALOGO_MESES.put(8, "AGOSTO");
        CATALOGO_MESES.put(9, "SEPTIEMBRE");
        CATALOGO_MESES.put(10, "OCTUBRE");
        CATALOGO_MESES.put(11, "NOVIEMBRE");
        CATALOGO_MESES.put(12, "DICIEMBRE");
    }
    
    public static boolean isFechaValida(String fechaOriginal) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		formatter.setLenient(false);
		boolean isfechaValida = true;

		try {
			formatter.parse(fechaOriginal);
		} catch (ParseException e) {
			isfechaValida = false;
		}

		String[] camposFecha = fechaOriginal.split("/");
		int longitudDia = camposFecha[0].length();
		int longitudMes = camposFecha[1].length();
		int longitudAnio = camposFecha[2].length();

		if (longitudDia > 2 || longitudMes > 2 || longitudAnio > 4) {
			isfechaValida = false;
		}

		return isfechaValida;
	}
    public static int compararFechasValor(Date fechabase, Date aComparar) {
		int valor = 0;
		valor = aComparar.compareTo(fechabase);
		return valor;
	}
    
    public Usuarioperfil removeUsuarioperfil(Usuarioperfil usuarioperfil) {
		getUsuarioperfils().remove(usuarioperfil);
		usuarioperfil.setPerfil(null);

		return usuarioperfil;
	}
}
