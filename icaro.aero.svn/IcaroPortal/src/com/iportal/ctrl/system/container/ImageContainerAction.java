/*
 * Created Feb 16, 2006
 *	ImageAction.java
 */
package com.iportal.ctrl.system.container;

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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.RowItem;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.facade.ImageContainerFacade;
import com.iportal.model.Image;
import com.yage.Globals;
import com.yage.file.FileManagerUtils;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.action.UploadMediaForm;

/** 
 * @author YAGE(hernan)
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class ImageContainerAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(ImageContainerAction.class);
    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	ActionMessages messages = new ActionMessages();
    	UploadMediaForm imageForm =(UploadMediaForm)form;
    	
    	try {
    		ImageContainerFacade facade = new ImageContainerFacade();
    		    		
    		if(imageForm.getReset().booleanValue()) {
    			imageForm.reset(mapping, request);
    		}    		
    		    		
	    	List results = facade.list(imageForm);     
			
	    	// Save the List of results in request scope
			request.setAttribute("imageList", results);
    	} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.error(e.getMessage(), e);
		} 		
    	//this.removeForm(mapping, request);
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
        UploadMediaForm imageContainerForm = (UploadMediaForm) form;
        
        imageContainerForm.reset(mapping, request);
        
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
		UploadMediaForm imageForm = (UploadMediaForm)form;
		
		Session sess = null;
		Image image = null;
		
		try {
			sess = getHibernateSession();
			
			image = (Image) sess.load(Image.class, imageForm.getCode());
			
			PropertyUtils.copyProperties(imageForm, image);
			
			imageForm.setUploadPath(image.getPath());
			imageForm.setCategoryCode(image.getImageCategory().getCode());		
			
			
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

    public ActionForward save (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	UploadMediaForm imageForm = (UploadMediaForm)form;
    	
    	if ( isCancelled(request) ) { 
    		form.reset(mapping, request);
        	
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages	 = new ActionMessages();    	
		Image document = null;    	
		ImageContainerFacade facade = new ImageContainerFacade(this.getServlet().getServletContext(), this, request); 
    	
    	    	
    	Transaction tx = null;
    	
    	try {

    		document = facade.save(imageForm, true);
            // Audit Transaction
            if (imageForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, document, document.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, document, document.getName(), Globals.AUDIT_UPDATE);
            }   
    	} catch(Exception e) {
    		
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.image", this.getLocale(request)), imageForm.getName(), null);
			
			if (tx != null) {
			    tx.rollback();
			}
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			}
			
			logger.error(e.getMessage(), e);

    		
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
		

		return mapping.findForward(Globals.FORWARD_SUCCESS);  //the default action
		
		
    }

    public ActionForward apply (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	    	
    	// Gets the action form
		UploadMediaForm imageForm = (UploadMediaForm)form;
        if ( isCancelled(request) ) {        	
        	imageForm.reset(mapping, request);
        	
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages = new ActionMessages();

		Image document = null;	
		ImageContainerFacade facade = new ImageContainerFacade();
		
		try {			
			
			document = facade.save(imageForm, true);
			imageForm.setCode(document.getCode());
            // Audit Transaction
            if (imageForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, document, document.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, document, document.getName(),Globals.AUDIT_UPDATE);
            }   
		    
		} catch (Exception e) {			
			
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.image", this.getLocale(request)), imageForm.getName(), null);
			
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			}
			logger.error(e.getMessage(), e);
		}
		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}
		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		
		return mapping.findForward(Globals.FORWARD_FORM);
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
		UploadMediaForm imageForm = (UploadMediaForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Image image = null;
		List<RowItem> fileList = null;
		int deletedEntities = 0;
		String currentTitle = "";
		
		try {
			sess = getHibernateSession();
			
			tx = sess.beginTransaction();
			
		    Long codes[] = imageForm.getCodes();
		    FileManagerUtils fileUtils = new FileManagerUtils(this.getServlet().getServletContext()); 
			
		    if (codes != null && codes.length > 0) {
			    if ( codes.length > 1) {			
			    	//lista los paths de documentos
			    	fileList = (ArrayList<RowItem>)sess
							.createQuery(" select new com.iportal.biz.RowItem (image.name, doc.path) from Image image where image.code in (:codes) ")
							.setParameterList("codes", codes, Hibernate.LONG)
							.list();
					String hqlDelete = " delete Image image where image.code in (:codes)";
					//for (int i =0; i < )
						
					//Borra la lista los paths de documentos que se eliminan
					for (RowItem rowItem : fileList) {
						currentTitle = rowItem.getName();
						try {
							fileUtils.deleteFile(rowItem.getNameValue());
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
						        
					deletedEntities = sess.createQuery(hqlDelete)
							.setParameterList("codes", codes, Hibernate.LONG)
							.executeUpdate();
					logger.debug("deleted: "+deletedEntities);
			    } else {
			    	image = (Image) sess.load(
							Image.class, codes[0]);
					sess.delete(image);
					SysAuditHelper.audit(this, request, image, image.getName(), Globals.AUDIT_DELETE, sess);
					currentTitle = image.getName();
					fileUtils.deleteFile(image.getPath());
			    }
		    }
		    
		    tx.commit();
			
		} catch (Exception e) {
			//This code was copied from MCPartners, verify what does the next line.
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.document", this.getLocale(request)), currentTitle, null);
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
		
		return mapping.findForward(Globals.FORWARD_SUCCESS);  //the default action
		
    }

}
