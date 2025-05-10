package com.iportal.ctrl.system.jobs;


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
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.jobs.Area;
import com.iportal.model.jobs.Position;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/** 
 * @author martha
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class PositionAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(PositionAction.class);
    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {            
    	
    	PositionForm positionForm = (PositionForm) form;
    	      
       // Session sess = null;
        List results = null;
        HttpSession session = request.getSession();
        
		Long areaCode = (Long)session.getAttribute("areaCode");
		positionForm.setAreaCode(areaCode);
		
        try {   
        	JobsBussinessLogic jobBL = new JobsBussinessLogic();
        	results = jobBL.getAllPositions(positionForm);
    		    		
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
		} 
		// Save the List of results in request scope
		request.setAttribute("positionList", results);
		
		//Save the areaCode in request scope
		//request.setAttribute("areaCode", areaCode);

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
        PositionForm positionForm = (PositionForm) form;        
        positionForm.reset(mapping, request);        
        
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
		PositionForm positionForm = (PositionForm) form;
		
		HttpSession session = request.getSession();
		Long areaCode = (Long)session.getAttribute("areaCode");
		
		Long code = positionForm.getCode();
		
		Session sess = null;
		Position position = null;				
		
		try {
			sess = getHibernateSession();
			
			Long applyCode = (Long) request.getAttribute("isApply");
			
			if (applyCode == null)
				applyCode = new Long(0);
			
			if(applyCode.longValue() > 0) { //The area has an Apply action.
				position = (Position) sess.load(Position.class, applyCode);					
				//PropertyUtils.copyProperties(positionForm, position);
			}else {			
				position = (Position) sess.load(Position.class, code);
			}
			PropertyUtils.copyProperties(positionForm, position);
			positionForm.setAreaCode(areaCode);
			
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
    	
    	    	
    	// Gets the action form
		PositionForm positionForm = (PositionForm) form;
        
        if ( isCancelled(request) ) {        	 
        	positionForm.reset(mapping, request);
        	
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages = new ActionMessages();

		
		Session sess   = null;
		Transaction tx = null;
		Position position = null;		

		try {
		    sess = getHibernateSession();
		    
			if (positionForm.getCode() != null && positionForm.getCode().longValue() != 0L) {
				position = (Position) sess.load(Position.class, positionForm.getCode());
			} else {
				position = new Position();
				positionForm.setCode(null);
			}
			
			PropertyUtils.copyProperties(position, positionForm);
			//Retrieve the owner area
			Area currentArea = (Area)sess.get(Area.class, positionForm.getAreaCode());
			
			position.setArea(currentArea);
			
			//Persist data
	 		tx = sess.beginTransaction();
	 		sess.saveOrUpdate(position);
	 		
	 		tx.commit();	 		 		

            // Audit Transaction
            if ( positionForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, position, position.getName(),  Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, position, position.getName(), Globals.AUDIT_UPDATE);
            }

		} catch (Exception e) {
			if (tx != null) {
			    tx.rollback();
			}
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.operationFailed", e.getCause()!=null?e.getCause().getMessage():e.getMessage()));
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					tx = null;
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
		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		//this.removeForm(mapping, request);				
		
		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }


    public ActionForward apply (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	    	
    	// Gets the action form
		PositionForm positionForm = (PositionForm) form;
		
        if ( isCancelled(request) ) {        	 
        	positionForm.reset(mapping, request);
        	
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages = new ActionMessages();

		
		Session sess   = null;
		Transaction tx = null;
		Position position = null;
		

		try {
		    sess = getHibernateSession();
		    
			if (positionForm.getCode() != null && positionForm.getCode().longValue() != 0L) {
				position = (Position) sess.load(Position.class, positionForm.getCode());
			} else {
				position = new Position();
				positionForm.setCode(null);
			}					
			
			PropertyUtils.copyProperties(position, positionForm);
			
			//Retrieve the owner area
			Area currentArea = (Area)sess.load(Area.class, positionForm.getAreaCode());
			
			if( !Hibernate.isInitialized(currentArea.getName()))
				Hibernate.initialize(currentArea);
			
			position.setArea(currentArea);

			//Persist data
	 		tx = sess.beginTransaction();
	 		sess.saveOrUpdate(position);
	 		
	 		tx.commit();	 		 		

		} catch (Exception e) {
			if (tx != null) {
			    tx.rollback();
			}
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.operationFailed", e.getCause()!=null?e.getCause().getMessage():e.getMessage()));
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					tx = null;
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
		
		// Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);

		this.removeForm(mapping, request);
				
		request.setAttribute("isApply",position.getCode());
		
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
		PositionForm positionForm = (PositionForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Position position = null;

		try {
		    Long codes[] = positionForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = getHibernateSession();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	if(codes[i].longValue() > 0) { 
				    	position = (Position) sess.load(Position.class, codes[i]);
						sess.delete(position);
                        SysAuditHelper.audit(this, request, position, position.getName(), Globals.AUDIT_DELETE, sess);
			    	}
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.position", this.getLocale(request)), position.getName(), null);
			
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
