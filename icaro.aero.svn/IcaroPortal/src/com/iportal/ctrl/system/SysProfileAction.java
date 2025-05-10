/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.iportal.Constants;
import com.iportal.biz.TimeUtils;
import com.iportal.model.system.SysModule;
import com.iportal.model.system.SysProfile;
import com.iportal.model.system.SysRole;
import com.iportal.model.system.SysUser;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * 
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *  
 */
public class SysProfileAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(SysModuleAction.class);

    /**
     * List action
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return @throws
     *         Exception
     */
    public ActionForward list(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
        throws Exception {

        SysProfileForm listForm = (SysProfileForm) form;

        Session sess = null;
        SysRole role = null;
        List results = null;
        ArrayList modules = new ArrayList();
        Query query = null;
        boolean isYageUser = true;

        try {
            sess = getHibernateSession();
            SysUser sysUser = (SysUser) request.getSession().getAttribute(Constants.SYSTEM_USER_KEY);
            
            // Gets the Role
            role = (SysRole) sess.get(SysRole.class, listForm.getRoleCode());
            
            StringBuffer sql = new StringBuffer();
            
            // Gets the list of modules assigned to the specified role
            sql.append("select sysProfile.module.code ");
            sql.append("from SysProfile as sysProfile ");
            sql.append("where sysProfile.role.code = ? ");
            
            query = sess.createQuery(sql.toString());
            query.setLong(0, role.getCode().longValue());
			results = query.list();

            if (results != null && results.size() > 0) {
                int len = results.size();
                Long codes[] = new Long[len];

                for (int i = 0; i < len; i++) {
                    codes[i] = (Long) results.get(i);
                }
                listForm.setCodes(codes);
            }
            if (!sysUser.getAccessMode().getCode().equals(Constants.YAGE_ACCESS_MODE)) 
				isYageUser = false;
			
            // Gets the list of all modules
            sql.delete(0, sql.length());
            sql.append("from SysModule as sysModule ");
            sql.append("where sysModule.level = 1 ");
            if (!isYageUser)
            	sql.append("AND sysModule.accessMode = ? ");
            
            sql.append("order by sysModule.orderIndex asc ");

            query = sess.createQuery(sql.toString());
            if (!isYageUser)
            	query.setLong(0, sysUser.getAccessMode().getCode());
            
            results = query.list();
            if (results != null) {
                int len = results.size();

                for (int i = 0; i < len; i++) {
                    SysModule module = (SysModule) results.get(i);
                    findChildren(modules, module, isYageUser, sysUser.getAccessMode().getCode());
                }
            }
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
        request.setAttribute("sysRole", role);
        request.setAttribute("sysModuleList", modules);

        return mapping.findForward(Globals.FORWARD_LIST);
    }

    

    public ActionForward save(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
        throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

 		ActionMessages messages = new ActionMessages();
 		SysProfileForm sysProfileForm = (SysProfileForm) form;
        
        Session sess = null;
 		Transaction tx = null;
 		SysRole role = null;
 		SysModule module = null;
        
 		try {
 		    sess = getHibernateSession();
 		    
 		    // Gets the Role
            role = (SysRole) sess.load(SysRole.class, sysProfileForm.getRoleCode()); 
            TimeUtils.printNow("Begin Transaction");
            tx = sess.beginTransaction();
            
            // Deletes all profiles
            StringBuffer sql = new StringBuffer();
            sql.append("delete SysProfile as sysProfile ");
            sql.append("where sysProfile.role.code = ? ");
            
            //sess.delete(sql.toString(), role.getCode(), Hibernate.LONG);
            Query q = sess.createQuery(sql.toString());
            q.setLong(0, role.getCode().longValue());
            q.executeUpdate();
            sess.flush();
            sess.clear();
            // Inserts new Profiles
            Long codes[] = sysProfileForm.getCodes();
            if (codes != null) {
                int len = codes.length;
                
                for (int i = 0; i < len; i++) {
                    module = (SysModule) sess.load(SysModule.class, codes[i]);
                    SysProfile profile = new SysProfile();
                    profile.setRole(role);
                    profile.setModule(module);
                    
                    sess.save(profile);
                    
                    if (i%20 == 0){
                    	sess.flush();
                    	sess.clear();
                    }
                }
            }
            
            tx.commit();
            TimeUtils.printNow("Commit Transaction");
 		} catch (Exception e) {
 			if (tx != null) {
 			    tx.rollback();
 			}
 			
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(""));
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
    
    
    @SuppressWarnings("unchecked")
	private static void findChildren(ArrayList list, SysModule module, boolean isYageUser, Long accessModeCode) {
        // adds the module to the end of the list
        list.add(module);

        try {
            // initializes the lazy set
            Hibernate.initialize(module.getModules());

            if (module.getModules() != null) {
            	boolean isValid = false;
            	SysModule subModule = null;
                Iterator it = module.getModules().iterator();
                while (it.hasNext()) {
                	isValid = false;
                    subModule = (SysModule) it.next();
                    
                    if (isYageUser){
                    	isValid = true;
                    } else {
                    	if (subModule.getAccessMode().getCode().equals(accessModeCode)) {
                        	isValid = true;
                        }
                    }
                    
                    if (isValid)
                        if (subModule.getGroup().booleanValue()) {
	                        findChildren(list, subModule, isYageUser, accessModeCode);
	                    } else {
	                        list.add(subModule);
	                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}