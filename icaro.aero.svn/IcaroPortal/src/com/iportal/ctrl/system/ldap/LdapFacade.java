package com.iportal.ctrl.system.ldap;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;

import com.iportal.Constants;
import com.iportal.biz.BaseBussinessLogic;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.system.SysAccessMode;
import com.iportal.model.system.SysRole;
import com.iportal.model.system.SysUser;
import com.yage.Globals;
import com.yage.commons.Md5;
import com.yage.ldap.JLdapUtils;
import com.yage.ldap.UserLdap;

public class LdapFacade extends BaseBussinessLogic  {
    
    private static Logger logger = Logger.getLogger(LdapFacade.class);
    private Boolean filtered;
    private Action action;
    private HttpServletRequest request;
    
    private HashMap<String,UserLdap> userMap = new HashMap<String,UserLdap>();
    
    public LdapFacade(){
    }
    
    public LdapFacade(Boolean filteredUsernames){
        filtered = filteredUsernames; 
    }
 
    public LdapFacade(Boolean filteredUsernames, Action action, HttpServletRequest request){
        filtered = filteredUsernames;
        this.action = action;
        this.request = request;
    }

    public List<LdapUserBean> findUser(List ldapUsers, String pattern){
        List<LdapUserBean> result = new ArrayList<LdapUserBean>();
        if (ldapUsers != null){
            for (int i = 0; i < ldapUsers.size(); i++){
                LdapUserBean userBean = (LdapUserBean)ldapUsers.get(i);
                String username = userBean.getUsername();
                if (username.equalsIgnoreCase(pattern)){
                    result.add(userBean);
                }
            }
        }
        return result;
    }
    
    public HashMap getUserMap(String idCity, String idDepartment){
        List ldapUsers = getLdapUsers(idCity, idDepartment);
        if (ldapUsers != null){
            for (int i = 0; i < ldapUsers.size(); i++){
                UserLdap user = (UserLdap)ldapUsers.get(i);
                String username = user.getUsername();
                if (filtered != null){
                    if (filtered.booleanValue()){
                        userMap.put(filterUsername(username, "@"), user);
                    }
                    else{
                        userMap.put(username, user);
                    } 
                }
            }
        }
        return userMap;
    }
    
    /**
     * Retrieves a UserLdap from a Map
     * @param username
     * @return
     */
    public UserLdap getUserLdap(String username, String idCity, String idDepartment){
        UserLdap user = null;
        HashMap userMap = getUserMap(idCity, idDepartment);
        user = (UserLdap)userMap.get(username);
        return user;
    }
    /**
     * Filters the string before an special pattern 
     *
     */
    public String filterUsername(String string, String pattern ){
        String newString = string.substring(0, string.indexOf(pattern));
        return newString; 
    }
    
    /**
     * Returns a list of LdapUsers
     * @return
     */
    
    public List getLdapUsers(String idCity, String idDepartment){
        List results = JLdapUtils.returnList(idCity, idDepartment);
        return results;
    }
    
    
    public List getCities(){
        List list = JLdapUtils.returnCities();
        return list;
    }
    
    public List getDepartments(String cityCode){
        List list = new ArrayList();
        if (cityCode != null){
            list = JLdapUtils.returnDepartment(cityCode);
        }
        if (list == null){
            list = new ArrayList();
        }
        return list;
    }

    
    /**
     * Returns a list of LdapUserBeans 
     * @return
     */
    public List<LdapUserBean> getLdapUserBeans(String cityParam, String departmentParam){
        List<LdapUserBean> results = new ArrayList<LdapUserBean>();
        List users = JLdapUtils.returnList(cityParam, departmentParam);
        Session sess = null;
        String username = null;
        Integer status = null;
        if (users != null){
            try {
                sess = getHibernateSession();
                for (int i = 0; i < users.size(); i++){
                    UserLdap userLdap = (UserLdap)users.get(i);
                    username = userLdap.getUsername();
                    if (filtered != null){
                        if (filtered.booleanValue()){
                            username =  filterUsername(userLdap.getUsername(), "@");
                        }
                    }
                    
                    LdapUserBean userBean = new LdapUserBean();
                    userBean.setUsername(username);
                    SysUser sysUser = findUserByUsername(username, sess);
                    if (sysUser != null){
                        userBean.setCode(sysUser.getCode());
                        userBean.setRegistered(Constants.LDAP_YES);
                        Boolean enabled = sysUser.getEnabled();
                        if (enabled){
                            status = Constants.LDAP_YES;
                        }else {
                            status = Constants.LDAP_NO;
                        }
                        userBean.setStatus(status);
                    }else {
                        userBean.setRegistered(Constants.LDAP_NO);
                        userBean.setStatus(Constants.LDAP_N_A);
                    }
                    userBean.setFirstName(userBean.getFirstName());
                    userBean.setLastName(userBean.getLastName());
                    userBean.setEmail(userBean.getEmail());
                    results.add(userBean);
                }
                
            } catch (Exception e){
                logger.error(e.getMessage(), e);
            } finally {
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
            }
        }
        return results;
    }
    
