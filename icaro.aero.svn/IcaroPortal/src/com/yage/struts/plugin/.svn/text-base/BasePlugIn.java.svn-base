/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.plugin;

import com.yage.Globals;


/**
 * BasePlugIn
 * 
 * @author YAGE (jtite)
 * @version 1.0
 * 
 */
public abstract class BasePlugIn {
    
    protected String id;
    
    protected String configFile;
    
    protected boolean putInContext;
    
    
    
    /**
     * @return Returns the configFile.
     */
    public String getConfigFile() {
        return configFile;
    }
    
    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }
    
    /**
     * @return Returns the putInContext.
     */
    public boolean getPutInContext() {
        return putInContext;
    }
    
    /**
     * @param configFile The configFile to set.
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }
    
    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @param putInContext The putInContext to set.
     */
    public void setPutInContext(boolean putInContext) {
        this.putInContext = putInContext;
    }

    /**
     * @param putInContext The putInContext to set.
     */
    public void setPutInContext(String putInContext) {
        this.putInContext = Globals.TRUE_STR.equals(putInContext);
    }
}
