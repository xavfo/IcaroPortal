/*
 * Created on Feb 27, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.iportal.cart.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.iportal.Constants;
import com.iportal.cart.biz.UserBean;
import com.iportal.cart.model.customer.Customer;

/**
 * taken from ferrero
 * 
 * @author mcnovillo
 */
public class CustomerLoginFilter implements Filter {

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
		Customer customer=null;

		if (request instanceof HttpServletRequest) {
			
			
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			if (session != null) {
				UserBean icaroUser=(UserBean) session.getAttribute(Constants.CLIENT_KEY);
				
				//Customer customer = (Customer) session.getAttribute(CartConstants.CUSTOMER);
		    	if(icaroUser!=null){
		    		customer=icaroUser.getCustomer();	
		    	}
				
			
				if (customer != null) {
					authorized = true;
				}
			}
		}
            
		if (authorized) {
			chain.doFilter(request, response);
			
		} else if (filterConfig != null) {
			
            HttpServletRequest httpRequest =  (HttpServletRequest)request;
            if(httpRequest.getRequestURI().endsWith("account.do")) {
                String loginPage = filterConfig.getInitParameter("loginAccount");
                if (loginPage != null && loginPage.length() > 0) {
                    filterConfig.getServletContext().getRequestDispatcher(loginPage).forward(request, response);
                }                
            } else {
                String loginPage = filterConfig.getInitParameter("loginCustomer");
                if (loginPage != null && loginPage.length() > 0) {
                    filterConfig.getServletContext().getRequestDispatcher(loginPage).forward(request, response);
                }
            }
		}
	}
	
	public void destroy() {
		this.filterConfig = null;
	}

}
