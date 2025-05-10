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
import com.iportal.model.icaro.fare.Frequency;
import com.iportal.model.icaro.fare.Route;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Operaciones CRUD (creacion, lectura, actualización y eliminación)
 * sobre clase persistente {@link com.iportal.model.icaro.fare.Frequency}.
 * 
 * Para cada operación existe su metodo adecuado
 * <ul>
 * 		<li>Listar frecuencias de un tipo: list</li>
 * 		<li>Leer los datos de un frecuencia: read</li>
 * 		<li>Guadar nueva frecuencia o actualizar: save</li>
 * 		<li>Eliminar frecuencia: delete</li>
 * </ul>
 * 
 * @author hernan
 * @version 1.0
 *
 */
public class FrequencyAction extends BaseDispatchAction {
	
	private static Log logger = LogFactory.getLog(FrequencyAction.class);
	
	/**
	 * 
	 */
	public FrequencyAction() {
		super();
	}
	
    /**
     * List todas las frecuencuas registradas en la ruta actual
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
        
    	FrequencyForm listForm = (FrequencyForm) form;
        
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
			
		   	hql.append(" from Frequency as frequency ");
			hql.append(" where frequency.route.code = ? ");
			hql.append(" order by frequency.name asc");

			sess = getHibernateSession();
		 	Query query = sess.createQuery(hql.toString());
		 	query.setParameter(0, listForm.getRouteCode(), Hibernate.LONG);

		   	/* no se sis se use o no por eso lo dejo comentado */
			/*
		   	/*	
		   	hql.append(" where 1 = 1 ");
			if (listForm.getRouteCode() != null && listForm.getRouteCode() > 0L) {
		   		hql.append("and frequency.route.code = ? ");
		      	params.add(listForm.getRouteCode());
		      	types.add(Hibernate.LONG);
		   		
		   	}
			if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() > -1) {
				hql.append(" and frequency.enabled = ? ");
		  		if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() ) {
		  			params.add( Globals.TRUE );
		  		} else {
		  				params.add( Globals.FALSE );
		  		}
				types.add(Hibernate.BOOLEAN);
			}
    		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
	   		    String name = "%" + listForm.getName() + "%";
	   		    hql.append("and frequency.name like ? ");
	   		    params.add(name);
	   		    types.add(Hibernate.STRING);  
    		}

		
		
		   	//solo ordenar si ya se esta cargando newsos
		   	if ( listForm.getOrderField() != null ) {
		   		hql.append(" order by frequency.");
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
     	request.setAttribute("frequencyList", results);

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
        FrequencyForm frequencyForm = (FrequencyForm) form;

        frequencyForm.reset(mapping, request);
        
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
		FrequencyForm frequencyForm = (FrequencyForm) form;
		
		Session sess = null;
		Frequency frequency = null;
		try {
			sess = getHibernateSession();
			
			frequency = (Frequency) sess.load(Frequency.class, frequencyForm.getCode());
			
			PropertyUtils.copyProperties(frequencyForm, frequency);
			
			frequencyForm.setRouteCode(frequency.getRoute() != null ?frequency.getRoute().getCode():null);
           
			
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
        FrequencyForm frequencyForm = (FrequencyForm) form;

		this.saveItem(request, frequencyForm, messages);
		 
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
        FrequencyForm frequencyForm = (FrequencyForm) form;
        

		this.saveItem(request, frequencyForm, messages);
		 
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
   		 FrequencyForm frequencyForm ,
   		 ActionMessages messages)
    throws Exception {
        
 		
    	Session sess = null;
 		Transaction tx = null;
 		Frequency frequency = null;
 		
 		try {
 		    sess =  this.getHibernateSession();
 		    
 			if (frequencyForm.getCode() != null && frequencyForm.getCode().longValue() > 0L) {
 				frequency = (Frequency) sess.load(Frequency.class, frequencyForm.getCode());
 			} else {
 				frequency = new Frequency();
 				
 				frequencyForm.setCode(null);
 			}
 			
 			//Persist data
 			tx = sess.beginTransaction();
 			  			
 			PropertyUtils.copyProperties(frequency, frequencyForm);
 			if (frequencyForm.getRouteCode() != null && frequencyForm.getRouteCode().longValue() > 0L) {
 				Route route = (Route) sess.load(Route.class, frequencyForm.getRouteCode());
 				frequency.setRoute(route);
 			}
 			
 			sess.saveOrUpdate(frequency);
 			
 			
 			tx.commit();
           
 			            
           //Audit Transaction
           if ( frequencyForm.getCode() == null ) {
               SysAuditHelper.audit(this, request, frequency, frequency.getName(), Globals.AUDIT_INSERT);                
           } else {
               SysAuditHelper.audit(this, request, frequency, frequency.getName(), Globals.AUDIT_UPDATE);
           }
           frequencyForm.setCode(frequency.getCode());
 			
 		} catch (Exception e) {
           
           boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.frequency", 
                   this.getLocale(request)), frequency.getName(), null);
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
		FrequencyForm frequencyForm = (FrequencyForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Frequency frequency = null;
		Integer  jdbcBachtSize = this.getJdbcBatchSize();
		
		try {
		    Long codes[] = frequencyForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess =  this.getHibernateSession();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	frequency = (Frequency) sess.load(Frequency.class, codes[i]);
					sess.delete(frequency);
					SysAuditHelper.audit(this, request, frequency, frequency.getName(), Globals.AUDIT_DELETE, sess);
					
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
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.frequency", this.getLocale(request)), frequency.getName(), null);
			
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
