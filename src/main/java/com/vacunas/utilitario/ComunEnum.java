
package com.vacunas.utilitario;

import java.math.RoundingMode;
import java.util.Locale;
import java.util.TimeZone;

public enum ComunEnum {
    REGISTRO_ACTUALIZADO("Registro actualizado."),
    ERROR_REGISTRO_ACTUALIZADO("Error al actualizar el registro, por favor intente nuevamente m\u00e1s tarde."),
    ERROR_RECUPERAR_REGISTRO("Error al recuperar el registro, por favor intente nuevamente m\u00e1s tarde."),
    USUARIO_NO_EXISTENTE("Usuario no existe."),
    SELECCIONE("Seleccione"),
    USUARIO_EXISTENTE("Usuario ya existe."),
    ERROR_REDIRECCION("Url no existe."),
    ERROR_CERRAR_SESION("No se puede cerrar la sesi\u00f3n."),
    EXITO("1"),
    NO_EXITO("0"),
    MENSAJE_ERROR_INICIALIZACION("Error al inicializar los datos de la pantalla, intente mas tarde."),
    MENSAJE_ERROR_FALLO_OPERACION("Fall\u00f3 la operaci\u00f3n, intente mas tarde."),
    MENSAJE_INFO_OPERACION_EXITOSA("La operaci\u00f3n se realiz\u00f3 con \u00e9xito."),
    MENSAJE_ERROR_CEDULA_INVALIDA("C\u00e9dula Inv\u00e1lida"),
    MENSAJE_ERROR_CUENTA_BANCARIA("Cuenta bancaria inv\u00e1\u013aida"),
    PATRON_VALIDA_CUENTA_BANCARIA("[0-9]+"),
    PATRON_FECHA1("yyyy/MM/dd"),
    PATRON_FECHA2("yyyyMMdd"),
    PATRON_FECHA3("yyyyMM"),
    PATRON_FECHA4("yyyy-MM"),
    PATRON_TIMESTAMP("dd/MM/yyyy hh:mm:ss"),
    PATRON_FECHA5("dd/MM/yyyy"),
    PATRON_TIMESTAMP_PERIODO("d/M/yy H:mm:ss.SSS"),
    PATRON_FECHA6("yyyy-MM-dd"),
    PATRON_FECHA7("dd-MM-yyyy"),
    PATRON_FECHA8("dd-MM-yyyy hh:mm:ss"),
    PATRON_VALIDA_CORREO("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"),
    MENSAJE_ERROR_VALIDA_CORREO("La cuenta de correo electr\u00f3nica ingresada no es v\u00e1lida."),
    MENSAJE_ERROR_VALIDA_TELEFONO("El n\u00famero de tel\u00e9fono ingresado no es v\u00e1lido."),
    MENSAJE_ERROR_VALIDA_IP("La direcci\u00f3n ip ingresada no es v\u00e1lida."),
    ALFABETO_BASICO("abcdefghijklmnopqrstuvwxyz"),
    ALFABETO_NUMERICO("0123456789"),
    ALFABETO_COMPLETO(ALFABETO_BASICO.getDescripcion() + ALFABETO_NUMERICO.getDescripcion()),
    MENSAJE_ERROR_INGRESO("Usuario \u00f3 clave incorrecta."),
    MENSAJE_ERROR_PING("El servicio solicitado no se encuentra disponible, por favor intente m\u00e1s tarde."),
    PARAMETROS_OBLIGATORIOS("Parametros de entrada obligatorios"),
    MENSAJE_ERROR_BASE_NO_DISPONIBLE("La base de datos no esta disponible, por favor intente m\u00e1s tarde"),
    MENSAJE_ERROR_ENTRADA_WS_INVALIDA("Los datos de entrada son invalidos"),
    AUTENTICACION_OK("OK"),
    ES_CODIGO_QR_REPORTE("esCodigoQr"),
    ES_IMAGEN_REPORTE("esImagen"),
    TIPO_EVENTO_LOGUEO_EXITOSO("E"),
    TIPO_EVENTO_LOGUEO_FALLIDO("F"),
    SI("SI"),
    NO("NO"),
    PATRON_VALIDA_CELULAR("09[6,7,8,9][0-9]{7,7}"),
    MENSAJE_ERROR_VALIDA_CELULAR("El celular ingresado no es v\u00e1lido.")
    ;
    
    private String descripcion;
    public static final TimeZone TIMEZONE_ECUADOR;
    public static final Locale LOCALE_ECUADOR;
    public static final int NUMERO_DECIMALES = 2;
    public static final RoundingMode MODO_REDONDEO;

    private ComunEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    static {
        TIMEZONE_ECUADOR = TimeZone.getTimeZone("America/Guayaquil");
        LOCALE_ECUADOR = new Locale("es_EC");
        MODO_REDONDEO = RoundingMode.HALF_UP;
    }
    
    public static Integer TIPO_MOVIMIENTO_BAJA = 257;
    public static Integer TIPO_MOVIMIENTO_ALTA = 258;
}
