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
import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.cart.CartConstants;
import com.iportal.cart.biz.helper.CatalogHelper;
import com.iportal.cart.model.catalog.Brand;
import com.iportal.cart.model.catalog.Category;
import com.iportal.cart.model.catalog.Product;
import com.iportal.cart.model.catalog.Seller;
import com.iportal.ctrl.portal.content.ContentAction;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.action.BaseForm;

/**
 * Accion para presentar la informacion de productos en el catalogo
 * del portal
 * @author hernan
 * @version 1.0
 *
 */
public class ProductAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(ProductAction.class);

    /**
     * Lista los productos en funci�n de los par�metros registrados
     * en el formulario
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward list (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        SearchProductForm searchForm = (SearchProductForm) form;
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();

        Session sess = null;
        Category category = null;
        List<Product> products = null;
        try {
            sess = getHibernateSession();
            if (searchForm.getCategoryCode() != null && searchForm.getCategoryCode().longValue() > 0L) {
                //si se debe listar en funcion de las categorías
                category = (Category) session.getAttribute("category");
                if(null == category  || !category.getCode().equals(searchForm.getCategoryCode())) {
                    category = CatalogHelper.loadCategory(request, sess, searchForm.getCategoryCode(), null);
                    session.setAttribute("category", category);
                }
                //En caso que no este el forward adecuado cargado
                if (searchForm.getListForward() == null || searchForm.getListForward().length() < 1) {
                    //Envia a cargar categorías
                    searchForm.setListForward(Constants.FORWARD_SUCCESS_CATEGORY);
                }
            } else if (searchForm.getBrandCode() != null && searchForm.getBrandCode().longValue() > 0L){
                List<Brand> brandList = (ArrayList<Brand>)servletContext.getAttribute(CartConstants.CART_CATALOG_ALL_BRANDS);
                Brand brand = null;
                if (brandList == null) {
                    brandList = CatalogHelper.listBrands(sess);
                    servletContext.setAttribute(CartConstants.CART_CATALOG_ALL_BRANDS, brandList);
                }

                for (Brand b : brandList) {
                    if (searchForm.getBrandCode().equals(b.getCode())) {
                        brand = b;
                        break;
                    }
                }

                //En caso que no este el forward adecuado cargado
                if (searchForm.getListForward() == null || searchForm.getListForward().length() < 1) {
                    //Envia a cargar categorías
                    searchForm.setListForward(Constants.FORWARD_SUCCESS_BRAND);
                }

                request.setAttribute("brand", brand);
                // Save the List of results (brand List) in request scope
                if(brandList != null && brandList.size() > 1) {
                    request.setAttribute("brandList", brandList);
                }


            } else if ( searchForm.getSellerCode() != null && searchForm.getSellerCode().longValue() > 0L ) {
                List<Seller> sellerList = (ArrayList<Seller>)servletContext.getAttribute(CartConstants.CART_CATALOG_ALL_SELLERS);
                Seller  seller = null;
                if (sellerList == null) {
                    sellerList = CatalogHelper.listSellers(sess);
                    servletContext.setAttribute(CartConstants.CART_CATALOG_ALL_SELLERS, sellerList);
                }

                for (Seller s : sellerList) {
                    if (searchForm.getSellerCode().equals(s.getCode())) {
                        seller = s;
                        break;
                    }
                }

                //En caso que no este el forward adecuado cargado
                if (searchForm.getListForward() == null || searchForm.getListForward().length() < 1) {
                    //Envia a cargar categorías
                    searchForm.setListForward(Constants.FORWARD_SUCCESS_BRAND);
                }

                request.setAttribute("seller", seller);
                // Save the List of results (seller List) in request scope
                if(sellerList != null && sellerList.size() > 1) {
                    request.setAttribute("sellerList", sellerList);
                }

            } else if( null != searchForm.getOnSale() && true ==  searchForm.getOnSale().booleanValue() ) {
                ContentAction contentAction = new ContentAction();
                BaseForm baseForm = (BaseForm) form;
                baseForm.setCode(baseForm.getItemCode());
                contentAction.read(mapping, form, request, response);

                //En caso que no este el forward adecuado cargado
                if (searchForm.getListForward() == null || searchForm.getListForward().length() < 1) {
                    //Envia a cargar categorías
                    searchForm.setListForward(Constants.FORWARD_SUCCESS_SALE);
                }

                request.setAttribute("content.mainContent", "/jsp/catalogue/saleWrapper.jsp");
            }


            //consultar la informacion de los productos con los parametros de la forma

            products = CatalogHelper.listProducts(searchForm, sess);

            // Save the List of results in request scope
            request.setAttribute("productList", products);
            //Listar productos que deben desplegarse como escondidos pues estan seleccionados
            //java.util.Arrays.asList(searchForm.getCompareCodes());
            if(null != searchForm.getCompareCodes()) {
                List<Long> hiddenCodesList = new ArrayList<Long>(searchForm.getCompareCodes().length);
                List<Long> compareCodesList = java.util.Arrays.asList(searchForm.getCompareCodes());
                boolean addToList = true;
                for (Long code :compareCodesList) {
                    addToList = true;
                    for (Product p : products){
                        if (code.equals(p.getCode())){
                            addToList = false;
                            break;
                        }
                    }
                    if (addToList) {
                        hiddenCodesList.add(code);
                    }
                }
                request.setAttribute("hiddenCodesList", hiddenCodesList);
            }


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return mapping.findForward(Globals.FORWARD_FAILURE);
        } finally {
            if (sess != null)
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
        }


        return mapping.findForward(searchForm.getListForward());
    }


    /**
     * Lista los productos a ser comparados de la seleccion en cualquier listado
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward compare (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        SearchProductForm searchForm = (SearchProductForm) form;

        Session sess = null;
        List<Product> products = null;
        try {
            sess = getHibernateSession();

            //consultar la informacion de los productos con los parametros de la forma

            products = CatalogHelper.listProducts(searchForm.getCompareCodes(), sess);

            // Save the List of results in request scope
            request.setAttribute("productList", products);


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return mapping.findForward(Globals.FORWARD_FAILURE);
        } finally {
            if (sess != null)
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
        }


        return mapping.findForward(Globals.FORWARD_SUCCESS.concat("_compare"));
    }

    /**
     * Carga la informacion de l producto seleccionados
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward load (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        SearchProductForm searchForm = (SearchProductForm) form;
        HttpSession session = request.getSession();

        Session sess = null;
        Category category = null;
        Product product = null;
        try {
            sess = getHibernateSession();
            //consultar la informacion del producto
            product = (Product) sess.get(Product.class, searchForm.getCode());

            //si se debe listar en funcion de las categor�as
            category = (Category) session.getAttribute("category");
            if(null == category  || !category.getCode().equals(searchForm.getCategoryCode())) {
                category = product.getCategory();
                //llama para que cargue la lista de categorias hermanas o descendientes
                category = CatalogHelper.loadCategory(request, sess, product.getCategory().getCode(), category);
                session.setAttribute("category", category);
            }

            //inicializar los productos relacionados
            Hibernate.initialize(product.getRelated());


            // Save the List of results in request scope
            request.setAttribute("product", product);


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


        return mapping.findForward(Globals.FORWARD_FORM);
    }


}