    /**
     * Loads an Ldap User into the database of the CMS  
     * @return
     */
    public SysUser saveOrUpdateUser(UserLdap userLdap, SysUser sysUser, Session sess){
        
        if (userLdap != null){
            String username = userLdap.getUsername();
            if (filtered != null){
                if (filtered.booleanValue()){
                    username =  filterUsername(userLdap.getUsername(), "@");
                }
            }
            try {
                if (sysUser == null){
                    sysUser = new SysUser();
                    sysUser.setCode(null);
                }
                //PropertyUtils.copyProperties(sysUser, userLdap);
                sysUser.setFirstName(userLdap.getFirstName());
                sysUser.setLastName(userLdap.getLastName());
                sysUser.setEmail(userLdap.getEmail());
                sysUser.setName(username);
                SysRole rol = (SysRole)sess.load(SysRole.class, Constants.SYS_ROLE_SYSTEM );
                sysUser.setRole(rol);
                sysUser.setPassword(Md5.hash(username));
                sysUser.setEnabled(Globals.TRUE);
                // this parameters allows displaying the save button for editing  sys usser form properties.
                // All users which are not application users may be edited
                sysUser.setApplication(Globals.FALSE);
                SysAccessMode sysAccessMode = (SysAccessMode)sess.load(SysAccessMode.class, Constants.CLIENT_ACCESS_MODE);
                sysUser.setAccessMode(sysAccessMode);
                sess.saveOrUpdate(sysUser);
            } catch (Exception e){
                logger.error(e.getMessage(), e);
            }
        }
        return sysUser;
    }

    /**
     * Loads a group of Ldap Users into the database of the CMS.
     * if the user is not found in the database load it in the CMS database, if the user
     * is found  updates the CMS user using the UserLdap data
     * @return
     */
    public void saveLdapUsers(LdapUsersForm listForm, String idCity, String idDepartment,
             Session sess) throws Exception {
        SysUser sysUser = null;
        String username = null;
        String usernames[] = listForm.getUsernames();
        List ldapUsers = listForm.getLdapUsers();
        if (usernames != null){
            for (int i = 0; i < usernames.length; i++){
                username = usernames[i];
                sysUser = findUserByUsername(username, sess);
                //UserLdap userLdap = getUserLdap(username, idCity, idDepartment);
                List ldapUserList = findUser(ldapUsers, username);
                if (ldapUserList != null && ldapUserList.size() > 0){
                    LdapUserBean userLdap = (LdapUserBean)ldapUserList.get(0);
                    username = userLdap.getUsername();
                    if (sysUser == null){
                        sysUser = new SysUser();
                        sysUser.setCode(null);
                    }
                    sysUser.setFirstName(userLdap.getFirstName());
                    sysUser.setLastName(userLdap.getLastName());
                    sysUser.setEmail(userLdap.getEmail());
                    sysUser.setName(username);
                    SysRole rol = (SysRole)sess.load(SysRole.class, Constants.SYS_ROLE_SYSTEM );
                    sysUser.setRole(rol);
                    sysUser.setPassword(Md5.hash(username));
                    sysUser.setEnabled(Globals.TRUE);
                    sysUser.setRegistrationDate(new GregorianCalendar());
                    // this parameters allows displaying the save button for editing  sys usser form properties.
                    // All users which are not application users may be edited
                    sysUser.setApplication(Globals.FALSE);
                    SysAccessMode sysAccessMode = (SysAccessMode)sess.load(SysAccessMode.class, Constants.CLIENT_ACCESS_MODE);
                    sysUser.setAccessMode(sysAccessMode);
                    sess.saveOrUpdate(sysUser);
                    // Audit Transaction
                    if ( sysUser.getCode() == null ) {
                        SysAuditHelper.audit(action, request, sysUser, sysUser.getName(), Globals.AUDIT_INSERT, sess);
                    } else {
                        SysAuditHelper.audit(action, request, sysUser, sysUser.getName(), Globals.AUDIT_UPDATE, sess);
                    }
                    if (i % 30 == 0){
                        sess.flush();
                        sess.clear();
                    }
                }
            }
        }
    }
    
    
    public SysUser findUserByUsername(String username, Session sess) throws Exception {
        SysUser sysUser = null;
        ArrayList<String> params = new ArrayList<String>();
        ArrayList<NullableType> types = new ArrayList<NullableType>();

            StringBuffer sql = new StringBuffer();
                
            sql.append("from SysUser as sysUser ");
            sql.append("where sysUser.name = ? ");
            params.add(username);
            types.add(Hibernate.STRING);      
            
            Object[] arrayParams = params.toArray();
            Query query = sess.createQuery(sql.toString());
            for (int i = 0; i < types.size(); i++) {
                query.setParameter(i, arrayParams[i], (Type) types.get(i));
            }
            List results = query.list();
            if (results != null && results.size() > 0){
                sysUser = (SysUser)results.get(0);
            }
          return sysUser;
    }
    /*
    public static void main(String args[]){
        LdapFacade f = new LdapFacade();
        List users = f.getLdapUsers();
        for (int i = 0; i < users.size(); i++){
            UserLdap ul = (UserLdap)users.get(i);
        }
    }
    */

}
