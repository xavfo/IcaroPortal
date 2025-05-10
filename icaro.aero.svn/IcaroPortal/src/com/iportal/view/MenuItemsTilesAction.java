/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view;

import java.util.ArrayList;
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
import com.iportal.biz.portal.bean.MenuItemBean;
import com.iportal.model.Language;
import com.iportal.model.portal.Menu;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * 
 * @author  YAGE
 * @version 1.0
 * @see com.iportal.view.AccessMenuTilesAction
 * @deprecated  As of CMS version 1.2, 
 * replaced by <code>{@link com.iportal.view.AccessMenuTilesAction}</code>. 
 *
 */
@Deprecated 
public class MenuItemsTilesAction extends BaseTilesAction {

    
    private static Log logger = LogFactory.getLog(MenuItemsTilesAction.class);
     
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        Query query = null;
        Menu menu=null;
        List<MenuItemBean> result = null;
        ServletContext mainContext = this.getServlet().getServletContext();
    	Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);
        String menuCode = (String)  context.getAttribute("menuCode");
        String initChildren = (String)  context.getAttribute("initChildren");
        
        //MenuLanguage mLang = null;
        try {
        	 sess = getHibernateSession();
        	 if (menuCode != null && menuCode.length() > 0){
        		 StringBuffer menuKey = new StringBuffer (15);
        		 menuKey.append(Constants.MAIN_MENU_KEY);
        		 menuKey.append(menuCode);

        		 result = (ArrayList<MenuItemBean>) mainContext.getAttribute(menuKey.toString());
        		 if ( result == null  ) {
        			 
            		// menu = (Menu)sess.load(Menu.class,Long.valueOf(menuCode));
     	        	// mLang = menu.getMenuLanguage(language); 	
     	        	 
     	        	 
     	        	 query = sess.createQuery(" SELECT mLang.code from MenuLanguage mLang where mLang.menu.code = ? and mLang.language= ? ");
     	        	 query.setLong(0, Long.valueOf(menuCode));
     	        	 query.setEntity(1, language);
     	        	 Long mLangCode = (Long) query.uniqueResult();
     	        	 
     	        	 
     	        	 StringBuffer hql = new StringBuffer();
     	        	 hql.append(" SELECT new com.iportal.biz.portal.bean.MenuItemBean ( ");
     	        	 hql.append(" menuItem.content.code, menuItem.name, menuItem.displayMode.code, menuItem.content.link, ");
     	        	 hql.append(" substring(menuItem.content.background,2), menuItem.content.background,  ");
     	        	 hql.append(" menuItem.content.intro, image.path, banner.path, ");
     	        	 hql.append(" accessImage.path )");
     	        	 hql.append(" FROM MenuItem as menuItem ");
     	        	 hql.append(" inner join  menuItem.content content");
     	        	 hql.append(" left join  content.image image ");
     	        	 hql.append(" left join  content.banner banner ");
     	        	 hql.append(" left join  content.accessImage accessImage ");
     	        	 hql.append(" WHERE menuItem.level = ? ");
     	        	 hql.append("  AND menuItem.enabled = ? ");
     	        	 hql.append("  AND menuItem.menuLanguage.code = ? ");
     	        	 hql.append(" ORDER BY menuItem.order ");
         		
     	        	 query = sess.createQuery(hql.toString());
     	        	 query.setParameter(0, 1,Hibernate.INTEGER);
     	        	 query.setParameter(1, Globals.TRUE, Hibernate.BOOLEAN );
     	        	 query.setParameter(2, mLangCode, Hibernate.LONG);
     	        	 result = (ArrayList<MenuItemBean>)query.list();
     	        	 
     	        	 if (initChildren != null && Boolean.valueOf(initChildren)) {
     	        		 hql.delete(0,hql.length());
     	        		 hql.append(" SELECT new com.iportal.biz.portal.bean.MenuItemBean ( ");
     	        		 hql.append(" content.code, content.title, content.link ) ");
     	        		 hql.append(" FROM Content content where content.parent.code= :parent ");
     	        		 hql.append(" and content.enabled = :enabled ");
     	        		 hql.append(" order by content.order ");
     	        		 query = sess.createQuery(hql.toString());
     	        		 query.setParameter("enabled", Globals.TRUE, Hibernate.BOOLEAN);
     	        		 for (MenuItemBean item : result ) {
     	        			 query.setParameter("parent", item.getContentCode(), Hibernate.LONG);
     	        			 item.setSubMenus((ArrayList<MenuItemBean>)query.list());
     	        		 }
     	        		 
     	        	 }
     	        	
     	        	 
     	        	mainContext.setAttribute(menuKey.toString(), result);
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
		
		request.setAttribute("menuItemsList", result);
		request.setAttribute("menuCode", menu);
		
		return null;
    }


}
