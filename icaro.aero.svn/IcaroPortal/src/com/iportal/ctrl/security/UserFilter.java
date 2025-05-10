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

import com.iportal.Constants;
import com.iportal.model.system.SysUser;

/**
 * @author mcnovillo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserFilter implements Filter {

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
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			if (session != null) {
				SysUser user = (SysUser) session.getAttribute(Constants.SYSTEM_USER_KEY);
			
				if (user != null) {
					authorized = true;
				}
			}
		}
            
		if (authorized) {
			chain.doFilter(request, response);
		} else if (filterConfig != null) {
			String loginPage = filterConfig.getInitParameter("login");
				
			if (loginPage != null && loginPage.length() > 0) {
				filterConfig.getServletContext().getRequestDispatcher(loginPage).forward(request, response);
			}
		}
	}

	
	public void destroy() {
		this.filterConfig = null;
	}

}
