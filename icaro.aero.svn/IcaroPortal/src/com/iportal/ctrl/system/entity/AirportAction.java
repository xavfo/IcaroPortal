/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
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

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.cart.CartConstants;
import com.iportal.model.City;
import com.iportal.model.icaro.Airport;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Operaciones CRUD (creacion, lectura, actualizaci�n y eliminaci�n)
 * sobre clase persistente {@link com.iportal.model.icaro.Airport}.
 * 
 * Para cada operaci�n existe su metodo adecuado
 * <ul>
 * 		<li>Listar aeropuertos de un tipo: list</li>
 * 		<li>Leer los datos de un aeropuerto: read</li>
 * 		<li>Guadar un nuevo aeropuerto o actualizar: save</li>
 * 		<li>Eliminar un aeropuerto: delete</li>
 * </ul> 
 * 
 * @author  ferTamayo
 * @version 1.0
 *
 */
public class AirportAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(AirportAction.class);

    
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
         
    	 
     	
     	 AirportForm listForm = (AirportForm) form;

     	 Session sess = null;
         List results = null;
         
         
         
         try {

       		StringBuffer sql = new StringBuffer();
       		ArrayList<Object> params = new ArrayList<Object>();
    		ArrayList<NullableType> types = new ArrayList<NullableType>();

    		sql.append("from Airport as airport ");
    		sql.append("where 1 = 1 ");
    		
    		
    		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
	   		    String name = "%" + listForm.getName() + "%";
	   			sql.append("and airport.name like ? ");
	   		    params.add(name);
	   		    types.add(Hibernate.STRING);  
    		}
    		
    		if (listForm.getCityCode() != null && listForm.getCityCode().longValue() > 0L ) {
    			sql.append(" and airport.city.code = ? ");
       		    params.add(listForm.getCityCode());
       		    types.add(Hibernate.LONG);  
    		}
    		
    		if (listForm.getIataCode() != null && listForm.getIataCode().length() != 0L ) {
	   		    String name = "%" + listForm.getIataCode() + "%";
	   			sql.append("and airport.iataCode like ? ");
	   		    params.add(name);
	   		    types.add(Hibernate.STRING);  
    		}
    		
    		if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() > -1) {
    			sql.append(" and airport.enabled = ? ");
      			if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
      				params.add( Globals.TRUE );
      			else
      				params.add( Globals.FALSE );
 				types.add(Hibernate.BOOLEAN);
 			}
    		
    		if ( listForm.getOrderField() != null ) {
        		sql.append("order by airport.");
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
   		request.setAttribute("airportList", results);

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
         AirportForm airportForm = (AirportForm) form;
         //Long typeCode = airportForm.getTypeCode();
         airportForm.reset(mapping, request);
         //airportForm.setTypeCode(typeCode);
         
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
 		AirportForm airportForm = (AirportForm) form;
 		
 		Session sess = null;
 		Airport airport = null;
 		
 		try {
 			sess = getHibernateSession();
 			
 			airport = (Airport) sess.load(Airport.class, airportForm.getCode());
 			
 			PropertyUtils.copyProperties(airportForm, airport);
 			airportForm.setCityCode(airport.getCity().getCode());
 			
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
         
    	
     	// Gets the action form
 		AirportForm airportForm = (AirportForm) form;
 		
 		
 		//session.setAttribute("typeCode", airportForm.getTypeCode()); 
         
         if ( isCancelled(request) ) {         	
         	airportForm.reset(mapping, request);         	
            return mapping.findForward(Globals.FORWARD_CANCEL);
         }

 		ActionMessages messages = new ActionMessages();

 		 this.saveItem(airportForm, messages, request);
 		 
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
         

     	// Gets the action form
 		AirportForm airportForm = (AirportForm) form;
         
         if ( isCancelled(request) ) {
         //	session.setAttribute("typeCode", airportForm.getTypeCode()); 
         	airportForm.reset(mapping, request);
         	
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }

 		ActionMessages messages = new ActionMessages();

 		 this.saveItem(airportForm, messages, request);
 		 
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
     
     public void saveItem (
    		 AirportForm airportForm,
    		 ActionMessages messages,
    		 HttpServletRequest request)
     throws Exception {
         
    	 
  		
  		Session sess = null;
  		Transaction tx = null;
  		Airport airport = null;
  		City city = new City();
  		//CatalogueType type  = null;

  		try {
  		    sess = this.getHibernateSession();
  		    
  			if (airportForm.getCode() != null && airportForm.getCode().longValue() != 0L) {
  				airport = (Airport) sess.load(Airport.class, airportForm.getCode());
  			} else {
  				airport = new Airport();
  				airportForm.setCode(null);
  			}
  			
/*  			if (catalogue.getType() == null || (!catalogue.getType().getCode().equals(airportForm.getTypeCode()))) {
				type = (CatalogueType) sess.load(CatalogueType.class, airportForm.getTypeCode());
				catalogue.setType(type);
			}*/
  			
//			Retrieve the current City			
			city = (City) sess.get(City.class, airportForm.getCityCode());
			if(!Hibernate.isInitialized(city.getCode()))
				Hibernate.initialize(city);
			
			PropertyUtils.copyProperties(airport, airportForm);
			airport.setCity(city);
  			//Persist data
  			tx = sess.beginTransaction();
  			sess.saveOrUpdate(airport);			
  			tx.commit();
  			
            //Quitar del contexto el atributo de aeropuertos
            ServletContext context = this.getServlet().getServletContext();
            context.removeAttribute(CartConstants.AIRPORT_HASH_KEY);

  			//Audit Transaction
			if ( airportForm.getCode() == null ) {
				SysAuditHelper.audit(this, request, airport, airport.getName(), Globals.AUDIT_INSERT);				
			} else {
				SysAuditHelper.audit(this, request, airport, airport.getName(), Globals.AUDIT_UPDATE);
			}
  			
            airportForm.setCode(airport.getCode());
            
  			
  		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.airport", this.getLocale(request)), airport.getName(), null);
			
			if (tx != null) {
			    tx.rollback();
			}
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			}
  			
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
		AirportForm airportForm = (AirportForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Airport airport = null;

		try {
		    Long codes[] = airportForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = this.getHibernateSession();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	airport = (Airport) sess.load(Airport.class, codes[i]);
					sess.delete(airport);
					SysAuditHelper.audit(this, request, airport, airport.getName(), Globals.AUDIT_DELETE, sess);			        
			    }
				tx.commit();
		    } 
			
            //Quitar del contexto el atributo de aeropuertos
            ServletContext context = this.getServlet().getServletContext();
            context.removeAttribute(CartConstants.AIRPORT_HASH_KEY);

		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.airport", this.getLocale(request)), airport.getName(), null);
			
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
