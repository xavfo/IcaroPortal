/*
 * Created Feb 16, 2006
 *	DocumentContainerAction.java
 */
package com.iportal.ctrl.system.event;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.facade.DocumentAssignFacade;
import com.iportal.ctrl.system.container.DocumentContainerForm;
import com.iportal.model.container.DocumentContainer;
import com.iportal.model.event.Event;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Esta clase realiza operaciones sobre documentos no relacionados a las noticias 
 * @author YAGE(martha)
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class AssignEventDocumentAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(AssignEventDocumentAction.class);
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	DocumentContainerForm listForm =(DocumentContainerForm)form;
		List results = null;
		HttpSession session = request.getSession();
    	Long code = (Long)session.getAttribute("eventCode");
    	DocumentAssignFacade facade = new DocumentAssignFacade(this, request);
    	results = facade.getNotAssignedDocuments(code, listForm, Event.class);

    	// Save the List of results in request scope
		request.setAttribute("assignDocumentList", results);
    	return mapping.findForward(Globals.FORWARD_LIST);
    }
 

    public ActionForward save (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	DocumentContainerForm documentForm = (DocumentContainerForm)form;
    	ActionMessages messages	 = new ActionMessages();    
    	
    	if ( isCancelled(request) ) { 
    		Long docTypeCode = documentForm.getDocTypeCode();
    		documentForm.reset(mapping, request);
    		documentForm.setDocTypeCode(docTypeCode);
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }else {
        	Long codes[] = documentForm.getCodes();
        	if (codes == null || (codes != null && codes.length == 0)){
        		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.at.least.one"));
        		saveMessages(request, messages);
        		return mapping.getInputForward();
        	}
        }
    	
    	
    	HttpSession session = request.getSession();
    	Long eventCode = (Long)session.getAttribute("eventCode");
    	documentForm.setOwnerCode(eventCode);
    	
        DocumentAssignFacade facade = new DocumentAssignFacade(this, request);
    	boolean result = facade.save(documentForm, Event.class);
    	
    	if (!result ){
    		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
    	}
    	
		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}
		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);
			
		//request.setAttribute("assignedNewsCode" , newsCode);
		return mapping.findForward(Globals.FORWARD_SUCCESS);  //the default action
		
		
    }

    public ActionForward apply (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	    	
    	// Gets the action form
    	DocumentContainerForm documentForm = (DocumentContainerForm)form;
      
    	ActionMessages messages = new ActionMessages();
    	if ( isCancelled(request) ) { 
    		Long docTypeCode = documentForm.getDocTypeCode();
    		documentForm.reset(mapping, request);
    		documentForm.setDocTypeCode(docTypeCode);
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }else {
        	Long codes[] = documentForm.getCodes();
        	if (codes == null || (codes != null && codes.length == 0)){
        		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.at.least.one"));
        		saveMessages(request, messages);
        		return mapping.getInputForward();
        	}
        }
       	HttpSession session = request.getSession();
    	Long eventCode = (Long)session.getAttribute("eventCode");
    	documentForm.setOwnerCode(eventCode);
    	DocumentAssignFacade facade = new DocumentAssignFacade(this, request);
    	//boolean result = facade.save(documentForm, new Event());
    	boolean result = facade.save(documentForm, Event.class);
    	
    	if (!result ){
    		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
    	}
    	
		// Report any messages we have discovered back to the original form
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return (mapping.getInputForward());
		}
		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		//request.setAttribute("assignedNewsCode" , newsCode);

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
		DocumentContainerForm documentForm = (DocumentContainerForm) form;
		
		Session sess = null;
		Transaction tx = null;
		String currentTitle = "";
		Long documentCode = null;
		
		HttpSession session = request.getSession();
		Long eventCode = (Long)session.getAttribute("eventCode");
		if (eventCode != null){
			try {
				sess = getHibernateSession();
				Event event = (Event)sess.load(Event.class, eventCode);
				
				Set<DocumentContainer> documents = event.getDocuments();
				
				tx = sess.beginTransaction();
			    Long codes[] = documentForm.getCodes();
			    if (codes != null){
			    	for (int i = 0; i < codes.length; i++){
			    		documentCode = codes[i];
			    		DocumentContainer documentContainer = (DocumentContainer)sess.load(DocumentContainer.class, documentCode);
			    		documents.remove(documentContainer);
			    	}
			    	event.setDocuments(documents);
			    }
			    sess.saveOrUpdate(event);
			    tx.commit();
                SysAuditHelper.audit(this, request, event, event.getTitle(), Globals.AUDIT_UPDATE);
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
