/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.mirian.util;

import com.ec.mirian.so.Linux;
import com.ec.mirian.so.Mac;
import com.ec.mirian.so.So;
import com.ec.mirian.so.Windows;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author MARCELO
 */
public class Util {

    public static String getUrl() {
        String url = "jdbc:informix-sqli://" + AccesoDB.getValorAcceso("db.ip")
                + ":" + AccesoDB.getValorAcceso("db.port") + "/" + AccesoDB.getValorAcceso("db.name")
                + ":informixserver=" + AccesoDB.getValorAcceso("db.informixserver") + ";user="
                + AccesoDB.getValorAcceso("db.user") + ";password=" + AccesoDB.getValorAcceso("db.pass");

        return url;
    }

    public static String getCalularPorcentaje(String valor, String porcentaje) {
        double v = Double.parseDouble(valor);
        double p = Double.parseDouble(porcentaje);
        double c = (v * p) / 100;
        BigDecimal bd = new BigDecimal(c);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return String.valueOf(bd.doubleValue());
    }

    public static Date getDateFromString(String fecha, String formato) {
        try {
            DateFormat df = new SimpleDateFormat(formato);
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.parse(fecha);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static synchronized void createDirectory(String path) {
        File directory = new File(path);
        if (directory.isFile()) {
            directory = directory.getParentFile();
        }
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static synchronized String getStringFromDateXmlName(Date date) {
        return getStringFromDate(date, "dd-MM-yyyy-HH-mm-ss");
    }

    public static String getStringFromDate(Date date, String formato) {
        DateFormat df = new SimpleDateFormat(formato);
        return df.format(date);
    }

    public static String getSecuencial(String pathFileSecuencial) throws FileNotFoundException, IOException {
        File file = new File(pathFileSecuencial);
        String secuencia;
        if (file.exists()) {
            FileReader f = new FileReader(file);
            BufferedReader b = new BufferedReader(f);
            secuencia = b.readLine();
            b.close();
        } else {
            secuencia = "1";
        }

        return secuencia;
    }

    public static void aumentarYgrabarSecuencia(String pathFile, String secuencia) throws IOException {
        File file = new File(pathFile);
        if (file.exists()) {
            file.delete();
        }
        int i = Integer.parseInt(secuencia) + 1;
        FileWriter textOut = new FileWriter(file, true);
        textOut.write(i + "");
        textOut.close();
    }

    public static String getFormatoNueveDigitos(String secuencia) {
        String cadena = "";
        for (int i = secuencia.length(); i < 9; i++) {
            cadena = cadena + "0";
        }
        cadena = cadena + secuencia;
        return cadena;
    }

    public static void mostrarError(Exception ex, String mensaje) {
        JOptionPane.showMessageDialog(null, ex, mensaje + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarExisto(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarWarning(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Informativo", JOptionPane.WARNING_MESSAGE);
    }

    public static String getString(String valor) {
        return valor != null ? valor.trim() : "";
    }

    public static String getRutaSegunSO() {
        if (So.isWindows()) {
            return Windows.FILE_PROPERTY;
        } else if (So.isUnix() || So.isMac()) {
            return Linux.FILE_PROPERTY;
        }
        return "";
    }

    public static String getDirectorioSegunSO() {
        if (So.isWindows()) {
            return Windows.DIR_SECUENCIALES;
        } else if (So.isUnix() || So.isMac()) {
            return Linux.DIR_SECUENCIALES;
        }
        return "";
    }

    public static String getDirectorioXMLSegunSO() {
        if (So.isWindows()) {
            return Windows.DIR_XML;
        } else if (So.isMac()) {
            return Mac.DIR_XML;
        } else if (So.isUnix()) {
            return Linux.DIR_XML;
        }
        return "";
    }

    public static String getAccesoPropertiesSegunSO() {
        if (So.isWindows()) {
            return Windows.ACCESO_PROPERTY;
        } else if (So.isUnix() || So.isMac()) {
            return Linux.ACCESO_PROPERTY;
        }
        return "";
    }

    public static String getMailPropertiesSegunSO() {
        if (So.isWindows()) {
            return Windows.NOTIFICACION_PROPERTY;
        } else if (So.isUnix() || So.isMac()) {
            return Linux.NOTIFICACION_PROPERTY;
        }
        return "";
    }

    public static Integer stringToInt(String numero) {
        return Integer.parseInt(numero);
    }

    public static long stringToLong(String numero) {
        if (numero != null && !numero.isEmpty()) {
            return Long.parseLong(numero);
        }
        return new Long("0");
    }

    public static BigDecimal stringToBigDecimal(String numero) {
        if (numero != null && !numero.isEmpty()) {
            return new BigDecimal(numero);
        }
        return BigDecimal.ZERO;

    }

    public static BigInteger stringToBigInteger(String numero) {
        if (numero != null && !numero.isEmpty()) {
            return new BigInteger(numero);
        }
        return BigInteger.ZERO;
    }

    /* CONVERSIÓN DE TEXTO SIMPLE A FORMATO HTML */
    public static String convierteTextoAFormatoHTML(int codigo, String texto) {
        switch (codigo) {
            case 1:
                texto = "<font size='3' color='black' face='Courier'>" + texto + "</font><br/></html>"; // Mensaje relacionado a la Base de Datos.
                break;
            case 2:
                texto = "<font size='3' color='black' face='Courier'>" + texto + "</font><br/></html>"; // Mensaje relacionado a SAP.
                break;
            case 3:
                texto = "<font size='3' color='red' face='Courier'>" + texto + "</font><br/></html>"; // Mensaje relacionado a las excepciones.
                break;
            case 4:
                texto = "<font size='3' color='black' face='Courier'>" + texto + "</font><br/></html>"; // Mensaje relacionado al tiempo de duraciòn.
                break;
        }
        return texto;
    }

    public static void setLogger(String name, Exception ex) {
        Logger.getLogger(name).log(Level.SEVERE, null, ex);
    }

    private static final String PATROM_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static String getCorreosValidos(String cadenaEmail) {
        String[] correos = cadenaEmail.split(",");
        Pattern pattern = Pattern.compile(PATROM_EMAIL);
        String correoValidos = "";
        for (String email : correos) {
            Matcher mather = pattern.matcher(email);
            if (mather.matches()) {
                if (correoValidos.isEmpty()) {
                    correoValidos = email;
                } else {
                    correoValidos = correoValidos + "," + email;
                }
            }
        }

        return correoValidos;
    }

    public static String dosDigitos(double d) {

        BigDecimal b = new BigDecimal(d);
        b = b.setScale(2, RoundingMode.HALF_UP);
        return String.valueOf(b.doubleValue());
    }

    public static java.sql.Date tranformarToJavaSqlDate(java.util.Date date) {
        return java.sql.Date.valueOf(date.toString());
    }

    public static Timestamp convertDateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static String getTipoDocumento(String identificacion) {
        if (identificacion.length() == Constante.LONGITUD_CEDULA) {
            if (identificacion.equalsIgnoreCase(Constante.CONSUMIDOR_FINAL)) {
                return Constante.TIPO_CONSUMIDOR_FINAL;
            } else {
                return Constante.TIPO_CEDULA;
            }
        } else if (identificacion.length() == Constante.LONGITUD_RUC) {
            if (Constante.FINAL_RUC.equalsIgnoreCase(identificacion.substring(identificacion.length() - 3, identificacion.length()))) {
                return Constante.TIPO_RUC;
            } else {
                return Constante.TIPO_PASAPORTE;
            }
        } else {
            return Constante.TIPO_PASAPORTE;
        }
    }

    public static String getLogo() {
        if (So.isWindows()) {
            return Windows.LOGO;
        } else if (So.isUnix() || So.isMac()) {
            return Linux.LOGO;
        }
        return "";
    }

    public static String getPathInfoAdicional() {
        if (So.isWindows()) {
            return Windows.INFO_ADICIONALE;
        } else if (So.isUnix() || So.isMac()) {
            return Linux.INFO_ADICIONALE;
        }
        return "";
    }

    public static String eliminarCerosInicios(String cadena) {
        String subCadena = "";
        int pos = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) != '0') {
                pos = i;
                break;
            }
        }
        subCadena = cadena.substring(pos, cadena.length());
        return subCadena;
    }
    
    public static Double parseDouble(String stringToDouble) {
        Double parce ;
        try {
            parce = Double.parseDouble(stringToDouble);
        } catch (NumberFormatException e) {
            parce = Double.parseDouble("0.0");
        }
        return parce;
    }
    
    public static int mensajeConfirmacion() {
        return JOptionPane.showConfirmDialog(null, "Desea Facturar a usted mismo?", "Confirmación",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
    
    public static void enableButton(JButton button, boolean flag) {
        button.setEnabled(flag);
    }
    
    public static String getText(javax.swing.JTextField field) {
        return field.getText();
    }
    
    public static void setText(javax.swing.JTextField field, String texto) {
        field.setText(texto);
    }
}
