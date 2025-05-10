/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.news;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.iportal.biz.facade.DocumentAssignFacade;
import com.iportal.model.Catalogue;
import com.iportal.model.Image;
import com.iportal.model.news.News;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * 
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class NewsAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(NewsAction.class);

    
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
         
         NewsForm listForm = (NewsForm) form;
         
         Session sess = null;
         List results = null;
         
         if (listForm.getListItems().booleanValue()) {
        	 try {

          		StringBuffer hql = new StringBuffer();
          		ArrayList<Object> params = new ArrayList<Object>();
        		ArrayList<NullableType> types = new ArrayList<NullableType>();
        		
        		hql.append(" from News as news ");
				hql.append("where 1 = 1 ");
        		
        		if (listForm.getTitle() != null && listForm.getTitle().length() != 0L ) {
           		    String name = "%" + listForm.getTitle() + "%";
           		    hql.append("and news.title like ? ");
           		    params.add(name);
           		    types.add(Hibernate.STRING);  
        		}
        		
        		if (listForm.getCategoryCode() != null && listForm.getCategoryCode().longValue() > 0L ) {
        			hql.append(" and news.category.code = ? ");
           		    params.add(listForm.getCategoryCode());
           		    types.add(Hibernate.LONG);  
        		}
        		
        		if (listForm.getBrandCode() != null && listForm.getBrandCode().longValue() > 0L ) {
        			hql.append(" and news.brand.code = ? ");
           		    params.add(listForm.getBrandCode());
           		    types.add(Hibernate.LONG);  
        		}
        		        		
        		if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() > -1) {
        			hql.append(" and news.isEnabled = ? ");
          			if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
          				params.add( Globals.TRUE );
          			else
          				params.add( Globals.FALSE );
     				types.add(Hibernate.BOOLEAN);
     			}
        		
        		if (listForm.getLocalInfoOption() != null && listForm.getLocalInfoOption().intValue() > -1) {
        			hql.append(" and news.isLocalInfo = ? ");
          			if ( listForm.getLocalInfoOption().intValue() == Constants.TRUE_INT.intValue() )
          				params.add( Globals.TRUE );
          			else
          				params.add( Globals.FALSE );
     				types.add(Hibernate.BOOLEAN);
     			}

        		//solo ordenar si ya se esta cargando newsos
        		if ( listForm.getOrderField() != null ) {
        			hql.append(" order by news.");
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
      		request.setAttribute("newsList", results);
         }
         

 		return mapping.findForward(Globals.FORWARD_LIST);
     }
     

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
     public ActionForward listDocuments (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
         
    	 
    	 NewsForm listForm = (NewsForm) form;
     
         Long code = listForm.getCode();
         
         HttpSession session = request.getSession();
         
         if (code != null){
        	 session.setAttribute("newsCode", code);
         }
         code = (Long)session.getAttribute("newsCode");
         DocumentAssignFacade facade = new DocumentAssignFacade(this, request);
         //Set results = facade.getRelatedDocuments(News.class, newsCode);
         List results = facade.getAssignedDocuments(code, null, News.class, request);
         listForm.setCode(code);
  		      
      	// Save the List of results in request scope
      	request.setAttribute("documentList", results);

 		return mapping.findForward(Globals.FORWARD_LIST_DOCUMENTS);
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
         NewsForm newsForm = (NewsForm) form;
         newsForm.reset(mapping, request);
         
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
 		NewsForm newsForm = (NewsForm) form;
 		
 		Session sess = null;
 		News news = null;
 		
 		try {
 			sess = getHibernateSession();
 			
 			news = (News) sess.load(News.class, newsForm.getCode());
 			
 			PropertyUtils.copyProperties(newsForm, news);
 			newsForm.setCategoryCode( news.getCategory().getCode() );
 			  
            Image intro = news.getIntroImage();
            Image main = news.getMainImage();
            
            if (intro != null){
                newsForm.setIntroImageCode(intro.getCode());
                newsForm.setIntroImageName(intro.getName());
            }
            if (main != null){
                newsForm.setMainImageCode(main.getCode());
                newsForm.setMainImageName(main.getName());
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
         NewsForm newsForm = (NewsForm) form;

 		 this.saveItem(request, newsForm, messages);
 		 
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
         NewsForm newsForm = (NewsForm) form;

 		 this.saveItem(request, newsForm, messages);
 		 
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
     
     public void saveItem (
    		 HttpServletRequest request,
    		 NewsForm newsForm,
    		 ActionMessages messages)
     throws Exception {
         
  		
    	Session sess = null;
  		Transaction tx = null;
  		News news = null;
  		Catalogue category = null;
  		
  		try {
  		    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
  		    
  			if (newsForm.getCode() != null && newsForm.getCode().longValue() != 0L) {
  				news = (News) sess.load(News.class, newsForm.getCode());
  			} else {
  				news = new News();
  				news.setCreation(Calendar.getInstance());
  				newsForm.setCode(null);
  			}
  			
  			if (news.getCategory() == null || (!news.getCategory().getCode().equals(newsForm.getCategoryCode()))) {
  				category = (Catalogue) sess.load(Catalogue.class, newsForm.getCategoryCode());
 				news.setCategory(category);
 			}	
  			  			
  			PropertyUtils.copyProperties(news, newsForm);
 	
            Long introImageCode = newsForm.getIntroImageCode();
            Long mainImageCode = newsForm.getMainImageCode();
            
            
            if (introImageCode != null && introImageCode.longValue() > 0){
                Image intro = (Image)sess.load(Image.class, introImageCode);
                news.setIntroImage(intro);
            }else {
                news.setIntroImage(null);
            }

            if (mainImageCode != null && mainImageCode.longValue() > 0){
                Image main = (Image)sess.load(Image.class, mainImageCode);
                news.setMainImage(main);
            }else {
                news.setMainImage(null);
            }

  			//Persist data
  			tx = sess.beginTransaction();
  			sess.saveOrUpdate(news);			
  			tx.commit();
            
  			            
            //Audit Transaction
            if ( newsForm.getCode() == null ) {
                SysAuditHelper.audit(this, request, news, news.getTitle(), Globals.AUDIT_INSERT);                
            } else {
                SysAuditHelper.audit(this, request, news, news.getTitle(), Globals.AUDIT_UPDATE);
            }
            newsForm.setCode(news.getCode());
  			
  		} catch (Exception e) {
            
            boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.subject", 
                    this.getLocale(request)), news.getTitle(), null);
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
		NewsForm newsForm = (NewsForm) form;
		
		Session sess = null;
		Transaction tx = null;
		News news = null;

		try {
		    Long codes[] = newsForm.getCodes();
		    
		    if ( codes != null && codes.length > 0) {
			    sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
			    
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	news = (News) sess.load(News.class, codes[i]);
					sess.delete(news);
					SysAuditHelper.audit(this, request, news, news.getTitle(), Globals.AUDIT_DELETE, sess);			        
			    }
				tx.commit();
		    } 
			
		} catch (Exception e) {
			boolean putMesage = checkControlledError (e, messages, BaseHelper.getApplicationBundleMessage("prompt.news", this.getLocale(request)), news.getTitle(), null);
			
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
     public ActionForward listImages (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
    	 
    	 NewsForm listForm = (NewsForm) form;
         Long newsCode = listForm.getCode();
         HttpSession session = request.getSession();
         if (newsCode != null){
        	 session.setAttribute("newsCode", newsCode);
         }
         newsCode = (Long)session.getAttribute("newsCode");
         Session sess = null;
         try {
        	 sess = getHibernateSession();
             
             News news = (News) sess.get(News.class, newsCode);
             Hibernate.initialize(news.getImageGalleries());
             Set results = news.getImageGalleries();
             
             listForm.setCode(newsCode);
          	 // Save the List of results in request scope
          	 request.setAttribute("imageList", results);
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

 		return mapping.findForward(Globals.FORWARD_LIST_IMAGES);
     }

}
