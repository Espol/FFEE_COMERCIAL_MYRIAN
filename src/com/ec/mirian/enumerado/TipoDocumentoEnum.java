/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.enumerado;

/**
 *
 * @author MARCELO
 */
public enum TipoDocumentoEnum {
    
    FACTURA("01", "factura", "FACTURA", "factura"),
    CREDITO("04", "nota_credito", "NOTA DE CRÉDITO", "notaCredito"),
    DEBITO("05", "nota_debito", "NOTA DE DÉBITO", "notaDebito"),
    GUIA("06", "guia_remision", "GUIA DE REMISIÓN", "guiaRemision"),
    RETENCION("07", "retencion", "COMPROBANTE RETENCIÓN", "comprobanteRetencion");

    private final String codigo;
    private final String directorio;
    private final String descripcion;
    private final String prefijoXml;

    private TipoDocumentoEnum(String codigo, String directorio, String descripcion, String prefijo) {
        this.codigo = codigo;
        this.directorio = directorio;
        this.descripcion = descripcion;
        this.prefijoXml = prefijo;
    }
    
     public static TipoDocumentoEnum getTipo(String codigo) {
        switch (codigo) {
            case "01":
                return TipoDocumentoEnum.FACTURA;
            case "04":
                return TipoDocumentoEnum.CREDITO;
            case "05":
                return TipoDocumentoEnum.DEBITO;
            case "06":
                return TipoDocumentoEnum.GUIA;
            case "07":
                return TipoDocumentoEnum.RETENCION;
            default:
                throw new IllegalArgumentException(codigo);
        }
    }
     
    public boolean isFactura(){return codigo.equals(FACTURA.codigo);}
    public boolean isCredito(){return codigo.equals(CREDITO.codigo);}
    public boolean isDebito(){return codigo.equals(DEBITO.codigo);}
    public boolean isGuia(){return codigo.equals(GUIA.codigo);}
    public boolean isRetencion(){return codigo.equals(RETENCION.codigo);}

    public String getCodigo() {
        return this.codigo;
    }

    public String getDirectorio() {
        return this.directorio;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getPrefijoXml() {
        return prefijoXml;
    }
    
}
