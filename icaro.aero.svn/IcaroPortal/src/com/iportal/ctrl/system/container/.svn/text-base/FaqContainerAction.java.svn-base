/*
 * Created Feb 16, 2006
 *	DocumentContainerAction.java
 */
package com.iportal.ctrl.system.container;

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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.facade.DocumentAssignFacade;
import com.iportal.biz.facade.FaqContainerFacade;
import com.iportal.model.container.FaqContainer;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Operaciones CRUD (creacion, lectura, actualización y eliminación)
 * sobre clase persistente {@link com.iportal.model.container.FaqContainer}.
 *  
 * @author YAGE(hernan)
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class FaqContainerAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(FaqContainerAction.class);
    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	ActionMessages messages = new ActionMessages();
    	FaqContainerForm docForm =(FaqContainerForm)form; 
    	try {
    		FaqContainerFacade facade = new FaqContainerFacade();
    		if(docForm.getReset().booleanValue()) {
    			docForm.reset(mapping, request);
    		}
	    	List results = facade.list(docForm);;        
	    	//  session.setAttribute("faqCode", code);
	    	// Save the List of results in request scope
			request.setAttribute("faqList", results);
    	} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.error(e.getMessage(), e);
		} 		
    	//this.removeForm(mapping, request);
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
    public ActionForward listDocuments (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        FaqContainerForm listForm = (FaqContainerForm) form;
        Long code = listForm.getCode();
        HttpSession session = request.getSession();
        if (code != null){
         session.setAttribute("faqCode", code);
       
        }
        code = (Long)session.getAttribute("faqCode");
        DocumentAssignFacade facade = new DocumentAssignFacade(this, request);
        //Set results = facade.getRelatedDocuments(FaqContainer.class, code);
        List results = facade.getAssignedDocuments(code, null, FaqContainer.class, request);
        listForm.setCode(code);
              
        // Save the List of results in request scope
        request.setAttribute("documentList", results);
        
        return mapping.findForward(Globals.FORWARD_LIST_DOCUMENTS);
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
        FaqContainerForm faqContainerForm = (FaqContainerForm) form;
        
        faqContainerForm.reset(mapping, request);
        
        
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
		FaqContainerForm faqForm = (FaqContainerForm)form;
		HttpSession session = request.getSession();
		Session sess = null;
		FaqContainer faq = null;
		
		try {
			sess = getHibernateSession();
			
			faq = (FaqContainer) sess.load(FaqContainer.class, faqForm.getCode());
			
			PropertyUtils.copyProperties(faqForm, faq);
			
			faqForm.setCategoryCode(faq.getCategory().getCode());
			   session.setAttribute("faqName", faq.getQuestion());
			
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
    	
    	if ( isCancelled(request) ) { 
    		form.reset(mapping, request);
        	
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages	 = new ActionMessages();    	
		    	
		FaqContainerFacade facade = new FaqContainerFacade(this, request); 
    	FaqContainerForm faqForm = (FaqContainerForm)form; 
    	
    	Transaction tx = null;
    	
    	try {
    		facade.save(faqForm, Globals.TRUE);
    	}catch(Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.faq", this.getLocale(request)), faqForm.getQuestion(), null);
			
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
		FaqContainerForm faqForm = (FaqContainerForm)form;
		
        if ( isCancelled(request) ) {        	
        	faqForm.reset(mapping, request);
        	
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages = new ActionMessages();

		FaqContainer faq = null;	
		FaqContainerFacade facade = new FaqContainerFacade(this, request);
		
		try {
			
			faq = facade.save(faqForm, Globals.TRUE);
			faqForm.setCode(faq.getCode());
			request.getSession().setAttribute("faqName",faqForm.getQuestion());
		    
		} catch (Exception e) {			
			
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.faq", this.getLocale(request)), faqForm.getQuestion(), null);
			
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
		FaqContainerForm faqForm = (FaqContainerForm) form;
		
		Session sess = null;
		Transaction tx = null;

		int deletedEntities = 0;
		try {
			sess = getHibernateSession();
			
			tx = sess.beginTransaction();
			
		    Long codes[] = faqForm.getCodes();
		    if (codes != null && codes.length > 0) {
			    //lista los paths de documentos
				String hqlDelete = " delete FaqContainer faq where faq.code in (:codes)";
				//for (int i =0; i < )
				
				deletedEntities = sess.createQuery(hqlDelete)
						.setParameterList("codes", codes, Hibernate.LONG)
						.executeUpdate();
				logger.debug("deleted: "+deletedEntities);
		    }
		    
		    tx.commit();
			
		} catch (Exception e) {
			//This code was copied from MCPartners, verify what does the next line.
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.faq", this.getLocale(request)), "FAQ", null);
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
