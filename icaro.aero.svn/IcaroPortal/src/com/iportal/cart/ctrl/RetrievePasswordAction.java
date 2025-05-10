/*
 * Created on Sep 9, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.cart.ctrl;

import java.util.Date;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.cart.model.customer.Customer;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.commons.Md5;
import com.yage.commons.PasswordGenerator;
import com.yage.struts.action.BaseAction;


/**
 * Resetea la contrase�a y la env�a por mail. 
 * 
 * @author martha
 * @version 1.0
 * @see com.yage.struts.action.BaseDispatchAction
 */
public class RetrievePasswordAction extends BaseAction {
    
    private static Log logger = LogFactory.getLog(RetrievePasswordAction.class);
    
    private String retrievePassword(){
    	String password = null;
    	password = PasswordGenerator.getNext();
    	return password; 
    }
    
    private Customer verifyUserData(Session sess, String username){
    	Customer user = null;
    	Query query=null;
		try {
		    StringBuffer sql = new StringBuffer();
		    sess = getHibernateSession();
		    sql.append(" from Customer cust");
			sql.append(" where cust.userName = ?");
			sql.append(" and cust.enabled = ?");
			

			query = sess.createQuery(sql.toString());
			query.setString(0, username);
			query.setBoolean(1, Globals.TRUE);
			query.setCacheable(true);
		    
			
			List results = query.list();;

			if (results != null && results.size() > 0 ) {
			    user = (Customer) results.get(0);
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
    
    public ActionForward execute (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

        String newPassword = null;
		ActionMessages messages = new ActionMessages();
		Customer user = null;
		
		Session sess = null;
		String username = (String) PropertyUtils.getSimpleProperty(form, "username");
		Transaction trans = null;
		
		setDefaultLocale(request);
				
		
		try {
		    sess = getHibernateSession();
		    user = verifyUserData(sess, username);
		    
		    if (user != null){
		    	
		    	if (user.getEnabled().booleanValue()) {
		    		
		    		newPassword = retrievePassword();
		    		//el password es reseteado por el sistema
			    	//user.setReset(Globals.TRUE);			    	  	
			    	user.setResetPassword(Globals.TRUE);
			    	String cryptPassword = Md5.hash(newPassword );
					user.setPassword(cryptPassword);
					user.setResetPassword(Globals.FALSE);
					trans = sess.beginTransaction();
					sess.saveOrUpdate(user);
					trans.commit();				
					
					request.setAttribute("user", user);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("date", DateFormatUtils.format(new Date(), Constants.DATE_FORMAT));					
					
					
		    	} else {
		    		 messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.userAccount"));
		    	}
								
		    } else {
		    	messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.username"));
		    }
			
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
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
