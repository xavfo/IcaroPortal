/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.action;

import java.util.Locale;

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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.Access;
import com.iportal.model.Image;
import com.iportal.model.ImageCategory;
import com.iportal.model.Language;
import com.yage.Globals;

/**
 * 
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public class UploadMediaAction extends BaseAction {
    
    private static Log logger = LogFactory.getLog(UploadMediaAction.class);
    
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		if ( isCancelled(request) ) { 
    		form.reset(mapping, request);
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }
		
		ActionMessages messages = new ActionMessages();
		String path = null;
		
		try {
			UploadMediaForm uploadMediaForm = (UploadMediaForm) form;
			ServletContext context = servlet.getServletContext();
			
			if ( uploadMediaForm.getFile().getFileName()!= null 
					&& uploadMediaForm.getFile().getFileName().length()>0 ){
				String fileName = null;
				if (uploadMediaForm.getMediaName()!= null && uploadMediaForm.getMediaName().length()>0)
					fileName = uploadMediaForm.getMediaName();
				else
					fileName = ManageFileFacade.buildName(uploadMediaForm.getMediaType(), uploadMediaForm.getFile().getFileName());
				if (fileName==null){
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.upload.name"));
					throw new Exception("File name generator error");
				}
					
				path = ManageFileFacade.upload(form, request, messages, uploadMediaForm.getMediaType(), fileName, context);
				
				if (path == null) 
					throw new Exception("Upload Image Exception.");
				else {
					uploadMediaForm.setMediaName(fileName);
					uploadMediaForm.setUploadPath(path);
				}
			}
			Boolean isSave = null;
			if ( uploadMediaForm.getMediaType().equals(Constants.FILE_TYPE_IMAGE) )
				isSave = saveImage(uploadMediaForm, request);
			else if ( uploadMediaForm.getMediaType().equals(Constants.FILE_TYPE_BANNER) )
				isSave = saveBanner(uploadMediaForm, request);
			/*
			 * FIXME si es falso borrar el archivo creado
			 */
		 	if ( !(isSave != null && isSave.booleanValue()) ){
		 		String repository = "";
		 		Language language = (Language)request.getSession().getAttribute(Constants.LANGUAGE_KEY);
		 		Locale locale = language.getLocale();
		 		if ( uploadMediaForm.getMediaType().equals(Constants.FILE_TYPE_IMAGE) )
		 			repository = BaseHelper.getApplicationBundleMessage("prompt.image", locale);
		 		else if ( uploadMediaForm.getMediaType().equals(Constants.FILE_TYPE_BANNER) )
		 			repository = BaseHelper.getApplicationBundleMessage("prompt.access", locale);
		 		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.upload.data", repository));
			} 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		// Report any errors we have discovered back to the original form
		if (!messages.isEmpty()) {
		    saveMessages(request, messages);
			return (mapping.getInputForward());
		}
		
		return ( mapping.findForward(Globals.FORWARD_SUCCESS) );
	}
	
	private boolean saveImage (UploadMediaForm form, HttpServletRequest request)
	throws Exception{
		Session sess = null;
		Transaction tx = null;
		Image image = null;
		ImageCategory imageCategory = null;
		boolean isSave = false;
        
		try {
			sess =  getHibernateSession();
			
			if (form.getCode()!=null && form.getCode()>0){
				image = (Image) sess.get(Image.class, form.getCode());
			} else {
				image = new Image();
				form.setCode(null);
			}
			
			PropertyUtils.copyProperties(image, form);
			
			if (form.getCategoryCode()!=null 
					&& form.getCategoryCode()>0){
				imageCategory = (ImageCategory)sess.load(ImageCategory.class, form.getCategoryCode());
			}
			
			image.setPath(form.getUploadPath());
			image.setImageCategory(imageCategory);
			
			tx = sess.beginTransaction();
			sess.saveOrUpdate(image);
			tx.commit();
			
			//Audit Transaction
			if ( form.getCode() == null ) {
				SysAuditHelper.audit(this, request, image, image.getName(),  Globals.AUDIT_INSERT);				
			} else {
				SysAuditHelper.audit(this, request, image, image.getName(), Globals.AUDIT_UPDATE);
			}
			
			form.setCode(image.getCode());
			isSave = true;
		} catch (Exception e) {
			if (tx!=null)
				tx.rollback();
			logger.error(e.getMessage(), e);
		} finally {
			if (sess!=null)
				try {
					 sess.clear();
	 				 sess.close();
				} catch (Exception e) {
				}
		}
		return isSave;
	}
	
	private boolean saveBanner (UploadMediaForm form, HttpServletRequest request)
	throws Exception{
		Session sess = null;
		Transaction tx = null;
		Access access = null;
		boolean isSave = false;
		try {
			sess =  getHibernateSession();
			
			if (form.getCode()!=null && form.getCode()>0){
				access = (Access) sess.get(Access.class, form.getCode());
			} else {
				access = new Access();
				form.setCode(null);
			}
			
			PropertyUtils.copyProperties(access, form);
			
			
			access.setPath(form.getUploadPath());
			
			tx = sess.beginTransaction();
			sess.saveOrUpdate(access);
			tx.commit();
			
			//Audit Transaction
			if ( form.getCode() == null ) {
				SysAuditHelper.audit(this, request, access, access.getName(), Globals.AUDIT_INSERT);				
			} else {
				SysAuditHelper.audit(this, request, access, access.getName(), Globals.AUDIT_UPDATE);
			}
			
			form.setCode(access.getCode());
			isSave = true;
		} catch (Exception e) {
			if (tx!=null)
				tx.rollback();
			logger.error(e.getMessage(), e);
		} finally {
			if (sess!=null)
				try {
					 sess.clear();
	 				 sess.close();
				} catch (Exception e) {
				}
		}
		return isSave;
	}
}
