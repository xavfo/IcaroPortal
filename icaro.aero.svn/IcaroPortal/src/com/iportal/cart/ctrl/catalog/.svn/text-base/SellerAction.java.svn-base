/**
 * 
 */
package com.iportal.cart.ctrl.catalog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.cart.CartConstants;
import com.iportal.cart.biz.helper.CatalogHelper;
import com.iportal.cart.model.catalog.Product;
import com.iportal.cart.model.catalog.Seller;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.action.BaseForm;

/**
 * Accion para consultar la informacion de Marcas en el portal
 * @author hernan
 * @version 1.0
 *
 */
public class SellerAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(SellerAction.class);

	
	/**
	 * Lista proveedores  para mostrar datos del subHome
	 * de Proveedores
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listSellers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//BaseForm listForm = (BaseForm) form;

		Session sess = null;
		HttpSession session = request.getSession();
		ServletContext servletContext = session.getServletContext();
		List<Seller> results = null;
		MenuPortalBean portalBean = (MenuPortalBean) session
				.getAttribute(Constants.MENU_KEY);

		try {
			
			results = (ArrayList<Seller>)servletContext.getAttribute(CartConstants.CART_CATALOG_ALL_SELLERS);
			
			if (results == null) {
				//consultar la informacion de las categorias de nivel 1 activas
				sess = getHibernateSession();
				results = CatalogHelper.listSellers(sess);
				servletContext.setAttribute(CartConstants.CART_CATALOG_ALL_SELLERS, results);
			}
			
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
		request.setAttribute("sellerList", results);
		if (results != null) {
			return mapping.findForward(Globals.FORWARD_SUCCESS.concat("_"
				+ portalBean.getLayoutForward()));
		} else {
			return mapping.findForward(Globals.FORWARD_FAILURE);
		}
		
	}
	
	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BaseForm baseForm = (BaseForm) form;
		HttpSession session = request.getSession();
		
		ServletContext servletContext = session.getServletContext();
		Session sess = null;
		Seller seller = null;
		List<Seller> results = (ArrayList<Seller>)servletContext.getAttribute(CartConstants.CART_CATALOG_ALL_SELLERS);

		try {
			if (results == null) {
                sess = getHibernateSession();
				results = CatalogHelper.listSellers(sess);
				servletContext.setAttribute(CartConstants.CART_CATALOG_ALL_SELLERS, results);
			}
			
			for (Seller s : results) {
				if (baseForm.getCode().equals(s.getCode())) {
					seller = s;
					break;
				}
			}
			
			
			//si no tiene hijos debe cargar el listado de productos.
			SearchProductForm searchForm = new SearchProductForm();
			searchForm.reset(mapping, request);
			searchForm.setSellerCode(baseForm.getCode());
			searchForm.setOrderField("regularPrice");
			searchForm.setListForward(Constants.FORWARD_SUCCESS_SELLER);
				
			//trae la lista de productos
				
			List<Product> products = CatalogHelper.listProducts(searchForm, sess);
			//Save the List of results in request scope
			request.setAttribute("productList", products);
			request.setAttribute("seller", seller);
			// Save the List of results (seller List) in request scope
			if(results != null && results.size() > 1) {
				request.setAttribute("sellerList", results);	
			}
			session.setAttribute("searchProductForm", searchForm);
			
			
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
		
		if (seller != null ) {
			return mapping.findForward(Globals.FORWARD_SUCCESS);			
		} else {
			return mapping.findForward(Globals.FORWARD_FAILURE);
		}
		
	}

}
