/*
 * Created 12/04/2007
 *	AccessMenuTilesAction.java
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
import com.iportal.biz.portal.bean.AccessItemBean;
import com.iportal.biz.portal.bean.AccessItemsList;
import com.iportal.model.Language;
import com.iportal.model.portal.Menu;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * Consulta la informacion para los banners del sistema y menus de acceso.
 *
 * SI ES UN LINK EXTERNO IMPRIME EL UR DEL REGISTRO DE LA TABLA DE ACCESS 
 * CASO CONTRARIO EL LINK INTERNO, es decir de la tabla AccessURL, 
 * asi las actualizaciones son automáticas
 * 
 * @author YAGE(hernan)
 *
 */
public class AccessMenuTilesAction extends BaseTilesAction {

	private static Log logger = LogFactory.getLog(AccessMenuTilesAction.class);
	
    /* 
     * (non-Javadoc)
     * @see org.apache.struts.tiles.actions.TilesAction#execute(org.apache.struts.tiles.ComponentContext, org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        

    	Session sess = null;
        Query query = null;
        Menu menu =null;
        AccessItemsList accessList = null;
        
        ServletContext mainContext = this.getServlet().getServletContext();
    	Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);
        
    	String menuCode = (String)  context.getAttribute("menuCode");
        
        
        //MenuLanguage mLang = null;
        try {
        	 sess = getHibernateSession();
        	 if (menuCode != null && menuCode.length() > 0){
        		 StringBuffer menuKey = new StringBuffer (15);
        		 menuKey.append(Constants.MAIN_MENU_KEY);
        		 menuKey.append(menuCode);

        		 accessList = (AccessItemsList) mainContext.getAttribute(menuKey.toString());
        		 if ( accessList == null  ) {
        			 

	 	        	 /*
	 	        	  * Si AccessUrl distinto de 1 (acceso externo-external URL), debe cargar el url definido en el AccessURL sino carga el acceso externo definido en el access 
	    			  */
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
		
		request.setAttribute("accessItemsList", accessList);
		request.setAttribute("menuCode", menu);
		
		return null;
    }

}
