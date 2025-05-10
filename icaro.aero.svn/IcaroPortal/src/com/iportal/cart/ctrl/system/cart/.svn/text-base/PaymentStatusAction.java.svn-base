/*
 * Created Jan 15, 2007
 *	PaymentStatusAction.java
 */
package com.iportal.cart.ctrl.system.cart;

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
import com.iportal.cart.model.cart.PaymentStatus;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * @author hernan
 *
 */
public class PaymentStatusAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(PaymentStatusAction.class);
	
	
    /**
     * List todos los proveedores registrados en el portal.
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
        
    	PaymentStatusForm listForm = (PaymentStatusForm) form;
        
        Session sess = null;
        List results = null;
        
        if (listForm.getListItems().booleanValue()) {
       	 try {

         		StringBuffer hql = new StringBuffer();
         		ArrayList<Object> params = new ArrayList<Object>();
         		ArrayList<NullableType> types = new ArrayList<NullableType>();
       		
		   		hql.append(" from PaymentStatus as status ");
				hql.append("where 1 = 1 ");
       		
	       		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
	       			String name = "%" + listForm.getName() + "%";
	          		hql.append("and status.name like ? ");
	          		params.add(name);
	          		types.add(Hibernate.STRING);
	       		}
       		
	       		if (listForm.getExternalCode() != null && listForm.getExternalCode().length() != 0L ) {
	       			String external =  listForm.getExternalCode() + "%";
	          		hql.append("and status.externalCode like ? ");
	          		params.add(external);
	          		types.add(Hibernate.STRING);
	       		}


	       		//solo ordenar si ya se esta cargando newsos
	       		if ( listForm.getOrderField() != null ) {
	       			hql.append(" order by status.");
	       			hql.append(listForm.getOrderField());
	       			if ( listForm.getOrderAsc().booleanValue() ) {
	       				hql.append(" asc ");
	       			} else {
	       				hql.append(" desc ");
	       			}
	       		}
        		
        		sess = getHibernateSession();
	     		Query query = sess.createQuery(hql.toString());
				
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
     		request.setAttribute("paymentStatusList", results);
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
        
        PaymentStatusForm paymentStatusForm = (PaymentStatusForm) form;

        paymentStatusForm.reset(mapping, request);
        
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
		
		PaymentStatusForm paymentStatusForm = (PaymentStatusForm) form;
		
		Session sess = null;
		PaymentStatus paymentStatus = null;
		
		try {
			sess = getHibernateSession();
			
			paymentStatus = (PaymentStatus) sess.load(PaymentStatus.class, paymentStatusForm.getCode());
			
			PropertyUtils.copyProperties(paymentStatusForm, paymentStatus);
			  
           
			
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
        
        PaymentStatusForm paymentStatusForm = (PaymentStatusForm) form;

		this.saveItem(request, paymentStatusForm, messages);
		 
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
        
        PaymentStatusForm paymentStatusForm = (PaymentStatusForm) form;

		this.saveItem(request, paymentStatusForm, messages);
		 
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
    
    protected void saveItem (
   		 HttpServletRequest request,
   		 PaymentStatusForm paymentStatusForm,
   		 ActionMessages messages)
    throws Exception {
        
 		
    	Session sess = null;
 		Transaction tx = null;
 		
 		PaymentStatus paymentStatus = null;
 		
 		try {
 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 		    
 			if (paymentStatusForm.getCode() != null && paymentStatusForm.getCode().longValue() != 0L) {
 				paymentStatus = (PaymentStatus) sess.load(PaymentStatus.class, paymentStatusForm.getCode());
 			} else {
 				paymentStatus = new PaymentStatus();
 				
 				paymentStatusForm.setCode(null);
 			}
 			
 			  			
 			PropertyUtils.copyProperties(paymentStatus, paymentStatusForm);
	

 			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(paymentStatus);			
 			tx.commit();
           
 			            
           //Audit Transaction
           if ( paymentStatusForm.getCode() == null ) {
               SysAuditHelper.audit(this, request, paymentStatus, paymentStatus.getName(), Globals.AUDIT_INSERT);                
           } else {
               SysAuditHelper.audit(this, request, paymentStatus, paymentStatus.getName(), Globals.AUDIT_UPDATE);
           }
           paymentStatusForm.setCode(paymentStatus.getCode());
 			
 		} catch (Exception e) {
           
           boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.paymentStatus", 
                   this.getLocale(request)), paymentStatus.getName(), null);
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
		PaymentStatusForm paymentStatusForm = (PaymentStatusForm) form;
		
		Session sess = null;
		Transaction tx = null;
		PaymentStatus paymentStatus  = null;
		Integer  jdbcBachtSize = this.getJdbcBatchSize();
		
		try {
		    Long codes[] = paymentStatusForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	paymentStatus = (PaymentStatus) sess.load(PaymentStatus.class, codes[i]);
					sess.delete(paymentStatus);
					//Audita transaccion
					SysAuditHelper.audit(this, request, paymentStatus, paymentStatus.getName(), Globals.AUDIT_DELETE, sess);
					//Limpia la session para evitar outOfMemory
					if ( i % jdbcBachtSize  == 0 ) {
						//flush a batch of updates and release memory:
						sess.flush();
						sess.clear();
					}

			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.paymentStatus", this.getLocale(request)), paymentStatus.getName(), null);
			
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
