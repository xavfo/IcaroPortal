/*
 * Created on May 26, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.yage.servlet.http.security;

import java.util.Date;

import javax.servlet.http.HttpSession;

/**
 * Objeto inventario para manejo de objetos controlados
 * en las sesiones de usuarios
 * @author YAGE (hernan)
 * @version 1.0
 */
public class InventoryItem {
   
	private transient HttpSession session;
    private transient Identity identity;
    
    public InventoryItem (HttpSession session, Identity identity) {
        this.session  = session;
        
        this.identity = identity;
    }
    
    public HttpSession getSession() {
        return session;
    }
    
    public Identity getUserIdentity() {
        return identity;
    }
    
    
    
    public Date getCreation () {
     	Date resp = null;
     	if ( this.getSession() != null ) {
     		resp = new Date (this.getSession().getCreationTime());
     	}
     	return resp;
     }

    public Date getLastAccessed () {
     	Date resp = null;
     	if ( this.getSession() != null ) {
     		resp = new Date (this.getSession().getLastAccessedTime());
     	}
     	return resp;
     }

}
