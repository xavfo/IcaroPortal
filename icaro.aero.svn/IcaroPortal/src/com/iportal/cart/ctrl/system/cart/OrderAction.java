/*
 * Created 22/01/2007
 *	OrderAction.java
 */
package com.iportal.cart.ctrl.system.cart;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.cart.model.cart.Order;
import com.iportal.cart.model.cart.OrderStatus;
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
public class OrderAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(OrderAction.class);
	
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
        
    	OrderForm listForm = (OrderForm) form;
        
        Session sess = null;
        List results = null;
        ArrayList <Order>ordenes=new ArrayList<Order>();
        
        CustomerForm customerForm = (CustomerForm) request.getSession().getAttribute("customerForm");
        if (customerForm != null) {
        	listForm.setCustomerCode(customerForm.getCode());
        }

        
        if (listForm.getListItems().booleanValue()) {
       	 try {

         		StringBuffer hql = new StringBuffer();
         		ArrayList<Object> params = new ArrayList<Object>();
         		ArrayList<NullableType> types = new ArrayList<NullableType>();
       		
				
				hql.append("from Order as ord ");
				hql.append("where 1 = 1 ");

        		if (listForm.getCode() != null && listForm.getCode().intValue() > 0) {
        			hql.append(" and ord.code = ? ");
    				params.add(listForm.getCode());
    				types.add(Hibernate.LONG);
    			}
        		
        		if (null != listForm.getRecordLocator()  && !"".equals(listForm.getRecordLocator()) ){
        			hql.append(" and ord.recordLocator = ? ");
    				params.add(listForm.getRecordLocator());
    				types.add(Hibernate.STRING);
    			}
        		
        		if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() > -1) {
	       			hql.append(" and ord.enabled = ? ");
	         			if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
	         				params.add( Globals.TRUE );
	         			else
	         				params.add( Globals.FALSE );
	    				types.add(Hibernate.BOOLEAN);
	    		}

        		if (listForm.getStatusCode() != null && listForm.getStatusCode().longValue() > 0) {
        			hql.append(" and ord.status.code = ? ");
    				params.add(listForm.getStatusCode());
    				types.add(Hibernate.LONG);
    			}
        		
        		if (listForm.getCustomerType() != null && listForm.getCustomerType().longValue() > 0) {
        			hql.append(" and ord.customerType.code = ? ");
    				params.add(listForm.getCustomerType());
    				types.add(Hibernate.LONG);
    			}
        		
        		if (listForm.getCustomerCode() != null && listForm.getCustomerCode().longValue() > 0) {
        			hql.append(" and ord.customer.code = ? ");
    				params.add(listForm.getCustomerCode());
    				types.add(Hibernate.LONG);
    			}

        		if (listForm.getPaymentTypeCode() != null && listForm.getPaymentTypeCode().longValue() > 0) {
        			hql.append(" and ord.payment.type.code = ? ");
    				params.add(listForm.getPaymentTypeCode());
    				types.add(Hibernate.LONG);
    			}

       		
        		if (listForm.getPaymentStatusCode() != null && listForm.getPaymentStatusCode().longValue() > 0) {
        			hql.append(" and ord.payment.status.code = ? ");
    				params.add(listForm.getPaymentStatusCode());
    				types.add(Hibernate.LONG);
    			}

                if (listForm.getSellerCode() != null && listForm.getSellerCode().longValue() > 0) {
                    hql.append(" and ord.details.product.seller.code = ? ");
                    params.add(listForm.getSellerCode());
                    types.add(Hibernate.LONG);
                }

                if (null != listForm.getCustomerName()  && !"".equals(listForm.getCustomerName()) ) {
                    hql.append(" and (ord.customer.firstName like ? ");
                    hql.append("  or  ord.customer.lastName like ?) ");
                    params.add("%" + listForm.getCustomerName() + "%");
                    params.add("%" + listForm.getCustomerName() + "%");
                    types.add(Hibernate.STRING);
                    types.add(Hibernate.STRING);
                }
                
                if (null != listForm.getCustomerReference()  && !"".equals(listForm.getCustomerReference()) ) {
                    hql.append(" and ord.customerReference like ? ");                    
                    params.add("%" + listForm.getCustomerReference() + "%");
                    types.add(Hibernate.STRING);
                }

                if (null != listForm.getCustomerIdentity() && !"".equals(listForm.getCustomerIdentity())) {
                    hql.append(" and ord.customer.identity = ? ");
                    params.add(listForm.getCustomerIdentity());
                    types.add(Hibernate.STRING);
                }

                if (null != listForm.getFrom() ) {
                    hql.append(" and ord.creation >= ? ");
                    
                    params.add(listForm.getFrom());
                    types.add(Hibernate.CALENDAR);
                }
                if ( null != listForm.getTo()) {
                    hql.append(" and ord.creation <= ? ");
                    params.add(listForm.getTo());
                    types.add(Hibernate.CALENDAR);

                }

                //solo ordenar si ya se esta cargando newsos
	       		if ( listForm.getOrderField() != null ) {
	       			hql.append(" order by ord.");
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
				 
	     		Iterator it = results.iterator();
	            while (it.hasNext()) {
	            	Order order = (Order) it.next();
	            	Hibernate.initialize(order.getItineraries());
	            	Hibernate.initialize(order.getPassengers());
	            	ordenes.add(order);    
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
     		request.setAttribute("orderList", ordenes);
        }
        

		return mapping.findForward(Globals.FORWARD_LIST);
    }
    
    public ActionForward load (
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
        Order order = null;
        OrderForm orderForm = (OrderForm) form;

        try {
 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 		    
 		   order = (Order) sess.load(Order.class, orderForm.getCode());
 		   
 		   orderForm.setStatusCode(order.getStatus().getCode());
 		   
 		   Hibernate.initialize(order.getItineraries());
 		   Hibernate.initialize(order.getPassengers());
 			
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
 		
 		request.setAttribute("order",order);
		
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
        
        OrderForm orderForm = (OrderForm) form;

		this.saveItem(request, orderForm, messages);
		 
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
        
        OrderForm orderForm = (OrderForm) form;

		this.saveItem(request, orderForm, messages);
		 
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
   		 OrderForm orderForm,
   		 ActionMessages messages)
    throws Exception {
        
 		
    	Session sess = null;
 		Transaction tx = null;
 		
 		Order order = null;
 		OrderStatus status = null;
 		
 		try {
 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 		    
 			if (orderForm.getCode() != null && orderForm.getCode().longValue() != 0L) {
 				order = (Order) sess.load(Order.class, orderForm.getCode());
 			} else {
 				order = new Order();
 				
 				orderForm.setCode(null);
 			}
 			
 			if (orderForm.getStatusCode() != null && (order.getStatus() == null || !orderForm.getStatusCode().equals(order.getStatus().getCode()))) {
 				status = (OrderStatus) sess.load(OrderStatus.class, orderForm.getStatusCode());
 				order.setStatus(status);
 			}
 			
 			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(order);			
 			tx.commit();
           
 			            
           //Audit Transaction
           if ( orderForm.getCode() == null ) {
               SysAuditHelper.audit(this, request, order, String.valueOf(order.getCode()), Globals.AUDIT_INSERT);                
           } else {
               SysAuditHelper.audit(this, request, order, String.valueOf(order.getCode()), Globals.AUDIT_UPDATE);
           }
           orderForm.setCode(order.getCode());
 			
 		} catch (Exception e) {
           
           boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.paymentStatus", 
                   this.getLocale(request)), String.valueOf(order.getCode()), null);
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
