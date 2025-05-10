/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.iportal.model.Catalogue;
import com.iportal.model.CatalogueType;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * Operaciones CRUD (creacion, lectura, actualización y eliminación)
 * sobre clase persistente {@link com.iportal.model.Catalogue}.
 * 
 * Para cada operación existe su metodo adecuado
 * <ul>
 * 		<li>Listar catalogos de un tipo: list</li>
 * 		<li>Leer los datos de un catalogo: read</li>
 * 		<li>Guadar un nuevo catalogo o actualizar: save</li>
 * 		<li>Eliminar un catalogo: delete</li>
 * </ul> 
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class CatalogueAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(CatalogueAction.class);

    
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
         
    	 HttpSession session = request.getSession();
     	
     	 CatalogueForm listForm = (CatalogueForm) form;

     	 //solo para tabla de catalogos general para filtrar por tipo
     	 if (session.getAttribute("typeCode") != null) {
     		listForm.setTypeCode((Long)session.getAttribute("typeCode"));
     		session.removeAttribute("typeCode");
     	 }
         
         Session sess = null;
         List results = null;
         
         
         try {

       		StringBuffer sql = new StringBuffer();
       		ArrayList<Object> params = new ArrayList<Object>();
    		ArrayList<NullableType> types = new ArrayList<NullableType>();

    		sql.append("from Catalogue as catalogue ");
    		sql.append("where 1 = 1 ");
    		
    		if (listForm.getTypeCode() != null && listForm.getTypeCode().longValue() > 0 ) {    		    
    			sql.append("and catalogue.type.code = ? ");
    			params.add(listForm.getTypeCode());
    			types.add(Hibernate.LONG);  
    		}

    		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
	   		    String name = "%" + listForm.getName() + "%";
	   			sql.append("and catalogue.name like ? ");
	   		    params.add(name);
	   		    types.add(Hibernate.STRING);  
    		}
    		
    		
    		if ( listForm.getOrderField() != null ) {
        		sql.append("order by catalogue.");
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
   		request.setAttribute("catalogueList", results);

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
         CatalogueForm catalogueForm = (CatalogueForm) form;
         Long typeCode = catalogueForm.getTypeCode();
         catalogueForm.reset(mapping, request);
         catalogueForm.setTypeCode(typeCode);
         
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
 		CatalogueForm catalogueForm = (CatalogueForm) form;
 		
 		Session sess = null;
 		Catalogue catalogue = null;
 		
 		try {
 			sess = getHibernateSession();
 			
 			catalogue = (Catalogue) sess.load(Catalogue.class, catalogueForm.getCode());
 			
 			PropertyUtils.copyProperties(catalogueForm, catalogue);
 			
 			if (catalogue.getType() != null) {
				catalogueForm.setTypeCode(catalogue.getType().getCode());
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
         
    	 HttpSession session = request.getSession();
     	// Gets the action form
 		CatalogueForm catalogueForm = (CatalogueForm) form;
 		
 		session.setAttribute("typeCode", catalogueForm.getTypeCode()); 
         
         if ( isCancelled(request) ) {         	
         	catalogueForm.reset(mapping, request);         	
            return mapping.findForward(Globals.FORWARD_CANCEL);
         }

 		ActionMessages messages = new ActionMessages();

 		 this.saveItem(catalogueForm, messages, request);
 		 
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
         
    	 HttpSession session = request.getSession();
     	// Gets the action form
 		CatalogueForm catalogueForm = (CatalogueForm) form;
         
         if ( isCancelled(request) ) {
         	session.setAttribute("typeCode", catalogueForm.getTypeCode()); 
         	catalogueForm.reset(mapping, request);
         	
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }

 		ActionMessages messages = new ActionMessages();

 		 this.saveItem(catalogueForm, messages, request);
 		 
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
    		 CatalogueForm catalogueForm,
    		 ActionMessages messages,
    		 HttpServletRequest request)
     throws Exception {
         
    	 
  		
  		Session sess = null;
  		Transaction tx = null;
  		Catalogue catalogue = null;
  		CatalogueType type  = null;

  		try {
  		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
  		    
  			if (catalogueForm.getCode() != null && catalogueForm.getCode().longValue() != 0L) {
  				catalogue = (Catalogue) sess.load(Catalogue.class, catalogueForm.getCode());
  			} else {
  				catalogue = new Catalogue();
  				catalogueForm.setCode(null);
  			}
  			
  			if (catalogue.getType() == null || (!catalogue.getType().getCode().equals(catalogueForm.getTypeCode()))) {
				type = (CatalogueType) sess.load(CatalogueType.class, catalogueForm.getTypeCode());
				catalogue.setType(type);
			}
  			
  			PropertyUtils.copyProperties(catalogue, catalogueForm);
  			
  			//Persist data
  			tx = sess.beginTransaction();
  			sess.saveOrUpdate(catalogue);			
  			tx.commit();
  			
  			//Audit Transaction
			if ( catalogueForm.getCode() == null ) {
				SysAuditHelper.audit(this, request, catalogue, catalogue.getName(), Globals.AUDIT_INSERT);				
			} else {
				SysAuditHelper.audit(this, request, catalogue, catalogue.getName(), Globals.AUDIT_UPDATE);
			}
  			
            catalogueForm.setCode(catalogue.getCode());
  			
  		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.category", this.getLocale(request)), catalogue.getName(), null);
			
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
		CatalogueForm catalogueForm = (CatalogueForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Catalogue catalogue = null;

		try {
		    Long codes[] = catalogueForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	catalogue = (Catalogue) sess.load(Catalogue.class, codes[i]);
					sess.delete(catalogue);
					SysAuditHelper.audit(this, request, catalogue, catalogue.getName(), Globals.AUDIT_DELETE, sess);			        
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.category", this.getLocale(request)), catalogue.getName(), null);
			
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
