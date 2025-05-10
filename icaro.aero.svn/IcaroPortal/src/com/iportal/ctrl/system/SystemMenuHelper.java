/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.model.system.SysModule;
import com.iportal.model.system.SysRole;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * 
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class SystemMenuHelper {
    
    
    private static Log logger = LogFactory.getLog(SystemMenuHelper.class);
    
    
    /**
     * Creates a new instance of SystemMenuHelper
     */
    public SystemMenuHelper() {
        super();
    }
    
    
    public StringBuffer createMenu(SysRole role, Locale locale, String contextPath) {
        Session sess = null;
        List results = null;
        StringBuffer menu = new StringBuffer();
        
        try {
            // Gets the menu's ResourceBundle
            ResourceBundle menuResource = ResourceBundle.getBundle("com.iportal.res.SystemMenu", locale);

            
            // Gets a hibernate Session
            sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
            
            StringBuffer sql = new StringBuffer();
            sql.append("select profile.module ");
            sql.append("from SysProfile as profile ");
            sql.append("where profile.role.code = ? ");
            sql.append("and profile.module.level = 1 ");
            sql.append("order by profile.module.orderIndex ");
            
            results = sess.createQuery(sql.toString())
            						.setLong(0, role.getCode().longValue())
            						.list();
            
            // Main Menu Items (Text, URL's, and Icon ID's)
            for ( int i = 0; i < results.size(); i++) {
                SysModule module = (SysModule) results.get(i);
                
                // comment
                menu.append("//Main Menu ");
                menu.append(i);
                menu.append("\n");
                
                // main description
                menu.append("dqm__maindesc");
                menu.append(i);
                menu.append(" = \"");
                menu.append( getString(module, menuResource) );
                menu.append("\" \n");
                
                // icon id
                menu.append("dqm__micon_index");
                menu.append(i);
                menu.append(" = 0 \n");
                
                if ( !module.getGroup().booleanValue() ) {
                    // url
                    menu.append("dqm__url");
                    menu.append(i);
                    menu.append(" = \"");
                    menu.append(contextPath);
                    menu.append(module.getUrl());
                    menu.append("\" \n");
                } else {
                    // Specific Sub Menu Settings
                    String index = String.valueOf(i);
                    createSubMenu(sess, menuResource, role, module, menu, contextPath, index);
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
        
        return menu;
    }
    
    
    private static void createSubMenu(
            final Session sess, 
            final ResourceBundle menuResource,
            final SysRole role, 
            final SysModule module, 
            final StringBuffer menu,
            final String contextPath,
            final String index) {
        
        
        // comment
        menu.append("//Sub Menu ");
        menu.append(index);
        menu.append("\n");
        
        // possition
        menu.append("dqm__sub_xy");
        menu.append(index);
        menu.append(" = \"");
        menu.append(module.getX());
        menu.append(",");
        menu.append(module.getY());
        menu.append("\" \n");
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select profile.module ");
            sql.append("from SysProfile as profile ");
            sql.append("where profile.role.code = ? ");
            sql.append("and profile.module.parent.code = ? ");
            sql.append("order by profile.module.orderIndex ");
            
            Query query = sess.createQuery(sql.toString());
            query.setLong(0, role.getCode().longValue());
            query.setLong(1, module.getCode().longValue());
            
            final List results = query.list();
            
            for ( int i = 0; i < results.size(); i++) {
                SysModule subModule = (SysModule) results.get(i);
                
                // description
                menu.append("dqm__subdesc");
                menu.append(index);
                menu.append("_");
                menu.append(i);
                menu.append(" = \"");
                menu.append( getString(subModule, menuResource) ) ;
                menu.append("\" \n");
                
                // icon id
                menu.append("dqm__icon_index");
                menu.append(index);
                menu.append("_");
                menu.append(i);
                menu.append(" = 2 \n");
                
                if ( !subModule.getGroup().booleanValue() ) {
                    // url
                    menu.append("dqm__url");
                    menu.append(index);
                    menu.append("_");
                    menu.append(i);
                    menu.append(" = \"");
                    menu.append(contextPath);
                    menu.append(subModule.getUrl());
                    menu.append("\" \n");
                } else {
                    // second icon id
                    menu.append("dqm__second_icon_index");
                    menu.append(index);
                    menu.append("_");
                    menu.append(i);
                    menu.append(" = 1 \n");
                    
                    String newIndex = index.concat( "_".concat(String.valueOf(i)) );
                    createSubMenu(sess, menuResource, role, subModule, menu, contextPath, newIndex);
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
				} catch (Exception e) {
				}
		}

    }
    
    
    private static String getString(
            final SysModule module, 
            final ResourceBundle menuResource) {
        
        String value = module.getName();
        try {
            value = menuResource.getString(module.getKey());
        } catch (Exception e) {
        }
        
        return value; 
    }
}
