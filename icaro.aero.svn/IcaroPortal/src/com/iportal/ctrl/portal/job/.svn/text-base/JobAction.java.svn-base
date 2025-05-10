/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.portal.job;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.ctrl.portal.content.ContentContainerAction;
import com.iportal.model.jobs.Area;
import com.iportal.model.jobs.Position;
import com.iportal.model.portal.Content;
import com.yage.Globals;

/**
 * 
 * 
 * @author  YAGE
 * @version 1.0
 *
 */
public class JobAction extends ContentContainerAction {

    
    private static Log logger = LogFactory.getLog(JobAction.class);
     
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
        
    throws Exception {
    		Session sess = null;
    	    List results = null;
    	    Query query = null;
    	    Content content=null;
    	    Position position=null;
  	  
    	
    	 HttpSession session = request.getSession();
    	 
    	 String codeCargo= request.getParameter("code");
    	 MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);   
	     	
    	 try {
    		 sess = getHibernateSession();
    	
    	     	if(portalBean !=null)
    	     	{
	    	     	content = (Content) sess.get(Content.class, portalBean.getContentCode());
	    	     	request.setAttribute("content",content);
	    	     	//layout = (Layout)sess.get(Layout.class,portalBean.getLayoutCode());
	    	    	//forward = layout.getForward();
    	     	}
    		  
    	     	 if(codeCargo != null){
    	     		position = (Position)sess.load(Position.class, new Long(codeCargo));
    	     	
    	     	 }
    	     		StringBuffer sql = new StringBuffer();
         	
    	     	sql.append("from com.iportal.model.jobs.Area as area ");
    			sql.append("where 1 = 1 ");
    			
    			 query = sess.createQuery(sql.toString());
                 results=query.list();
                 
              
                 for (int i = 0; i < results.size(); i++ ){
				    	Area area1 = (Area)results.get(i);
				    	Long code =  area1.getCode();
				    	Area ar = (Area)sess.load(Area.class, code);
				    	Hibernate.initialize(ar.getListOfPosition());
				    	
				    }
                 this.setCountContentContainers(request, sess, content, content.getCode()); 
	        
            
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
	        
		request.setAttribute("jobList", results);
		request.setAttribute("position",position);
		
		return mapping.findForward(portalBean.getLayoutForward() +"_"+ Globals.FORWARD_LIST);
    }
    
   

}
