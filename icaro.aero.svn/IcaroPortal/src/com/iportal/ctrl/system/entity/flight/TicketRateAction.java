/**
 * 
 */
package com.iportal.ctrl.system.entity.flight;

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

import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.icaro.fare.PaxType;
import com.iportal.model.icaro.fare.Route;
import com.iportal.model.icaro.fare.TicketRate;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Operaciones CRUD (creacion, lectura, actualización y eliminación)
 * sobre clase persistente {@link com.iportal.model.icaro.fare.TicketRate}.
 * 
 * Para cada operación existe su metodo adecuado
 * <ul>
 * 		<li>Listar tarifas de un tipo: list</li>
 * 		<li>Leer los datos de un tarifas: read</li>
 * 		<li>Guadar nueva tarifas o actualizar: save</li>
 * 		<li>Eliminar ruta: delete</li>
 * </ul>
 *  
 * @author hernan
 * @version 1.0
 *
 */
public class TicketRateAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(TicketRateAction.class);
	
	/**
	 * 
	 */
	public TicketRateAction() {
		super();
	}
	
    /**
     * List todas las tarifas registradas en la ruta actual
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
        
    	TicketRateForm listForm = (TicketRateForm) form;
        RouteForm routeForm = (RouteForm) request.getSession().getAttribute("routeForm");
        if (routeForm != null && (listForm.getRouteCode() == null ||listForm.getRouteCode() < 1L) ) {
        	listForm.setRouteCode(routeForm.getCode());
        }

        Session sess = null;
        List results = null;
        try {
		
        	StringBuffer hql = new StringBuffer();
		 	/*ArrayList<Object> params = new ArrayList<Object>();
		 	ArrayList<NullableType> types = new ArrayList<NullableType>();*/
			
		   	hql.append(" from TicketRate as ticketRate ");
			hql.append(" where ticketRate.route.code = ?  ");
			hql.append(" order by ticketRate.specialFare, ticketRate.paxType.name asc");
			sess = getHibernateSession();
		 	Query query = sess.createQuery(hql.toString());
		 	query.setParameter(0, listForm.getRouteCode(), Hibernate.LONG);
		   	/* no se sis se use o no por eso lo dejo comentado */
		   	/*	
		   	hql.append(" where 1 = 1 ");
		   	if (listForm.getRouteCode() != null && listForm.getRouteCode() > 0L) {
		   		hql.append("and ticketRate.route.code = ? ");
		      	params.add(listForm.getRouteCode());
		      	types.add(Hibernate.LONG);
		   		
		   	}
		   	if (listForm.getPaxTypeCode()!= null && listForm.getPaxTypeCode() > 0L) {
		   		hql.append("and ticketRate.paxType.code = ? ");
		      	params.add(listForm.getPaxTypeCode());
		      	types.add(Hibernate.LONG);
		   	}

			
			if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() > -1) {
				hql.append(" and ticketRate.enabled = ? ");
		  		if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() ) {
		  			params.add( Globals.TRUE );
		  		} else {
		  				params.add( Globals.FALSE );
		  		}
				types.add(Hibernate.BOOLEAN);
			}
		
		
		   	//solo ordenar si ya se esta cargando newsos
		   	if ( listForm.getOrderField() != null ) {
		   		hql.append(" order by ticketRate.");
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
			}*/
				
			query.setCacheable(true);
			results = query.list ();
							
		 		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		         
		} finally {
			if (sess != null) {
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
			}
		}
			// Save the List of results in request scope
     	request.setAttribute("ticketRateList", results);

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
        
        TicketRateForm ticketRateForm = (TicketRateForm) form;

        ticketRateForm.reset(mapping, request);
        
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
		
		
		TicketRateForm ticketRateForm = (TicketRateForm) form;
		
		Session sess = null;
		TicketRate ticketRate = null;
		try {
			sess = getHibernateSession();
			
			ticketRate = (TicketRate) sess.load(TicketRate.class, ticketRateForm.getCode());
			
			PropertyUtils.copyProperties(ticketRateForm, ticketRate);
			ticketRateForm.setApplyTax3Int(ticketRate.getApplyTax3());
			ticketRateForm.setPaxTypeCode(ticketRate.getPaxType()!= null?ticketRate.getPaxType().getCode():null);
			ticketRateForm.setRouteCode(ticketRate.getRoute().getCode());
           
			
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
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
        
        TicketRateForm ticketRateForm = (TicketRateForm) form;

		this.saveItem(request, ticketRateForm, messages);
		 
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
        TicketRateForm ticketRateForm = (TicketRateForm) form;
        

		this.saveItem(request, ticketRateForm, messages);
		 
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
   		 TicketRateForm ticketRateForm ,
   		 ActionMessages messages)
    throws Exception {
        
 		
    	Session sess = null;
 		Transaction tx = null;
 		TicketRate ticketRate = null;
 		
 		try {
 		    sess =  this.getHibernateSession();
 		    
 			if (ticketRateForm.getCode() != null && ticketRateForm.getCode().longValue() > 0L) {
 				ticketRate = (TicketRate) sess.load(TicketRate.class, ticketRateForm.getCode());
 			} else {
 				ticketRate = new TicketRate();
 				
 				ticketRateForm.setCode(null);
 			}
 			
 			//Persist data
 			tx = sess.beginTransaction();
 			  			
 			PropertyUtils.copyProperties(ticketRate, ticketRateForm);
 			if (ticketRateForm.getRouteCode() != null && ticketRateForm.getRouteCode().longValue() > 0L) {
 				Route route = (Route) sess.load(Route.class, ticketRateForm.getRouteCode());
 				ticketRate.setRoute(route);
 			}
 			//siempre debe haber un paxType
 			if (ticketRate.getPaxType() == null || (ticketRateForm.getPaxTypeCode() != null && !ticketRateForm.getPaxTypeCode().equals(ticketRate.getPaxType().getCode()))) {
 				PaxType paxType = (PaxType) sess.load(PaxType.class, ticketRateForm.getPaxTypeCode());
 				ticketRate.setPaxType(paxType);
 			}
 			
 			sess.saveOrUpdate(ticketRate);
 			
 			
 			tx.commit();
           
 			            
           //Audit Transaction
           if ( ticketRateForm.getCode() == null ) {
               SysAuditHelper.audit(this, request, ticketRate, ticketRate.getCode()!= null?ticketRate.getCode().toString():"nuevo rate", Globals.AUDIT_INSERT);                
           } else {
               SysAuditHelper.audit(this, request, ticketRate, ticketRate.getCode()!= null?ticketRate.getCode().toString():"nuevo rate", Globals.AUDIT_UPDATE);
           }
           ticketRateForm.setCode(ticketRate.getCode());
 			
 		} catch (Exception e) {
           
           boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.ticketRate", 
                   this.getLocale(request)), ticketRate.getCode()!= null?ticketRate.getCode().toString():"nuevo rate", null);
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
		TicketRateForm ticketRateForm = (TicketRateForm) form;
		
		Session sess = null;
		Transaction tx = null;
		TicketRate ticketRate = null;
		Integer  jdbcBachtSize = this.getJdbcBatchSize();
		
		try {
		    Long codes[] = ticketRateForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess =  this.getHibernateSession();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	ticketRate = (TicketRate) sess.load(TicketRate.class, codes[i]);
					sess.delete(ticketRate);
					SysAuditHelper.audit(this, request, ticketRate, ticketRate.getCode().toString(), Globals.AUDIT_DELETE, sess);
					
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
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.ticketRate", this.getLocale(request)), ticketRate.getCode().toString(), null);
			
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
