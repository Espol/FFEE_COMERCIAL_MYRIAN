/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.service;

import ec.incloud.ce.xml.exception.XmlException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author MARCELO
 * @param <T>
 */
public interface Emision<T> {
    
    public T obtenerInformacion(String numeroPedido) throws SQLException, IOException, XmlException, ClassNotFoundException;
    
}
