/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.portal.MainMenuManager;
import com.iportal.biz.portal.bean.AccessItemBean;
import com.iportal.biz.portal.bean.AccessItemsList;
import com.iportal.model.Language;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * @author  FernandoT
 * @version 1.0
 *
 */
public class MainMenuTilesAction extends BaseTilesAction {

    
    private static Log logger = LogFactory.getLog(MainMenuTilesAction.class);
     
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        Query query = null;
        AccessItemsList accessList = null;
        
        ServletContext mainContext = this.getServlet().getServletContext();
    	Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);
        String menuCode = "2";
        
        //MenuLanguage mLang = null;
        try {
        	 sess = getHibernateSession();
        	 if (menuCode != null && menuCode.length() > 0){
        		 StringBuffer menuKey = new StringBuffer (15);
        		 menuKey.append(Constants.MAIN_MENU_KEY);
        		 menuKey.append(menuCode);

        		 //results = (List) mainContext.getAttribute(menuKey.toString());
        		 accessList = (AccessItemsList) mainContext.getAttribute(menuKey.toString());
        		 
        		 if ( accessList == null  ) {
        			 
        			 StringBuffer hql = new StringBuffer();
     	        	 hql.append(" SELECT new com.iportal.biz.portal.bean.AccessItemBean ( ");
     	        	 hql.append(" accessItem.displayMode.code, accessItem.order, accessItem.access.name, ");
     	        	 hql.append(" accessItem.access.relatedCode, accessItem.access.relatedName,  ");
     	        	 hql.append(" CASE WHEN accessItem.access.accessUrl.code != 1 THEN accessItem.access.accessUrl.path  ELSE accessItem.access.url END, "); 
     	        	 hql.append(" CASE WHEN accessItem.access.accessUrl.code != 1 THEN false  ELSE true END, ");
     	        	 hql.append(" accessItem.access.path, accessItem.menuLanguage.menu.code, accessItem.menuLanguage.name, accessItem.access.description ) ");
     	        	 hql.append(" FROM AccessMenu as accessItem ");
     	        	 hql.append(" WHERE accessItem.enabled = ? ");
     	        	 hql.append("  AND accessItem.menuLanguage.language = ? ");
     	        	 hql.append("  AND accessItem.menuLanguage.menu.code = ? ");
     	        	 hql.append(" ORDER BY accessItem.order ");
         		
     	        	 query = sess.createQuery(hql.toString());
     	        	 query.setParameter(0, Globals.TRUE, Hibernate.BOOLEAN );
     	        	 query.setEntity(1, language);
     	        	 query.setParameter(2, Long.valueOf(menuCode), Hibernate.LONG);
     	        	 List results = query.list();
     	        	
     	        	accessList = new AccessItemsList (results);
     	        	if (results.size() > 0) {
     	        		AccessItemBean bean = (AccessItemBean) results.get(0);
     	        		accessList.setListTitle(bean.getMenuName());
     	        	} else {
     	        		query = sess.createQuery(" SELECT mLang.name from MenuLanguage mLang where mLang.menu.code = ? and mLang.language= ? ");
        	        	 query.setLong(0, Long.valueOf(menuCode));
         	        	 query.setEntity(1, language);
         	        	accessList.setListTitle((String) query.uniqueResult());
     	        	}
     	        	
     	        	mainContext.setAttribute(menuKey.toString(), accessList);
	        	  
	        	  StringBuffer buffer = MainMenuManager.createXmlBuffer(accessList, sess, request);
	    		  MainMenuManager.writeToFile( "data.xml", buffer.toString(), getServlet().getServletContext().getRealPath( "/" ));
	        	  
   		   	   }        		 
        		 
        	 }
        	 
            
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}		
		
		return null;
    }   


}
