/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.helper.SysParamHelper;
import com.iportal.cart.CartConstants;
import com.iportal.model.system.SysParam;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;
import com.yage.struts.plugin.InitParametersPlugIn;

/**
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class SysParamAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(SysParamAction.class);

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
         
         SysParamForm listForm = (SysParamForm) form;
         
         Session sess = null;
         List results = null;
         
         if (listForm.getListItems().booleanValue()) {
        	 try {

          		StringBuffer sql = new StringBuffer();
          		ArrayList<Object> params = new ArrayList<Object>();
         		ArrayList<NullableType> types = new ArrayList<NullableType>();

         		sql.append(" from SysParam as sp "); 
         		sql.append(" where 1 = 1 ");
         		
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
      		request.setAttribute("sysParamList", results);
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
         SysParamForm sysParamForm = (SysParamForm) form;
         sysParamForm.reset(mapping, request);
         /*List stateList = getStates(sysParamForm.getCountryCode());        
         request.setAttribute("states", stateList);*/
         
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
 		SysParamForm sysParamForm = (SysParamForm) form;
 		
 		Session sess = null;
 		SysParam sysParam = null;
 		try {
 			sess = getHibernateSession();
 			
 			sysParam = (SysParam) sess.load(SysParam.class, sysParamForm.getCode());
 			
            sysParamForm.setCode( sysParam.getCode() );
            sysParamForm.setName( sysParam.getName() );
            sysParamForm.setValue(sysParam.getValue() );
            sysParamForm.setType(sysParam.getType() );
             			
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
         SysParamForm sysParamForm = (SysParamForm) form;

 		 this.saveItem(sysParamForm, messages, request);
        
        /* List states = getStates(sysParamForm.getCountryCode());
         request.setAttribute("states", states);*/
 		 
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
         SysParamForm sysParamForm = (SysParamForm) form;

 		 SysParam sysParam = this.saveItem(sysParamForm, messages, request);
 		 
 		/* List states = getStates(sysParamForm.getCountryCode());
 		 request.setAttribute("states", states);*/
         
         if (sysParam != null){
             sysParamForm.setCode(sysParam.getCode());
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
     
     public SysParam saveItem (
    		 SysParamForm sysParamForm,
    		 ActionMessages messages, HttpServletRequest request)
     throws Exception {
         
  		
    	Session sess = null;
  		Transaction tx = null;
  		SysParam sysParam = null;

  		try {
  		    sess = getHibernateSession();
  		    
  			if (sysParamForm.getCode() != null && sysParamForm.getCode().longValue() != 0L) {
  				sysParam = (SysParam) sess.load(SysParam.class, sysParamForm.getCode());
  			} else {
  				sysParam = new SysParam();
                sysParamForm.setCode(null);
  			}
  			
  			sysParam.setCode(sysParamForm.getCode());  			
            sysParam.setName(sysParamForm.getName());           
            sysParam.setValue(sysParamForm.getValue());           
            sysParam.setType(sysParamForm.getType());           
  			
  			//Persist data
  			tx = sess.beginTransaction();
  			sess.saveOrUpdate(sysParam);
            Constants constants = new Constants ();
            CartConstants cartConstants = new CartConstants ();
            Properties initParameters = (Properties)request.getSession().getServletContext().getAttribute(InitParametersPlugIn.DEFAULT_ID);
            initParameters.put(sysParam.getName(), SysParamHelper.getObject(sysParam.getValue(), sysParam.getType()));
            BeanUtils.populate(constants, initParameters);
            BeanUtils.populate(cartConstants, initParameters);
            request.getSession().getServletContext().setAttribute(InitParametersPlugIn.DEFAULT_ID, initParameters);
  			tx.commit();
         
            // Audit Transaction
            if ( sysParamForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, sysParam, sysParam.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, sysParam, sysParam.getName(), Globals.AUDIT_UPDATE);
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
  		return sysParam;
        
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
		SysParamForm sysParamForm = (SysParamForm) form;
		
		Session sess = null;
		Transaction tx = null;
		SysParam sysParam = null;

		try {
		    Long codes[] = sysParamForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	sysParam = (SysParam) sess.load(SysParam.class, codes[i]);
					sess.delete(sysParam);
                    SysAuditHelper.audit(this, request, sysParam, sysParam.getName(), Globals.AUDIT_DELETE, sess);
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.sysParam", this.getLocale(request)), sysParam.getName(), null);
			
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
