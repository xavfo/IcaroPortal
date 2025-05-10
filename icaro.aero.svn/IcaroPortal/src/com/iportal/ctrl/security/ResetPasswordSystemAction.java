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
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.model.system.SysUser;
import com.yage.Globals;
import com.yage.commons.MailSender;
import com.yage.commons.Md5;
import com.yage.commons.PasswordGenerator;
import com.yage.struts.action.BaseDispatchAction;

/**
 * Resets the sysUser password
 * 
 * @author  YAGE (pablor)
 * @version 1.0.1
 *
 */
public class ResetPasswordSystemAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(ResetPasswordSystemAction.class);

    
    public ActionForward forward (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	    
    	
		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }
    
    public ActionForward find (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {    	             	        
        try {  
        	HttpSession session = request.getSession();
        	ActionMessages messages = new ActionMessages();
        	//System.out.println("find ");
        	//Get email
        	String userEmail = (String) PropertyUtils.getSimpleProperty(form, "email");
        	
        	SysUser user = getUser(userEmail);
            
        	if ( user == null ) {
			    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.username"));
        	}
        	
        	if ( messages.isEmpty() ) {
		    	session.setAttribute(Constants.SYSTEM_USER_KEY, user);
		    	
			}   
        	
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
		} 
        return mapping.findForward(Globals.FORWARD_SUCCESS);			
    }  
    
    private SysUser getUser(String userEmail) {
		SysUser user 	= null;
		Session sess 	= null;
		String userPass = null;

		try {
		    StringBuffer sql = new StringBuffer();
		    Transaction tx = null;
		    
		    sql.append("from SysUser as user ");
		    sql.append("where user.email = ? ");
		    
			sess = getHibernateSession();
			Query query = sess.createQuery(sql.toString());
			query.setString(0, userEmail);
			
			List results = query.list();
			
			if (results != null && results.size() > 0 ) {
			    
				user = (SysUser) results.get(0);
			    
				userPass = PasswordGenerator.getNext();
 				String md5Pass = Md5.hash(userPass);
 				user.setPassword(md5Pass);
 				 	
 				//Persist data
 				tx = sess.beginTransaction();
 				sess.saveOrUpdate(user);
 				tx.commit();
 				
 				//notify user
 				notifyUser(user, userPass);
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
	}//end getUser
    
    private void notifyUser(SysUser user, String newPass) {
    	
    	try{
	    	StringBuffer strMessage = new StringBuffer();
			String smtp_server 		= getServlet().getServletContext().getInitParameter("SMTP_SERVER").toString();
			int smtp_Port 			= Integer.valueOf(getServlet().getServletContext().getInitParameter("SMTP_PORT").toString()).intValue();
			String autor 			= getServlet().getServletContext().getInitParameter("EMAIL_ROBOT").toString();			
				 			
			strMessage.append("<br>Su nombre de usuario es: ");
			strMessage.append(user.getName().toString());
			strMessage.append("<br> y su <strong>nueva contrase�a</strong> es: ");
			strMessage.append(newPass);
			strMessage.append("<br><hr>");
			 						
			MailSender.send(smtp_server,smtp_Port,autor,user.getEmail().toString(),"CMS : Nueva Contrase�a",strMessage.toString());
	    	} catch (Exception e){
	    		logger.error(e.getMessage(), e);
	    	}
    	}
    }