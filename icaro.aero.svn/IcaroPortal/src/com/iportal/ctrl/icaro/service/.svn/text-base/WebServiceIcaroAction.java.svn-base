/*
 * Created on May 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.ctrl.icaro.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Query;
import org.hibernate.Session;

import aero.icaro.service.IcaroDocBean;
import aero.icaro.service.IcaroServiceMessageBean;
import aero.icaro.service.IcaroUserBean;
import aero.icaro.service.agency.AgencyAccountSummaryBean;
import aero.icaro.service.agency.AgencyServiceManager;
import aero.icaro.service.cargo.IcargoServiceManager;
import aero.icaro.service.corp.CorpAccountSummaryBean;
import aero.icaro.service.corp.CorpServiceManager;
import aero.icaro.service.skies.SkiesAccountSummaryBean;
import aero.icaro.service.skies.SkiesServiceManager;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.cart.biz.UserBean;
import com.iportal.ctrl.icaro.ClientWebServiceCall;
import com.iportal.ctrl.portal.content.ContentAction;
import com.iportal.model.portal.Content;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;

/**
 * @author fernandoT
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class WebServiceIcaroAction extends ContentAction implements
		ClientWebServiceCall {

	//private static Log logger = LogFactory.getLog(WebServiceIcaroAction.class);

	private SkiesServiceManager managerSkies;
	
	private CorpServiceManager managerCorp;
	
	private AgencyServiceManager managerAgency;
	
	private	IcargoServiceManager managerCargo;
	

	public void initManagerSkies(HttpServletRequest request) throws ServiceException {
		if (this.managerSkies == null) {
			this.managerSkies = new SkiesServiceManager(this, request);
		} else {
			this.managerSkies.setRequest(null);
			this.managerSkies.setRequest(request);
		}
	}

	public void initManagerCorp(HttpServletRequest request) throws ServiceException {
		if (this.managerCorp == null) {
			this.managerCorp = new CorpServiceManager(this, request);
		} else {
			this.managerCorp.setRequest(null);
			this.managerCorp.setRequest(request);
		}
	}
	
	public void initManagerAgency(HttpServletRequest request) throws ServiceException {
		if (this.managerAgency == null) {
			this.managerAgency = new AgencyServiceManager(this, request);
		} else {
			this.managerAgency.setRequest(null);
			this.managerAgency.setRequest(request);
		}
	}
	
	public void initManagerCargo(HttpServletRequest request) throws ServiceException {
		if (this.managerCargo == null) {
			this.managerCargo = new IcargoServiceManager(this, request);
		} else {
			this.managerCargo.setRequest(null);
			this.managerCargo.setRequest(request);
		}
	}

	public ActionForward icargoEstado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	//	Content content=null;
		HttpSession session = request.getSession();
	//	Session sess = getHibernateSession();
		IcaroDocBean icaroDoc=null;
		ActionMessages errors = new ActionMessages();
		String docType = request.getParameter("docType");
		String docNumber = request.getParameter("docNumber");
		
		this.initManagerCargo(request);
		icaroDoc=this.managerCargo.icargoEstado(docType,docNumber);
		
		
		if (icaroDoc == null) {
			errors.add(ActionMessages.GLOBAL_MESSAGE,
					new ActionMessage("error.webService.client.login"));
		} else if (icaroDoc.getStatusCode().intValue() == Constants.FALSE_INT) {
			errors.add(ActionMessages.GLOBAL_MESSAGE,
					new ActionMessage(
							"error.webService.client.message.response",
							icaroDoc.getResponse()));
		}


		
		if (errors.isEmpty()) {
			Calendar calendar = new GregorianCalendar();
			String today=DateFormatUtils.format(calendar.getTime(), Constants.DATE_TIME_FORMAT);
			icaroDoc.setNumberDoc(docNumber);
			session.setAttribute("icaroDoc", icaroDoc);			
			session.setAttribute("today", today);				
		}
		
		if (null != errors && !errors.isEmpty()) {
			saveMessages(request, errors);
		}
		
		
		return mapping.findForward(Globals.FORWARD_SUCCESS+"_"+Globals.FORWARD_CARGO);

	
}
	
	public ActionForward skiesLogin(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			HttpSession session = request.getSession();
			UserBean icaroUser=(UserBean) session.getAttribute(Constants.CLIENT_KEY);
			IcaroUserBean user = null;			
			ActionMessages errors = new ActionMessages();
			Content content=null;
			Session sess = getHibernateSession();
			MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
			content = (Content) sess.get(Content.class, portalBean.getContentCode());
			request.setAttribute("content",content);
			if(null!=icaroUser){
				if(icaroUser.getCustomerType()!=Constants.CLIENT_SKIES_KEY){
					icaroUser=null;
				}
			}
			if (null == icaroUser) {						
									
				
				// Get cardNumber/password
				String cardNumber = (String) PropertyUtils.getSimpleProperty(form,"cardNumber");
				String password = (String) PropertyUtils.getSimpleProperty(form,"password");
	
				if ((null == cardNumber || 0 == cardNumber.trim().length())
						|| (null == password || 0 == password.trim().length())) {
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.webService.client.notLogged"));
				} else {
	
					// inicia el manager para servicios web
					this.initManagerSkies(request);
					user = this.managerSkies.skiesLogin(cardNumber, password);
					if (user == null) {
						errors.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("error.webService.client.login"));
					} else if (user.getStatusCode().intValue() == Constants.FALSE_INT) {
						errors.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage(
										"error.webService.client.loginFailed",
										user.getResponse()));
					}
	
	
					
						if (errors.isEmpty()) {
							request.setAttribute("code",portalBean.getContentCode());
							user.setCardNumber(cardNumber);
							user.setPassword(password);
							user.setUserType(Constants.CLIENT_SKIES_KEY);
							UserBean iUser=new UserBean(user, Constants.CLIENT_SKIES_KEY, user.getCardNumber());
							session.setAttribute(Constants.CLIENT_KEY, iUser);
	
						}
	
				}
			if (null != errors && !errors.isEmpty()) {
				saveMessages(request, errors);
				return mapping.findForward(Globals.FORWARD_SUCCESS+"_"+portalBean.getLayoutForward());
			}
	
			
			
		}
			
			return mapping.findForward(Globals.FORWARD_PRINCIPAL+"_"+portalBean.getLayoutForward());
			
	
	}

	public ActionForward corpLogin(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			HttpSession session = request.getSession();
			UserBean icaroUser=(UserBean) session.getAttribute(Constants.CLIENT_KEY);
			IcaroUserBean user = null;
			ActionMessages errors = new ActionMessages();
			Content content=null;
			Session sess = getHibernateSession();
			MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
			content = (Content) sess.get(Content.class, portalBean.getContentCode());
			request.setAttribute("content",content);
			if(null!=icaroUser){
				if(icaroUser.getCustomerType()!=Constants.CLIENT_CORP_KEY){
					icaroUser=null;
				}
			}
			if (null == icaroUser) {
				
					
				// Get cardNumber/password
				String cardNumber = (String) PropertyUtils.getSimpleProperty(form,"cardNumber");
				String password = (String) PropertyUtils.getSimpleProperty(form,"password");
	
				if ((null == cardNumber || 0 == cardNumber.trim().length())
						|| (null == password || 0 == password.trim().length())) {
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.webService.client.notLogged"));
				} else {
	
					// inicia el manager para servicios web
					this.initManagerCorp(request);
					user = this.managerCorp.corpLogin(cardNumber, password);
					if (user == null) {
						errors.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("error.webService.client.login"));
					} else if (user.getStatusCode().intValue() == Constants.FALSE_INT) {
						errors.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage(
										"error.webService.client.loginFailed",
										user.getResponse()));
					}
	
	
					
						if (errors.isEmpty()) {
							request.setAttribute("code",portalBean.getContentCode());
							user.setCardNumber(cardNumber);
							user.setPassword(password);
							user.setUserType(Constants.CLIENT_CORP_KEY);							
							UserBean iUser=new UserBean(user, Constants.CLIENT_CORP_KEY, user.getCardNumber());
							session.setAttribute(Constants.CLIENT_KEY, iUser);
	
						}
	
				}
			if (null != errors && !errors.isEmpty()) {
				saveMessages(request, errors);
				return mapping.findForward(Globals.FORWARD_SUCCESS+"_"+portalBean.getLayoutForward());
			}
	
			
	
		}
			
			return mapping.findForward(Globals.FORWARD_PRINCIPAL+"_"+portalBean.getLayoutForward());
			
	}

	public ActionForward agencyLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		UserBean icaroUser=(UserBean) session.getAttribute(Constants.CLIENT_KEY);
		IcaroUserBean user = null;
		ActionMessages errors = new ActionMessages();
		Content content=null;
		Session sess = getHibernateSession();
		MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
		content = (Content) sess.get(Content.class, portalBean.getContentCode());
		request.setAttribute("content",content);
		
		if(null!=icaroUser){
			if(icaroUser.getCustomerType()!=Constants.CLIENT_AGENCY_KEY){
				icaroUser=null;
			}
		}
		
		if (null == user) {			
			
			// Get cardNumber/password
			String cardNumber = (String) PropertyUtils.getSimpleProperty(form,"cardNumber");
			String password = (String) PropertyUtils.getSimpleProperty(form,"password");

			if ((null == cardNumber || 0 == cardNumber.trim().length())
					|| (null == password || 0 == password.trim().length())) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.webService.client.notLogged"));
			} else {

				// inicia el manager para servicios web
				this.initManagerAgency(request);
				user = this.managerAgency.agencyLogin(cardNumber, password);
				if (user == null) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage("error.webService.client.login"));
				} else if (user.getStatusCode().intValue() == Constants.FALSE_INT) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage(
									"error.webService.client.loginFailed",
									user.getResponse()));
				}


				
					if (errors.isEmpty()) {
						request.setAttribute("code",portalBean.getContentCode());
						user.setCardNumber(cardNumber);
						user.setPassword(password);
						user.setUserType(Constants.CLIENT_AGENCY_KEY);						
						UserBean iUser=new UserBean(user, Constants.CLIENT_AGENCY_KEY, user.getCardNumber());
						session.setAttribute(Constants.CLIENT_KEY, iUser);

					}

			}
		if (null != errors && !errors.isEmpty()) {
			saveMessages(request, errors);
			return mapping.findForward(Globals.FORWARD_SUCCESS+"_"+portalBean.getLayoutForward());
		}

		

	}	
		
		return mapping.findForward(Globals.FORWARD_PRINCIPAL+"_"+portalBean.getLayoutForward());
		
}

	public ActionForward accountSkies(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			Content content=null;
			HttpSession session = request.getSession();
			Session sess = getHibernateSession();
			UserBean icaroUser=(UserBean) session.getAttribute(Constants.CLIENT_KEY);
			IcaroUserBean user = icaroUser.getIcaroUserBean();
			IcaroServiceMessageBean accountDetail=null;
			SkiesAccountSummaryBean skiesAccountSummary=null;
			ActionMessages errors = new ActionMessages();
			
			this.initManagerSkies(request);
			skiesAccountSummary=this.managerSkies.getSkiesAccountSummary(user.getCardNumber());
			accountDetail=this.managerSkies.getSkiesAccountDetail(user.getCardNumber());
			Long code = (Long) PropertyUtils.getSimpleProperty(form,"code");
			
			content = (Content) sess.get(Content.class, code);
			request.setAttribute("content",content);
			
			if (skiesAccountSummary == null) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("error.webService.client.login"));
			} else if (skiesAccountSummary.getStatusCode().intValue() == Constants.FALSE_INT) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage(
								"error.webService.client.message.response",
								skiesAccountSummary.getResponse()));
			}
	
	
			
			if (errors.isEmpty()) {
				Calendar calendar = new GregorianCalendar();
				String today=DateFormatUtils.format(calendar.getTime(), Constants.DATE_TIME_FORMAT);
				if(accountDetail.getResponse()!=null){
					skiesAccountSummary.setUrlDetail(accountDetail.getResponse());
				}
				session.setAttribute("accountInfo", skiesAccountSummary);
				session.setAttribute("user", user);
				session.setAttribute("today", today);				
			}
			
			
			
			
			return mapping.findForward(Globals.FORWARD_ACCOUNT+"_"+Globals.FORWARD_SKIES);
	
		
	}

	public ActionForward accountCorp(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			Content content=null;
			HttpSession session = request.getSession();
			Session sess = getHibernateSession();
			UserBean icaroUser=(UserBean) session.getAttribute(Constants.CLIENT_KEY);
			IcaroUserBean user = icaroUser.getIcaroUserBean();
			IcaroServiceMessageBean accountDetail=null;
			CorpAccountSummaryBean corpAccountSummary=null;
			ActionMessages errors = new ActionMessages();
			
			this.initManagerCorp(request);
			corpAccountSummary=this.managerCorp.getCorpAccountSummary(user.getCardNumber());
			accountDetail=this.managerCorp.getCorpAccountDetail(user.getCardNumber());
			Long code = (Long) PropertyUtils.getSimpleProperty(form,"code");
			
			content = (Content) sess.get(Content.class, code);
			request.setAttribute("content",content);
			
			if (corpAccountSummary == null) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("error.webService.client.login"));
			} else if (corpAccountSummary.getStatusCode().intValue() == Constants.FALSE_INT) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage(
								"error.webService.client.message.response",
								corpAccountSummary.getResponse()));
			}
	
	
			
			if (errors.isEmpty()) {
				Calendar calendar = new GregorianCalendar();
				String today=DateFormatUtils.format(calendar.getTime(), Constants.DATE_TIME_FORMAT);
				if(accountDetail.getResponse()!=null){
					corpAccountSummary.setUrlDetail(accountDetail.getResponse());
				}
				session.setAttribute("accountInfo", corpAccountSummary);
				session.setAttribute("user", user);
				session.setAttribute("today", today);				
			}
			
			
			
			
			return mapping.findForward(Globals.FORWARD_ACCOUNT+"_"+Globals.FORWARD_CORP);
	
		
	}

	public ActionForward accountAgency(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			Content content=null;
			HttpSession session = request.getSession();
			Session sess = getHibernateSession();
			UserBean icaroUser=(UserBean) session.getAttribute(Constants.CLIENT_KEY);
			IcaroUserBean user = icaroUser.getIcaroUserBean();
			IcaroServiceMessageBean accountDetail=null;
			AgencyAccountSummaryBean agencyAccountSummary=null;
			ActionMessages errors = new ActionMessages();
			
			this.initManagerCorp(request);
			agencyAccountSummary=this.managerAgency.getAgencyAccountSummary(user.getCardNumber());
			accountDetail=this.managerAgency.getAgencyAccountDetail(user.getCardNumber());
			Long code = (Long) PropertyUtils.getSimpleProperty(form,"code");
			
			content = (Content) sess.get(Content.class, code);
			request.setAttribute("content",content);
			
			if (agencyAccountSummary == null) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("error.webService.client.login"));
			} else if (agencyAccountSummary.getStatusCode().intValue() == Constants.FALSE_INT) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage(
								"error.webService.client.message.response",
								agencyAccountSummary.getResponse()));
			}


			
			if (errors.isEmpty()) {
				Calendar calendar = new GregorianCalendar();
				String today=DateFormatUtils.format(calendar.getTime(), Constants.DATE_TIME_FORMAT);
				if(accountDetail.getResponse()!=null){
					agencyAccountSummary.setUrlDetail(accountDetail.getResponse());
				}
				session.setAttribute("accountInfo", agencyAccountSummary);
				session.setAttribute("user", user);
				session.setAttribute("today", today);				
			}
						
			
			return mapping.findForward(Globals.FORWARD_ACCOUNT+"_"+Globals.FORWARD_AGENCY);
	
		
	}

	public ActionForward updateAccountSkies(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			Content content=null;
			HttpSession session = request.getSession();
			Session sess = getHibernateSession();
			MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
			UserBean icaroUser=(UserBean) session.getAttribute(Constants.CLIENT_KEY);
			IcaroUserBean user = icaroUser.getIcaroUserBean();
			IcaroServiceMessageBean accountUpdate=null;
			ServiceAccountForm accountForm=(ServiceAccountForm)form;
			ActionMessages errors = new ActionMessages();
			
			this.initManagerSkies(request);
			accountUpdate=this.managerSkies.updateSkiesAccount(accountForm);
			Long code = (Long) PropertyUtils.getSimpleProperty(form,"code");
			
			content = (Content) sess.get(Content.class, code);
			request.setAttribute("content",content);
			
			if (accountUpdate == null) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("error.webService.client.login"));
			} else if (accountUpdate.getStatusCode().intValue() == Constants.FALSE_INT) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage(
								"error.webService.client.message.response",
								accountUpdate.getResponse()));
			}
	
	
			
			if (errors.isEmpty()) {		
		
				session.setAttribute("user", user);
						
			}		
			
			
			return mapping.findForward(Globals.FORWARD_UPDATE_ACCOUNT+"_"+portalBean.getLayoutForward());
	
		
	}

	public ActionForward updateAccountCorp(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			Content content=null;
			HttpSession session = request.getSession();
			Session sess = getHibernateSession();
			MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
			UserBean icaroUser=(UserBean) session.getAttribute(Constants.CLIENT_KEY);
			IcaroUserBean user = icaroUser.getIcaroUserBean();
			IcaroServiceMessageBean accountUpdate=null;
			ServiceAccountForm accountForm=(ServiceAccountForm)form;
			ActionMessages errors = new ActionMessages();
			
			this.initManagerSkies(request);
			accountUpdate=this.managerCorp.updateCorpAccount(accountForm);
			Long code = (Long) PropertyUtils.getSimpleProperty(form,"code");
			
			content = (Content) sess.get(Content.class, code);
			request.setAttribute("content",content);
			
			if (accountUpdate == null) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("error.webService.client.message.response"));
			} else if (accountUpdate.getStatusCode().intValue() == Constants.FALSE_INT) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage(
								"error.webService.client.loginFailed",
								accountUpdate.getResponse()));
			}
	
	
			
			if (errors.isEmpty()) {		
		
				session.setAttribute("user", user);
						
			}
			return mapping.findForward(Globals.FORWARD_UPDATE_ACCOUNT+"_"+portalBean.getLayoutForward());		
	}

	public ActionForward updateAccountAgency(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			Content content=null;
			HttpSession session = request.getSession();
			Session sess = getHibernateSession();
			MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
			UserBean icaroUser=(UserBean) session.getAttribute(Constants.CLIENT_KEY);
			IcaroUserBean user = icaroUser.getIcaroUserBean();
			IcaroServiceMessageBean accountUpdate=null;
			ServiceAccountForm accountForm=(ServiceAccountForm)form;
			ActionMessages errors = new ActionMessages();
			
			this.initManagerSkies(request);
			accountUpdate=this.managerAgency.updateAgencyAccount(accountForm);
			Long code = (Long) PropertyUtils.getSimpleProperty(form,"code");
			
			content = (Content) sess.get(Content.class, code);
			request.setAttribute("content",content);
			
			if (accountUpdate == null) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("error.webService.client.login"));
			} else if (accountUpdate.getStatusCode().intValue() == Constants.FALSE_INT) {
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage(
								"error.webService.client.message.response",
								accountUpdate.getResponse()));
			}
		
			if (errors.isEmpty()) {		
		
				session.setAttribute("user", user);
						
			}
			
			return mapping.findForward(Globals.FORWARD_UPDATE_ACCOUNT+"_"+portalBean.getLayoutForward());
	
	}

	public ActionForward read(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			HttpSession session = request.getSession();
			Session sess = getHibernateSession();			
			Content content=null;
			MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
			content = (Content) sess.get(Content.class, portalBean.getContentCode());
			request.setAttribute("content",content);
			UserBean icaroUser=(UserBean) session.getAttribute(Constants.CLIENT_KEY);
			
			
			if(icaroUser!=null){
				//IcaroUserBean user = icaroUser.getIcaroUserBean();
			//Comprueba si hay un cliente logeado en la seccion a la que se trata de acceder
			if(portalBean.getLayoutForward().equals(Globals.FORWARD_SKIES)&& icaroUser.getCustomerType()==Constants.CLIENT_SKIES_KEY){
				return mapping.findForward(Globals.FORWARD_PRINCIPAL+"_"+portalBean.getLayoutForward());
			}else if(portalBean.getLayoutForward().equals(Globals.FORWARD_AGENCY)&& icaroUser.getCustomerType()==Constants.CLIENT_AGENCY_KEY){
				return mapping.findForward(Globals.FORWARD_PRINCIPAL+"_"+portalBean.getLayoutForward());
			}else if(portalBean.getLayoutForward().equals(Globals.FORWARD_CORP)&& icaroUser.getCustomerType()==Constants.CLIENT_CORP_KEY){
				return mapping.findForward(Globals.FORWARD_PRINCIPAL+"_"+portalBean.getLayoutForward());
			}else{
				return mapping.findForward(Globals.FORWARD_SUCCESS+"_"+portalBean.getLayoutForward());
			}
			}else{
				return mapping.findForward(Globals.FORWARD_SUCCESS+"_"+portalBean.getLayoutForward());
			}
		
	}

	public ActionForward principal(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			//HttpSession session = request.getSession();
			Session sess = null;
	        Query query = null;
	        Content content=null;
			/*IcaroUserBean user =null;
			Content content=null;*/
	        ServiceAccountForm serviceForm=(ServiceAccountForm)form;
			String forwardString=null;
			//serviceForm.getCode();
			//ActionForward forward = super.read(mapping, form, request, response);
			//MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
	        try {
	        	sess = getHibernateSession();
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("from Content as content ");
	    		sql.append("where content.code = ? ");	    		
	    		query = sess.createQuery(sql.toString());
	    		query.setLong(0, serviceForm.getCode());
	            content=(Content)query.uniqueResult();
	            
			} catch(Exception e) {
			} finally {
				if (sess != null)
					try {
						sess.clear();
						sess.close();
					} catch (Exception e) {
					}
			}
			
			//Comprueba para que tipo de servicio
			if(serviceForm.getServiceType().equals(Constants.WEBSERVICE_SKIES)){
				forwardString=Globals.FORWARD_SKIES;
			}else if(serviceForm.getServiceType().equals(Constants.WEBSERVICE_AGENCY)){
				forwardString=Globals.FORWARD_AGENCY;
			}else if(serviceForm.getServiceType().equals(Constants.WEBSERVICE_CORP)){
				forwardString=Globals.FORWARD_CORP;
			}			
			if(content.getLayout().getForward().equals("account_buys")||content.getLayout().getForward().equals("search_flight")){
				return mapping.findForward(content.getLayout().getForward());
			}else{
				return mapping.findForward(content.getLayout().getForward()+"_"+forwardString);	
			}
				
			
			
			
		
	}
}
