/*
 * Created Feb 16, 2006
 *	EventImageAction.java
 */
package com.iportal.ctrl.system.news;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.ctrl.system.container.ImageContainerForm;
import com.iportal.model.Image;
import com.iportal.model.ImageGallery;
import com.iportal.model.news.News;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Operaciones CRUD (creacion, lectura, actualización y eliminación)
 * sobre clase persistente {@link com.iportal.model.ImageGallery}.
 *  
 * @author YAGE(martha)
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class NewsImageAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(NewsImageAction.class);
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	ImageContainerForm listForm = (ImageContainerForm) form;
    	
		HttpSession session = request.getSession();
    	Long code = (Long)session.getAttribute("newsCode");
    	
    	 Session sess = null;
         List results = null;
    	
    	if (code != null){
    		try {

          		StringBuffer hql = new StringBuffer();
          		ArrayList<Object> params = new ArrayList<Object>();
        		ArrayList<NullableType> types = new ArrayList<NullableType>();
        		
        		hql.append("select gallery from ImageGallery gallery ");
        		hql.append("join gallery.news news where news.code = ? ");
	    		params.add(code);
				types.add(Hibernate.LONG);
        		
				if (listForm.getTitle() != null && listForm.getTitle().length() > 0 ) {
	    			String name = "%" + listForm.getTitle() + "%";
	    			hql.append("and gallery.title like ? ");
	    			params.add(name);
	    			types.add(Hibernate.STRING);
	    		}
	      		
	      		if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() >  Constants.SEARCH_ALL_OPTION) {
	      			hql.append("and gallery.enabled = ? ");
	      			if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
	      				params.add( Globals.TRUE );
	      			else
	      				params.add( Globals.FALSE );
	      		    types.add(Hibernate.BOOLEAN);
                }  
	

        		//solo ordenar si ya se esta cargando newsos
        		if ( listForm.getOrderField() != null ) {
        			hql.append(" order by gallery.");
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
    	}
		
    	// Save the List of results in request scope
		request.setAttribute("imageList", results);

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
        ImageContainerForm imageContainerForm = (ImageContainerForm) form;
        
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
		ImageContainerForm imageContainerForm = (ImageContainerForm) form;
		
		Session sess = null;
		ImageGallery image = null;
		
		try {
			sess = getHibernateSession();
			if ( !(imageContainerForm.getCode()!=null && imageContainerForm.getCode().longValue()>0) )
				if (imageContainerForm.getCodes()!=null && imageContainerForm.getCodes().length>0)
					imageContainerForm.setCode(imageContainerForm.getCodes()[0]);
			
			image = (ImageGallery) sess.get(ImageGallery.class, imageContainerForm.getCode());
			
			PropertyUtils.copyProperties(imageContainerForm, image);
			
			if (image.getThumbnailImage()!=null){
				imageContainerForm.setThumbnailImageCode(image.getThumbnailImage().getCode());
				imageContainerForm.setThumbnailImageName(image.getThumbnailImage().getName());
			}
			
			if (image.getMediumImage()!=null) {
				imageContainerForm.setMediumImageCode(image.getMediumImage().getCode());
				imageContainerForm.setMediumImageName(image.getMediumImage().getName());
			}
			
			if (image.getLargeImage()!=null) {
				imageContainerForm.setLargeImageCode(image.getLargeImage().getCode());
				imageContainerForm.setLargeImageName(image.getLargeImage().getName());
			}
			
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
	 * Saves new ImageGalleries in the database and assigns these to an News    
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
    	
    	ImageContainerForm imageContainerForm = (ImageContainerForm) form;
    	
    	if ( isCancelled(request) ) { 
    		imageContainerForm.reset(mapping, request);
    		return mapping.findForward(Globals.FORWARD_CANCEL);
        }
    	
		ActionMessages messages	 = new ActionMessages();    	
    	HttpSession session = request.getSession();
    	ImageGallery imageGallery = null;
    	Long newsCode = (Long)session.getAttribute("newsCode");
    	boolean putMesage = false;
    	try {
    		imageGallery = saveImageGallery(imageContainerForm, newsCode, messages, putMesage, request);
    	} catch(Exception e) {
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			}
			logger.error(e.getMessage(), e);
    	} finally {
    	}
    	
		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}
		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		//this.removeForm(mapping, request);				

		return mapping.findForward(Globals.FORWARD_SUCCESS);  //the default action
    }

    public ActionForward apply (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	    	
    	// Gets the action form
    	ImageContainerForm imageContainerForm = (ImageContainerForm) form;
        if ( isCancelled(request) ) {        	
        	imageContainerForm.reset(mapping, request);
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages = new ActionMessages();
		ImageGallery imageGallery = null;
		Long newsCode = (Long)request.getSession().getAttribute("newsCode");
    	boolean putMesage = false;
		try {
			
			imageGallery = saveImageGallery(imageContainerForm, newsCode, messages, putMesage, request);
			
			imageContainerForm.setCode(imageGallery.getCode());
		} catch (Exception e) {			
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
		ImageContainerForm imageContainerForm = (ImageContainerForm) form;
		
		Session sess = null;
		Transaction tx = null;
		String currentTitle = "";
		Long imageGalleryCode = null;
		
		HttpSession session = request.getSession();
		Long newsCode = (Long)session.getAttribute("newsCode");
		if (newsCode != null){
			try {
				sess = getHibernateSession();
				News news = (News)sess.load(News.class, newsCode);
				
				Set<ImageGallery> imageGalleries = news.getImageGalleries();
				
				tx = sess.beginTransaction();
			    Long codes[] = imageContainerForm.getCodes();
			    if (codes != null){
			    	for (int i = 0; i < codes.length; i++){
			    		imageGalleryCode = codes[i];
			    		ImageGallery imageGallery = (ImageGallery)sess.load(ImageGallery.class, imageGalleryCode);
			    		imageGalleries.remove(imageGallery);
			    	}
			    	news.setImageGalleries(imageGalleries);
			    }
			    sess.saveOrUpdate(news);
			    tx.commit();
			} catch (Exception e) {
			
				//This code was copied from MCPartners, verify what does the next line.
				boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.gallery", this.getLocale(request)), currentTitle, null);
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
    
    private ImageGallery saveImageGallery(ImageContainerForm imageContainerForm, Long newsCode, ActionMessages messages, boolean putMessage, HttpServletRequest request) 
    throws Exception{
    	ImageGallery imageGallery = null;
    	Image thumbnailImage = null;
    	Image mediumImage = null;
    	Image largeImage = null;
    	Session sess = null;
    	Transaction tx = null;
    	try {
		    	sess = getHibernateSession();
		    	
    			if (imageContainerForm.getCode()!=null && imageContainerForm.getCode()>0){
					imageGallery = (ImageGallery) sess.load(ImageGallery.class, imageContainerForm.getCode());
				} else {
					imageGallery = new ImageGallery();
					imageContainerForm.setCode(null);
				}
				PropertyUtils.copyProperties(imageGallery, imageContainerForm);
				
				if (imageContainerForm.getThumbnailImageCode()!=null && imageContainerForm.getThumbnailImageCode()>0)
					thumbnailImage = (Image) sess.load(Image.class, imageContainerForm.getThumbnailImageCode());
				
				if (imageContainerForm.getMediumImageCode()!=null && imageContainerForm.getMediumImageCode()>0)
					mediumImage = (Image) sess.load(Image.class, imageContainerForm.getMediumImageCode());
				
				if (imageContainerForm.getLargeImageCode()!=null && imageContainerForm.getLargeImageCode()>0)
					largeImage = (Image) sess.load(Image.class, imageContainerForm.getLargeImageCode());
				
				imageGallery.setThumbnailImage(thumbnailImage);
				imageGallery.setMediumImage(mediumImage);
				imageGallery.setLargeImage(largeImage);
				tx = sess.beginTransaction();
				sess.saveOrUpdate(imageGallery);
				
				if (newsCode != null ){
					News news = (News)sess.load(News.class, newsCode);
					Set<ImageGallery> imageGalleries = news.getImageGalleries();
					boolean contains = imageGalleries.contains(imageGallery);
					
					if (! contains){ // it is not in the imageGalleries list
						imageGalleries.add(imageGallery);
						news.setImageGalleries(imageGalleries);
						sess.saveOrUpdate(news);
					}
				}
				tx.commit();
    	} catch (Exception e) {
    		putMessage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.gallery", this.getLocale(request)), imageContainerForm.getTitle(), null);
			if (tx != null) {
			    tx.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while try to save the ImageGallery: " + imageContainerForm.getTitle());
		} finally{
			if (sess != null)
				try {
					sess.close();
				} catch (Exception e) {
				}
		}
    	
    	return imageGallery;
    }
}