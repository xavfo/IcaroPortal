/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz;

import java.util.ArrayList;

/**
 * 
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public abstract class GroupBase implements Group {
    
    /**
     * Creates a new isntance of GroupBase
     */
    public GroupBase() {
        super();
    }
    
    public abstract Integer getLevel();

    /**
     * Gets the parent of this Group
     */
    public abstract Group getAncestor();
    
    /**
     * @see com.iportal.biz.Module#findAncestor(int)
     */
    public Group findAncestor(Integer level) {
        int lp = level.intValue();
        int li = getLevel().intValue();
        
        if ( lp < li ) {
            while ( getAncestor() != null) {
                Group group = (Group) getAncestor();
                if ( group.getLevel().intValue() == lp ) {
                    return group;
                }
            }
        }
        return null;
    }
    
    /**
     * Finds the root Module
     */
    public Group getRoot() {
        Group root = this;
        while (root.getAncestor() != null) {
            root = root.getAncestor();
        }
        return root;
    }
    
    public ArrayList getHierarchy( ) {
		ArrayList<Group> aux = new ArrayList<Group>();
		Group c = this;
		if (c != null) {
			aux.add(c);
		}
		
		while (c != null) {
			if (c.getAncestor() != null){
				aux.add( c.getAncestor() );
			}
			c = c.getAncestor();
		}
		 
		ArrayList<Group> hierarchy = new ArrayList<Group>();
		if (aux.size() > 0) {
			int size = aux.size();
				for (int i = size; i > 0; i--) {
				    hierarchy.add( aux.get(i-1) );
				}
				
		}
		return hierarchy;
    }

}
