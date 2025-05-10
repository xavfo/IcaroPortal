/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.iportal.biz.system.portal.ContentFacade;
import com.iportal.biz.system.portal.ContentForm;
import com.iportal.biz.system.portal.SearchContentForm;
import com.iportal.model.container.DocumentContainer;
import com.iportal.model.container.FaqContainer;
import com.iportal.model.container.LinkContainer;
import com.iportal.model.portal.Content;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * ContentAction
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 */
public class ContentAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(ContentAction.class);
    
    public ActionForward search (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
   	
   	ActionMessages messages = new ActionMessages();
   	List<Content> results = null;
   	
   	try {
   		SearchContentForm searchContentForm = (SearchContentForm) form; 
   		ContentFacade contentFacade = new ContentFacade();
   		results = contentFacade.search(searchContentForm, request);
   		searchContentForm.reset(mapping, request);
   		request.setAttribute("contentList", results);
   		
   		
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.info(e.getMessage(), e);
		}
		
		return mapping.findForward(Globals.FORWARD_LIST);
    }
    
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
    	
    	
    	ActionMessages messages = new ActionMessages();
    	List<Content> results = null;
    	
    	try {
    		ContentForm contentForm = (ContentForm) form;
    		ContentFacade contentFacade = new ContentFacade();
    		results = contentFacade.getPage(contentForm, request);
    		
    		request.setAttribute("contentList", results);
    		
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
 			logger.info(e.getMessage(), e);
		}
		
 		return mapping.findForward(Globals.FORWARD_LIST);
     }
          
     /**
      * Retrieves the documents that belong to the current content.
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return List
      * @throws Exception
      */
     public ActionForward listDocuments (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
     	
     	ActionMessages messages = new ActionMessages();     	
     	Session sess = null;
         
     	try {
     		
     		sess = getHibernateSession();
     		
     		ContentForm contentForm = (ContentForm)form;
     		
     		Content content = (Content)sess.get(Content.class, contentForm.getCode());
     		
     		//Get the list members
     		content.getListOfRelatedDocuments().size();     		
     		
 	    	Set<DocumentContainer> results = content.getListOfRelatedDocuments();        
 			
 	    	// Save the List of results in request scope
 			request.setAttribute("documentContainerList", results);
 			
 			//Save the code of the current object in request
 			request.setAttribute("itemCode", contentForm.getCode());
 			
 			//Set a flag to indicate the jsp that this data belongs to Content.
 			request.setAttribute("ownerObjectCode", Constants.CONTAINER_CONTENT_DOC_OWNER_CODE);
 			
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
 		return mapping.findForward(Globals.FORWARD_LIST_DOCUMENTS);
     }
     
     /**
      * Retrieves the FAQs that belong to the current content.
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return List
      * @throws Exception
      */
     public ActionForward listFaq (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
     	
     	ActionMessages messages = new ActionMessages();     	
     	Session sess = null;
         
     	try {
     		
     		sess = getHibernateSession();
     		
     		ContentForm contentForm = null;
     		
     		
     		if(request.getAttribute("faqContentForm") != null) //viene del save faq
     			contentForm = (ContentForm)request.getAttribute("faqContentForm");
     		else //viene del list del ContentAction.
     			contentForm = (ContentForm)form;
     		
     		Content content = (Content)sess.get(Content.class, contentForm.getCode());
     		
     		//Get the list members
     		content.getListOfRelatedFaq().size();
     		
 	    	Set<FaqContainer> results = content.getListOfRelatedFaq();        
 			
 	    	// Save the List of results in request scope
 			request.setAttribute("faqContainerList", results);
 			
 			//Save the code of the current object in request
 			request.setAttribute("itemCode", contentForm.getCode());
 			
 			//Set a flag to indicate the jsp that this data belongs to Content.
 			request.setAttribute("ownerObjectCode", Constants.CONTAINER_FAQ_CONTENT_OWNER_CODE);
 			
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
 		return mapping.findForward(Globals.FORWARD_LIST_FAQ);
     }
     
     /**
      * Retrieves the Links that belong to the current content.
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return List
      * @throws Exception
      */
     public ActionForward listLink (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
     	
     	ActionMessages messages = new ActionMessages();     	
     	Session sess = null;
         
     	try {
     		
     		sess = getHibernateSession();
     		
     		ContentForm contentForm = (ContentForm)form;
     		
     		Content content = (Content)sess.get(Content.class, contentForm.getCode());
     		
     		//Get the list members     		
     		content.getListOfRelatedLink().size();
     		
 	    	Set<LinkContainer> results = content.getListOfRelatedLink();        
 			
 	    	// Save the List of results in request scope
 			request.setAttribute("linkContainerList", results);
 			
 			//Save the code of the current object in request
 			request.setAttribute("itemCode", contentForm.getCode());
 			
 			//Set a flag to indicate the jsp that this data belongs to Content.
 			request.setAttribute("ownerObjectCode", Constants.CONTAINER_LINK_CONTENT_OWNER_CODE);
 			
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
 		return mapping.findForward(Globals.FORWARD_LIST_LINK);
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
         ContentForm contentForm = (ContentForm) form;
         contentForm.reset(mapping, request);
         
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
    	ActionMessages messages = new ActionMessages();
    	ContentForm contentForm = (ContentForm)form;
    	String formName = "itemForm";
    	try {
     		ContentFacade contentFacade = new ContentFacade();
     		contentFacade.getByCode(contentForm);
     		
     		if (contentForm.getLevel().intValue()==1)
     			formName = "sectionForm";
     		else if (contentForm.getLevel().intValue()==2)
     			formName = "categoryForm";
     		
    	} catch (Exception e) {
    		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
    		logger.info(e.getMessage());
		}
        
    	if (!messages.isEmpty()) {
  			saveMessages(request, messages);
  			return (mapping.getInputForward());
  		}
    	request.setAttribute(formName, contentForm);
        return mapping.findForward(Globals.FORWARD_FORM); 
     }
     
     /**
      * apply action
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
    	ActionMessages messages = new ActionMessages();
     	try {
     		ContentForm contentForm = (ContentForm) form;
      		ContentFacade contentFacade = new ContentFacade(request, this);
      		contentFacade.save(contentForm);
      		
  		} catch (Exception e) {
  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
   			logger.error(e.getMessage(), e);
  		}
  		
  		if (!messages.isEmpty()) {
  			saveMessages(request, messages);
  			return (mapping.getInputForward());
  		}
  		
 		// Report a success action
 		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
 		saveMessages(request, messages);
		return mapping.findForward(Globals.FORWARD_APPLY);
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
    	if (isCancelled(request)) {
    		return mapping.findForward(Globals.FORWARD_CANCEL);
    	}
    	ActionMessages messages = new ActionMessages();
    	try {
    		ContentForm contentForm = (ContentForm) form;
     		ContentFacade contentFacade = new ContentFacade(request, this);
     		contentFacade.save(contentForm);
           
 		} catch (Exception e) {
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
  			logger.error(e.getMessage(), e);
 		}
 		
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return (mapping.getInputForward());
 		}
 		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);
		
		return mapping.findForward(Globals.FORWARD_SUCCESS);
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
     /*public ActionForward delete (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        
        ActionMessages messages = new ActionMessages();

		// Gets the action form
		ContentForm contentForm = (ContentForm) form;
		try {
		    
            ContentFacade contentFacade = new ContentFacade(request, this);
			contentFacade.delete(contentForm.getCodes());
			
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
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
        
		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }*/
     
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
      	ContentForm officeForm = (ContentForm)form;

  		Session sess = null;
  		Transaction tx = null;
  		Content content = null;
  		
  		try {
  			sess = getHibernateSession();
  			Long[] codes = officeForm.getCodes();
  			HashSet<Content> parentList = new HashSet<Content>();
  		    if ( codes != null && codes.length > 0) {
  			    
  				tx = sess.beginTransaction();
  			    for (int i = 0; i < codes.length; i++) {
  			    	content = (Content) sess.load(Content.class, codes[i]);
  			    	if(codes[i].longValue() > 0)  {
  						if (content.getParent() != null) {
  							parentList.add(content.getParent());
  						}

  			    		sess.delete(content);
  			    		SysAuditHelper.audit(this, request, content, content.getTitle(), Globals.AUDIT_DELETE, sess);	
  			    	}			    		
  			    }
  			    sess.flush();
  			    //Ver como optimizar esto a futuro para evitar problemas de performance
			    for (Content parent : parentList) {
			    	if (parent.getContents() == null  || parent.getContents().size() == 0) {
			    		parent.setGroup(Globals.FALSE);
			    		sess.update(parent);
			    	}
			    }
			    parentList.clear();
  				tx.commit();
  		    } 
  		}catch (Exception e) {
  			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.content", 
  					this.getLocale(request)), content.getTitle(), null);
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
   					sess.clear();
   					sess.close();
   				} catch (Exception e) {
   				}
   		}
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