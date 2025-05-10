package com.iportal.ctrl.portal.job;
import java.util.GregorianCalendar;

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
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.model.Country;
import com.iportal.model.jobs.Position;
import com.iportal.model.jobs.PositionRequest;
import com.iportal.model.jobs.Requester;
import com.iportal.model.portal.Content;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;


public class VitaeFormAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(VitaeFormAction.class);
    

  public ActionForward form(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	 ActionMessages messages = new ActionMessages();
    	 Session sess = null;
    	 HttpSession session = request.getSession();
	     MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
	     try{
	    	 sess = getHibernateSession();
	    	 Content content = (Content) sess.get(Content.class, portalBean.getContentCode());
	    	 request.setAttribute("content", content);
	    	 RequestForm requestForm=(RequestForm) form;  
	    	 Position position= (Position) sess.get(Position.class, requestForm.getPosition());
	    	 session.setAttribute("positionName", position.getName());	
	    	
	    	 
	     }catch(Exception e) {
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
	     
    	
    	return mapping.findForward(portalBean.getLayoutForward()+"_"+ Globals.FORWARD_LIST);
		
    }
  public ActionForward save(
          ActionMapping mapping, 
          ActionForm form,
          HttpServletRequest request, 
          HttpServletResponse response)
  throws Exception {
  	 ActionMessages messages = new ActionMessages();
  	 Session sess = null;
  	 HttpSession session = request.getSession();
	 MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
	 Requester requester = new Requester();
	 Transaction tx = null;
	     try{
	    	 sess = getHibernateSession();
	    	 Content content = (Content) sess.get(Content.class, portalBean.getContentCode());
	    	 request.setAttribute("content", content);
	    	 RequestForm requestForm=(RequestForm) form;
	    	
	    	 requester.setDateCreated(new GregorianCalendar());
	    	 requester.setFirstName(requestForm.getFirstName());
	    	 requester.setLastName(requestForm.getLastName());
	    	 requester.setAddress(requestForm.getAddress());
	    	 requester.setPhoneNumber(requestForm.getPhoneNumber());
	    	 Country country = (Country) sess.get(Country.class, requestForm.getCountry());
	    	 requester.setCountry(country);
	    	 requester.setEmail(requestForm.getEmail());
	    	 requester.setVitae(requestForm.getVitae());
	    	 requester.setBriefApplication(true);
	    	 requester.setRequestedCity(requestForm.getRequestedCity());
	    
	    	 
		    
		   
		     tx = sess.beginTransaction();
				sess.saveOrUpdate(requester);			
				tx.commit();
		    
	 			Position position= (Position) sess.get(Position.class, requestForm.getPosition());
	 			PositionRequest requested =new PositionRequest();
	 			requested.setCode(null);
	 			requested.setPosition(position);
	 			requested.setRequester(requester);
	 			requested.setExperience(requestForm.getExperience());
	 			tx = sess.beginTransaction();
	 			sess.save(requested);
	 			tx.commit();
		     //requester.setRequesterWorkCity();
	 			 request.setAttribute("requester",requester);
	 			 request.setAttribute("jobName", position.getName());
	 			 request.setAttribute("jobDepartment",position.getArea().getName());
	 			request.setAttribute("fileCV", requester.getVitae().substring(1));
	 			
	 			
	    
	    	 
	    	 
	    	 
	     }catch(Exception e) {
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
	     
  	
  	return mapping.findForward(Globals.FORWARD_FORM);
		
  }
  
  
  
  
  
  
  
  
  
}
