/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.ldap;

import java.util.ArrayList;
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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * 
 * 
 * @author  YAGE (martha)
 * @version 1.0
 *
 */
public class LdapUsersAction extends BaseDispatchAction {

    
   private static Log logger = LogFactory.getLog(LdapUsersAction.class);
    
    private boolean contains(String string, String pattern){
        boolean results = false;
        if(string.indexOf(pattern) > 0  || string.startsWith(pattern) || string.endsWith(pattern)){
            results = true;
        }
        return results;
    }
    
    public List<LdapUserBean> findByUsername(List ldapUsers, String pattern){
        List<LdapUserBean> result = new ArrayList<LdapUserBean>();
        if (ldapUsers != null){
           
            for (int i = 0; i < ldapUsers.size(); i++){
                LdapUserBean userBean = (LdapUserBean)ldapUsers.get(i);
                String username = userBean.getUsername();
                if (contains(username, pattern)){
                    result.add(userBean);
                }
            }
        }
        return result;
    }
    
    public List<LdapUserBean> findByUsernameAndStatus(List ldapUsers, String pattern, Integer status){
        List<LdapUserBean> result = new ArrayList<LdapUserBean>();
        if (ldapUsers != null){
            for (int i = 0; i < ldapUsers.size(); i++){
                LdapUserBean userBean = (LdapUserBean)ldapUsers.get(i);
                String username = userBean.getUsername();
                if (contains(username, pattern) && userBean.getStatus().equals(status)){
                    result.add(userBean);
                }
            }
        }
        return result;
    }

    public List<LdapUserBean> findByUsernameAndRegistered(List ldapUsers, String pattern, Integer registered){
        List<LdapUserBean> result = new ArrayList<LdapUserBean>();
        if (ldapUsers != null){
            for (int i = 0; i < ldapUsers.size(); i++){
                LdapUserBean userBean = (LdapUserBean)ldapUsers.get(i);
                String username = userBean.getUsername();
                if (contains(username, pattern) && userBean.getRegistered().equals(registered)){
                    result.add(userBean);
                }
            }
        }
        return result;
    }
    
    public List<LdapUserBean> findByUsernameAndParams(List ldapUsers, String pattern, Integer status, Integer registered){
        List<LdapUserBean> result = new ArrayList<LdapUserBean>();
        if (ldapUsers != null){
            for (int i = 0; i < ldapUsers.size(); i++){
                LdapUserBean userBean = (LdapUserBean)ldapUsers.get(i);
                String username = userBean.getUsername();
                if (contains(username, pattern) && userBean.getStatus().equals(status) && userBean.getRegistered().equals(registered)){
                    result.add(userBean);
                }
            }
        }
        return result;
    }
    
