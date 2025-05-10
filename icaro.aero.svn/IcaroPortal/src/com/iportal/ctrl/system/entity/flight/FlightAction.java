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
import com.iportal.model.icaro.fare.Flight;
import com.iportal.model.icaro.fare.Frequency;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Operaciones CRUD (creacion, lectura, actualización y eliminación)
 * sobre clase persistente {@link com.iportal.model.icaro.fare.Flight}.
 * 
 * Para cada operación existe su metodo adecuado
 * <ul>
 * 		<li>Listar vuelos de un tipo: list</li>
 * 		<li>Leer los datos de un vuelo: read</li>
 * 		<li>Guadar nuevo vuelo o actualizar: save</li>
 * 		<li>Eliminar vuelos: delete</li>
 * </ul>
 * 
 * @author hernan
 * @version 1.0
 *
 */
public class FlightAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(FlightAction.class);
	
	/**
	 * 
	 */
	public FlightAction() {
		super();
	}
	
    /**
     * List todas los vuelos registrados en las frecuncias de la ruta actual
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
        
    	//FlightForm listForm = (FlightForm) form;

    	FrequencyForm frequencyForm = (FrequencyForm) request.getSession().getAttribute("frequencyForm");
    	
        Session sess = null;
        List results = null;
        try {
		
        	StringBuffer hql = new StringBuffer();
		 	/*ArrayList<Object> params = new ArrayList<Object>();
		 	ArrayList<NullableType> types = new ArrayList<NullableType>();*/
			
		   	hql.append(" from Flight as flight ");
		   	hql.append(" where  flight.frequency.code = ?  ");
		   	hql.append(" order by flight.number asc ");
			sess = getHibernateSession();
		 	Query query = sess.createQuery(hql.toString());
				
			query.setParameter(0, frequencyForm.getCode(), Hibernate.LONG);
		   	
		   	/* no se sis se use o no por eso lo dejo comentado */
		   	/*
		   	hql.append(" where 1 = 1 ");
			
		   	if (listForm.getFrequencyCode() != null && listForm.getFrequencyCode() > 0L) {
		   		hql.append("and flight.frequency.code = ? ");
		      	params.add(listForm.getFrequencyCode());
		      	types.add(Hibernate.LONG);
		   		
		   	}
			if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() > -1) {
				hql.append(" and flight.enabled = ? ");
		  		if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() ) {
		  			params.add( Globals.TRUE );
		  		} else {
		  				params.add( Globals.FALSE );
		  		}
				types.add(Hibernate.BOOLEAN);
			}
    		if (listForm.getNumber() != null && listForm.getNumber().length() != 0L ) {
	   		    String number = "%" + listForm.getNumber() + "%";
	   		    hql.append("and flight.number like ? ");
	   		    params.add(number);
	   		    types.add(Hibernate.STRING);  
    		}

		
		
		   	//solo ordenar si ya se esta cargando newsos
		   	if ( listForm.getOrderField() != null ) {
		   		hql.append(" order by flight.");
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
     	request.setAttribute("flightList", results);

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
        
        FlightForm flightForm = (FlightForm) form;

        flightForm.reset(mapping, request);
        
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
		
		FlightForm flightForm = (FlightForm) form;
		
		Session sess = null;
		Flight flight = null;
		try {
			sess = getHibernateSession();
			
			flight = (Flight) sess.load(Flight.class, flightForm.getCode());
			
			PropertyUtils.copyProperties(flightForm, flight);
			Long frequencyCode = (flight.getFrequency()!= null)?flight.getFrequency().getCode():null;
			flightForm.setFrequencyCode(frequencyCode);
           
			
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
        FlightForm flightForm = (FlightForm) form;

		this.saveItem(request, flightForm, messages);
		 
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
        FlightForm flightForm = (FlightForm) form;
        

		this.saveItem(request, flightForm, messages);
		 
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
   		 FlightForm flightForm,
   		 ActionMessages messages)
    throws Exception {
        
 		
    	Session sess = null;
 		Transaction tx = null;
 		Flight flight = null;
 		
 		try {
 		    sess =  this.getHibernateSession();
 		    
 			if (flightForm.getCode() != null && flightForm.getCode().longValue() > 0L) {
 				flight = (Flight) sess.load(Flight.class, flightForm.getCode());
 			} else {
 				flight = new Flight();
 				flightForm.setCode(null);
 			}
 			
 			//Persist data
 			tx = sess.beginTransaction();
 			  			
 			PropertyUtils.copyProperties(flight, flightForm);
 			if (flight.getFrequency() == null || (flightForm.getFrequencyCode() != null && !flightForm.getFrequencyCode().equals(flight.getFrequency().getCode()))) {
 				Frequency frequency = (Frequency) sess.load(Frequency.class, flightForm.getFrequencyCode());
 				flight.setFrequency(frequency);
 			}
 			
 			sess.saveOrUpdate(flight);
 			
 			
 			tx.commit();
           
 			            
           //Audit Transaction
           if ( flightForm.getCode() == null ) {
               SysAuditHelper.audit(this, request, flight, flight.getNumber(), Globals.AUDIT_INSERT);                
           } else {
               SysAuditHelper.audit(this, request, flight, flight.getNumber(), Globals.AUDIT_UPDATE);
           }
           flightForm.setCode(flight.getCode());
 			
 		} catch (Exception e) {
           
           boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.flight", 
                   this.getLocale(request)), flight.getNumber(), null);
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
		FlightForm flightForm = (FlightForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Flight flight = null;
		Integer  jdbcBachtSize = this.getJdbcBatchSize();
		
		try {
		    Long codes[] = flightForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess =  this.getHibernateSession();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	flight = (Flight) sess.load(Flight.class, codes[i]);
					sess.delete(flight);
					SysAuditHelper.audit(this, request, flight, flight.getNumber(), Globals.AUDIT_DELETE, sess);
					
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
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.flight", this.getLocale(request)), flight.getNumber(), null);
			
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
