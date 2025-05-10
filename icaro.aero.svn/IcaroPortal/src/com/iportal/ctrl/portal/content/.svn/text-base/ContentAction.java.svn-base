/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.portal.content;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.PortalBeanHelper;
import com.iportal.biz.RowItem;
import com.iportal.biz.portal.bean.ItemSummaryBean;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.model.portal.Content;
import com.yage.Globals;
import com.yage.struts.action.BaseForm;

public class ContentAction extends ContentContainerAction {

    private static Log logger = LogFactory.getLog(ContentAction.class);
   
   /* public ActionForward loadSubHome (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }
        ActionMessages messages = new ActionMessages();
        HttpSession session = request.getSession();
        MenuPortalBean menuPortalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
        
 	   	if (menuPortalBean == null) {
   			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.content.notFound"));
   		} 
    
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return mapping.findForward(Globals.FORWARD_FAILURE);
		}

        Session sess = null;
        List<MapSiteBean> finalResults = null;
        List<Long> categoryCodes = null;

        try {
       
			sess = getHibernateSession();
			
			StringBuffer hql = new StringBuffer(); 
			hql.append(" select content.code"); 
			hql.append(" from Content content");
			hql.append(" where content.enabled = ? ");
			hql.append(" and content.parent.code = ? ");
			Query query = sess.createQuery(hql.toString());
			query.setParameter(0, Globals.TRUE, Hibernate.BOOLEAN);
			query.setParameter(1, menuPortalBean.getContentCode(), Hibernate.LONG);
			categoryCodes = (ArrayList<Long>)query.list();
			
			hql.delete(0,hql.length());
			hql.append(" select new com.iportal.biz.MapSiteBean(content.code, content.parent.code, content.title, content.level, content.link)");
			hql.append(" from Content content");
			hql.append(" where content.enabled = ? ");
			hql.append(" and ( (content.level = ? and content.parent.code = :parent ) ");
			hql.append(" or  (content.level = :level and content.parent.code in (:categoryList) ) )");
			hql.append(" order by  content.level, content.parent.code, content.order ");
			  
			query = sess.createQuery(hql.toString());
			
			query.setParameter(0, Globals.TRUE, Hibernate.BOOLEAN);
			query.setInteger (1, 2);
			query.setParameter("parent", menuPortalBean.getContentCode(), Hibernate.LONG);
			query.setInteger("level", 3);
			query.setParameterList("categoryList", categoryCodes);
			
			List<MapSiteBean> results = (ArrayList<MapSiteBean>)query.list();
			PortalBeanHelper helper = new PortalBeanHelper (); 
			finalResults  = helper.organizeMapBeanList(results);
		
			request.setAttribute("contentList", finalResults);
			
			
 	       
         
   		} catch (Exception e) {
   			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
 			logger.info(e.getMessage(), e);
   		}finally {
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
 		return mapping.findForward(Globals.FORWARD_SUCCESS.concat("_".concat (menuPortalBean.getLayoutForward())));
    }*/

    
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
       HttpSession session = request.getSession();
       Session sess = null;
       ArrayList<ItemSummaryBean> result1 = null;	
       Content content=null;
       List<RowItem> result2 = null;    
       BaseForm contentForm=(BaseForm)form;
       MenuPortalBean menuPortalBean  = null;
       try {
    	   menuPortalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
           sess = getHibernateSession();
           if(menuPortalBean == null || !menuPortalBean.getContentCode().equals(contentForm.getCode())) {
           		menuPortalBean = PortalBeanHelper.setNewBean(contentForm.getCode(), request, sess);
	    		if ( menuPortalBean == null ){
	    			throw new Exception(BaseHelper.getApplicationBundleMessage("error.content.notFound", new Long[] {contentForm.getCode()}));
   			}
	    		if ( menuPortalBean.getPortalModuleCode() == null) {
	    			throw new Exception(BaseHelper.getApplicationBundleMessage("error.content.module",new Long[] {menuPortalBean.getContentCode()}));
	    		}
           }
           if (menuPortalBean != null) {
				content = (Content) sess.get(Content.class, menuPortalBean.getContentCode());
				request.setAttribute("content", content);
				
			}	
	       StringBuffer hql = new StringBuffer();
	       hql = new StringBuffer();
           hql.append("select new com.iportal.biz.portal.bean.ItemSummaryBean ( ");
    	   hql.append(" content.code, content.title, content.description, content.link, "); 
    	   hql.append(" accessImage.path, accessImage.height, accessImage.width, content.text, content.intro ) ");
    	   hql.append(" FROM Content as content ");
    	   hql.append(" inner join  content.parent parent");
	       hql.append(" left  join  content.accessImage accessImage ");
	       hql.append(" WHERE parent.code = ? ");
	       hql.append(" and content.enabled = ? ");
	       
	       //sql.append("AND menuItem.menuLanguage.menu.code = ? ");
	       hql.append(" order by content.order");
	       Query query = sess.createQuery(hql.toString());
			
		
		   query.setLong(0, menuPortalBean.getContentCode());
		   query.setBoolean(1,Globals.TRUE);
		   query.setCacheable(true);
		   result1 = (ArrayList<ItemSummaryBean>)query.list();
			
		   request.setAttribute("contentChildList", result1);
           
		   
		   if (menuPortalBean.getContentParentCode() != null ) {
				//consulta hermanos
		       hql = hql.delete(0, hql.length());
	           hql.append("select new com.iportal.biz.RowItem ( ");
	    	   hql.append(" content.code, content.title, content.link, accessImage.path) ");
	    	   hql.append(" FROM Content as content ");
	    	   hql.append(" inner join  content.parent parent");
	    	   hql.append(" left  join  content.accessImage accessImage ");
		       hql.append(" WHERE parent.code = ? ");
		       hql.append(" and content.enabled = ? ");
		       
		       //sql.append("AND menuItem.menuLanguage.menu.code = ? ");
		       hql.append(" order by content.order");
		       
		       query = sess.createQuery(hql.toString());
			
			   query.setLong(0, menuPortalBean.getContentParentCode());
			   query.setBoolean(1,Globals.TRUE);
			   query.setCacheable(true);
			   result2 = (ArrayList<RowItem>)query.list();
			   
			   request.setAttribute("contentMenuList", result2);
				
		   }
	     /*  if(contentForm.getCode() > 0 && contentForm.getCode() != null){
	    	   content = (Content) sess.get(Content.class, contentForm.getCode());
	       }*/
	       
	       //Hibernate.initialize(content.getContents());
	      /* List children =new ArrayList (content.getContents().size());
	       for (Content child: content.getContents() ) {
	    	   children.add(child);
	       }*/
           /*
	       request.setAttribute("childContentList", children);
	       
	       StringBuffer hql = new StringBuffer();
	       hql = new StringBuffer();
           hql.append("select new com.iportal.biz.RowItem ( ");
    	   hql.append(" related.code, related.title, related.link, related.accessImage.path ) ");
    	   hql.append(" FROM RelatedContent as relatedContent ");
    		hql.append(" inner join  relatedContent.related related");
	        hql.append(" left  join  related.accessImage accessImage ");
    		hql.append(" WHERE relatedContent.content.code = ? ");
    		hql.append(" and relatedContent.related.enabled = ? ");
			//sql.append("AND menuItem.menuLanguage.menu.code = ? ");
    		hql.append(" order by relatedContent.related.order");
			Query query = sess.createQuery(hql.toString());
			
			//query.setLong(1, language.getCode());
			query.setLong(0, content.getCode());
			query.setBoolean(1,Globals.TRUE);
			query.setCacheable(true);
			result = query.list();
			
			request.setAttribute("relatedContentList", result);
	       
	       //Set<Content> contents = content.getContents();
	       
	       	
	       	/*for (Content contentaux : contents) {
	       		if(contentaux != null){
					if (contentaux.getLinksNumber()!=null && contentaux.getLinksNumber().longValue()>0L) {
						Hibernate.initialize(contentaux.getListOfRelatedLink());
					}*/
					//Esto se comenta para evitar los queries y se carga la info desde el contador ya cargado
					/*query = sess.createFilter(contentaux.getListOfRelatedDocuments(),"select count(*) where this.type.code = ? and  this.isEnabled= ? ").setLong(0, Constants.DOCUMENT_TYPE_DOCUMENTS).setBoolean(1,Globals.TRUE);
					contentaux.setDocument((Long)query.uniqueResult());
					
					query = sess.createFilter(contentaux.getListOfRelatedDocuments(),"select count(*) where this.type.code = ? and this.isEnabled= ? ").setLong(0, Constants.DOCUMENT_TYPE_SUPPORT).setBoolean(1,Globals.TRUE);
					contentaux.setSupport((Long)query.uniqueResult());*/
					//contentList.add(contentaux);
					
					//carga desde los contadores
					//contentaux.setDocument(contentaux.getDocumentsNumber());
					//contentaux.setSupport(contentaux.getSupportDocumentsNumber());
				/*}
			}
	       	
       		/*StringBuffer sql = new StringBuffer();
        	sql.append("select new com.iportal.biz.RowItem ( ");
        	sql.append("relatedContent.related.code, relatedContent.content.code, relatedContent.name ) ");
        	sql.append("FROM RelatedContent as relatedContent ");
			sql.append("WHERE relatedContent.content.parent.code = ? ");
			sql.append("and relatedContent.enabled = ? ");
			Query queryrelated = sess.createQuery(sql.toString());
			queryrelated.setLong(0,content.getCode());
			queryrelated.setBoolean(1,Globals.TRUE);
			result = queryrelated.list();*/

	       	// Funcion que setea si existen o no contenedores activos relacionados 
	       	//con este contenido 
			//POR EL MOMENTO SE SUSPENDE Y SE COPIA DESDE LOS CONTADORES INTERNOS
	       	//this.setCountContentContainers(request, sess, content, content.getCode());
			
			//request.setAttribute("documents", content.getDocumentsNumber());
			//request.setAttribute("support", content.getSupportDocumentsNumber());
	     	//request.setAttribute("faqs", content.getFaqsNumber());	
	     	//request.setAttribute("links", content.getLinksNumber());
		    	 
		     

		     //request.setAttribute("relatedContent", result);
		     
 	     	 
	         //request.setAttribute("contentList", contentList);
	        /* request.setAttribute("content", content);*/
	        // request.setAttribute("menuItem",menuItem);*/
	       
        
  		} catch (Exception e) {
  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
			logger.info(e.getMessage(), e);
  		}finally {
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
			return (mapping.findForward(Globals.FORWARD_FAILURE));
		}
		return mapping.findForward(Globals.FORWARD_SUCCESS.concat("_"+menuPortalBean.getLayoutForward()));
		
   }
 
   /* public ActionForward onSale (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
     throws Exception {
        
        HttpSession session = request.getSession();
        Session sess = null;
        ActionForward forward = null;

        if ( isCancelled(request) ) {
            return mapping.findForward(Globals.FORWARD_CANCEL);
        }

        try {
            sess = getHibernateSession();
            forward = this.read(mapping, form, request, response);        

            SearchProductForm searchForm = new SearchProductForm();
            searchForm.reset(mapping, request);
            searchForm.setOnSale(Globals.TRUE);
            searchForm.setOrderField("regularPrice");
            searchForm.setListForward(Constants.FORWARD_SUCCESS_SALE);
            
            List<Product> products = CatalogHelper.listProducts(searchForm, sess);
            //Save the List of results in request scope
            request.setAttribute("productList", products);
            session.setAttribute("searchProductForm", searchForm);        
            request.setAttribute("content.mainContent", "/jsp/catalogue/saleWrapper.jsp");
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
        
        return forward;
        
    }
        */
}