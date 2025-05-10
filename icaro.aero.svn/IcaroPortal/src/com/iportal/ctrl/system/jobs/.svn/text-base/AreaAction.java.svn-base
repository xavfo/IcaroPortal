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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.jobs.Area;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Operaciones CRUD (creacion, lectura, actualización y eliminación)
 * sobre clase persistente {@link com.iportal.model.jobs.Area}.
 * 
 * Para cada operación existe su metodo adecuado
 * <ul>
 * 		<li>Listar catalogos de un tipo: list</li>
 * 		<li>Leer los datos de un catalogo: read</li>
 * 		<li>Guadar un nuevo catalogo o actualizar: save</li>
 * 		<li>Eliminar un catalogo: delete</li>
 * </ul> 
 * 
 * @author pajaro
 * @version 1.0
 * @see BaseDispatchAction
 *
 */
public class AreaAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(AreaAction.class);
    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {            
    	
    	AreaForm areaForm = (AreaForm) form;
    	      
       // Session sess = null;
        List results = null;

        try {        	        	
        	JobsBussinessLogic jobBL = new JobsBussinessLogic();
        	
        	results = jobBL.getAllAreas(areaForm);        	
    		    		
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
		} finally {
			
		}
        
		// Save the List of results in request scope
		request.setAttribute("areaList", results);

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
        AreaForm areaForm = (AreaForm) form;        
        areaForm.reset(mapping, request);        
        
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
        
        HttpSession session = request.getSession();
		
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		AreaForm areaForm = (AreaForm) form;
		
		Session sess = null;
		Area area = null;
		Long areaCode = null;
		
		// param area code  is read from tab
		Long applyCode = (Long) request.getAttribute("isApply");
		if (applyCode!=null && applyCode.longValue()>0)
			areaForm.setCode(applyCode);
		areaCode = areaForm.getCode();
		
		if (areaCode != null){
			session.setAttribute("areaCode", areaCode);
		}
		
		try {
			sess = getHibernateSession();
			
			if (areaCode != null){
				area = (Area) sess.load(Area.class, areaCode);
			}
			
			if (applyCode == null)
				applyCode = new Long(0);
			
			if(applyCode.longValue() > 0) { //The area has an Apply action.
				area = (Area) sess.load(Area.class, applyCode);					
				PropertyUtils.copyProperties(areaForm, area);
			}
			
			PropertyUtils.copyProperties(areaForm, area);
			
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
		AreaForm areaForm = (AreaForm) form;

        if ( isCancelled(request) ) {        	 
        	areaForm.reset(mapping, request);
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages = new ActionMessages();
		
		Session sess   = null;
		Transaction tx = null;
		Area area = null;		

		try {
		    sess = getHibernateSession();
		    
			if (areaForm.getCode() != null && areaForm.getCode().longValue() != 0L) {
				area = (Area) sess.load(Area.class, areaForm.getCode());
			} else {
				area = new Area();
				areaForm.setCode(null);
			}
			
			PropertyUtils.copyProperties(area, areaForm);

			//Persist data
	 		tx = sess.beginTransaction();
	 		sess.saveOrUpdate(area);
	 		tx.commit();	 		 		

            // Audit Transaction
            if (areaForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, area, area.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, area, area.getName(), Globals.AUDIT_UPDATE);
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

		this.removeForm(mapping, request);				
		
		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }


    public ActionForward apply (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	    	
    	// Gets the action form
		AreaForm areaForm = (AreaForm) form;
        
        if ( isCancelled(request) ) {        	 
        	areaForm.reset(mapping, request);
        	
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

		ActionMessages messages = new ActionMessages();

		
		Session sess   = null;
		Transaction tx = null;
		Area area = null;		
		
		try {
		    sess = getHibernateSession();
		    
			if (areaForm.getCode() != null && areaForm.getCode().longValue() != 0L) {
				area = (Area) sess.load(Area.class, areaForm.getCode());
			} else {
				area = new Area();
				areaForm.setCode(null);
			}					
			
			PropertyUtils.copyProperties(area, areaForm);

			//Persist data
	 		tx = sess.beginTransaction();
	 		sess.saveOrUpdate(area);
	 		
	 		tx.commit();
	 		areaForm.setCode(area.getCode());
	 		// Audit Transaction
            if ( areaForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, area, area.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, area, area.getName(), Globals.AUDIT_UPDATE);
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

		this.removeForm(mapping, request);
		request.setAttribute("isApply",area.getCode());
		
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
		AreaForm areaForm = (AreaForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Area area = null;

		try {
		    Long codes[] = areaForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = getHibernateSession();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	if(codes[i].longValue() > 0) { //A veces llega un code basura '0' que debe ser ignorado.
				    	area = (Area) sess.load(Area.class, codes[i]);
						sess.delete(area);
                        SysAuditHelper.audit(this, request, area, area.getName(), Globals.AUDIT_DELETE, sess);
			    	}
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.area", this.getLocale(request)), area.getName(), null);
			
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
