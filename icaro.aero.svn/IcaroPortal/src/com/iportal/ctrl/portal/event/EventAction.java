/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.portal.event;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.audit.PageLogHelper;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.biz.portal.content.ContainerForm;
import com.iportal.biz.portal.event.EventFacade;
import com.iportal.biz.portal.event.EventForm;
import com.iportal.ctrl.portal.content.ContentContainerAction;
import com.iportal.model.event.Event;
import com.iportal.model.portal.Content;
import com.yage.Globals;

public class EventAction extends ContentContainerAction {

    private static Log logger = LogFactory.getLog(EventAction.class);

    /**
     * List action
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */ 
   public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
	  		ActionMessages messages = new ActionMessages();
	   		List<Event> results = null;
//	   		List results2=null;
	   		Session sess = null;
	   		Content content=null;
//	        Layout layout =null;
//	 	   	String forward =null;
//	 	   	Query query = null;
	 	    
	        HttpSession session = request.getSession();
	 	    MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);   
    	    
   		try {
   			
   			 sess = getHibernateSession();
	     	 EventForm eventForm= (EventForm)form;	
		     	if(portalBean !=null)
		     	{
		     	content = (Content) sess.get(Content.class, portalBean.getContentCode());
		     	request.setAttribute("content",content);
		     	//layout = (Layout)sess.get(Layout.class,portalBean.getLayoutCode());
		    	//forward = layout.getForward();
		    	this.setCountContentContainers(request, sess, content,  content.getCode());
		        
		     	}
			   if(eventForm.getListEvent().booleanValue()==true){ 	
		   		   EventFacade eventFacade = new EventFacade();
				   
		   		 //  eventForm.setOrderField("index");
		   		   results = eventFacade.getEvents(eventForm);
			   }
		 /*  StringBuffer hql = new StringBuffer();
		   		hql.append(" select count (doc.code) ");
		   		hql.append(" from DocumentContainer doc  ");
		   		hql.append(" join doc.contents content ");
		   		hql.append(" where content.code = ? ");
		   		hql.append(" and doc.isEnabled = ? ");
		   		
		   		query = sess.createQuery(hql.toString());
		   		query.setLong(0, portalBean.getContentCode());
		   		query.setBoolean(1, Globals.TRUE);
		   		query.setCacheable(true);	
		   		results2 = query.list();*/
		
   			} catch (Exception e) {
   			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.info(e.getMessage(), e);
   			}finally {
			if (sess != null)
				try {
				    sess.clear();
					sess.close();
				} catch (Exception e) {
				} 			
   			} 		
	
	    request.setAttribute("eventList", results);
	    
		return mapping.findForward(portalBean.getLayoutForward() +"_"+ Globals.FORWARD_LIST);
    }
   
   
public ActionForward form (
           ActionMapping mapping, 
           ActionForm form,
           HttpServletRequest request, 
           HttpServletResponse response)
   throws Exception {
       
	  		ActionMessages messages = new ActionMessages();
	   		List<Event> results = null;
	   		
	   		Session sess = null;
	   		Content content=null;
//	        Layout layout =null;
//	 	   	String forward =null;
	 		
	        HttpSession session = request.getSession();
	     	MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);   
    	    
  		try {
  				sess = getHibernateSession();
  				
//  				EventForm eventForm= (EventForm)form;	
		     	if(portalBean !=null)
		     	{
		     	content = (Content) sess.get(Content.class, portalBean.getContentCode());
		     	request.setAttribute("content",content);
		     	//layout = (Layout)sess.get(Layout.class,portalBean.getLayoutCode());
		    	//forward = layout.getForward();
		    	this.setCountContentContainers(request, sess, content,  content.getCode());
		        
		     	}
			  
				
  			} catch (Exception e) {
  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.info(e.getMessage(), e);
  			}finally {
			if (sess != null)
				try {
				    sess.clear();
					sess.close();
				} catch (Exception e) {
				} 			
  			} 		
	
	    request.setAttribute("eventList", results);
	   
	  
	    
	    
		return mapping.findForward(portalBean.getLayoutForward()+"_"+ Globals.FORWARD_LIST);
   }
      
   
   
   /**
    * Read action
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    * @throws Exception
    */
   public ActionForward read (
           ActionMapping mapping, 
           ActionForm form,
           HttpServletRequest request, 
           HttpServletResponse response)
   throws Exception {
       
       if ( isCancelled(request) ) {
           return mapping.findForward(Globals.FORWARD_CANCEL);
       }

       ActionMessages messages = new ActionMessages();
       Session sess = null;
       Content content=null;
//       Layout layout =null;
//       String forward =null;
	   Event event=null;
	   
       HttpSession session = request.getSession();
   	
    	MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);  
   	
       try {
        
    	   sess = getHibernateSession();
  		 	if(portalBean !=null){
  		 		content = (Content) sess.get(Content.class, portalBean.getContentCode());
  		 		//layout = (Layout)sess.get(Layout.class,portalBean.getLayoutCode());
  		 		//forward = layout.getForward();
  		 		request.setAttribute("content",content);
  		 	}
	    	
	       	EventFacade eventfacade = new EventFacade();
	       	event = eventfacade.read((EventForm) form);
		 	
	        request.setAttribute("event", event);      
        
  		} catch (Exception e) {
  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.info(e.getMessage(), e);
  		}finally {
			if (sess != null)
				try {
				    sess.clear();
					sess.close();
				} catch (Exception e) {
				} 			
		} 		
		
		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}
		
		PageLogHelper.log(request, event.getClass().getName(), event.getCode(), event.getTitle());
		
		return mapping.findForward(portalBean.getLayoutForward()+"_"+ Globals.FORWARD_LIST);
   }
   
   public ActionForward listDocuments (
           ActionMapping mapping, 
           ActionForm form,
           HttpServletRequest request, 
           HttpServletResponse response)
   throws Exception {
       
 		ActionMessages messages = new ActionMessages();
  	List results = null;
  	Session sess = null;
  		
	   	HttpSession session = request.getSession();
	   	MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
	   	
	   	ContainerForm containerForm=(ContainerForm)form;

	   	if (portalBean == null) {
  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.content.notFound"));
  		} else {
  	 	   	try {
  		   		sess = getHibernateSession();
  		   		Event event = (Event) sess.load(Event.class, containerForm.getCode());
  		   		
  		   		results = sess.createFilter(event.getDocuments()," where this.isEnabled = ? order by this.title").setBoolean(0, true).list();
  		   		
  		   		request.setAttribute("event", event);
  		   		request.setAttribute("documents", results);
  			
  	   		} catch (Exception e) {
  				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
  	   			
  	   			logger.error(e.getMessage(), e);
  	   		}finally {
  				if (sess != null)
  					try {
  					    sess.clear();
  						sess.close();
  					} catch (Exception e) {
  					} 			
  			} 		
  		
  		}
	    
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return mapping.findForward(Globals.FORWARD_FAILURE);
 		}
	    
		return mapping.findForward(portalBean.getLayoutForward() +"_"+ Globals.FORWARD_LIST);
  }
   
}