/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

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
import com.iportal.model.ImageCategory;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * 
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class ImageCategoryAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(ImageCategoryAction.class);

    
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
         
         ImageCategoryForm listForm = (ImageCategoryForm) form;
         
         Session sess = null;
         List results = null;
         
         try {

       		StringBuffer sql = new StringBuffer();
       		ArrayList<Object> params = new ArrayList<Object>();
      		ArrayList<NullableType> types = new ArrayList<NullableType>();

      		sql.append("from ImageCategory as imageCategory "); 
      		sql.append(" where 1 = 1 ");
      		      		
      		if (listForm.getParentCode() != null && listForm.getParentCode().longValue() > 0) {
 				sql.append("and imageCategory.parent.code = ? ");
 				params.add(listForm.getParentCode());
 				types.add(Hibernate.LONG);
 			}
      		
      		if (listForm.getName() != null && listForm.getName().length() > 0) {
      			String name = "%" + listForm.getName() + "%";				
 				sql.append("and imageCategory.name like ? ");
 				params.add(name);
 				types.add(Hibernate.STRING);
 			}      		
      		
      		if ( listForm.getOrderField() != null ) {
          		sql.append("order by imageCategory.");
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
   		request.setAttribute("imageCategoryList", results);
         

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
         ImageCategoryForm imageCategoryForm = (ImageCategoryForm) form;
         imageCategoryForm.reset(mapping, request);
         
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
 		ImageCategoryForm imageCategoryForm = (ImageCategoryForm) form;
 		
 		Session sess = null;
 		ImageCategory imageCategory = null;
 		
 		try {
 			sess = getHibernateSession();
 			
 			imageCategory = (ImageCategory) sess.load(ImageCategory.class, imageCategoryForm.getCode());
 			
 			PropertyUtils.copyProperties(imageCategoryForm, imageCategory);
 			if (imageCategory.getParent() != null)
 				imageCategoryForm.setParentCode( imageCategory.getParent().getCode() );
 			
 			
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
         ImageCategoryForm imageCategoryForm = (ImageCategoryForm) form;

 		 this.saveItem(imageCategoryForm, messages, request);
 		 
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
         ImageCategoryForm imageCategoryForm = (ImageCategoryForm) form;

 		 this.saveItem(imageCategoryForm, messages, request);
 		 
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
    		 ImageCategoryForm imageCategoryForm,
    		 ActionMessages messages, HttpServletRequest request)
     throws Exception {
         
  		
    	Session sess = null;
  		Transaction tx = null;
  		ImageCategory imageCategory = null;
  		ImageCategory parent = null;

  		try {
  		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
  		    
  			if (imageCategoryForm.getCode() != null && imageCategoryForm.getCode().longValue() != 0L) {
  				imageCategory = (ImageCategory) sess.load(ImageCategory.class, imageCategoryForm.getCode());
  			} else {
  				imageCategory = new ImageCategory();
  				imageCategoryForm.setCode(null);
  			}
  			
  			if ( imageCategoryForm.getParentCode() != null && imageCategoryForm.getParentCode().longValue() > 0L ) {
  				if (imageCategory.getParent() == null || !imageCategory.getParent().getCode().equals(imageCategoryForm.getParentCode()) ) {
  	  				parent = (ImageCategory) sess.load(ImageCategory.class, imageCategoryForm.getParentCode()); 				
  	 			}
  			}
  			
  			PropertyUtils.copyProperties(imageCategory, imageCategoryForm);
  			imageCategory.setParent(parent);  			
  			
  			//Persist data
  			tx = sess.beginTransaction();
  			sess.saveOrUpdate(imageCategory);			
  			tx.commit();
            
            // Audit Transaction
            if (imageCategoryForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, imageCategory, imageCategory.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, imageCategory, imageCategory.getName(), Globals.AUDIT_UPDATE);
            }
  			
  		} catch (Exception e) {
  			if (tx != null) {
  			    tx.rollback();
  			}
  			
  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
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
		ImageCategoryForm imageCategoryForm = (ImageCategoryForm) form;
		
		Session sess = null;
		Transaction tx = null;
		ImageCategory imageCategory = null;

		try {
		    Long codes[] = imageCategoryForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	imageCategory = (ImageCategory) sess.load(ImageCategory.class, codes[i]);
					sess.delete(imageCategory);
                    SysAuditHelper.audit(this, request, imageCategory, imageCategory.getName(), Globals.AUDIT_DELETE, sess);
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.imageCategory", this.getLocale(request)), imageCategory.getName(), null);
			
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
