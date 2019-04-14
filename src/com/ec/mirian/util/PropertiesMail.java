/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ec.mirian.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 *
 * @author MARCELO
 */
public class PropertiesMail {
    
    private static Properties INSTANCE;
    
    public static void getInstanceProperties() throws FileNotFoundException, UnsupportedEncodingException, IOException{
        synchronized(PropertiesUtil.class) {
            if(INSTANCE == null) {
                INSTANCE =  new Properties();
                InputStreamReader in = new InputStreamReader(new FileInputStream(Util.getMailPropertiesSegunSO()), "UTF-8");
                INSTANCE.load(in);
            }
        }
    }
    
    public static String getValorAcceso(String valor) {
        return INSTANCE.getProperty(valor).trim();
    }
    
}
