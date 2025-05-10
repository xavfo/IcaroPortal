package com.yage.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.model.Access;
import com.iportal.model.Image;
import com.yage.Globals;


public class PreviewMediaFileAction extends BaseAction{

	public PreviewMediaFileAction() {
		super();
	}

	private static Log logger = LogFactory.getLog(PreviewMediaFileAction.class);
    
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		Image image = null;
		Access banner = null;
		Session sess = null;
		try {
			UploadMediaForm popupForm = (UploadMediaForm) form;
			sess = getHibernateSession();
			if (popupForm.getMediaType().equals(Constants.FILE_TYPE_IMAGE)){
				image = (Image)sess.get(Image.class, popupForm.getCode());
				request.setAttribute("media", image);
			} else if (popupForm.getMediaType().equals(Constants.FILE_TYPE_BANNER)){
				banner = (Access)sess.get(Access.class, popupForm.getCode());
				request.setAttribute("media", banner);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess!=null)
				try {
					 sess.clear();
	 				 sess.close();
				} catch (Exception e) {
				}
		}
		return mapping.findForward(Globals.FORWARD_SUCCESS);
	}
}
