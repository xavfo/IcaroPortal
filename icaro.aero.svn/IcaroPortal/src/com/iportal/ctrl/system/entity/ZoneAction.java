/*
 * Created Feb 16, 2006
 *	CatalogueAction.java
 */
package com.iportal.ctrl.system.entity;

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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.City;
import com.iportal.model.Zone;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Operaciones CRUD (creacion, lectura, actualizaci�n y eliminaci�n)
 * sobre clase persistente {@link com.iportal.model.Zone}.
 * 
 * Para cada operaci�n existe su metodo adecuado
 * <ul>
 * 		<li>Listar zonas de un tipo: list</li>
 * 		<li>Leer los datos de una zona: read</li>
 * 		<li>Guadar una nueva zona o actualizar: save</li>
 * 		<li>Eliminar una zona: delete</li>
 * </ul> 
 * 
 * @author pajaro
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class ZoneAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(ZoneAction.class);
    
    
    @SuppressWarnings("unchecked")
	public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	ZoneForm listForm = (ZoneForm) form;    	
        
        Session sess = null;
        List results = null;
        Long cityCode = null;

        try {
    		StringBuffer sql = new StringBuffer();
    		ArrayList params = new ArrayList();
    		ArrayList types = new ArrayList();

    		sql.append("from Zone as zone ");
    		sql.append("where 1 = 1 ");
    		
    		sql.append(" and zone.city.code = ? ");
    		cityCode = listForm.getCode().longValue();
    		params.add(cityCode);
    		types.add(Hibernate.LONG);
    		    			
    		    		
    		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
	   		    String name = "%" + listForm.getName() + "%";
	   			sql.append("and zone.name like ? ");
	   		    params.add(name);
	   		    types.add(Hibernate.STRING);  
    		}   		
    		
    		if ( listForm.getOrderField() != null ) {
        		sql.append("order by zone.");
        		sql.append(listForm.getOrderField());
        		if ( listForm.getOrderAsc().booleanValue() ) {
        		    sql.append(" asc ");
        		} else {
        		    sql.append(" desc ");
        		}
    		}
    		
    		if ( listForm.getOrderField() == null ) {
        		sql.append("order by zone.name asc");
    		}
    		
    		sess = getHibernateSession();

    		Object[] arrayParams = params.toArray();
			Query query = sess.createQuery(sql.toString());
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, arrayParams[i], (Type) types.get(i));
			}
			
			listForm.setCityCode(cityCode);
			results = query.list();
			
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
		request.setAttribute("zoneList", results);

		return mapping.findForward(Globals.FORWARD_LIST);
    }

		
    public ActionForward create (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }
        ZoneForm zoneForm = (ZoneForm) form;   
        Long cityCode = zoneForm.getCityCode().longValue();
        
        request.setAttribute("cityCode",cityCode);
        
        //zoneForm.reset(mapping, request);
        //zoneForm.setCityCode(cityCode);
        
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

		// Gets the action form
		ZoneForm zoneForm = (ZoneForm) form;
		
		Session sess = null;
		Zone zone = null;				
		
		try {
			sess = getHibernateSession();
			
			Long applyCode = (Long) request.getAttribute("isApply");
			
			if (applyCode == null)
				applyCode = new Long(0);
			
			if(applyCode.longValue() > 0) { //The catalogue has an Apply action.
				zone = (Zone) sess.load(Zone.class, applyCode);					
				PropertyUtils.copyProperties(zoneForm, zone);
			}else {			
				Long codes[] = zoneForm.getCodes();
				
				for (int i = 0; i < codes.length; i++) {
			    	if(codes[i].longValue() > 0) { //A veces llega un code basura '0' que debe ser ignorado.			
						zone = (Zone) sess.load(Zone.class, codes[i]);					
						PropertyUtils.copyProperties(zoneForm, zone);
			    	}
				}
			}						
			
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
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



    public ActionForward save (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	// Gets the action form
		ZoneForm zoneForm = (ZoneForm) form;
        
        if ( isCancelled(request) ) {
        	//session.setAttribute("typeCode", zoneForm.getTypeCode()); 
        	zoneForm.reset(mapping, request);
        	
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages = new ActionMessages();

		
		Session sess   = null;
		Transaction tx = null;
		Zone zone = null;
		City city = new City();
		//CatalogueType type  = null;

		try {
		    sess = getHibernateSession();
		    
			if (zoneForm.getCode() != null && zoneForm.getCode().longValue() != 0L) {
				zone = (Zone) sess.load(Zone.class, zoneForm.getCode());
			} else {
				zone = new Zone();
				zoneForm.setCode(null);
			}						
			
//			Retrieve the current City			
			city = (City) sess.get(City.class, zoneForm.getCityCode());
			if(!Hibernate.isInitialized(city.getCode()))
				Hibernate.initialize(city);
			
			PropertyUtils.copyProperties(zone, zoneForm);
			
			zone.setCity(city);
			
			//Persist data
	 		tx = sess.beginTransaction();
	 		sess.saveOrUpdate(zone);	 		
	 		tx.commit();	 		 		

		} catch (Exception e) {
			
			if (e instanceof HibernateException) {
			}
			if (tx != null) {
			    tx.rollback();
			}
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.operationFailed", e.getCause()!=null?e.getCause().getMessage():e.getMessage()));
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					tx = null;
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
		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		this.removeForm(mapping, request);
		
		//session.setAttribute("typeCode", catalogueForm.getTypeCode());
		
		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }


    public ActionForward apply (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	// Gets the action form
		ZoneForm zoneForm = (ZoneForm) form;
        
        if ( isCancelled(request) ) {
        	//session.setAttribute("typeCode", catalogueForm.getTypeCode()); 
        	zoneForm.reset(mapping, request);
        	
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages = new ActionMessages();

		
		Session sess   = null;
		Transaction tx = null;
		Zone zone = null;
		

		try {
		    sess = getHibernateSession();
		    
			if (zoneForm.getCode() != null && zoneForm.getCode().longValue() != 0L) {
				zone = (Zone) sess.load(Zone.class, zoneForm.getCode());
			} else {
				zone = new Zone();
				zoneForm.setCode(null);
			}
						
			PropertyUtils.copyProperties(zone, zoneForm);

			//Persist data
	 		tx = sess.beginTransaction();
	 		sess.saveOrUpdate(zone);	 		
	 		tx.commit();	
	 		
	 		//Audit Transaction
			if ( zoneForm.getCode() == null ) {
				SysAuditHelper.audit(this, request, zone, zone.getName(),  Globals.AUDIT_INSERT);				
			} else {
				SysAuditHelper.audit(this, request, zone, zone.getName(), Globals.AUDIT_UPDATE);
			}

		} catch (Exception e) {
			if (tx != null) {
			    tx.rollback();
			}
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.operationFailed", e.getCause()!=null?e.getCause().getMessage():e.getMessage()));
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					tx = null;
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
		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		this.removeForm(mapping, request);
		
		//session.setAttribute("typeCode", catalogueForm.getTypeCode());
		request.setAttribute("isApply",zone.getCode());
		
		return mapping.findForward(Globals.FORWARD_APPLY);
    }

    
    
    public ActionForward delete (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		//HttpSession session = request.getSession();
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		ZoneForm zoneForm = (ZoneForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Zone zone = null;

		try {
		    Long codes[] = zoneForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = getHibernateSession();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	if(codes[i].longValue() > 0) { //A veces llega un code basura '0' que debe ser ignorado.
				    	zone = (Zone) sess.load(Zone.class, codes[i]);
						sess.delete(zone);
						SysAuditHelper.audit(this, request, zone, zone.getName(), Globals.AUDIT_DELETE, sess);
			    	}
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			//This code was copied from MCPartners, verify what does the next line.
			//boolean putMesage = checkControlledError (e, messages, catalogue.getType().getName(), catalogue.getName());
			if (tx != null) {
			    tx.rollback();
			}
			//si no se ha escrito ningun error colocar el error generico
			//if (!putMesage) {
			//	messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			//}
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
