package com.iportal.ctrl.portal.event;

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
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.biz.portal.event.EventSubscriberFacade;
import com.iportal.biz.portal.event.EventSubscriberForm;
import com.iportal.model.EventSubscriber;
import com.iportal.model.event.Event;
import com.iportal.model.portal.Content;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

public class EventSubscriberAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(EventSubscriberAction.class);
    
    /**
     * Save action
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    
    throws Exception {
        
    	 ActionMessages messages = new ActionMessages();
    	 Session sess = null;
    	 EventSubscriber results = null;
//         EventSubscriber eventSubscriber = null;
    	 
    	 Content content=null;
//         Layout layout =null;
//         String forward =null;

    	
    	 sess = getHibernateSession();
	     HttpSession session = request.getSession();
	    
    	 try {
    		 MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);   
             if(portalBean !=null) {
                 content = (Content) sess.get(Content.class, portalBean.getContentCode());
                 request.setAttribute("content",content);
 	     	}
 	     
    		EventSubscriberFacade eventSubscriberFacade = new EventSubscriberFacade();
    		results= eventSubscriberFacade.save((EventSubscriberForm) form);
    		request.setAttribute("eventSubscriber",results);
  			
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
  		
	  		if (!messages.isEmpty()) {
	  			saveMessages(request, messages);
	  			return (mapping.getInputForward());
	  		}
  		
 		// Report a success action
	 		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
	 		saveMessages(request, messages);
	 		
	 		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

    
    public ActionForward form(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    
    throws Exception {
    	ActionMessages messages = new ActionMessages();
    	Session sess = null;
    	sess = getHibernateSession();
  	    HttpSession session = request.getSession();
  	    Content content=null;
//        Layout layout =null;
//        String forward =null;
        Event event=null;
    	try{
    		String eventCode = (String)  request.getParameter("code");
    		 
   		 
   		 if (eventCode != null && eventCode.length() > 0){
	       		event = (Event)sess.load(Event.class,Long.valueOf(eventCode));
	       		request.setAttribute("eventTitle",event.getTitle());
			   }
   		    		 
    		MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);   
   	 
	   	  if(portalBean !=null)
	   	  {
	   		  content = (Content) sess.get(Content.class, portalBean.getContentCode());
	   		  request.setAttribute("content",content);
	   		
	   		 
	   		 
	   	  }	
	   	  request.setAttribute("eventCode",eventCode);
	   	
    		
    		}catch (Exception e) {
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
    		return mapping.findForward(Globals.FORWARD_SUCCESS);	
    }
    
}
    