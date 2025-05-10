/*
 * Created on May 25, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.yage.servlet.http.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author hleon
 *
 * Implementacion de inventario o repositorio de usuarios conectados
 * 
 */
public class UserInventory implements Inventory {


    private transient Hashtable inventory;
    
    /** Creates a new instance of Inventory */
    public UserInventory() {
        this.inventory = new Hashtable();
    }

    @SuppressWarnings("unchecked")
	public void add(HttpSession session, Identity identity) {
        this.inventory.put(session.getId(), new InventoryItem(session, identity));
    }
    
    public String getName(String sessionId) {
    	InventoryItem item = (InventoryItem) this.inventory.get(sessionId);
        return item != null ? item.getUserIdentity().getFullName(): null;
    }
    
    
    public String getRoleName(String sessionId) {
    	InventoryItem item = (InventoryItem) this.inventory.get(sessionId);
        return item != null ? item.getUserIdentity().getRoleName(): null;
    }
    
    public Identity UserIdentity(String sessionId) {
    	InventoryItem item = (InventoryItem) this.inventory.get(sessionId);
        return item != null ? item.getUserIdentity(): null;
    }

    public Date getLastAccessedTime(String sessionId) {
    	InventoryItem item = (InventoryItem) this.inventory.get(sessionId);
        return item != null ? new Date(item.getSession().getLastAccessedTime()): null;
    }
    
    public Date getCreationTime(String sessionId) {
    	InventoryItem item = (InventoryItem) this.inventory.get(sessionId);
        return item != null ? new Date(item.getSession().getCreationTime()): null;
    }
    
    public Enumeration sessionIds() {
        return this.inventory.keys();
    }
    
    public void remove(String sessionId) {
        this.inventory.remove(sessionId);
    }
    
    public void clear() {
        this.inventory.clear();
    }
    
	/* (non-Javadoc)
	 * @see com.yage.servlet.http.security.Inventory#listAll()
	 */
	public List getAllElements() {
		ArrayList<InventoryItem> results = new ArrayList<InventoryItem>();
		Enumeration list = this.sessionIds();
		InventoryItem item = null;
		while (list.hasMoreElements()){
			item = (InventoryItem) this.inventory.get(list.nextElement());
			results.add(item);
		}
		return results;
	}
    

	

}
