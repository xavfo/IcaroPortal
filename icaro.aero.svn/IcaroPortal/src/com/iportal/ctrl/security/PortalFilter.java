/*
 * Created on Feb 27, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.iportal.ctrl.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.PortalBeanHelper;
import com.iportal.biz.portal.bean.MenuPortalBean;

/**
 * @author mcnovillo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PortalFilter implements Filter {
	
	private static Log logger = LogFactory.getLog(PortalFilter.class);
	
    /**
     * 
     * @uml.property name="filterConfig"
     * @uml.associationEnd 
     * @uml.property name="filterConfig" multiplicity="(0 1)"
     */
    private FilterConfig filterConfig;

	

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}


	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain)
	throws IOException, ServletException {
		
		boolean authorized = false;
		
		if (request instanceof HttpServletRequest) {
			MenuPortalBean portalBean = null;
			Long itemCode = null;
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession(false);
			if (session != null) {
				portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
			}
			
			
			try {
				itemCode = new Long(req.getParameter("itemCode"));
			} catch (Exception e) {
			}
			
			/*
			 * Comprueba si existe un id válido en la vareable itemCode.
			 * Si el link accedido es un acceso directo a un módulo, esta vareable 
			 * almacena el id de contenido que se debe cargar.
			 **/
			if (itemCode != null && itemCode.longValue()>0L) {
				/*
				 * Evalúa si es necesario si este contenido no esta asignado a Session
				 */
				boolean changeBean = false;
				if (portalBean==null ) {
					changeBean = true;
				} else if (!portalBean.getContentCode().equals(itemCode)) {
					changeBean = true;
				}
				if (changeBean) {
					try {
						portalBean = PortalBeanHelper.setNewBean(itemCode, req);
						if ( portalBean == null ){
							throw new Exception(BaseHelper.getApplicationBundleMessage("error.content.notFound", new Long[] {itemCode}));
		    			}
			    		
			    		if ( portalBean.getPortalModuleCode() == null)
			    			throw new Exception(BaseHelper.getApplicationBundleMessage("error.content.module",new Long[] {itemCode}));
			    		
			    		authorized = true;
			    		
			    		session.setAttribute(Constants.MENU_KEY, portalBean);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						authorized = false;
					}
				} else {
					authorized = true;
				}
			} else if( filterConfig != null ) {
				StringBuffer testUri1 = new StringBuffer ();
				StringBuffer testUri2 = new StringBuffer ();
				
				testUri1.append(req.getContextPath());
				testUri1.append(filterConfig.getInitParameter("exceptionPath1"));
				testUri2.append(req.getContextPath());
				testUri2.append(filterConfig.getInitParameter("exceptionPath2"));
				
				/*
				 * Si el URI solicitado esta excepto a la validación.
				 **/
				if (testUri1.toString().equals(req.getRequestURI())) {
					authorized = true;
				} else if (testUri2.toString().equals(req.getRequestURI())) {
					authorized = true;
				} else  if (portalBean != null) {
					/*
					 * Valida si ha ingresado a un módulo sin asignar a sesión el contenido principal
					 **/
					authorized = true;
				}
					
			}
		}
		
		if (authorized) {
			chain.doFilter(request, response);
		} else if (filterConfig != null) {
			String errorPage = filterConfig.getInitParameter("errorPage");
			
			if (errorPage != null && errorPage.length() > 0) {
				filterConfig.getServletContext().getRequestDispatcher(errorPage).forward(request, response);
			}
		}
	}

	
	public void destroy() {
		this.filterConfig = null;
	}

}
