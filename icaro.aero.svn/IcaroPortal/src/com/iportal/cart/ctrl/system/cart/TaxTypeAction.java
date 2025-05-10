/*
 * Created 22/01/2007
 *	OrderAction.java
 */
package com.iportal.cart.ctrl.system.cart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.iportal.cart.model.tax.Tax;
import com.iportal.cart.model.tax.TaxType;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * Accion para revisar pedidos puestos en el carrito de compras
 * @author hernan
 * @version 1.0
 *
 */
public class TaxTypeAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(TaxTypeAction.class);
	
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
    @SuppressWarnings("unchecked")
	public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	TaxTypeForm listForm = (TaxTypeForm) form;
        
        Session sess = null;
        List results = null;
        
        if (listForm.getListItems().booleanValue()) {
       	 try {

         		StringBuffer hql = new StringBuffer();
         		ArrayList<Object> params = new ArrayList<Object>();
         		ArrayList<NullableType> types = new ArrayList<NullableType>();
       		
				
				hql.append("from TaxType as tax ");
				hql.append("where 1 = 1 ");

        		if (listForm.getCode() != null && listForm.getCode().intValue() > 0) {
        			hql.append(" and tax.code = ? ");
    				params.add(listForm.getCode());
    				types.add(Hibernate.LONG);
    			}
        		
        		sess = getHibernateSession();
	     		Query query = sess.createQuery(hql.toString());
				
				for (int i = 0; i < types.size(); i++) {
					query.setParameter(i, params.get(i), types.get(i));
				}
				
				query.setCacheable(true);
				results = query.list ();
				
				for(TaxType taxType: (List<TaxType>)results) {
			 		   Hibernate.initialize(taxType.getTaxes());
				}
								
         		
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
     		request.setAttribute("taxTypeList", results);
     		
     		// Fix Code issue for tabs...
     		listForm.setCode(-1L);
     		request.setAttribute("tab", 1);
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
        TaxTypeForm taxTypeForm = (TaxTypeForm) form;
        
        taxTypeForm.reset(mapping, request);
        
        return mapping.findForward(Globals.FORWARD_FORM);
    }

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
	        
	        //Gets the action form
	        
	        Session sess = null;
	        TaxType taxType = null;
	        TaxTypeForm taxTypeForm = (TaxTypeForm) form;
	
	        try {
	 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
	 		    
	 		   taxType = (TaxType) sess.load(TaxType.class, taxTypeForm.getCode());
//	 		   Hibernate.initialize(taxType.getTaxes());	 		   
  
 		       taxTypeForm.setName(taxType.getName());
 		       
 		       ArrayList<String> taxCodes = new ArrayList<String>();
               String[] strTaxCodes = new String[] {};
 		       for(Tax tax: taxType.getTaxes()) {
 		    	   taxCodes.add(tax.getCode().toString());
 		       }
 		       taxTypeForm.setTaxCodes(taxCodes.toArray(strTaxCodes));
	 		   
	 			
	 		} catch (Exception e) {
	           
	           messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
	           
	           logger.error(e.getMessage(), e);
	 		} finally {
	 			if (sess != null)
	 				try {
	 					sess.close();
	 				} catch (Exception e) {
	 				}
	 		}
	 		
	 		request.setAttribute("taxType",taxType);
			
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
        
        TaxTypeForm taxTypeForm = (TaxTypeForm) form;

		this.saveItem(request, taxTypeForm, messages);
		 
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
        
        TaxTypeForm taxTypeForm = (TaxTypeForm) form;

		this.saveItem(request, taxTypeForm, messages);
		 
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
   		 TaxTypeForm taxTypeForm,
   		 ActionMessages messages)
    throws Exception {
        
 		
    	Session sess = null;
 		Transaction tx = null;
 		
 		TaxType taxType = null;
 		Tax tax = null;
 		
 		try {
 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 		    
 			if (taxTypeForm.getCode() != null && taxTypeForm.getCode().longValue() != 0L) {
 				taxType = (TaxType) sess.load(TaxType.class, taxTypeForm.getCode());
 			} else {
 				taxType = new TaxType();
 				
 				taxTypeForm.setCode(null);
 			}

            taxType.setName(taxTypeForm.getName());
 			
            if (taxType.getTaxes() != null) {
                taxType.getTaxes().clear();
            }
 			if (taxTypeForm.getTaxCodes() != null ) {
 				String[] taxCodes = taxTypeForm.getTaxCodes();  
 				for(int i=0; i < taxCodes.length; ++i ) {
 	 				tax = (Tax) sess.load(Tax.class, Long.valueOf(taxCodes[i]));
                    if (null == taxType.getTaxes()) {
                        taxType.setTaxes(new HashSet<Tax>()); 
                    }
                    taxType.getTaxes().add(tax);
 				}
 			}
 			
 			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(taxType);			
 			tx.commit();
           
 			            
           //Audit Transaction
           if ( taxTypeForm.getCode() == null ) {
               SysAuditHelper.audit(this, request, taxType, String.valueOf(taxType.getCode()), Globals.AUDIT_INSERT);                
           } else {
               SysAuditHelper.audit(this, request, taxType, String.valueOf(taxType.getCode()), Globals.AUDIT_UPDATE);
           }
           taxTypeForm.setCode(taxType.getCode());
 			
 		} catch (Exception e) {
           
           boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.paymentStatus", 
                   this.getLocale(request)), String.valueOf(taxType.getCode()), null);
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

}
