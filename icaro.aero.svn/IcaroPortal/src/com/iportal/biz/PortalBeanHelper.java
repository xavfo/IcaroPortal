package com.iportal.biz;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.audit.PageLogHelper;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.model.portal.Content;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.plugin.HibernatePlugIn;

public class PortalBeanHelper {
	
	private static Log logger = LogFactory.getLog(PortalBeanHelper.class);
	 
	public PortalBeanHelper() {
		super();
	}
	
	/**
	 * Asigna o Reemplaza un nuevo Bean a Session, creando una nueva session de Hibernate
	 * @param code - C�digo de Contenido
	 * @param request - Http Request  
	 * @return MenuPortalBean
	 * @throws Exception
	 */
	public static MenuPortalBean setNewBean(Long code, HttpServletRequest request)
	throws Exception{
		return setNewBean(code, request, null);
	}
	
	
	/**
	 * Asigna o Reemplaza un nuevo Bean a Session, usando una session abienta de Hibernate
	 * @param code - C�digo de contenido 
	 * @param request - Http Request
	 * @param  currentSession - Sesion Hibernate activa
	 * @return MenuPortalBean
	 * @throws Exception
	 */
	public static MenuPortalBean setNewBean(Long code, HttpServletRequest request,  Session currentSession) 
	throws Exception{
		Session sess = null;
		MenuPortalBean menuPortalBean = null; 
		try {
			HttpSession session = request.getSession();
			
			if (currentSession != null)
				sess = currentSession;
			else
				sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			
			StringBuffer hql = new StringBuffer();
    		hql.append(" select new com.iportal.biz.portal.bean.MenuPortalBean ( ");
    		hql.append(" content.code, content.accessLevel.code, ");
    		hql.append(" content.layout.portalModule.code, content.layout.portalModule.forward, ");
    		hql.append(" content.layout.code, content.layout.forward,");
    		hql.append(" content.title, content.text, content.background, ");
    		hql.append(" parent.code" );
    		/**Modificacion con left joins**/
    		hql.append(" ,parent.title, parent.background, image.name, ");
    		hql.append(" image.path, image.height, image.width)");
    		hql.append       (" from  Content content ");
    		hql.append       ("  left join  content.parent parent ");
    		hql.append       ("  left join  content.image image ");
    		hql.append       (" where  content.code = ? ");
    		
    		Query q = sess.createQuery(hql.toString());
    		q.setLong(0, code);
    		q.setCacheable(true);
    		menuPortalBean = (MenuPortalBean) q.uniqueResult();
    		
    		if (menuPortalBean != null){
    			session.setAttribute(Constants.MENU_KEY, menuPortalBean);
    			
    			//Registra visita a este contenido
        		PageLogHelper.log(request, Content.class.getName(), menuPortalBean.getContentCode(), menuPortalBean.getContentTitle());
    		}
    		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally{
			try {
				if (sess!=null && currentSession==null) {
					sess.clear();
					sess.close();
				}
			} catch (Exception e) {
			}
		}
		return menuPortalBean;
	}
	
	/**
	 * Organiza informaci�n para subhomes
	 * @param results
	 * @return
	 */
	public  List<MapSiteBean> organizeMapBeanList (List<MapSiteBean> results ) {
		List<MapSiteBean> finalResults =  new ArrayList<MapSiteBean> (results.size());
		MapSiteBean oldParent = null;
   		MapSiteBean parent = null;
   		List<MapSiteBean> children = null; 
   		for (MapSiteBean map : results) {
   			if (map.getParentCode() != null) {
   				parent = this.findMapSiteBean(results, map.getParentCode());
				if (parent != null) {
					if ( oldParent != null && !parent.getCode().equals(oldParent.getCode())) {
						if (oldParent != null) {
							oldParent.setChildren(children);
							finalResults.add(oldParent);
						}
						children = new ArrayList<MapSiteBean> ();
					}
					if (children == null) {
						children = new ArrayList<MapSiteBean> ();		   					
					}
					children.add(map);
					oldParent= parent;
				} else {
					logger.error("parent not Found in finalResultList code: "+map.getParentCode());
				}
			  } else {
				finalResults.add(map);
			  }
		}
		if (oldParent != null) {
			oldParent.setChildren(children);
			finalResults.add(oldParent);
		}
   		
   		return finalResults;
	}
	
    private MapSiteBean findMapSiteBean (List<MapSiteBean> source, Long code) {
    	for (MapSiteBean map : source) {
    		if (map.getCode().equals(code)) {
    			return map;
    		} else {
    			if (map.getChildren() != null) {
    				MapSiteBean result = this.findMapSiteBean(map.getChildren(), code);
    				if (result != null) {
    					return result;
    				}
    			}
    		}
    			
    		
    	}
    	return null;
    }


}
