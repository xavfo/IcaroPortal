/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.Country;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * java comment CountryAction
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class CountryAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(CountryAction.class);

    
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
         
         CountryForm listForm = (CountryForm) form;
         
         Session sess = null;
         List results = null;
         
         //if (listForm.getListItems().booleanValue()) {
        	 try {

          		StringBuffer sql = new StringBuffer();
          		ArrayList<Object> params = new ArrayList<Object>();
				ArrayList<NullableType> types = new ArrayList<NullableType>();

          		sql.append("from Country as country ");    		
          		sql.append("where 1 = 1 ");
          		
          		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
		   		    String name = "%" + listForm.getName() + "%";
		   		    sql.append(" and country.name like ? ");
		   		    params.add(name);
		   		    types.add(Hibernate.STRING);  
	    		}
          		
          		if ( listForm.getOrderField() != null ) {
              		sql.append("order by country.");
              		sql.append(listForm.getOrderField());
              		if ( listForm.getOrderAsc().booleanValue() ) {
              		    sql.append(" asc ");
              		} else {
              		    sql.append(" desc ");
              		}
          		}
          		sess = getHibernateSession();
	     		Query query = sess.createQuery(sql.toString());
				
				for (int i = 0; i < types.size(); i++) {
					query.setParameter(i, params.get(i), types.get(i));
				}
				
				query.setCacheable(true);
				results = query.list ();
          		
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
              
      		// Save the List of results in request scope
      		request.setAttribute("countryList", results);
         //}
         

 		return mapping.findForward(Globals.FORWARD_LIST);
     }
     
     
     /**
      * Create action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward create (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
         CountryForm countryForm = (CountryForm) form;
         countryForm.reset(mapping, request);
         
         return mapping.findForward(Globals.FORWARD_FORM);
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

 		// Gets the action form
 		CountryForm countryForm = (CountryForm) form;
 		
 		Session sess = null;
 		Country country = null;
 		
 		try {
 			sess = getHibernateSession();
 			
 			country = (Country) sess.load(Country.class, countryForm.getCode());
 			
 			PropertyUtils.copyProperties(countryForm, country);
 			
 		} catch (Exception e) {
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(""));
 			logger.error(e.getMessage(), e);
 		} finally {
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
 		
         return mapping.findForward(Globals.FORWARD_FORM);
     }   
     
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
   		 CountryForm countryForm = (CountryForm) form;

 		 this.saveItem(countryForm, messages, request);
          
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
     
     /**
      * Apply action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward apply (
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
   		 CountryForm countryForm = (CountryForm) form;

 		 Country country = this.saveItem(countryForm, messages, request);
         if (country != null){
             countryForm.setCode(country.getCode());
         }
 		 
 		 //Report any messages we have discovered back to the original form
  		 if (!messages.isEmpty()) {
  			saveMessages(request, messages);
  			return (mapping.getInputForward());
  		 }
  		
  		 //Report a success action
  		 messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
  		 saveMessages(request, messages);
 		
		 return mapping.findForward(Globals.FORWARD_APPLY);
		 
     }
     
     public Country saveItem (
    		 CountryForm countryForm,
    		 ActionMessages messages, HttpServletRequest request)
     throws Exception {
         
    	 
  		
  		Session sess = null;
  		Transaction tx = null;
  		Country country = null;

  		try {
  		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
  		    
  			if (countryForm.getCode() != null && countryForm.getCode().longValue() != 0L) {
  				country = (Country) sess.load(Country.class, countryForm.getCode());
  			} else {
  				country = new Country();
  				countryForm.setCode(null);
  			}
  			
  			PropertyUtils.copyProperties(country, countryForm);
  			
  			//Persist data
  			tx = sess.beginTransaction();
  			sess.saveOrUpdate(country);			
  			tx.commit();
            
            // Audit Transaction
             if ( countryForm.getCode() == null ) {
                 SysAuditHelper.audit(this, request, country, country.getName(), Globals.AUDIT_INSERT);
             } else {
                 SysAuditHelper.audit(this, request, country, country.getName(), Globals.AUDIT_UPDATE);
             }
  			
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
  		return country;
 		
     }


     /**
      * Delete action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward delete (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		CountryForm countryForm = (CountryForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Country country = null;

		try {
		    Long codes[] = countryForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	country = (Country) sess.load(Country.class, codes[i]);
					sess.delete(country);
                    SysAuditHelper.audit(this, request, country, country.getName(), Globals.AUDIT_DELETE, sess);			        
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.country", this.getLocale(request)), country.getName(), null);
			
			if (tx != null) {
			    tx.rollback();
			}
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			}
			
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.close();
				} catch (Exception e) {
				}
		}

		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}

		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

}
