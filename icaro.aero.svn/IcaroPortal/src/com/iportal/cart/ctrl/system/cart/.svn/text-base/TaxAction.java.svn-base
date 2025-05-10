/*
 * Created 22/01/2007
 *	OrderAction.java
 */

package com.iportal.cart.ctrl.system.cart;

import java.util.ArrayList;
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
import com.iportal.cart.model.tax.TaxRate;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Accion para revisar pedidos puestos en el carrito de compras
 * @author hernan
 * @version 1.0
 *
 */
public class TaxAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(TaxAction.class);
	
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
        
    	TaxForm listForm = (TaxForm) form;
        
        Session sess = null;
        List<TaxRate> results = null;
        
        if (listForm.getListItems().booleanValue()) {
       	 try {

         		StringBuffer hql = new StringBuffer();
         		ArrayList<Object> params = new ArrayList<Object>();
         		ArrayList<NullableType> types = new ArrayList<NullableType>();
       		
				
				hql.append("from TaxRate as tax ");
				hql.append("where 1 = 1 ");

        		if (listForm.getCode() != null && listForm.getCode().longValue() > 0L) {
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
				results = query.list();
                
                for(TaxRate taxRate: (List<TaxRate>)results) {
                       Hibernate.initialize(taxRate.getTax());
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
     		request.setAttribute("taxRateList", results);

            
            // Fix Code issue for tabs...
            listForm.setCode(-1L);
            request.setAttribute("tab", 2);
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
        TaxForm taxForm = (TaxForm) form;
        
        taxForm.reset(mapping, request);
                
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
        TaxRate taxRate = null;
        TaxForm taxForm = (TaxForm) form;

        try {
 		    sess = getHibernateSession(); 
 		    
 		    taxRate = (TaxRate) sess.load(TaxRate.class, taxForm.getCode());
 		   
 		    taxForm.setTaxCode(taxRate.getTax().getCode());
            taxForm.setTaxName(taxRate.getTax().getName());
            taxForm.setRate(taxRate.getRate());
            taxForm.setSince(taxRate.getSince());
            taxForm.setUntil(taxRate.getUntil());
 			
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
 		
 		request.setAttribute("taxRate",taxRate);
		
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
        
        TaxForm taxForm = (TaxForm) form;

		this.saveItem(request, taxForm, messages);
		 
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
//    public ActionForward apply (
//            ActionMapping mapping, 
//            ActionForm form,
//            HttpServletRequest request, 
//            HttpServletResponse response)
//    throws Exception {
//        
//   	 if ( isCancelled(request) ) {
//            return mapping.findForward(Globals.FORWARD_CANCEL);
//        }
//        
//        ActionMessages messages = new ActionMessages();  		
//        
//        //Gets the action form
//        
//        TaxForm taxForm = (TaxForm) form;
//
//		this.saveItem(request, taxForm, messages);
//		 
//		 //Report any messages we have discovered back to the original form
// 		if (!messages.isEmpty()) {
// 			saveMessages(request, messages);
// 			return (mapping.getInputForward());
// 		}
// 		
// 		//Report a success action
//		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
//		saveMessages(request, messages);
//		
//		 return mapping.findForward(Globals.FORWARD_APPLY);
//		 
//    }
    
    protected void saveItem (
   		 HttpServletRequest request,
   		 TaxForm taxForm,
   		 ActionMessages messages)
    throws Exception {
        
 		
    	Session sess = null;
 		Transaction tx = null;
 		
 		TaxRate taxRate = null;
 		
 		try {
 		    sess = getHibernateSession();
 		    
 			if (taxForm.getCode() != null && taxForm.getCode().longValue() != 0L) {
 				taxRate = (TaxRate) sess.load(TaxRate.class, taxForm.getCode());
 			} else {
 				taxRate = new TaxRate();
 				
 				taxForm.setCode(null);
 			}

            // if rate changed and not a new record -> 
            // create new tax with same ID but new end date...
            if( null == taxRate.getRate() || !taxRate.getRate().equals(taxForm.getRate()) ) {
                tx = sess.beginTransaction();
                if(null != taxForm.getCode() && 0L != taxForm.getTaxCode().longValue()) {
                    taxRate.setUntil(taxForm.getSince());
                    sess.update(taxRate);
                    sess.flush();
                    SysAuditHelper.audit(this, request, taxRate, String.valueOf(taxRate.getCode()), Globals.AUDIT_UPDATE);
                    Tax tax = taxRate.getTax();
                    taxRate = new TaxRate();
                    taxRate.setTax(tax);
                } else {
                    taxRate.setTax(new Tax());
                    taxRate.getTax().setName(taxForm.getTaxName());
                    sess.save(taxRate.getTax());
                    SysAuditHelper.audit(this, request, taxRate.getTax(), String.valueOf(taxRate.getTax().getCode()), Globals.AUDIT_INSERT);                
                }
            
                taxRate.setRate(taxForm.getRate());
                taxRate.setSince(taxForm.getSince());
                taxRate.setUntil(TaxForm.MAX_DATE);
            
     			//Persist data
     			sess.save(taxRate);			
     			tx.commit();

                //Audit Transaction
                if ( taxForm.getCode() == null ) {
                    SysAuditHelper.audit(this, request, taxRate, String.valueOf(taxRate.getCode()), Globals.AUDIT_INSERT);                
                } else {
                    SysAuditHelper.audit(this, request, taxRate, String.valueOf(taxRate.getCode()), Globals.AUDIT_UPDATE);
                }
 		   } 
           // only name changed of existing (!) tax     
           if( null != taxRate.getTax() &&
                   !taxRate.getTax().getName().equals(taxForm.getTaxName())) {
                tx = sess.beginTransaction();
                Tax tax = taxRate.getTax();
                tax.setName(taxForm.getTaxName());
                sess.save(tax);         
                tx.commit();
                SysAuditHelper.audit(this, request, tax, String.valueOf(tax.getCode()), Globals.AUDIT_UPDATE);
           }
 			            
           taxForm.setCode(taxRate.getCode());
 			
 		} catch (Exception e) {
           
           boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.paymentStatus", 
                   this.getLocale(request)), String.valueOf(taxRate.getCode()), null);
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
