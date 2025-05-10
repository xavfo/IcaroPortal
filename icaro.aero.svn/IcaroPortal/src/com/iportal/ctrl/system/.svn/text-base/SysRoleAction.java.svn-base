/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.system.SysRole;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * 
 * 
 * @author  YAGE (pablor)
 * @version 1.1
 *
 */
public class SysRoleAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(SysRoleAction.class);
    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        SysRoleForm listForm = (SysRoleForm) form;
        
        Session sess = null;
        List results = null;

        try {
    		sess = getHibernateSession();

    		StringBuffer sql = new StringBuffer();
    		

    		sql.append("from SysRole as sysRole ");
    		if ( listForm.getOrderField() != null ) {
        		sql.append("order by sysRole.");
        		sql.append(listForm.getOrderField());
        		if ( listForm.getOrderAsc().booleanValue() ) {
        		    sql.append(" asc ");
        		} else {
        		    sql.append(" desc ");
        		}
    		}
    		
    		results = sess.createQuery(sql.toString()).list();
    		
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
        
		// Save the List of results in request scope
		request.setAttribute("sysRoleList", results);

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
        SysRoleForm sysRoleForm = (SysRoleForm) form;
        sysRoleForm.reset(mapping, request);
        
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
		SysRoleForm sysRoleForm = (SysRoleForm) form;
		
		Session sess = null;
		SysRole sysRole = null;
		
		try {
			sess = getHibernateSession();
			
			sysRole = (SysRole) sess.load(SysRole.class, sysRoleForm.getCode());
			
			PropertyUtils.copyProperties(sysRoleForm, sysRole);
			if ( sysRole.getParent() != null ) {
				sysRoleForm.setParentCode( sysRole.getParent().getCode() );
			}
			
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

    public SysRole saveItem ( HttpServletRequest request, SysRoleForm sysRoleForm, ActionMessages messages)   throws Exception {
        SysRole sysRole = null;
        Session sess = null;
        Transaction tx = null;
        SysRole parent = null;

        try {
            sess = getHibernateSession();
            
            if (sysRoleForm.getCode() != null && sysRoleForm.getCode().longValue() != 0L) {
                sysRole = (SysRole) sess.load(SysRole.class, sysRoleForm.getCode());
            } else {
                sysRole = new SysRole();
                sysRoleForm.setCode(null);
            }
            
            int level = 0;
            if (sysRoleForm.getParentCode() != null && sysRoleForm.getParentCode().longValue() != 0) {
                parent = (SysRole) sess.load(SysRole.class, sysRoleForm.getParentCode());
                level = parent.getLevel().intValue(); 
            }

            PropertyUtils.copyProperties(sysRole, sysRoleForm);
            sysRole.setParent(parent);
            sysRole.setLevel(new Integer(level + 1));   

            //Persist data
            tx = sess.beginTransaction();
            sess.saveOrUpdate(sysRole);
            tx.commit();
            
            //Audit Transaction
			if ( sysRoleForm.getCode() == null ) {
				SysAuditHelper.audit(this, request, sysRole, sysRole.getName(), Globals.AUDIT_INSERT);
			} else {
				SysAuditHelper.audit(this, request, sysRole, sysRole.getName(), Globals.AUDIT_UPDATE);
			}
            
            sysRoleForm.setCode(sysRole.getCode());
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
            logger.error(e.getMessage(), e);
        } finally {
            if (sess != null)
                try {
                    sess.close();
                } catch (Exception e) {
                }
        }
        
        return sysRole;
    }

    public ActionForward save (
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
		SysRoleForm sysRoleForm = (SysRoleForm) form;
		this.saveItem(request, sysRoleForm, messages);
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

    public ActionForward apply (
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
        SysRoleForm sysRoleForm = (SysRoleForm) form;
        this.saveItem(request, sysRoleForm, messages);
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

		
		ActionMessages messages = new ActionMessages();

		// Gets the action form
		SysRoleForm sysRoleForm = (SysRoleForm) form;
		
		Session sess = null;
		Transaction tx = null;
		SysRole sysRole = null;

		try {
		    Long codes[] = sysRoleForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = getHibernateSession();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
					sysRole = (SysRole) sess.load(SysRole.class, codes[i]);
					sess.delete(sysRole);
					SysAuditHelper.audit(this, request, sysRole, sysRole.getName(), Globals.AUDIT_DELETE, sess);
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			if (tx != null) {
			    tx.rollback();
			}
			
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
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
