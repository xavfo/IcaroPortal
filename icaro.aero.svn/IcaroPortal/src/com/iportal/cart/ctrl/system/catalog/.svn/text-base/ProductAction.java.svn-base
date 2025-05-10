/*
 * Created Jan 16, 2007
 *	ProductAction.java
 */
package com.iportal.cart.ctrl.system.catalog;

import java.lang.reflect.InvocationTargetException;
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
import com.iportal.cart.CartConstants;
import com.iportal.cart.model.catalog.Brand;
import com.iportal.cart.model.catalog.Category;
import com.iportal.cart.model.catalog.Product;
import com.iportal.cart.model.catalog.Seller;
import com.iportal.cart.model.tax.TaxType;
import com.iportal.model.system.SysUser;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.action.BaseForm;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * Accion para la ejecuci�n de operaciones CRUD con datos 
 * de la tabla productos.
 * 
 * @author hernan
 * @version 1.0
 *
 */
/**
 * @author hernan
 *
 */
public class ProductAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(ProductAction.class);
	
    /**
     * List todas los productos del cat�logo.
     * Si el usuario es un administrador de un proveedor, solo
     * muestra los productos pertenecientes al proveedor al cual
     * pertene el usuario conectado.
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
        
        ProductForm listForm = (ProductForm) form;
        
        Session sess = null;
        List results = null;
        
        
        if (listForm.getListItems().booleanValue()) {
       	 try {

       		 	
       		 	//Controla que de ser un usuario de proveedor solo vea sus productos
       		 	this.checkSysUserAccess(request, listForm);
       		 	StringBuffer hql = new StringBuffer();
         		ArrayList<Object> params = new ArrayList<Object>();
         		ArrayList<NullableType> types = new ArrayList<NullableType>();
         		
         		hql.append(" select product ");
		   		hql.append(" from Product as product left join product.brand brand ");
				hql.append(" where 1 = 1 ");

	       		if (listForm.getExternalCode() != null && listForm.getExternalCode().length() != 0L ) {
	          		hql.append(" and product.externalCode = ? ");
	          		params.add(listForm.getExternalCode());
	          		types.add(Hibernate.STRING);
	       		}

				if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() > -1) {
	       			hql.append(" and product.enabled = ? ");
	         			if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
	         				params.add( Globals.TRUE );
	         			else
	         				params.add( Globals.FALSE );
	    				types.add(Hibernate.BOOLEAN);
	    		}
       		
				if (listForm.getOnSaleOption() != null && listForm.getOnSaleOption().intValue() > -1) {
	       			hql.append(" and product.onSale = ? ");
	         			if ( listForm.getOnSaleOption().intValue() == Constants.TRUE_INT.intValue() ) {
	         				params.add( Globals.TRUE );
	         			} else {
	         				params.add( Globals.FALSE );
	         			}
	    				types.add(Hibernate.BOOLEAN);
	    		}

				if (listForm.getOnClearanceOption() != null && listForm.getOnClearanceOption().intValue() > -1) {
	       			hql.append(" and product.onClearance = ? ");
	         			if ( listForm.getOnClearanceOption().intValue() == Constants.TRUE_INT.intValue() ) {
	         				params.add( Globals.TRUE );
	         			} else {
	         				params.add( Globals.FALSE );
	         			}
	    				types.add(Hibernate.BOOLEAN);
	    		}


				if (listForm.getFeaturedOption() != null && listForm.getFeaturedOption().intValue() > -1) {
	       			hql.append(" and product.featured = ? ");
	         			if ( listForm.getFeaturedOption().intValue() == Constants.TRUE_INT.intValue() ) {
	         				params.add( Globals.TRUE );
	         			} else {
	         				params.add( Globals.FALSE );
	         			}
	    				types.add(Hibernate.BOOLEAN);
	    		}

	       		if (listForm.getCategoryCode() != null && listForm.getCategoryCode().longValue() > 0L ) {
	       			hql.append(" and (product.category.code = ?  ");
	       			hql.append(" or product.category.code = ? ) ");
	       			
	          		params.add(listForm.getCategoryCode());
	          		params.add(listForm.getCategoryCode());
	          		types.add(Hibernate.LONG);
	          		types.add(Hibernate.LONG);
	       		}

	       		if (listForm.getTaxTypeCode() != null && listForm.getTaxTypeCode().longValue() > 0L ) {
	       			hql.append(" and product.taxType.code = ?  ");
	          		params.add(listForm.getTaxTypeCode());
	          		types.add(Hibernate.LONG);
	       		}

	       		if (listForm.getBrandCode() != null && listForm.getBrandCode().longValue() > 0L ) {
	       			hql.append(" and product.brand.code = ?  ");
	          		params.add(listForm.getBrandCode());
	          		types.add(Hibernate.LONG);
	       		}
	       		if (listForm.getSellerCode() != null && listForm.getSellerCode().longValue() > 0L ) {
	       			hql.append(" and product.seller.code = ?  ");
	          		params.add(listForm.getSellerCode());
	          		types.add(Hibernate.LONG);
	       		}

	       		
	       		String priceProperty = null; 
	       		
	       		/*  
	       		 * En la pagina que llama se podria cambiar la opcion de
	       		 * precio que se desea buscar. Sino el sistema detecta
	       		 * si el producto esta o no en oferta y dependiendo de eso
	       		 * filtra por el precio oferta o el regular */
	       		if (listForm.getSearchBySalePriceOption() != null && listForm.getSearchBySalePriceOption().intValue() > -1) {
         			if ( listForm.getSearchBySalePriceOption().intValue() == Constants.TRUE_INT.intValue() ) {
         				priceProperty = "salePrice";
         			} else {
         				priceProperty = "regularPrice";
         			}
	       		} 
	       		
	       		if (listForm.getFromPrice() != null && listForm.getFromPrice().floatValue() > 0.0F ) {
	          		if (priceProperty != null) {
		       			hql.append(" and  product.");
		          		hql.append(priceProperty);
		          		hql.append(" >= ? ");
		          		params.add(listForm.getFromPrice());
		          		types.add(Hibernate.FLOAT);
	          			
	          		} else {
	          			hql.append(" and ( (product.onSale = ? and  product.salePrice >= ?)");
	          			hql.append(" or (  (product.onSale = ? and product.regularPrice >= ?)) ");

	          			params.add(Globals.TRUE);
		          		params.add(listForm.getFromPrice());
		          		params.add(Globals.FALSE);
		          		params.add(listForm.getFromPrice());

		          		types.add(Hibernate.BOOLEAN);
		          		types.add(Hibernate.FLOAT);
		          		types.add(Hibernate.BOOLEAN);
		          		types.add(Hibernate.FLOAT);
	          			
	          		}
	       		}
	       		
	       		if (listForm.getToPrice() != null && listForm.getToPrice().floatValue() > 0.0F ) {
	          		if (priceProperty != null) {
		       			hql.append(" and  product.");
		          		hql.append(priceProperty);
		          		hql.append(" <= ? ");
		          		params.add(listForm.getToPrice());
		          		types.add(Hibernate.FLOAT);
	          			
	          		} else {
	          			hql.append(" and ( (product.onSale = ? and  product.salePrice <= ?)");
	          			hql.append(" or (  (product.onSale = ? and product.regularPrice <= ?)) ");
	          			
	          			params.add(Globals.TRUE);
		          		params.add(listForm.getToPrice());
		          		params.add(Globals.FALSE);
		          		params.add(listForm.getToPrice());

		          		types.add(Hibernate.BOOLEAN);
		          		types.add(Hibernate.FLOAT);
		          		types.add(Hibernate.BOOLEAN);
		          		types.add(Hibernate.FLOAT);
	          			
	          		}

	       		}

	       		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
    				StringBuffer name = new StringBuffer();
    				name.append("%");
    				name.append(listForm.getName());
    				name.append("%");
    				hql.append(" and  ( product.name like ?  ");
    				hql.append(" or product.modelNumber like ?  ");
    				hql.append(" or brand.name like ? )" );

	          		/*hql.append(" and  (upper(product.name) like ?  ");
	          		hql.append(" or upper(product.modelNumber) like ?  ");
	          		hql.append(" or upper(brand.name) like ? )" );*/
	          		params.add(name.toString());
	          		params.add(name.toString());
	          		params.add(name.toString());
	          		types.add(Hibernate.STRING);
	          		types.add(Hibernate.STRING);
	          		types.add(Hibernate.STRING);
	       		}
       		
	       		if ( listForm.getOrderField() != null ) {
	       			hql.append(" order by product.");
	       			hql.append(listForm.getOrderField());
	       			if ( listForm.getOrderAsc().booleanValue() ) {
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
				results = query.list ();
								
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
     		request.setAttribute("productList", results);
        }
        

		return mapping.findForward(Globals.FORWARD_LIST);
    }
    
    /**
     * Controla que de ser un usuario de proveedor solo vea sus productos
     * o guarde sus productos.
     * @param request
     * @param form
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    private void checkSysUserAccess (HttpServletRequest request, BaseForm form) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    	SysUser user = (SysUser) request.getSession().getAttribute(Constants.SYSTEM_USER_KEY);
	 	//Controla que de ser un usuario de proveedor solo vea sus productos
	 	if (user.getRole().getCode().equals(CartConstants.SYS_ROLE_SELLER)) {
	 		PropertyUtils.setSimpleProperty(form, "sellerCode", user.getSeller().getCode());
	 		
 		}
	
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
        ProductForm productForm = (ProductForm) form;
        
        productForm.reset(mapping, request);
        
        return mapping.findForward(Globals.FORWARD_FORM);
    }


    /**
     * Read action
     * 
     * Si el usuario es de tipo proveedor, solo permite lectura de productos 
     * que pertenezcan a la empresa proveedora del usuario conectado.
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

		SysUser user = (SysUser) request.getSession().getAttribute(Constants.SYSTEM_USER_KEY);
		
		// Gets the action form
		ProductForm productForm = (ProductForm) form;
		
		Session sess = null;
		Product product = null;
		
		
		try {
			sess = getHibernateSession();
			
			product = (Product) sess.load(Product.class, productForm.getCode());
			
			//Controla si puede o no leer esa informacion
			if (user.getRole().getCode().equals(CartConstants.SYS_ROLE_SELLER)) {
				if(!user.getSeller().getCode().equals(product.getSeller().getCode())) {
					//Si no el producto pertenece a otro proveedor se devuelve un mensaje de error
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.accessDenied",user.getRole().getName(), user.getSeller().getName()));
				}
			}
			
			PropertyUtils.copyProperties(productForm, product);
			
			if (product.getTaxType() != null) {
				productForm.setTaxTypeCode(product.getTaxType().getCode());
			}
			if (product.getBrand() != null) {
				productForm.setBrandCode(product.getBrand().getCode());
			}
			
			if (product.getCategory() != null) {
				productForm.setCategoryCode(product.getCategory().getCode());
				productForm.setCategoryName(product.getCategory().getName());
			}
			if (product.getSeller() != null) {
				productForm.setSellerCode(product.getSeller().getCode());
			}
			  
           
			
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
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
		
        return mapping.findForward(Globals.FORWARD_FORM);
    }   
    
    /**
     * Save action
     * 
     * Si el usuario
     * es de tipo proveedor, solo permite la edici�n y actualizaci�n
     * de productos de la empresa a la cual el pertence.
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
        
        ActionMessages messages = new ActionMessages();  		
        
        //Gets the action form
        
        ProductForm productForm = (ProductForm) form;

		this.saveItem(request, productForm, messages);
		 
		 //Report any messages we have discovered back to the original form
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return (mapping.getInputForward());
 		}
 		
 		//Report a success action
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));
		saveMessages(request, messages);
		
		 return mapping.findForward(Globals.FORWARD_SUCCESS);
		 
    }
    
    /**
     * Apply action
     * 
     * Si el usuario
     * es de tipo proveedor, solo permite la edici�n y actualizaci�n
     * de productos de la empresa a la cual el pertence.
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
        ProductForm productForm = (ProductForm) form;

		this.saveItem(request, productForm, messages);
		 
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
    
    /**
     * Guarda los cambios efectuados en un producto. Si el usuario
     * es de tipo proveedor, solo permite la edici�n y actualizaci�n
     * de productos de la empresa a la cual el pertence.
     * 
     * @param request
     * @param productForm
     * @param messages
     * @throws Exception
     */
    protected void saveItem (
   		 HttpServletRequest request,
   		ProductForm productForm,
   		 ActionMessages messages)
    throws Exception {
        
    	
    	Session sess = null;
 		Transaction tx = null;
 		Product product = null;
 		Calendar now = new GregorianCalendar ();
 		//Integer oldStock = null;
	 	//Controla que de ser un usuario de proveedor solo pueda actualizar productos de su empresa
 		this.checkSysUserAccess(request, productForm);
 		
 		//bandera para controla cuando debe borrarse la lista de productos del
 		//showroom
 		Boolean oldShowRoomIndicator = null;
 		try {
 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 		   
 			if (productForm.getCode() != null && productForm.getCode().longValue() != 0L) {
 				product = (Product) sess.load(Product.class, productForm.getCode());
 				//si se va a editar guardar el valor anterior de showroom
 				oldShowRoomIndicator =product.getShowRoom(); 
 				/*oldStock = product.getStock();
 				productForm.setMaxStock(product.getMaxStock());*/
 			} else {
 				product = new Product();
 				productForm.setCode(null);
 				product.setCreation(now);
 				
 			}
 			if (productForm.getSellerCode() != null && (product.getSeller() == null || !productForm.getSellerCode().equals(product.getSeller().getCode()))) {
 				Seller seller = (Seller) sess.load(Seller.class, productForm.getSellerCode());
 				product.setSeller(seller);
 			}

 			if (productForm.getBrandCode() != null && (product.getBrand() == null || !productForm.getBrandCode().equals(product.getBrand().getCode()))) {
 				Brand brand = (Brand) sess.load(Brand.class, productForm.getBrandCode());
 				product.setBrand(brand);
 			}
 			if (productForm.getCategoryCode() != null && (product.getCategory() == null || !productForm.getCategoryCode().equals(product.getCategory().getCode()))) {
 				Category category = (Category) sess.load(Category.class, productForm.getCategoryCode());
 				product.setCategory(category);
 			}
 			
 			if (productForm.getTaxTypeCode() != null && (product.getTaxType() == null || !productForm.getTaxTypeCode().equals(product.getTaxType().getCode()))) {
 				TaxType taxType = (TaxType) sess.load(TaxType.class, productForm.getTaxTypeCode());
 				product.setTaxType(taxType);
 			}
 			  			
 			
 			PropertyUtils.copyProperties(product, productForm);
 			
 	
 			
 			/*if (productForm.getMaxStock() == null || (oldStock != null && !oldStock.equals(product.getStock()))) {
 				//el stock cambio por lo que debe actualizarse el stock maximo
 				product.setMaxStock(product.getStock());
 			}*/ 
 			
 			product.setLastUpdate(now);
 			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(product);			
 			tx.commit();
           
 			//controla si debe reiniciar o no los productos en showroom
 			if (( product.getShowRoom().booleanValue()) || (oldShowRoomIndicator != null && !product.getShowRoom().equals(oldShowRoomIndicator) )) {
 				this.getServlet().getServletContext().removeAttribute(CartConstants.CART_CATALOG_SHOW_ROOM);
 			}

 			//Audit Transaction
           if ( productForm.getCode() == null ) {
               SysAuditHelper.audit(this, request, product, product.getName(), Globals.AUDIT_INSERT);                
           } else {
               SysAuditHelper.audit(this, request, product, product.getName(), Globals.AUDIT_UPDATE);
           }
           productForm.setCode(product.getCode());
 			
 		} catch (Exception e) {
           
           boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.product", 
                   this.getLocale(request)), product.getName(), null);
           if (tx != null) {
               tx.rollback();
           }
           //si no se ha escrito ningun error colocar el error generico
           if (!putMesage) {
               messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
           }
           logger.error(e.getMessage(), e);
 		} finally {
 			if (sess != null)
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
 		}

		
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

		// Gets the action form
		ProductForm productForm = (ProductForm) form;
		
		
		Session sess = null;
		Transaction tx = null;
		Product product = null;
		Integer jdbcBachtSize = this.getJdbcBatchSize();
		
		try {
		    Long codes[] = productForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	product = (Product) sess.load(Product.class, codes[i]);
					sess.delete(product);
					SysAuditHelper.audit(this, request, product, product.getName(), Globals.AUDIT_DELETE, sess);

					if ( i % jdbcBachtSize  == 0 ) {
						//flush a batch of updates and release memory:
						sess.flush();
						sess.clear();
					}

			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.product", this.getLocale(request)), product.getName(), null);
			
			if (tx != null) {
			    tx.rollback();
			}
			//si no se ha escrito ningun error colocar el error generico
			if (!putMesage) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
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

}
