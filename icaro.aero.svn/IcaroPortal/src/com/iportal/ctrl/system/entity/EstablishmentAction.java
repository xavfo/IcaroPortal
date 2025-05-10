/*
 * Created Jan 15, 2007
 *	EstablishmentAction.java
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.cart.CartConstants;
import com.iportal.model.Catalogue;
import com.iportal.model.City;
import com.iportal.model.icaro.ContactInfo;
import com.iportal.model.icaro.office.Establishment;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * @author ferTamayo
 *
 */
public class EstablishmentAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(EstablishmentAction.class);
	
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
        
        EstablishmentForm listForm = (EstablishmentForm) form;
        
        Session sess = null;
        List results = null;
        
        if (listForm.getListItems().booleanValue()) {
       	 try {

         		StringBuffer hql = new StringBuffer();
         		ArrayList<Object> params = new ArrayList<Object>();
         		ArrayList<NullableType> types = new ArrayList<NullableType>();
       		
		   		hql.append(" from Establishment as establishment ");
				hql.append("where 1 = 1 ");
       		
	       		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
	       			String name = "%" + listForm.getName() + "%";
	          		hql.append("and establishment.name like ? ");
	          		params.add(name);
	          		types.add(Hibernate.STRING);
	       		}
	       		
	       		if (listForm.getCategoryCode() != null && listForm.getCategoryCode().longValue() > 0L ) {
        			hql.append(" and establishment.category.code = ? ");
           		    params.add(listForm.getCategoryCode());
           		    types.add(Hibernate.LONG);  
        		}
	       		
	       		if (listForm.getCityCode() != null && listForm.getCityCode().longValue() > 0L ) {
        			hql.append(" and establishment.city.code = ? ");
           		    params.add(listForm.getCityCode());
           		    types.add(Hibernate.LONG);  
        		}
       		

	       		//solo ordenar si ya se esta cargando newsos
	       		if ( listForm.getOrderField() != null ) {
	       			hql.append(" order by establishment.");
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
     		request.setAttribute("establishmentList", results);
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
        
        EstablishmentForm establishmentForm = (EstablishmentForm) form;
        establishmentForm.reset(mapping, request);
        
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
		
		EstablishmentForm establishmentForm = (EstablishmentForm) form;
		
		Session sess = null;
		Establishment establishment = null;
		
		try {
			sess = getHibernateSession();
			
			establishment = (Establishment) sess.load(Establishment.class, establishmentForm.getCode());
			
			
			PropertyUtils.copyProperties(establishmentForm, establishment);
			establishmentForm.setCityCode(establishment.getCity().getCode());
			establishmentForm.setCategoryCode(establishment.getCategory().getCode());
			if(establishment.getContact()!=null){
				if(establishment.getContact().getContactName()!=null)
				establishmentForm.setContactName(establishment.getContact().getContactName());
				if(establishment.getContact().getContactPosition()!=null)
				establishmentForm.setContactPosition(establishment.getContact().getContactPosition());
				if(establishment.getContact().getLastContact()!=null)
				establishmentForm.setLastContact(establishment.getContact().getLastContact().toString());	
			}
			
           
			
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
        
        EstablishmentForm establishmentForm = (EstablishmentForm) form;

		 this.saveItem(request, establishmentForm, messages);
		 
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
        EstablishmentForm establishmentForm = (EstablishmentForm) form;

		this.saveItem(request, establishmentForm, messages);
		 
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
   		 EstablishmentForm establishmentForm,
   		 ActionMessages messages)
    throws Exception {
        
 		
    	Session sess = null;
 		Transaction tx = null;
 		Establishment establishment = null;
 		City city = new City();
 		Catalogue catalogue=new Catalogue();
 		ContactInfo contactInfo=new ContactInfo();
 		
 		try {
 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 		    
 			if (establishmentForm.getCode() != null && establishmentForm.getCode().longValue() != 0L) {
 				establishment = (Establishment) sess.load(Establishment.class, establishmentForm.getCode());
 			} else {
 				establishment = new Establishment();
 				
 				establishmentForm.setCode(null);
 			}
 			city = (City) sess.get(City.class, establishmentForm.getCityCode());
 			catalogue=(Catalogue) sess.get(Catalogue.class, establishmentForm.getCategoryCode());
 			if(establishmentForm.getContactName()!=null)
 			contactInfo.setContactName(establishmentForm.getContactName());
 			if(establishmentForm.getContactPosition()!=null)
 			contactInfo.setContactPosition(establishmentForm.getContactPosition());
 			if(establishmentForm.getTo()!=null)
 			contactInfo.setLastContact(establishmentForm.getTo().getTime());
 
			if(!Hibernate.isInitialized(city.getCode()))
				Hibernate.initialize(city);
			if(!Hibernate.isInitialized(catalogue.getCode()))
				Hibernate.initialize(catalogue);
 			  			
 			PropertyUtils.copyProperties(establishment, establishmentForm);
 			establishment.setCity(city);
 			establishment.setCategory(catalogue);
 			establishment.setContact(contactInfo);

 			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(establishment);			
 			tx.commit();

 			//Quitar del contexto el listado de categorias del front end
 			this.getServlet().getServletContext().removeAttribute(CartConstants.CART_CATALOG_ALL_BRANDS);

 			            
           //Audit Transaction
           if ( establishmentForm.getCode() == null ) {
               SysAuditHelper.audit(this, request, establishment, establishment.getName(), Globals.AUDIT_INSERT);                
           } else {
               SysAuditHelper.audit(this, request, establishment, establishment.getName(), Globals.AUDIT_UPDATE);
           }
           establishmentForm.setCode(establishment.getCode());
 			
 		} catch (Exception e) {
           
           boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.establishment", 
                   this.getLocale(request)), establishment.getName(), null);
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
		EstablishmentForm establishmentForm = (EstablishmentForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Establishment establishment = null;
		Integer jdbcBachtSize = this.getJdbcBatchSize();
		
		try {
		    Long codes[] = establishmentForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	establishment = (Establishment) sess.load(Establishment.class, codes[i]);
					sess.delete(establishment);
					SysAuditHelper.audit(this, request, establishment, establishment.getName(), Globals.AUDIT_DELETE, sess);			        
					
					//Limpia la session para evitar outOfMemory
					if ( i % jdbcBachtSize  == 0 ) {
						//flush a batch of updates and release memory:
						sess.flush();
						sess.clear();
					}

			    }
				tx.commit();
	 			//Quitar del contexto el listado de categorias del front end
	 			this.getServlet().getServletContext().removeAttribute(CartConstants.CART_CATALOG_ALL_BRANDS);

		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.establishment", this.getLocale(request)), establishment.getName(), null);
			
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