    public List<LdapUserBean> findByFields(List ldapUsers, Integer status, Integer registered){
        List<LdapUserBean> result = new ArrayList<LdapUserBean>();
        if (ldapUsers != null){
            for (int i = 0; i < ldapUsers.size(); i++){
                LdapUserBean userBean = (LdapUserBean)ldapUsers.get(i);
                if (status != null && registered != null){
                    Integer statusBean = userBean.getStatus();
                    Integer registeredBean = userBean.getRegistered();
                    if (statusBean.equals(status) && registeredBean.equals(registered)){
                        result.add(userBean);
                    }
                }else {
                    if (registered == null){// then filter by status field
                        Integer statusBean = userBean.getStatus();
                        if (statusBean.equals(status)){
                            result.add(userBean);
                        }
                    } else {// then filter by registered filed
                        Integer registeredBean = userBean.getRegistered();
                        if (registeredBean.equals(registered)){
                            result.add(userBean);
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public ActionForward listDepartments (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)  throws Exception {            
        
        LdapUsersForm listForm = (LdapUsersForm) form;
        String cityParam = request.getParameter("cityCode");
        Boolean filtered = Globals.TRUE;
        List departments = new ArrayList();
        List cities = new ArrayList();
        LdapFacade facade = new LdapFacade(filtered);
        
        if (cityParam != null){
            listForm.setCityCode(cityParam);
            departments = facade.getDepartments(cityParam);
            cities = facade.getCities();
        }
        
        // Save the List of results in request scope
        request.setAttribute("ldapDepartmentsList", departments);
        request.setAttribute("ldapCitiesList", cities);

        return mapping.findForward(Globals.FORWARD_LIST);
    }

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
         
         LdapUsersForm listForm = (LdapUsersForm) form;
         int startIndex = 0;
         /* usernames being filtered do not contain the "@" symbol */
         Boolean filtered = Globals.TRUE;

         //Reads the list of all Ldap Users
         LdapFacade facade = new LdapFacade(filtered);
         
         List cities = facade.getCities();
         String cityParam = listForm.getCityCode();
         
         String departmentParam = listForm.getDepartmentCode();
         
         List departments = facade.getDepartments(cityParam);
         
         String orderField = listForm.getOrderField();
         Boolean sortType = listForm.getOrderAsc();
         Integer registered = listForm.getRegistered();
         Integer status = listForm.getStatus();
         String username = listForm.getUsername();
         List<LdapUserBean> results = new ArrayList<LdapUserBean>();
         
         
         List<LdapUserBean> ldapUsers = facade.getLdapUserBeans(cityParam,departmentParam );
         
         listForm.setLdapUsers(ldapUsers);
         // sorts the list of Ldap Users
         if (orderField != null && sortType != null){
             LdapSort ldapSort = new LdapSort();
             ldapSort.sort(ldapUsers, startIndex, ldapUsers.size() - 1, orderField, sortType);
         }

                
         if (username == null || (username != null && username.length() == 0)){
             //no value input for username
             if (registered != null && status != null){
                 if (registered.equals(Constants.LDAP_ALL)){
                     if (status.equals(Constants.LDAP_ALL)){
                          // displays all the list
                         results = ldapUsers;
                     } else {
                         // filters by status
                         results = findByFields(ldapUsers, status, null);
                     }
                 } else {
                     if (status.equals(Constants.LDAP_ALL)){
                         // filters by registered field
                         results = findByFields(ldapUsers, null, registered);
                     } else {
                         // filters by both status and registered fields 
                         results = findByFields(ldapUsers, status, registered);
                     }
                 }
             }else {
                 results = ldapUsers;
             }
         } else {
             if (registered != null && status != null ){
                 if ( registered.equals(Constants.LDAP_ALL)){
                     if (status.equals(Constants.LDAP_ALL)){
                         results = findByUsername(ldapUsers, username); 
                     } else {
                         results = findByUsernameAndStatus(ldapUsers,username, status); 
                     }
                 } else {
                     if (status.equals(Constants.LDAP_ALL)){
                         results = findByUsernameAndRegistered(ldapUsers,username, registered);
                     } else {
                         results = findByUsernameAndParams(ldapUsers,username, status, registered);
                     }
                 }
             }
         }
         // Save the List of results in request scope
         request.setAttribute("ldapUsersList", results);
         request.setAttribute("ldapCitiesList", cities);
         request.setAttribute("ldapDepartmentsList", departments);
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
         LdapUsersForm eventForm = (LdapUsersForm) form;
         eventForm.reset(mapping, request);
         
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
 		//LdapUsersForm ldapForm = (LdapUsersForm) form;
 		

 		// Report any messages we have discovered back to the original form
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return (mapping.getInputForward());
 		}
 		
         return mapping.findForward(Globals.FORWARD_FORM);
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
     public ActionForward assign (
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
         LdapUsersForm ldapForm = (LdapUsersForm) form;
         
         Boolean filtered = Globals.TRUE;

         //Reads the list of all Ldap Users
         LdapFacade facade = new LdapFacade(filtered, this, request);
         
         
         String[] usernames = ldapForm.getUsernames();
         ldapForm.setUsernames(usernames);
         String idCity = ldapForm.getCityCode();
         String idDepartment = ldapForm.getDepartmentCode();
         Session sess = null;
         Transaction tx = null;
         try {
             sess = getHibernateSession();
             tx = sess.beginTransaction();
             facade.saveLdapUsers(ldapForm, idCity, idDepartment, sess);
             tx.commit();
         } catch (Exception e){
             if (tx != null){
                 tx.rollback();
             }
             logger.error(e.getMessage(), e);
             messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
         }finally {
             try {
                 sess.clear();
                 sess.close();
             } catch (Exception e) {
             }
         }
 		 
 		 //Report any messages we have discovered back to the original form
  		 if (messages.isEmpty()) {
  		     messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success.assign.users"));
         }
        
  		 saveMessages(request, messages);
 		
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
        // LdapUsersForm ldapForm = (LdapUsersForm) form;


 		 
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

}
