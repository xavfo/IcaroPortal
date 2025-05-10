/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz.portal;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuItemBean;
import com.iportal.model.portal.MenuLanguage;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * 
 * 
 * Arma el menu de uso en Portal dato que MenuItem solo define
 * los contenidos padres, de ahi consulta la jerarquía de 
 * contenidos para armar el resto de menus.
 * 
 * 
 * @author  YAGE (mnovillo)
 * @version 1.0
 *
 */
public class MenuUtils {

    
    private static Log logger = LogFactory.getLog(MenuUtils.class);
    

    /**
     * Creates a new instance of MenuUtils
     */
    public MenuUtils() {
        super();
    }
    
    
    public MenuLanguage getMenu(Long code) {

    	MenuLanguage menu = null;

        Session sess = null;
       // List results = null;
        
        try {
            sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
            //Query query = sess.createQuery(" From Menu as menu where menu.code = ?");
            //query.setLong(0, code.longValue());
            //query.setCacheable(Globals.TRUE.booleanValue());
            //menu = (Menu) query.uniqueResult();
            menu = (MenuLanguage) sess.get(MenuLanguage.class, code);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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


  /*  public MenuLanguage getDefaultMenu() {
        
    	MenuLanguage menu = null;

        Session sess = null;
        List results = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("from Menu as menu ");
            sql.append("where menu.default = ? ");
            
            sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);*/
            
            /*Query query = sess.createQuery (sql.toString());
            query.setBoolean(0, Globals.TRUE.booleanValue());
            query.setCacheable(Globals.TRUE.booleanValue());
            results = query.list();*/
       /*     results = sess.find(sql.toString(), Globals.TRUE, Hibernate.BOOLEAN);
            
            if (results != null && results.size() > 0) {
                menu = (Menu) results.get(0);                
            }
            
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
    */
    
    /**
     * @param menu
     * @param contextPath
     * @return
     */
    public StringBuffer createMenu(Long menuCode,  String contextPath) {
        Session sess = null;
        List results = null;
        StringBuffer menuBuff = new StringBuffer();
        //Todos los urls apuntan a un contenido, si se deseara 
        //que apunten a links externos o a otro debe aumentarse en 
        //contenido los urls
        String url = Constants.MAIN_ACTION;
        try {
            // Gets a hibernate Session
            sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
            
            StringBuffer sql = new StringBuffer();
            
            sql.append(" select new com.iportal.biz.portal.bean.MenuItemBean ( ");
        	sql.append(" menuItem.content.code, menuItem.name, menuItem.menuX, ");
            sql.append(" menuItem.menuY, menuItem.menuWidth,menuItem.content.group) ");
            sql.append("from MenuItem as menuItem inner join menuItem.content ");
            sql.append("where menuItem.menuLanguage.code = ? ");
            sql.append("and menuItem.enabled = ? ");
            sql.append("order by menuItem.content.order ");
            
            //Object params[] = new Object[] {menu.getCode(),  Globals.TRUE};
            //Type types[] = new Type[] {Hibernate.LONG,  Hibernate.BOOLEAN};
            
            Query query = sess.createQuery(sql.toString());
            
            query.setLong(0, menuCode.longValue());
            query.setBoolean(1, Globals.TRUE.booleanValue());
           
            query.setCacheable(Globals.TRUE.booleanValue());
            results = query.list();
            //results = sess.find(sql.toString(), params, types);
            
            // Main Menu Items (Text, URL's, and Icon ID's)
            for ( int i = 0; i < results.size(); i++) {
            	MenuItemBean menuItem = (MenuItemBean) results.get(i);
                   
                // comment
                menuBuff.append("//Main Menu ");
                menuBuff.append(i);
                menuBuff.append("\n");
                
                // main description
                menuBuff.append("dqm__maindesc");
                menuBuff.append(i);
                menuBuff.append(" = \"");
                if(i == 0) {
                	menuBuff.append("|&nbsp;");
                }
                menuBuff.append( menuItem.getName() );
                menuBuff.append("&nbsp;| \" \n");
                
                // main width 
               if ( menuItem.getMenuWidth() != null && menuItem.getMenuWidth().intValue() > 0) {
                    menuBuff.append("dqm__main_width");
                    menuBuff.append(i);
                    menuBuff.append(" = ");
                    menuBuff.append( menuItem.getMenuWidth() );
                    menuBuff.append("\n");
                }
                
                // icon id
               /* menuBuff.append("dqm__micon_index");
                menuBuff.append(i);
                menuBuff.append(" = 0 \n");*/
                
             /*   if (section.getUrl() != null && !section.getUrl().equals("")
						&& section.getCreateUrl() != null
						&& section.getCreateUrl().booleanValue()) {*/
                    menuBuff.append("dqm__url");
                    menuBuff.append(i);
                    menuBuff.append(" = \"");
                    menuBuff.append(contextPath);
                    //menuBuff.append(section.getUrl());
                    menuBuff.append(url);
                    //esto solo aplica si menu fuese dinamico
                   /*if ( StringUtils.contains(url, "?") ) {
                        menuBuff.append("&");
                    } else {
                        menuBuff.append("?");
                    }*/
                    menuBuff.append("?code=");
                    menuBuff.append(menuItem.getContentCode());
                    menuBuff.append("\" \n");
                	
             //   }
                if ( menuItem.getGroup().booleanValue() ) {
                    String index = String.valueOf(i);
                    createSubMenu(sess, menuItem, menuBuff, contextPath, index, null,i);
                	
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
        
        return menuBuff;
    }



    /**
     * Crea submenu a partir de una sección padre. Devuelve menuBuff
     * con el código javascript para generar el menú dinámicamente.
     * @param sess sesión de hibernate con la Base de Datos
     * @param section sección padre de la cual se va a consultar sus subsecciones 
     * @param menuBuff buffer donde se escribe el código javascript del menú 
     * @param contextPath Path de contexto de la aplicación
     * @param index índice del menú que se va a generar.
     */
    private static void createSubMenu(
            final Session sess, 
            final MenuItemBean menuItem, 
            final StringBuffer menuBuff,
            final String contextPath,
            final String index,
			final String oldIndex,
			final int oldI) {
        
    	 String url = Constants.MAIN_ACTION;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" select new com.iportal.biz.portal.bean.MenuItemBean ( ");
        	sql.append(" content.code, content.title, content.group, content.link) ");

            sql.append("from Content content ");
            sql.append("where content.parent.code = ? ");
            sql.append("and content.enabled = ? ");
            sql.append("order by content.order ");
            
            /*final Object params[] = new Object[] {section.getCode(), Globals.TRUE};
            final Type types[] = new Type[] {Hibernate.LONG, Hibernate.BOOLEAN};*/
            
           
            Query query = sess.createQuery(sql.toString());
            
            query.setLong(0, menuItem.getContentCode().longValue());
            query.setBoolean(1, Globals.TRUE.booleanValue());
          
            query.setCacheable( Globals.TRUE.booleanValue());
            final List results = query.list();
            
            if (results.size() > 0) {
                
            	//si no es el primer Menú genera el segundo ícono
            	/*if (oldIndex != null ) {
                    // second icon id
                    menuBuff.append("dqm__second_icon_index");
                    menuBuff.append(oldIndex);
                    menuBuff.append("_");
                    menuBuff.append(oldI);
                    menuBuff.append(" = 1 \n");
            		
            	}*/
            	// comment
                menuBuff.append("//Sub Menu ");
                menuBuff.append(index);
                menuBuff.append("\n");
                
                // possition
                
                menuBuff.append("dqm__sub_xy");
                menuBuff.append(index);
                menuBuff.append(" = \"");
                menuBuff.append( menuItem.getMenuX() );
                menuBuff.append(",");
                menuBuff.append( menuItem.getMenuY() );
                menuBuff.append("\" \n");
            

	            //final List results = sess.find(sql.toString(), params, types);
	            
	            for ( int i = 0; i < results.size(); i++) {
	            	MenuItemBean submenu = (MenuItemBean) results.get(i);
	                // description
	            	
						
	              /*  if ( submenu.getMenuWidth() != null && submenu.getMenuWidth().intValue() > 0) {
	                    menuBuff.append("dqm__sub_menu_width");
	                    menuBuff.append(index);
	                    menuBuff.append("_");
	                    menuBuff.append(i);
	                    menuBuff.append(" = ");
	                    menuBuff.append( submenu.getMenuWidth() );
	                    menuBuff.append("\n");
	                }*/
						
						
	            	menuBuff.append("dqm__subdesc");
	                menuBuff.append(index);
	                menuBuff.append("_");
	                menuBuff.append(i);
	                menuBuff.append(" = \"");
	                menuBuff.append( submenu.getName() ) ;
	                menuBuff.append("\" \n");
	                
	                // icon id
	                menuBuff.append("dqm__icon_index");
	                menuBuff.append(index);
	                menuBuff.append("_");
	                menuBuff.append(i);
	                menuBuff.append(" = 2 \n");
	                
	               /* if (subSection.getUrl() != null && !subSection.getUrl().equals("")
							&& subSection.getCreateUrl() != null
							&& subSection.getCreateUrl().booleanValue()) {*/
	                    // url
	                    menuBuff.append("dqm__url");
	                    menuBuff.append(index);
	                    menuBuff.append("_");
	                    menuBuff.append(i);
	                    menuBuff.append(" = \"");
	                    
	                  //  menuBuff.append(subSection.getUrl());
	                    if (submenu.getLink() != null && submenu.getLink().length()>0) {
	                    	menuBuff.append(submenu.getLink());
	                    } else {
	                    	menuBuff.append(contextPath);
	                    	menuBuff.append("/portal/main.do");	
		                    if ( StringUtils.contains(url, "?") ) {
		                        menuBuff.append("&");
		                    } else {
		                        menuBuff.append("?");
		                    }
		                    menuBuff.append("code=");
		                    menuBuff.append(submenu.getContentCode());
	                    }
	                    menuBuff.append("\" \n");
	                    
	               // } 
	                if ( submenu.getGroup().booleanValue() ) {

	                	menuBuff.append("dqm__second_icon_index");
	                    menuBuff.append(index);
	                    menuBuff.append("_");
	                    menuBuff.append(i);
	                    menuBuff.append(" = 1 \n");
    
	                    String newIndex = index.concat( "_".concat(String.valueOf(i)) );
	                    createSubMenu(sess, submenu, menuBuff, contextPath, newIndex, index, i);
	                }
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
}
