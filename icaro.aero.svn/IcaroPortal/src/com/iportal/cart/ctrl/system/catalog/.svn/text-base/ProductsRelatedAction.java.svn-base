/*
 * Created Feb 16, 2006
 *	EventImageAction.java
 */
package com.iportal.cart.ctrl.system.catalog;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.cart.model.catalog.Product;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;
/**
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class ProductsRelatedAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(ProductsRelatedAction.class);

    
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
         
         ProductsRelatedForm listForm = (ProductsRelatedForm) form;
         
         ProductForm productForm = (ProductForm) request.getSession().getAttribute("productForm");
         if (productForm != null) {
        	 listForm.setParentCode(productForm.getCode());
         }
         
         Session sess = null;
         Product product = null;

         try {
     		
			
			sess = getHibernateSession();
			
			product = (Product) sess.load(Product.class, listForm.getParentCode());
			
			Hibernate.initialize(product.getRelated());
			
			//Guarda en la forma que est� en sesi�n al producto principal
			productForm.setSessionProduct(product);
 					
     		
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
 		request.setAttribute("product", product);

 		return mapping.findForward(Globals.FORWARD_LIST);
     }
     
     public ActionForward findRelated (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         ProductsRelatedForm listForm = (ProductsRelatedForm) form;         
         
         ProductForm productForm = (ProductForm) request.getSession().getAttribute("productForm");
         if (productForm != null) {
        	 listForm.setParentCode(productForm.getCode());
         }
         
         Session sess = null;
         List productList = null;

         try {
        	sess = getHibernateSession();
        	 
     		StringBuffer sql = new StringBuffer();
     		ArrayList<Object> params = new ArrayList<Object>();
    		ArrayList<NullableType> types = new ArrayList<NullableType>();
					
 			//Productos Disponibles
 			sql = new StringBuffer();
 			sql.append("from Product as product ");
     		sql.append("where product.enabled = ? ");
     		params.add( Globals.TRUE );
			types.add(Hibernate.BOOLEAN);
     		
            if (listForm.getCategoryCode() != null && listForm.getCategoryCode().longValue() > 0L ) {
                sql.append(" and product.category.code = ?  ");
                
                params.add(listForm.getCategoryCode());
                types.add(Hibernate.LONG);
            }

            if (listForm.getBrandCode() != null && listForm.getBrandCode().longValue() > 0L ) {
                sql.append(" and product.brand.code = ?  ");
                params.add(listForm.getBrandCode());
                types.add(Hibernate.LONG);
            }

            if (listForm.getSellerCode() != null && listForm.getSellerCode().longValue() > 0L ) {
                sql.append(" and product.seller.code = ?  ");
                params.add(listForm.getSellerCode());
                types.add(Hibernate.LONG);
            }

            sql.append("and product.code not in (");
 			sql.append(listForm.getParentCode());
 			
 			Iterator it = productForm.getSessionProduct().getRelated().iterator();
            while (it.hasNext()) {
            	Product ps = (Product) it.next();
            	sql.append(", ");
     			sql.append(ps.getCode());    
            }
            
     		sql.append(") ");
     		
     		if (listForm.getProductName() != null && listForm.getProductName().length() > 0) {
				StringBuffer name = new StringBuffer();
				name.append(listForm.getProductName().toUpperCase());
				name.append("%");
				sql.append("and upper(product.name) like ? ");
				params.add( name.toString() );
				types.add(Hibernate.STRING);
			}
     		
     		//solo ordenar si ya se esta cargando newsos
       		if ( listForm.getOrderField() != null ) {
       			sql.append(" order by product.");
       			sql.append(listForm.getOrderField());
       			if ( listForm.getOrderAsc().booleanValue() ) {
       				sql.append(" asc ");
       			} else {
       				sql.append(" desc ");
       			}
       		}
     		     		
     		Query query = sess.createQuery(sql.toString());
     		
     		for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, params.get(i), types.get(i));
			}
			
			query.setCacheable(true);
			productList = query.list ();
     		
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
 		request.setAttribute("productList", productList);

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
         ProductsRelatedForm productsRelatedForm = (ProductsRelatedForm) form;
         productsRelatedForm.reset(mapping, request);
         
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
     public ActionForward save (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }
          		
 		 return mapping.findForward(Globals.FORWARD_SUCCESS);
 		 
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
 		
 		ProductForm productForm = (ProductForm) request.getSession().getAttribute("productForm");

 		// Gets the action form
 		ProductsRelatedForm productsRelatedForm = (ProductsRelatedForm) form;
 		
 		Session sess = null;
 		Transaction tx = null;
 		Product product = null;
 		Product related = null;

 		try {
		    Long codes[] = productsRelatedForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = getHibernateSession();
			    
				tx = sess.beginTransaction();
								
				product = (Product) sess.get(Product.class, productForm.getCode());
				
			    for (int i = 0; i < codes.length; i++) {
			    	related = (Product) sess.load(Product.class, codes[i]);
			    	product.getRelated().remove(related);
			    }
			    
			    sess.saveOrUpdate(product);
				SysAuditHelper.audit(this, request, product, product.getName(), Globals.AUDIT_UPDATE);
				
				tx.commit();
		    } 
 			
 		} catch (Exception e) {
 			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.product", this.getLocale(request)), product.getName(), null);
			if (tx != null) {
			    tx.rollback();
			}
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
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
 		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
 		saveMessages(request, messages);

 		return mapping.findForward(Globals.FORWARD_SUCCESS);
     }
     
     public ActionForward saveAll (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
         if ( isCancelled(request) ) {
             return mapping.findForward(Globals.FORWARD_CANCEL);
         }

 		ActionMessages messages = new ActionMessages();
 		
 		ProductForm productForm = (ProductForm) request.getSession().getAttribute("productForm");

 		// Gets the action form
 		ProductsRelatedForm productsRelatedForm = (ProductsRelatedForm) form;
 		
 		Session sess = null;
 		Transaction tx = null;		
 		Product product = null;
 		Product related = null;

 		try {
		    Long codes[] = productsRelatedForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = getHibernateSession();
			    
			    product = productForm.getSessionProduct();
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {			
			    	related = (Product) sess.load(Product.class, codes[i]);
					product.getRelated().add(related);					
			    }
			    
			    sess.saveOrUpdate(product);
				SysAuditHelper.audit(this, request, product, product.getName(), Globals.AUDIT_UPDATE);
			    
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
}
