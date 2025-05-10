/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.system.SysAccessMode;
import com.iportal.model.system.SysRole;
import com.iportal.model.system.SysUser;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.commons.Md5;
import com.yage.struts.action.BaseDispatchAction;

/**
 * SysUserAction store the SysUser administrator screens logic
 * 
 * @author  YAGE (pablor)
 * @version 1.1.1
 *
 */
public class SysUserAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(SysUserAction.class);

    
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
     public ActionForward list (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         SysUserForm listForm = (SysUserForm) form;
         
         Session sess = null;
         List results = null;
         //HttpSession session = null;
         //boolean isYageUser = false;
         ArrayList<Object> params = new ArrayList<Object>();
         ArrayList<NullableType> types = new ArrayList<NullableType>();


         try {
     		StringBuffer sql = new StringBuffer();
     		//session = request.getSession();
     		//SysUser sysUser = (SysUser) session.getAttribute(Constants.SYSTEM_USER_KEY);
     			
     		sql.append("from SysUser as sysUser ");
            sql.append("where 1 = 1 ");
     		/*
     		if (!sysUser.getAccessMode().getCode().equals(Constants.YAGE_ACCESS_MODE)) {
     			sql.append("where sysUser.accessMode.code = ? ");
     			//isYageUser = true;
                params.add(sysUser.getAccessMode().getCode());
                types.add(Hibernate.BOOLEAN);
     		}
            */
           
            if (listForm.getName() != null && listForm.getName().length() > 0) {
                String name = "%" + listForm.getName() + "%";
                sql.append("and sysUser.name like ? ");
                params.add( name );
                types.add(Hibernate.STRING);
            }

            if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() > -1) {
                sql.append("and sysUser.enabled = ? ");
                if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
                    params.add( Globals.TRUE );
                else
                    params.add( Globals.FALSE );
                types.add(Hibernate.BOOLEAN);
            }
     			
     		if ( listForm.getOrderField() != null ) {
         		sql.append("order by sysUser.");
         		sql.append(listForm.getOrderField());
         		if ( listForm.getOrderAsc().booleanValue() ) {
         		    sql.append(" asc ");
         		} else {
         		    sql.append(" desc ");
         		}
     		}
     		
     		sess = getHibernateSession();

 			Query query = sess.createQuery(sql.toString());
 			/*
 			if (isYageUser) {
 				query.setLong(0, sysUser.getAccessMode().getCode().longValue());
 			}
            */
            
            for (int i = 0; i < types.size(); i++) {
                query.setParameter(i, params.get(i), types.get(i));
            }
 			results = query.list();
           
     		
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
 		request.setAttribute("sysUserList", results);
 		Calendar calendar = new GregorianCalendar();
        String today=DateFormatUtils.format(calendar.getTime(), Constants.DATE_TIME_FORMAT);
        request.setAttribute("today", today);
        Object sendMail=null;
        Object sysUser=null;
        sendMail=request.getSession().getAttribute("sendMailMail");
        sysUser=request.getSession().getAttribute("sysUserMail");
 		request.setAttribute("sysUserMail",sysUser);
 		request.setAttribute("sendMailMail",sendMail);
 		request.getSession().removeAttribute("sendMailMail");
 		request.getSession().removeAttribute("sysUserMail");
 		return mapping.findForward(Globals.FORWARD_LIST);
     }
     
     
     /**
      * Create action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward create (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
         SysUserForm sysUserForm = (SysUserForm) form;
         sysUserForm.reset(mapping, request);
         
         return mapping.findForward(Globals.FORWARD_FORM);
     }


     /**
      * Read action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
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
 		SysUserForm sysUserForm = (SysUserForm) form;
 		
 		Session sess = null;
 		SysUser sysUser = null;
 		HttpSession session = null;
 		boolean isFirstAccess = false;
 		
 		try {
 			sess = getHibernateSession();
 			
 			if ( sysUserForm.getCode() == null) {
 				isFirstAccess = true;
 			}
 			
 			
 			if ( isFirstAccess ) {
 				session = request.getSession();
 				sysUser = (SysUser) session.getAttribute(Constants.SYSTEM_USER_KEY);
 			} else {
 				sysUser = (SysUser) sess.get(SysUser.class, sysUserForm.getCode());
 			}
 			
 			PropertyUtils.copyProperties(sysUserForm, sysUser);
 			sysUserForm.setRoleCode( sysUser.getRole().getCode() );
 			
            sysUserForm.setRegistrationDate(sysUser.getRegistrationDate());
            SysAccessMode mode = sysUser.getAccessMode();
            
            if (mode != null){
                sysUserForm.setAccessModeCode( mode.getCode() );
            }

 			if ( isFirstAccess ){
 				request.setAttribute("sysUserUpdateForm", sysUserForm);
 			}
 			
 		} catch (Exception e) {
 			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(""));
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

     public void saveItem (
             HttpServletRequest request,
             SysUserForm sysUserForm,
             ActionMessages messages)
     throws Exception {
         
        Session sess            = null;
        Transaction tx          = null;
        SysUser sysUser         = null;
        SysRole sysRole         = null;
        SysAccessMode accessMode= null;
 		String password = null;
       
        try {
            sess = getHibernateSession();
            
            if (sysUserForm.getCode() != null && sysUserForm.getCode().longValue() != 0L) {
                sysUser = (SysUser) sess.get(SysUser.class, sysUserForm.getCode());
                password = sysUser.getPassword();
            } else {
                sysUser = new SysUser();
                sysUserForm.setCode(null);
                password = Md5.hash(sysUserForm.getPassword());
                request.getSession().setAttribute("sendMailMail", Globals.TRUE);
            }
            
            if (sysUser.getRole() == null || (!sysUser.getRole().getCode().equals(sysUserForm.getRoleCode()))) {
 				sysRole = (SysRole) sess.load(SysRole.class, sysUserForm.getRoleCode());
 				sysUser.setRole(sysRole);
			}
            
            if (sysUser.getAccessMode() == null || (!sysUser.getAccessMode().getCode().equals(sysUserForm.getAccessModeCode()))) {
            	accessMode = (SysAccessMode) sess.load(SysAccessMode.class, sysUserForm.getAccessModeCode());
 				sysUser.setAccessMode(accessMode);
			}
            
            PropertyUtils.copyProperties(sysUser, sysUserForm);
            
            sysUser.setRegistrationDate(Calendar.getInstance());
            
            //si esta el pasword para editar
 			if(sysUserForm.getPassword() != null && sysUserForm.getPassword().length() > 0) {
 				password = Md5.hash(sysUserForm.getPassword());
 			} 			
 			sysUser.setPassword( password );
            
            //TODO revisar esto si es necesario y optimo pues se 
 			//hacen dos queries inecesarios.
 			ActionMessage message = isValidUser(sysUser, sess);
            if (message != null) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, message);
                throw new Exception("SysUser Validation Exception");
            }            
            
            //Persist data
            tx = sess.beginTransaction();
            sess.saveOrUpdate(sysUser);
            tx.commit();
            
            //Audit Transaction
			if ( sysUserForm.getCode() == null ) {
				SysAuditHelper.audit(this, request, sysUser, sysUser.getName(), Globals.AUDIT_INSERT);				
			} else {
				SysAuditHelper.audit(this, request, sysUser, sysUser.getName(), Globals.AUDIT_UPDATE);
			}
            
            sysUserForm.setCode(sysUser.getCode());
            
        } catch (Exception e) {
            boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.username", this.getLocale(request)), sysUserForm.getName(), null);
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

     
     /**
      * Save action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
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
         
         //Gets the action form
         SysUserForm sysUserForm = (SysUserForm) form;

         saveItem(request, sysUserForm, messages);
         
         //Report any messages we have discovered back to the original form
         if (!messages.isEmpty()) {
            saveMessages(request, messages);
            return (mapping.getInputForward());
         }
        
         //Report a success action
        messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
        saveMessages(request, messages);   
        
        request.getSession().setAttribute("sysUserMail",sysUserForm);
 		return mapping.findForward(Globals.FORWARD_SUCCESS);
     }
     
     /**
      * Apply action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
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
         
         //Gets the action form
         SysUserForm sysUserForm = (SysUserForm) form;

         this.saveItem(request, sysUserForm, messages);
         
         //Report any messages we have discovered back to the original form
        if (!messages.isEmpty()) {
            saveMessages(request, messages);
            return (mapping.getInputForward());
        }
        
        //Report a success action
        messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
        saveMessages(request, messages);
        
         return mapping.findForward(Globals.FORWARD_APPLY);
         
     }


     /**
      * Delete action
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
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
 		SysUserForm sysUserForm = (SysUserForm) form;
 		
 		Session sess = null;
 		Transaction tx = null;
 		SysUser sysUser = null;

 		try {
		    Long codes[] = sysUserForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = getHibernateSession();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			        sysUser = (SysUser) sess.get(SysUser.class, codes[i]);
					sess.delete(sysUser);	        
					SysAuditHelper.audit(this, request, sysUser, sysUser.getName(), Globals.AUDIT_DELETE, sess);  }
				tx.commit();
		    } 
 			
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
/*
     private Boolean sendMail(String userName, String userPass, String userEmail, HttpServletRequest request) {
    	 try {    		 	
	 			String smtp_server = getServlet().getServletContext().getInitParameter("SMTP_SERVER").toString();
	 			int smtp_Port = Integer.valueOf(getServlet().getServletContext().getInitParameter("SMTP_PORT").toString()).intValue();
	 			
	 			String autor = getServlet().getServletContext().getInitParameter("EMAIL_ROBOT").toString();	
	 			InetAddress i = InetAddress.getLocalHost();
	 		    String hostAddress = i.getHostAddress();
	 		    
	 			String promptPortal=BaseHelper.getApplicationBundleMessageEs("prompt.portal");
	 			//String subject = BaseHelper.getApplicationBundleMessageEs("prompt.email.cart.account");
	 			
	 			String promptPort = BaseHelper.getApplicationBundleMessageEs("prompt.port");
	 			String promptLink = BaseHelper.getApplicationBundleMessageEs("prompt.link");
	 			String promptAccountData = BaseHelper.getApplicationBundleMessageEs("prompt.email.cart.account.body");
	 			
	 			String promptUser = BaseHelper.getApplicationBundleMessageEs("prompt.username");
	 			String promptPwd =	BaseHelper.getApplicationBundleMessageEs("prompt.password");
	 			String promptAddinfo = BaseHelper.getApplicationBundleMessageEs("prompt.email.cart.addInfo");
	 			String path = BaseHelper.getApplicationBundleMessageEs("prompt.path");
	 			String promptDearUser = BaseHelper.getApplicationBundleMessageEs("prompt.dear.user");
	 			String body = promptDearUser + promptAccountData + "<BR>" + promptUser + ": " + userName + "<BR>" + "<BR>"
					+ promptPwd + ":" + userPass  + "<BR>" + "<BR>" + promptAddinfo + "<BR>" + "<BR>" + "<A HREF=\"http://"+ hostAddress + ":" + promptPort + request.getContextPath() + "/" + path + "\">"+ promptLink + "</A>";
	 			 									 			
			SendMail.send(smtp_server,smtp_Port,autor,userEmail,promptPortal,body); 			

    	 }catch(Exception e) {
    		 logger.error(e.getMessage(), e);
    		 return Globals.FALSE;
    	 }
    	 return Globals.TRUE;
     }
 */    
     private ActionMessage isValidUser(SysUser sysUser, Session sess) {
    	 ActionMessage message = null;
    	 StringBuffer sql = new StringBuffer();
    	 Query query =null;
    	 try {
    		 
    		 sql.append("FROM SysUser ");
    		 sql.append("WHERE name = ? ");
    		 if (sysUser.getCode() != null) {
    			 sql.append("AND code != ? ");
    		 }
    		 
    		 query = sess.createQuery(sql.toString());
	 		 query.setString(0, sysUser.getName());
	 		 if (sysUser.getCode() != null)
	 			query.setLong(1, sysUser.getCode());
    		 
    		 if ( query.list().size() > 0 ) {
    			 return new ActionMessage("error.userNameExist");
    		 }
    		 
    		 sql.delete(0, sql.length());
    		 sql.append("FROM SysUser ");
    		 sql.append("WHERE email = ? ");
    		 if (sysUser.getCode() != null) {
    			 sql.append("AND code != ? ");
    		 }
    		 
    		 query = sess.createQuery(sql.toString());
	 		 query.setString(0, sysUser.getEmail());
	 		 if (sysUser.getCode() != null) {
	 			query.setLong(1, sysUser.getCode());
	 		 }
    		 
    		 if ( query.list().size() > 0 ) {
    			 return new ActionMessage("error.subscriberExists");
    		 }
    		 
    	 }catch(Exception e) {
    		 logger.error(e.getMessage(), e);
    		 return new ActionMessage("error.unexpected");
    	 }
    	 return message;
     }
     
}
