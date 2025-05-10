/*
 * Created Feb 16, 2006
 *	DocumentContainerAction.java
 */
package com.iportal.ctrl.system.container;

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
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.biz.facade.LinkContainerFacade;
import com.iportal.model.container.LinkContainer;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Operaciones CRUD (creacion, lectura, actualización y eliminación)
 * sobre clase persistente {@link com.iportal.model.container.LinkContainer}.
 *  
 * @author YAGE(hernan)
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class LinkContainerAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(LinkContainerAction.class);
    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	
    	ActionMessages messages = new ActionMessages();
    	LinkContainerForm docForm =(LinkContainerForm)form;
    	try {
    		LinkContainerFacade facade = new LinkContainerFacade();
    		if(docForm.getReset().booleanValue()) {
    			docForm.reset(mapping, request);
    		}
	    	List results = facade.list(docForm);        
			
	    	// Save the List of results in request scope
			request.setAttribute("linkList", results);
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
        LinkContainerForm linkContainerForm = (LinkContainerForm) form;
        
        linkContainerForm.reset(mapping, request);
        
        
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
		LinkContainerForm linkForm = (LinkContainerForm)form;
		
		Session sess = null;
		LinkContainer link = null;
		
		try {
			sess = getHibernateSession();
			
			link = (LinkContainer) sess.load(LinkContainer.class, linkForm.getCode());
			
			PropertyUtils.copyProperties(linkForm, link);
			
			linkForm.setCategoryCode(link.getCategory().getCode());
			linkForm.setTypeCode(link.getType().getCode());
			
			
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
		LinkContainer link = null;    	
		LinkContainerFacade facade = new LinkContainerFacade(); 
    	LinkContainerForm linkForm = (LinkContainerForm)form; 
    	
    	Transaction tx = null;
    	
    	try {

    		link =facade.save(linkForm, Globals.TRUE);
            // Audit Transaction
            if (linkForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, link, link.getTitle(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, link, link.getTitle(), Globals.AUDIT_UPDATE);
            }   
    		
    	}catch(Exception e) {
    		
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.link", this.getLocale(request)), linkForm.getTitle(), null);
			
			if (tx != null) {
			    tx.rollback();
			}
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
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
		LinkContainerForm linkForm = (LinkContainerForm)form;
        if ( isCancelled(request) ) {        	
        	linkForm.reset(mapping, request);
        	
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages = new ActionMessages();

		LinkContainer link = null;	
		LinkContainerFacade facade = new LinkContainerFacade();
		
		try {
			
			LinkContainerForm currentForm = (LinkContainerForm)form;
			link = facade.save(linkForm, Globals.TRUE);
            
            // Audit Transaction
            if (linkForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, link, link.getTitle(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, link, link.getTitle(), Globals.AUDIT_UPDATE);
            }   
            currentForm.setCode(link.getCode());
		} catch (Exception e) {			
			
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.link", this.getLocale(request)), linkForm.getTitle(), null);
			
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
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
		LinkContainerForm linkForm = (LinkContainerForm) form;
		
		Session sess = null;
		Transaction tx = null;
		//LinkContainer linkContainer = null;
		int deletedEntities = 0;
		try {
			sess = getHibernateSession();
			
			tx = sess.beginTransaction();
			
		    Long codes[] = linkForm.getCodes();
		    if (codes != null && codes.length > 0) {
			    //lista los paths de documentos
				String hqlDelete = " delete LinkContainer link where link.code in (:codes)";
				//for (int i =0; i < )
				
				deletedEntities = sess.createQuery(hqlDelete)
						.setParameterList("codes", codes, Hibernate.LONG)
						.executeUpdate();
				logger.debug("deleted: "+deletedEntities);
		    }
		    
		    tx.commit();
			
		} catch (Exception e) {
			//This code was copied from MCPartners, verify what does the next line.
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.link", this.getLocale(request)), "Link", null);
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
