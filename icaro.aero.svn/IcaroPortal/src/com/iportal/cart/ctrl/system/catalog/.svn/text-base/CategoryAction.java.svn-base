/*
 * Created Jan 16, 2007
 *	CategoryAction.java
 */
package com.iportal.cart.ctrl.system.catalog;

import java.util.ArrayList;
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
import com.iportal.cart.model.catalog.Category;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * @author hernan
 *
 */
public class CategoryAction extends BaseDispatchAction {

	private static Log logger = LogFactory.getLog(CategoryAction.class);
	
    /**
     * List todas las categorías del portal
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
        
        CategoryForm listForm = (CategoryForm) form;
        
        Session sess = null;
        List results = null;
        
        if (listForm.getListItems().booleanValue()) {
       	 try {

         		StringBuffer hql = new StringBuffer();
         		ArrayList<Object> params = new ArrayList<Object>();
         		ArrayList<NullableType> types = new ArrayList<NullableType>();
         		
		   		hql.append(" from Category as category ");
				hql.append(" where 1 = 1 ");
       		
	       		if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() > -1) {
	       			hql.append(" and category.enabled = ? ");
	         			if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
	         				params.add( Globals.TRUE );
	         			else
	         				params.add( Globals.FALSE );
	    				types.add(Hibernate.BOOLEAN);
	    		}

	       		if (listForm.getParentCode() != null && listForm.getParentCode().longValue() > 0L ) {
	       			hql.append(" and category.parent.code = ? ");
	          		params.add(listForm.getParentCode());
	          		types.add(Hibernate.LONG);
	       		}

	       		if (listForm.getLevel() != null && listForm.getLevel().intValue() > 0 ) {
	       			hql.append(" and category.level = ? ");
	          		params.add(listForm.getLevel());
	          		types.add(Hibernate.INTEGER);
	       		}

	       		if (listForm.getName() != null && listForm.getName().length() != 0L ) {
	       			String name = "%" + listForm.getName() + "%";
	          		hql.append(" and category.name like ? ");
	          		params.add(name);
	          		types.add(Hibernate.STRING);
	       		}
       		

	       		//solo ordenar si ya se esta cargando newsos
	       		if ( listForm.getOrderField() != null ) {
	       			hql.append(" order by category.");
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
     		request.setAttribute("categoryList", results);
        }
        

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
        CategoryForm categoryForm = (CategoryForm) form;
        
        categoryForm.reset(mapping, request);
        
        return mapping.findForward(Globals.FORWARD_FORM);
    }


    /**
     * Read action
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

		// Gets the action form
		CategoryForm categoryForm = (CategoryForm) form;
		
		Session sess = null;
		Category category = null;
		
		try {
			sess = getHibernateSession();
			
			category = (Category) sess.load(Category.class, categoryForm.getCode());
			
			PropertyUtils.copyProperties(categoryForm, category);
			
			if(category.getParent() != null) {
				categoryForm.setParentCode(category.getParent().getCode());
			}
			  
           
			
		} catch (Exception e) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(""));
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
        CategoryForm categoryForm = (CategoryForm) form;

		this.saveItem(request, categoryForm, messages);
		 
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
        CategoryForm categoryForm = (CategoryForm) form;

		this.saveItem(request, categoryForm, messages);
		 
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
    
    protected void saveItem (
   		 HttpServletRequest request,
   		CategoryForm categoryForm,
   		 ActionMessages messages)
    throws Exception {
        
    	
    	Session sess = null;
 		Transaction tx = null;
 		Category category = null;
 		
 		try {
 		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
 		    
 			if (categoryForm.getCode() != null && categoryForm.getCode().longValue() != 0L) {
 				category = (Category) sess.load(Category.class, categoryForm.getCode());
 			} else {
 				category = new Category();
 				
 				categoryForm.setCode(null);
 			}
 			if (categoryForm.getParentCode() != null &&  categoryForm.getParentCode().longValue() > 0L && (category.getParent() == null || !categoryForm.getParentCode().equals(category.getParent().getCode()))) {
 				
 				Category parent = (Category) sess.load(Category.class, categoryForm.getParentCode());
 				category.setParent(parent);
 				
 				
 			}
 			int level = (category.getParent() != null)?category.getParent().getLevel():0;
 			  			
 			PropertyUtils.copyProperties(category, categoryForm);
 			
 			category.setLevel(new Integer(level + 1));

 			//Persist data
 			tx = sess.beginTransaction();
 			sess.saveOrUpdate(category);			
 			tx.commit();
           //Quitar del contexto el listado de categorias del front end
 			this.getServlet().getServletContext().removeAttribute(CartConstants.CART_CATALOG_ALL_CATEGORIES);
 			
 			            
           //Audit Transaction
           if ( categoryForm.getCode() == null ) {
               SysAuditHelper.audit(this, request, category, category.getName(), Globals.AUDIT_INSERT);                
           } else {
               SysAuditHelper.audit(this, request, category, category.getName(), Globals.AUDIT_UPDATE);
           }
           categoryForm.setCode(category.getCode());
 			
 		} catch (Exception e) {
           
           boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.category", 
                   this.getLocale(request)), category.getName(), null);
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
		CategoryForm categoryForm = (CategoryForm) form;
		
		Session sess = null;
		Transaction tx = null;
		Category category = null;
		Integer jdbcBachtSize = this.getJdbcBatchSize();
		
		try {
		    Long codes[] = categoryForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	category = (Category) sess.load(Category.class, codes[i]);
					sess.delete(category);
					SysAuditHelper.audit(this, request, category, category.getName(), Globals.AUDIT_DELETE, sess);			        
					
					if ( i % jdbcBachtSize  == 0 ) {
						//flush a batch of updates and release memory:
						sess.flush();
						sess.clear();
					}

			    }
				tx.commit();
	           //Quitar del contexto el listado de categorias del front end
	 			this.getServlet().getServletContext().removeAttribute(CartConstants.CART_CATALOG_ALL_CATEGORIES);

		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.category", this.getLocale(request)), category.getName(), null);
			
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
