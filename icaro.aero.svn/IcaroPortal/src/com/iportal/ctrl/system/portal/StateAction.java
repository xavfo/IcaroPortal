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
import com.iportal.model.State;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * 
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class StateAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(StateAction.class);

    
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
         
         StateForm listForm = (StateForm) form;
         
         Session sess = null;
         List results = null;
         
         if (listForm.getListItems().booleanValue()) {
        	 try {

          		StringBuffer sql = new StringBuffer();
          		ArrayList<Object> params = new ArrayList<Object>();
				ArrayList<NullableType> types = new ArrayList<NullableType>();

          		sql.append("from State as state ");    		
          		sql.append("where 1 = 1 ");
          		
          		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
		   		    String name = "%" + listForm.getName() + "%";
		   		    sql.append(" and state.name like ? ");
		   		    params.add(name);
		   		    types.add(Hibernate.STRING);  
	    		}
          		
          		if (listForm.getCountryCode() != null && listForm.getCountryCode().longValue() > 0L ) {
		   		    sql.append(" and state.country.code = ? ");
		   		    params.add(listForm.getCountryCode());
		   		    types.add(Hibernate.LONG);  
	    		}
          		
          		if ( listForm.getOrderField() != null ) {
              		sql.append("order by state.");
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
      		request.setAttribute("stateList", results);
         }
         

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
         StateForm stateForm = (StateForm) form;
         stateForm.reset(mapping, request);
         
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
 		StateForm stateForm = (StateForm) form;
 		
 		Session sess = null;
 		State state = null;
 		
 		try {
 			sess = getHibernateSession();
 			
 			state = (State) sess.load(State.class, stateForm.getCode());
 			
 			PropertyUtils.copyProperties(stateForm, state);
 			stateForm.setCountryCode( state.getCountry().getCode() );
 			
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
         StateForm stateForm = (StateForm) form;

 		 this.saveItem(stateForm, messages, request);
 		 
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
         StateForm stateForm = (StateForm) form;

 		 State state = this.saveItem(stateForm, messages, request);
         if (state != null){
             stateForm.setCode(state.getCode());
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
     
     public State saveItem ( StateForm stateForm, ActionMessages messages, HttpServletRequest request)  throws Exception {
         
  		
    	Session sess = null;
  		Transaction tx = null;
  		State state = null;
  		Country country = null;

  		try {
  		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
  		    
  			if (stateForm.getCode() != null && stateForm.getCode().longValue() != 0L) {
  				state = (State) sess.load(State.class, stateForm.getCode());
  			} else {
  				state = new State();
  				stateForm.setCode(null);
  			}
  			
  			if (state.getCountry() == null || (!state.getCountry().getCode().equals(stateForm.getCountryCode()))) {
  				country = (Country) sess.load(Country.class, stateForm.getCountryCode());
 				state.setCountry(country);
 			}
  			
  			PropertyUtils.copyProperties(state, stateForm);
  			
  			//Persist data
  			tx = sess.beginTransaction();
  			sess.saveOrUpdate(state);			
  			tx.commit();
            
            // Audit Transaction
            if (stateForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, state, state.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, state, state.getName(), Globals.AUDIT_UPDATE);
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
  		return state;
 		
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
		StateForm stateForm = (StateForm) form;
		
		Session sess = null;
		Transaction tx = null;
		State state = null;

		try {
		    Long codes[] = stateForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	state = (State) sess.load(State.class, codes[i]);
					sess.delete(state);
                    SysAuditHelper.audit(this, request, state, state.getName(), Globals.AUDIT_DELETE, sess);                    
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.state", this.getLocale(request)), state.getName(), null);
			
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
