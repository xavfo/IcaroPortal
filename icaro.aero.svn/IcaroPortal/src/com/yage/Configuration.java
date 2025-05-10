/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage;

/**
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public class Configuration {
    
    private static Configuration config = null;

    /**
     * Crea una nueva instancia de Configuration
     */
    private Configuration() {
        super();
    }
    
    public Configuration getInstance() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException(); 
    }

}
