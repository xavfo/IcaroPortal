/*
 * Created on May 24, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.yage.servlet.http.security;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author hleon
 *
 * Repositorio volatil de usuarios actualmente conectados al 
 * sistema
 */
public interface Inventory {

    public abstract void add(HttpSession httpsession, Identity identity);

    public abstract String getName(String s);


    public abstract String getRoleName(String s);

    public abstract Date getCreationTime(String s);

    public abstract Date getLastAccessedTime(String s);

    public abstract Enumeration sessionIds();

    public abstract void remove(String s);

    public abstract void clear();
    
    public abstract List getAllElements ();

}
