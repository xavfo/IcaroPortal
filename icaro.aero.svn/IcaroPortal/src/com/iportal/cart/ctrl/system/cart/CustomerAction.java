/*
 * Created 15/02/2007
 *	CustomerAction.java
 */
package com.iportal.cart.ctrl.system.cart;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.cart.biz.UserBean;
import com.iportal.cart.model.customer.BillInfo;
import com.iportal.cart.model.customer.Customer;
import com.iportal.cart.model.customer.HomeAddress;
import com.iportal.cart.model.customer.IDType;
import com.iportal.model.Country;
import com.yage.Globals;
import com.yage.commons.Md5;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;


/**
 * @author hernan
 * @version 1.0
 * @FIXME cuando se haga el registro de usuarios debe aumentarse la logica para
 *        guardar y actualizar la informacion de domicilio del cliente como los
 *        datos de facturacion. al momento esta clase solo consulta los datos
 *        del cliente para mostrarlos y solo permite editar el estado del
 *        cliente como un mensaje personalizado al mismo.
 * 
 * 
 * 
 */
public class CustomerAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(CustomerAction.class);


    /**
     * List todas los productos del cat�logo. Si el usuario es un administrador
     * de un proveedor, solo muestra los productos pertenecientes al proveedor
     * al cual pertene el usuario conectado.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CustomerForm listForm = (CustomerForm) form;

        Session sess = null;
        List results = null;

        if (listForm.getListItems().booleanValue()) {
            try {

                StringBuffer hql = new StringBuffer();
                ArrayList<Object> params = new ArrayList<Object>();
                ArrayList<NullableType> types = new ArrayList<NullableType>();

                hql.append(" select customer ");
                hql.append(" from Customer as customer ");
                hql.append(" where 1 = 1 ");

                if (listForm.getEnabledOption() != null
                        && listForm.getEnabledOption().intValue() > -1) {
                    hql.append(" and customer.enabled = ? ");
                    if (listForm.getEnabledOption().intValue() == Constants.TRUE_INT
                            .intValue())
                        params.add(Globals.TRUE);
                    else
                        params.add(Globals.FALSE);
                    types.add(Hibernate.BOOLEAN);
                }

                if (listForm.getGenderOption() != null
                        && listForm.getGenderOption().intValue() > -1) {
                    hql.append(" and customer.gender = ? ");
                    if (listForm.getGenderOption().intValue() == Constants.TRUE_INT
                            .intValue()) {
                        params.add(Constants.GENDER_MALE);
                    } else {
                        params.add(Constants.GENDER_FEMALE);
                    }
                    types.add(Hibernate.BOOLEAN);
                }

                if (listForm.getSuscribeBulletinOption() != null
                        && listForm.getSuscribeBulletinOption().intValue() > -1) {
                    hql.append(" and customer.suscibeBulletin = ? ");
                    if (listForm.getSuscribeBulletinOption().intValue() == Constants.TRUE_INT
                            .intValue()) {
                        params.add(Globals.TRUE);
                    } else {
                        params.add(Globals.FALSE);
                    }
                    types.add(Hibernate.BOOLEAN);
                }

                if (listForm.getIdentity() != null
                        && listForm.getIdentity().length() > 0) {
                    hql.append(" and customer.identity = ? ");
                    params.add(listForm.getIdentity().toUpperCase());
                    types.add(Hibernate.STRING);
                }

                if (listForm.getEmail() != null
                        && listForm.getEmail().length() > 0) {
                    hql.append(" and UPPER(customer.email) = ? ");
                    params.add(listForm.getEmail().toUpperCase());
                    types.add(Hibernate.STRING);
                }

                if (listForm.getAddressName() != null
                        && listForm.getAddressName().length() > 0) {
                    StringBuffer name = new StringBuffer();
                    name.append("%");
                    name.append(listForm.getAddressName().toUpperCase());
                    name.append("%");
                    hql.append(" and ( UPPER(customer.firstName) like ? ");
                    hql.append(" or  UPPER(customer.lastName) like ? ) ");
                    params.add(name.toString());
                    params.add(name.toString());
                    types.add(Hibernate.STRING);
                    types.add(Hibernate.STRING);

                }

                if (listForm.getOfficePhone() != null
                        && listForm.getOfficePhone().length() > 0) {
                    hql.append(" and customer.mobile = ? ");
                    params.add(listForm.getOfficePhone().toUpperCase());
                    types.add(Hibernate.STRING);
                }

                // solo ordenar si ya se esta cargando newsos
                if (listForm.getOrderField() != null) {
                    hql.append(" order by customer.");
                    hql.append(listForm.getOrderField());
                    if (listForm.getOrderAsc().booleanValue()) {
                        hql.append(" asc ");
                    } else {
                        hql.append(" desc ");
                    }
                }

                sess = getHibernateSession();
                Query query = sess.createQuery(hql.toString());

                for (int i = 0; i < types.size(); i++) {
                    query.setParameter(i, params.get(i), types.get(i));
                }

                query.setCacheable(true);
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
            request.setAttribute("customerList", results);
        }

        return mapping.findForward(Globals.FORWARD_LIST);
    }


//    public ActionForward register(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//
//        Session sess = null;
//
//        try {
//            sess = getHibernateSession();
//
////            results = query.list();
//
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            if (sess != null)
//                try {
//                    sess.clear();
//                    sess.close();
//                } catch (Exception e) {
//                }
//        }
//
//        return mapping.findForward("delivery");
//
//    }


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
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (isCancelled(request)) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }
        CustomerForm customerForm = (CustomerForm) form;

        customerForm.reset(mapping, request);

        return mapping.findForward(Globals.FORWARD_FORM);
    }


    /**
     * Read action
     * 
     * Si el usuario es de tipo proveedor, solo permite lectura de productos que
     * pertenezcan a la empresa proveedora del usuario conectado.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward read(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (isCancelled(request)) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }
        ActionMessages messages = new ActionMessages();

        // Gets the action form
        CustomerForm customerForm = (CustomerForm) form;

        Session sess = null;
        Customer customer = null;

        try {
            sess = getHibernateSession();

            customer = (Customer) sess.load(Customer.class, customerForm
                    .getCode());

            PropertyUtils.copyProperties(customerForm, customer);

            //Hibernate.initialize(customer.getAddresses());

        } catch (Exception e) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "message.failure.msg"));
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
        request.setAttribute("customer", customer);

        return mapping.findForward(Globals.FORWARD_FORM);
    }


    /**
     * Save action
     * 
     * Si el usuario es de tipo proveedor, solo permite la edici�n y
     * actualizaci�n de productos de la empresa a la cual el pertence.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (isCancelled(request)) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

        ActionMessages messages = new ActionMessages();

        // Gets the action form
        CustomerForm customerForm = (CustomerForm) form;

        this.saveItem(request, customerForm, messages);

        // Report any messages we have discovered back to the original form
        if (!messages.isEmpty()) {
            saveMessages(request, messages);
            return (mapping.getInputForward());
        }

        // Report a success action
        messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage(
                "message.success"));
        saveMessages(request, messages);

        return mapping.findForward(Globals.FORWARD_SUCCESS);

    }


    /**
     * Apply action
     * 
     * Si el usuario es de tipo proveedor, solo permite la edici�n y
     * actualizaci�n de productos de la empresa a la cual el pertence.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward apply(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (isCancelled(request)) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

        ActionMessages messages = new ActionMessages();

        // Gets the action form
        CustomerForm customerForm = (CustomerForm) form;

        Customer cust = this.updateItem(request, customerForm, messages);
        

        // Report any messages we have discovered back to the original form
        if (!messages.isEmpty()) {
            saveMessages(request, messages);
            return (mapping.getInputForward());
        }

        if (cust != null) {
        	UserBean icaroUser=(UserBean)request.getSession().getAttribute(Constants.CLIENT_KEY);
        	if ( icaroUser != null) {
        		icaroUser.setCustomer(cust);
        		icaroUser.setName(cust.getUserName());
        	}
        	request.setAttribute("sendMail", Globals.TRUE);
        	request.setAttribute("customer", cust);
        	request.setAttribute("newPassword", customerForm.getPasswordVerify());
        }

        // Report a success action
        messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage(
                "message.success"));
        saveMessages(request, messages);

        return mapping.findForward(Globals.FORWARD_APPLY);

    }


    /**
     * Guarda los cambios efectuados en un producto. Si el usuario es de tipo
     * proveedor, solo permite la edici�n y actualizaci�n de productos de la
     * empresa a la cual el pertence.
     * 
     * @param request
     * @param productForm
     * @param messages
     * @throws Exception
     */
    protected void saveItem(HttpServletRequest request,
            CustomerForm customerForm, ActionMessages messages)
            throws Exception {

        Session sess = null;
        Transaction tx = null;
        Customer customer = null;
        Calendar now = new GregorianCalendar();
        HomeAddress homeAddress = null;
        BillInfo billInfo = null;
        try {
            sess = this.getHibernateSession();
            

            if (customerForm.getCode() != null
                    && customerForm.getCode().longValue() != 0L) {
                customer = (Customer) sess.load(Customer.class, customerForm
                        .getCode());
                homeAddress = customer.getHomeAddress();
                billInfo = customer.getBillInfo();
            } else {
                customer = new Customer();
                customerForm.setCode(null);
                customer.setCreation(now);
                customer.setEnabled(Globals.TRUE);
                homeAddress = new HomeAddress();
                customer.setHomeAddress(homeAddress);
                
                billInfo = new BillInfo();
                customer.setBillInfo(billInfo);
            }
            PropertyUtils.copyProperties(homeAddress, customerForm);
            homeAddress.setCountry((Country)sess.get(Country.class, Long.valueOf(customerForm.getCountryCode())));
            billInfo.setAddress(customerForm.getAddress());
            billInfo.setIdentity(customerForm.getIdentity());
            billInfo.setName(customerForm.getFirstName() + " " + customerForm.getLastName());
            billInfo.setPhone(customerForm.getPhone());

            String oldPassword = customer.getPassword();
            String oldEmail = customer.getEmail();
            PropertyUtils.copyProperties(customer, customerForm);     
            if(customerForm.getGenderOption()==1){
            	customer.setGender(Globals.TRUE);
            }
            else{
            	customer.setGender(Globals.FALSE);
            }
            	
            customer.setIdType((IDType)sess.get(IDType.class, Long.valueOf(customerForm.getIdTypeCode())));
            if(null != customerForm.getPassword()) {
                customer.setPassword(Md5.hash(customerForm.getPassword()));
            } else {
                customer.setPassword(oldPassword);
            }

            // en el perfil del usuario valida si el customer ingresa un email diferente al que tiene actualmente
            boolean uniqueMail= true;
            boolean uniqueUser= true;
            if (oldEmail != null){
                if (!oldEmail.equalsIgnoreCase(customerForm.getEmail())){
                    uniqueMail = testEmailUnique(sess, customer.getEmail(), customerForm.getCode());
                }
            } else {// se trata de un registro de usuario
                uniqueMail = testEmailUnique(sess, customer.getEmail(), customerForm.getCode());
                uniqueUser = testUserNameUnique(sess, customer.getUserName(), customerForm.getCode());                
            }
                
            if (!uniqueMail){
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.alreadyExists", customer.getEmail()));
                saveMessages(request, messages);
                return;
            }
            if (!uniqueUser){
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.alreadyExists", customer.getUserName()));
                saveMessages(request, messages);
                return;
            }
            
            
            customer.setLastModified(now);
            // Persist data
            tx = sess.beginTransaction();
            sess.saveOrUpdate(customer);
            tx.commit();

            // Audit Transaction
            if (customerForm.getCode() == null) {
                SysAuditHelper.audit(this, request, customer, customer
                        .getFullName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(this, request, customer, customer
                        .getFullName(), Globals.AUDIT_UPDATE);
            }
            customerForm.setCode(customer.getCode());

        } catch (Exception e) {

            boolean putMesage = checkControlledError(e, messages, BaseHelper
                    .getApplicationBundleMessage("prompt.product", this
                            .getLocale(request)), customer.getFullName(), null);
            if (tx != null) {
                tx.rollback();
            }
            // si no se ha escrito ningun error colocar el error generico
            if (!putMesage) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "message.failure.msg", e.getMessage()));
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
     * Actualiza los datos del usuario del portal viene de apply
     * a diferencia de saveItem no verifica si el mail y el usuario existen
     * 
     * @param request
     * @param productForm
     * @param messages
     * @throws Exception
     */
    protected Customer updateItem(HttpServletRequest request,
            CustomerForm customerForm, ActionMessages messages)
            throws Exception {

        Session sess = null;
        Transaction tx = null;
        Customer customer = null;
        Calendar now = new GregorianCalendar();

        try {
            sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
           CustomerForm custForm = (CustomerForm) customerForm;
           
           customer = (Customer) sess.load(Customer.class, custForm
                        .getCode());
           
            String oldPassword = customer.getPassword();

            HomeAddress  homeAddress = customer.getHomeAddress();
            BillInfo billInfo = customer.getBillInfo();
            String oldEmail = customer.getEmail();
            String oldUser  = customer.getUserName();
            
            PropertyUtils.copyProperties(homeAddress, customerForm);
            homeAddress.setCountry((Country)sess.get(Country.class, Long.valueOf(customerForm.getCountryCode())));
            billInfo.setAddress(customerForm.getAddress());
            billInfo.setIdentity(customerForm.getIdentity());
            billInfo.setName(customerForm.getFirstName() + " " + customerForm.getLastName());
            billInfo.setPhone(customerForm.getPhone());

            PropertyUtils.copyProperties(customer, custForm);
            if(customerForm.getGenderOption()==1){
            	customer.setGender(Globals.TRUE);
            }
            else{
            	customer.setGender(Globals.FALSE);
            }
            customer.setIdType((IDType)sess.get(IDType.class, Long.valueOf(custForm.getIdTypeCode())));
            
            if(null != customerForm.getPasswordVerify()) {
                customer.setPassword(Md5.hash(customerForm.getPasswordVerify()));
            } else {
                customer.setPassword(oldPassword);
            }

            
            

            // en el perfil del usuario valida si el customer ingresa un email diferente al que tiene actualmente
            boolean uniqueMail= true;
            boolean uniqueUser= true;
            if (oldEmail != null || oldUser!= null ){
                if (!oldEmail.equalsIgnoreCase(customerForm.getEmail())){
                    uniqueMail = testEmailUnique(sess, customer.getEmail(), customerForm.getCode());
                }
                if (!oldUser.equalsIgnoreCase(customerForm.getUserName())){
                	uniqueUser = testUserNameUnique(sess, customer.getUserName(), customerForm.getCode());
                }

            } else {// se trata de un registro de usuario
                uniqueMail = testEmailUnique(sess, customer.getEmail(), customerForm.getCode());
                uniqueUser = testUserNameUnique(sess, customer.getUserName(), customerForm.getCode());                
            }
                
            if (!uniqueMail){
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.alreadyExists", customer.getEmail()));
                saveMessages(request, messages);
                return null;
            }
            if (!uniqueUser){
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.alreadyExists", customer.getUserName()));
                saveMessages(request, messages);
                return null;
            }
            

            if (messages.isEmpty()) {
	            customer.setLastModified(now);
	            // Persist data
	            tx = sess.beginTransaction();
	            sess.saveOrUpdate(customer);
	            tx.commit();
	
	            // Audit Transaction
	            if (custForm.getCode() == null) {
	                SysAuditHelper.audit(this, request, customer, customer
	                        .getFullName(), Globals.AUDIT_INSERT);
	            } else {
	                SysAuditHelper.audit(this, request, customer, customer
	                        .getFullName(), Globals.AUDIT_UPDATE);
	            }
            } else {
            	return null;
            }
           // custForm.setCode(customer.getCode());

        } catch (Exception e) {

            boolean putMesage = checkControlledError(e, messages, BaseHelper
                    .getApplicationBundleMessage("prompt.product", this
                            .getLocale(request)), customer.getFullName(), null);
            if (tx != null) {
                tx.rollback();
            }
            // si no se ha escrito ningun error colocar el error generico
            if (!putMesage) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "message.failure.msg", e.getMessage()));
            }
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            if (sess != null)
                try {
                    sess.close();
                } catch (Exception e) {
                }
        }
        return customer;

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
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (isCancelled(request)) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

        ActionMessages messages = new ActionMessages();

        // Gets the action form
        CustomerForm customerForm = (CustomerForm) form;

        Session sess = null;
        Transaction tx = null;
        Customer customer = null;
        Integer jdbcBatchSize = this.getJdbcBatchSize();

        try {
            Long codes[] = customerForm.getCodes();

            if (codes != null && codes.length > 0) {
                sess = SessionFactoryManager
                        .getSession(HibernatePlugIn.DEFAULT_ID);

                tx = sess.beginTransaction();
                for (int i = 0; i < codes.length; i++) {
                    customer = (Customer) sess.load(Customer.class, codes[i]);
                    sess.delete(customer);
                    SysAuditHelper.audit(this, request, customer, customer
                            .getFullName(), Globals.AUDIT_DELETE, sess);

                    if (i % jdbcBatchSize == 0) {
                        // flush a batch of updates and release memory:
                        sess.flush();
                        sess.clear();
                    }

                }
                tx.commit();
            }

        } catch (Exception e) {
            boolean putMesage = checkControlledError(e, messages, BaseHelper
                    .getApplicationBundleMessage("prompt.product", this
                            .getLocale(request)), customer.getFullName(), null);

            if (tx != null) {
                tx.rollback();
            }
            // si no se ha escrito ningun error colocar el error generico
            if (!putMesage) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "message.failure.msg", e.getMessage()));
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
        messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage(
                "message.success"));
        saveMessages(request, messages);

        return mapping.findForward(Globals.FORWARD_SUCCESS);
    }


    public boolean testEmailUnique(Session sess, String email, Long customerCode){
    	boolean unique = true;
    	
    	Long count = null;
    	StringBuffer sql = new StringBuffer();
    	sql.append("select count(cust.code) ");
    	sql.append(" from Customer cust");
    	sql.append(" where cust.email = ?");
    	if (customerCode != null) {
    		sql.append(" and cust.code != ?");	
    	}
    	
    	
    	try {
            Query query = sess.createQuery(sql.toString());
            query.setString(0, email);
            if(customerCode!= null) {
            	query.setLong(1, customerCode);
            }
            query.setCacheable(true);
            
            count = (Long)query.uniqueResult();
    	}catch (Exception e){
    		logger.error(e.getMessage(), e);
    	}
    	if( count!=null && count>0) {
            unique = false;
    	}
    	//System.out.println(unique);
    	return unique;
    }
    
    public boolean testUserNameUnique(Session sess, String userName, Long customerCode){
    	boolean unique = true;
    	Long count = null;
    	StringBuffer sql = new StringBuffer();
    	sql.append("select count(cust.code) ");
    	sql.append(" from Customer cust");
    	sql.append(" where cust.userName = ?");
    	if (customerCode != null) {
    		sql.append(" and cust.code != ?");	
    	}

    	try {
            Query query = sess.createQuery(sql.toString());
            query.setString(0, userName);
            if(customerCode!= null) {
            	query.setLong(1, customerCode);
            }
            query.setCacheable(true);
            
            count = (Long)query.uniqueResult();
    	}catch (Exception e){
    		logger.error(e.getMessage(), e);
    	}
    	if( count!=null && count>0) {
            unique = false;
    	}
    	//System.out.println(unique);
    	return unique;
    }

}
