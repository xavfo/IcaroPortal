/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.portal.contact;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
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
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.model.Catalogue;
import com.iportal.model.Contact;
import com.iportal.model.Country;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * TODO java comment ContactAction
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class ContactAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(ContactAction.class);
    
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
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
          ActionMessages messages = new ActionMessages();  		
         
         //Gets the action form
         ContactForm contactForm = (ContactForm) form;
         
         Session sess = null;
         Transaction tx = null;
         HttpSession session = request.getSession();
         //Content content=null;
         Contact contact = null;
         MenuPortalBean portalBean = (MenuPortalBean) session
			.getAttribute(Constants.MENU_KEY);
     

         try {
        	 
        	 sess = this.getHibernateSession(); 
        	 
        	 contact = new Contact();
        	 contactForm.setCode(null);
   			
   			if (contactForm.getCountryCode() != null && contactForm.getCountryCode().longValue() > 0) {
   				//Se hace un get para poder acceder al nombre del pa�s en la capa de presentaci�n
   				Country country = (Country) sess.get(Country.class, contactForm.getCountryCode());
   				contact.setCountry(country);
  			}
   			if (contactForm.getCatalogueCode() != null && contactForm.getCatalogueCode().longValue() > 0) {
   				//Se hace un get para poder acceder al nombre del catalogo en la capa de presentaci�n
   				Catalogue catalogue = (Catalogue) sess.get(Catalogue.class, contactForm.getCatalogueCode());
   				contact.setCatalogue(catalogue);
  			}
   			
   			PropertyUtils.copyProperties(contact, contactForm);
   			
   			//Persist data
   			tx = sess.beginTransaction();
   			
   			contact.setDate(new GregorianCalendar());
   			sess.saveOrUpdate(contact);			
   			tx.commit();
   			
   			   
   			request.setAttribute("contactData", contact);
          
            SysAuditHelper.audit(this, request, contact, contact.getName(), Globals.AUDIT_INSERT);

   		} catch (Exception e) {
   			if (tx != null) {
   			    tx.rollback();
   			}
   			
   			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
   			logger.error(e.getMessage(), e);
   		} finally {
   			if (sess != null)
   				try {
   					sess.close();
   				} catch (Exception e) {
   				}
   		}
        
 		 
 		 //Report any messages we have discovered back to the original form
  		if (!messages.isEmpty()) {
  			saveMessages(request, messages);
  			return (mapping.getInputForward());
  		}
  		
  		//Report a success action
 		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
 		saveMessages(request, messages);
 		
		 return mapping.findForward(Globals.FORWARD_SUCCESS);
		 
     }
     

}
