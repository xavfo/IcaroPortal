/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.security;

import java.util.List;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.LanguageUtils;
import com.iportal.model.Language;
import com.iportal.model.system.SysUser;
import com.yage.Globals;
import com.yage.commons.Md5;
import com.yage.servlet.http.security.Inventory;
import com.yage.struts.action.BaseLoginAction;

/**
 * 
 * 
 * @author YAGE (pablor)
 * @version 1.1.1
 *
 */
public class LoginSystemAction extends BaseLoginAction {

    private static Log logger = LogFactory.getLog(LoginSystemAction.class);
    
    protected ActionMessages authenticateLdap(ActionForm form, HttpServletRequest request) throws Exception {
        ActionMessages messages = new ActionMessages();
        HttpSession session = request.getSession();
        String username =  (String)PropertyUtils.getSimpleProperty(form, "username");
        String password =  (String)PropertyUtils.getSimpleProperty(form, "password");
        String s = new String ("lmslogin");
        try {
            CallbackHandler cbh = new MyCallbackHandler(username, password);
            LoginContext lc = new LoginContext(s, cbh);
            lc.login();
        } catch (LoginException le) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.wrong.credentials"));
            logger.error(le.getMessage(), le);
        } catch (Exception e) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.unexpected"));
            logger.error(e.getMessage(), e);
        }
        if (messages.isEmpty()){
            SysUser user = getUser(username);
            // Validate User
            if ( user == null ) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.username"));
            } else if ( !user.getEnabled().booleanValue() ) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.userAccount"));
            }

            if ( messages.isEmpty() ) {
                ServletContext context = this.getServlet().getServletContext();
                Inventory inventory = (Inventory)  context.getAttribute("loggedUserInventory");//context.getInitParameter("CURRENT_INVENTORY_ID"));
                user.setInventory(inventory);
                session.setAttribute(Constants.SYSTEM_USER_KEY, user);
            }
        }
        return messages;
    }

    
    
    protected ActionMessages authenticate(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        ActionMessages messages = new ActionMessages();
        
		// Look for the user in the Session Context
		HttpSession session = request.getSession();
		SysUser user = (SysUser) session.getAttribute(Constants.SYSTEM_USER_KEY);

		if (user == null) {
			// Get username/passowrd
			String username = (String) PropertyUtils.getSimpleProperty(form, "username");
			String password = Md5.hash((String) PropertyUtils.getSimpleProperty(form, "password"));
			
			user = getUser(username);
            
			String userPassword = null;
            Boolean userEnabled = null;
            if (user != null){
                userPassword = user.getPassword();
                userEnabled = user.getEnabled();
            }
            
			// Validate User
			if ( user == null ) {
			    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.username"));
			} else if (  userPassword != null && ! userPassword.equals(password) ) {
			    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.password"));
			} else if (  userEnabled != null && ! userEnabled.booleanValue() ) {
			    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.userAccount"));
			}

			if ( messages.isEmpty() ) {
				ServletContext context = this.getServlet().getServletContext();
				Inventory inventory = (Inventory)  context.getAttribute("loggedUserInventory");//context.getInitParameter("CURRENT_INVENTORY_ID"));
		    	user.setInventory(inventory);

				session.setAttribute(Constants.SYSTEM_USER_KEY, user);
			}
		}		    		
        return messages;
    }

    
    protected void initUserSession(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
		// Look for the user in the Session Context
		HttpSession session = request.getSession();

        LanguageUtils utils = new LanguageUtils();
        //Language language = utils.getLanguage(request);
        Language language = utils.getLanguage(Constants.LANGUAGE_SPANISH);
        
        if (language == null) {
            String message = getResources(request).getMessage("error.language"); 
            logger.error(message);
            throw new ServletException(message);           
        }
        
        session.setAttribute(Constants.LANGUAGE_KEY, language);
        setLocale(request, language.getLocale());
    }


	private SysUser getUser(String username) {
		SysUser user = null;
		Session sess = null;

		try {
		    StringBuffer sql = new StringBuffer();
		    
		    sql.append("from SysUser as user ");
		    sql.append("where user.name = ? ");
		    
			sess = getHibernateSession();
			
			Query query = sess.createQuery(sql.toString());
			query.setString(0, username);
			
			List results = query.list();
			
			if (results != null && results.size() > 0 ) {
			    user = (SysUser) results.get(0);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.close();
				} catch (Exception e) {
				}
		}

		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.yage.struts.action.BaseLoginAction#findSuccessForward(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
    protected ActionForward findSuccessForward(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response) 
    throws Exception {
    	
    	// Look for the user in the Session Context
		HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute(Constants.SYSTEM_USER_KEY);
        if (user.getReset()!= null && !user.getReset().booleanValue()) {
        	request.setAttribute("userCode", user.getCode());
        	return mapping.findForward(Globals.FORWARD_FORM);
        } else {
        	return mapping.findForward(Globals.FORWARD_SUCCESS);	
        }
    }
	
}
