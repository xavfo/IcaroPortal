/*
 * Created Jan 15, 2007
 *	OfficeAction.java
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
import com.iportal.model.City;
import com.iportal.model.icaro.office.Office;
import com.iportal.model.icaro.office.OfficeType;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * @author ferTamayo
 *
 */
public class OfficeAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(OfficeAction.class);
	
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
        
        OfficeForm listForm = (OfficeForm) form;
        
        Session sess = null;
        List results = null;
        
        if (listForm.getListItems().booleanValue()) {
       	 try {

         		StringBuffer hql = new StringBuffer();
         		ArrayList<Object> params = new ArrayList<Object>();
         		ArrayList<NullableType> types = new ArrayList<NullableType>();
       		
		   		hql.append(" from Office as office ");
				hql.append("where 1 = 1 ");
       		
	       		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
	       			String name = "%" + listForm.getName() + "%";
	          		hql.append("and office.name like ? ");
	          		params.add(name);
	          		types.add(Hibernate.STRING);
	       		}
	       		
	       		if (listForm.getTypeCode() != null && listForm.getTypeCode().longValue() > 0L ) {
        			hql.append(" and office.type.code = ? ");
           		    params.add(listForm.getTypeCode());
           		    types.add(Hibernate.LONG);  
        		}
	       		
	       		if (listForm.getCityCode() != null && listForm.getCityCode().longValue() > 0L ) {
        			hql.append(" and office.city.code = ? ");
           		    params.add(listForm.getCityCode());
           		    types.add(Hibernate.LONG);  
        		}
       		

	       		//solo ordenar si ya se esta cargando newsos
	       		if ( listForm.getOrderField() != null ) {
	       			hql.append(" order by office.");
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
     		request.setAttribute("officeList", results);
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
        
        OfficeForm officeForm = (OfficeForm) form;
        officeForm.reset(mapping, request);
        
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
		
		OfficeForm officeForm = (OfficeForm) form;
		
		Session sess = null;
		Office office = null;
		
		try {
			sess = getHibernateSession();
			
			office = (Office) sess.load(Office.class, officeForm.getCode());
			
			
			PropertyUtils.copyProperties(officeForm, office);
			officeForm.setCityCode(office.getCity().getCode());
			officeForm.setTypeCode(office.getType().getCode());
			/*if(office.getContact()!=null){
				if(office.getContact().getContactName()!=null)
				officeForm.setContactName(office.getContact().getContactName());
				if(office.getContact().getContactPosition()!=null)
				officeForm.setContactPosition(office.getContact().getContactPosition());
				if(office.getContact().getLastContact()!=null)
				officeForm.setLastContact(office.getContact().getLastContact().toString());	
			}*/
			
           
			
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
        
        OfficeForm officeForm = (OfficeForm) form;

		 this.saveItem(request, officeForm, messages);
		 
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
        OfficeForm officeForm = (OfficeForm) form;

		this.saveItem(request, officeForm, messages);
		 
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
   		 OfficeForm officeForm,
   		 ActionMessages messages)
    throws Exception {
        
 		
    	Session sess = null;
 		Transaction tx = null;
 		Office office = null;
 		City city = new City();
 		OfficeType officeType=new OfficeType();
 		
 		try {
 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 		    
 			if (officeForm.getCode() != null && officeForm.getCode().longValue() != 0L) {
 				office = (Office) sess.load(Office.class, officeForm.getCode());
 			} else {
 				office = new Office();
 				
 				officeForm.setCode(null);
 			}
 			city = (City) sess.get(City.class, officeForm.getCityCode());
 			officeType=(OfficeType) sess.get(OfficeType.class, officeForm.getTypeCode());
 			/*if(officeForm.getContactName()!=null)
 			contactInfo.setContactName(officeForm.getContactName());
 			if(officeForm.getContactPosition()!=null)
 			contactInfo.setContactPosition(officeForm.getContactPosition());
 			if(officeForm.getTo()!=null)
 			contactInfo.setLastContact(officeForm.getTo().getTime());*/
 
			if(!Hibernate.isInitialized(city.getCode()))
				Hibernate.initialize(city);
			if(!Hibernate.isInitialized(officeType.getCode()))
				Hibernate.initialize(officeType);
 			  			
 			PropertyUtils.copyProperties(office, officeForm);
 			office.setCity(city);
 			office.setType(officeType);

 			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(office);			
 			tx.commit();

 			//Quitar del contexto el listado de categorias del front end
 			this.getServlet().getServletContext().removeAttribute(CartConstants.CART_CATALOG_ALL_BRANDS);

 			            
           //Audit Transaction
           if ( officeForm.getCode() == null ) {
               SysAuditHelper.audit(this, request, office, office.getName(), Globals.AUDIT_INSERT);                
           } else {
               SysAuditHelper.audit(this, request, office, office.getName(), Globals.AUDIT_UPDATE);
           }
           officeForm.setCode(office.getCode());
 			
 		} catch (Exception e) {
           
           boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.office", 
                   this.getLocale(request)), office.getName(), null);
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
		OfficeForm officeForm = (OfficeForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Office office = null;
		Integer jdbcBachtSize = this.getJdbcBatchSize();
		
		try {
		    Long codes[] = officeForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	office = (Office) sess.load(Office.class, codes[i]);
					sess.delete(office);
					SysAuditHelper.audit(this, request, office, office.getName(), Globals.AUDIT_DELETE, sess);			        
					
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
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.brand", this.getLocale(request)), office.getName(), null);
			
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
