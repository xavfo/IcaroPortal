/*
 * Created Jul 12, 2006
 *	DocumentContainerFacade.java
 */
package com.iportal.biz.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;

import com.iportal.Constants;
import com.iportal.biz.BaseBussinessLogic;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.ctrl.system.container.DocumentContainerForm;
import com.iportal.model.container.DocumentContainer;
import com.iportal.model.container.FaqContainer;
import com.iportal.model.event.Event;
import com.iportal.model.news.News;
import com.iportal.model.portal.Content;
import com.yage.Globals;
/**
 * Facade object for managing relations between documents and containers like faqs, news, etc.
 * @author YAGE( martha)
 * @version 1.0
 *
 */
public class DocumentAssignFacade extends BaseBussinessLogic {

	private static Logger logger = Logger.getLogger(DocumentAssignFacade.class);

    private Action action;
    private HttpServletRequest request;
    /*
    public DocumentAssignFacade() {
		super();
	}
    */
    
    public DocumentAssignFacade(Action action, HttpServletRequest request) {
        super();
        this.action = action;
        this.request = request;
    }
	
	public List getNotAssignedDocuments(Long code, DocumentContainerForm listForm, Class clazz){
		List results = null;
		Session sess = null;
    	if (code != null){
	    	try {
	    		sess = getHibernateSession();
	    		StringBuffer sql = new StringBuffer();
	    		ArrayList<Object> params = new ArrayList<Object>();
	    		ArrayList<NullableType> types = new ArrayList<NullableType>();
	    		sql.append("select  doc ");
	    		sql.append("from DocumentContainer as doc ");
	    		sql.append("where doc not in ( select doc from DocumentContainer doc ");
	    		if (clazz.equals(News.class)){
	    			sql.append("join doc.news news where news.code = ? ) ");
	    		} else if (clazz.equals(Event.class)){
	    			sql.append("join doc.events event where event.code = ? ) ");
	    		} else if (clazz.equals(FaqContainer.class)){
                    sql.append("join doc.faqs faq where faq.code = ? ) ");
                } else if (clazz.equals(Content.class)){
                    sql.append("join doc.contents content where content.code = ? ) ");
                }
	    		
	    		params.add(code);
				types.add(Hibernate.LONG);
	    		
	    		if (listForm.getTitle() != null && listForm.getTitle().length() > 0 ) {
	    			String name = "%" + listForm.getTitle() + "%";
	    			sql.append("and doc.title like ? ");
	    			params.add(name);
	    			types.add(Hibernate.STRING);
	    		}
	      		if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() >  Constants.SEARCH_ALL_OPTION) {
	      			sql.append("and doc.isEnabled = ? ");
	      			if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
	      				params.add( Globals.TRUE );
	      			else
	      				params.add( Globals.FALSE );
	    				types.add(Hibernate.BOOLEAN);
	    			}
	
	    		if (listForm.getCategoryCode() != null
	    				&& listForm.getCategoryCode().longValue() > 0L) {
	    			sql.append("and doc.category.code = ? ");
	    			params.add(listForm.getCategoryCode());
	    			types.add(Hibernate.LONG);
	    		}
	
	    		if (listForm.getDocTypeCode() != null
	    				&& listForm.getDocTypeCode().longValue() > 0L) {
	    			sql.append("and doc.type.code = ? ");
	    			params.add(listForm.getDocTypeCode());
	    			types.add(Hibernate.LONG);
	    		}
	
	    		if (listForm.getDisplayModeCode() != null
	    				&& listForm.getDisplayModeCode().longValue() > 0L) {
	    			sql.append("and doc.displayMode.code = ? ");
	    			params.add(listForm.getDisplayModeCode());
	    			types.add(Hibernate.LONG);
	    		}
	
	
	    		if (listForm.getOrderField() != null) {
	    			sql.append("order by doc.");
	    			sql.append(listForm.getOrderField());
	    			if (listForm.getOrderAsc().booleanValue()) {
	    				sql.append(" asc ");
	    			} else {
	    				sql.append(" desc ");
	    			}
	    		} else {
	    			sql.append("order by doc.title asc");
	    		}
	    		
	    		Object[] arrayParams = params.toArray();
				Query query = sess.createQuery(sql.toString());
				//query.setParameterList("codes", docCodes, Hibernate.LONG);
				
				for (int i = 0; i < types.size(); i++) {
					query.setParameter(i, arrayParams[i],  types.get(i));
				}
	
	    		results = query.list();
	    		
			} catch (HibernateException e) {
				logger.error(e.getMessage(),e);
			} finally {
				//Debe cerrara la conexion si se la abrio en este mismo metodo
				if (sess != null) {
					try {
						sess.clear();
						sess.close();
					} catch (Exception e) {
					}
				}
			}
    	}
		
		return results;
	}

    
	public List getAssignedDocuments(Long code, DocumentContainerForm listForm, 
            Class clazz, HttpServletRequest request){
		List results = null;
		Session sess = null;
        Query query = null;
    	if (code != null){
	    	try {
	    		sess = getHibernateSession();
                RowItemFacade relatedFacade = new RowItemFacade();
                relatedFacade.setInRequest( code, sess, clazz, request);
                
	    		StringBuffer sql = new StringBuffer();
	    		ArrayList<Object> params = new ArrayList<Object>();
	    		ArrayList<NullableType> types = new ArrayList<NullableType>();
	    		sql.append(" select doc from DocumentContainer doc ");
	    		if (clazz.equals(News.class)){
                    
	    			sql.append("join doc.news news where news.code = ?  ");
	    		} else if (clazz.equals(Event.class)){
	    			sql.append("join doc.events event where event.code = ?  ");
	    		} else if (clazz.equals(FaqContainer.class)){
                    sql.append("join doc.faqs faq where faq.code = ? ");
                } else if (clazz.equals(Content.class)){
                    sql.append("join doc.contents content where content.code = ? ");
                }
	    		params.add(code);
				types.add(Hibernate.LONG);
				
                if (listForm != null){
    	    		if (listForm.getTitle() != null && listForm.getTitle().length() > 0 ) {
    	    			String name = "%" + listForm.getTitle() + "%";
    	    			sql.append("and doc.title like ? ");
    	    			params.add(name);
    	    			types.add(Hibernate.STRING);
    	    		}
                   
    	      		if (listForm.getEnabledOption() != null 
                            && listForm.getEnabledOption().intValue() >  Constants.SEARCH_ALL_OPTION) {
    	      			sql.append("and doc.isEnabled = ? ");
    	      			if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
    	      				params.add( Globals.TRUE );
    	      			else
    	      				params.add( Globals.FALSE );
    	      		    types.add(Hibernate.BOOLEAN);
                    }  
    	
    	    		if (listForm.getCategoryCode() != null
    	    				&& listForm.getCategoryCode().longValue() > 0L) {
    	    			sql.append("and doc.category.code = ? ");
    	    			params.add(listForm.getCategoryCode());
    	    			types.add(Hibernate.LONG);
    	    		}
    	
    	    		if (listForm.getDocTypeCode() != null
    	    				&& listForm.getDocTypeCode().longValue() > 0L) {
    	    			sql.append("and doc.type.code = ? ");
    	    			params.add(listForm.getDocTypeCode());
    	    			types.add(Hibernate.LONG);
    	    		}
    	
    	    		if (listForm.getDisplayModeCode() != null
    	    				&& listForm.getDisplayModeCode().longValue() > 0L) {
    	    			sql.append("and doc.displayMode.code = ? ");
    	    			params.add(listForm.getDisplayModeCode());
    	    			types.add(Hibernate.LONG);
    	    		}
    	
    	    		if (listForm.getOrderField() != null) {
    	    			sql.append("order by doc.");
    	    			sql.append(listForm.getOrderField());
                        
    	    			if (listForm.getOrderAsc().booleanValue()) {
    	    				sql.append(" asc ");
    	    			} else {
    	    				sql.append(" desc ");
    	    			}
    	    		} else {
    	    			sql.append("order by doc.title asc");
    	    		}
                }
                query = sess.createQuery(sql.toString());
                Object[] arrayParams = params.toArray();
                for (int i = 0; i < types.size(); i++) {
    				query.setParameter(i, arrayParams[i],  types.get(i));
    			}
               
	    		results = query.list();
	    		
			} catch (HibernateException e) {
				logger.error(e.getMessage(),e);
			} finally {
				//Debe cerrara la conexion si se la abrio en este mismo metodo
				if (sess != null) {
					try {
						sess.clear();
						sess.close();
					} catch (Exception e) {
					}
				}
			}
    	}
		return results;
	}
	
     
    
	public void assignDocuments(Long ownerCode, Class ownerClass, Long documentCodes[],  Session sess) throws Exception {
		Transaction tx = null;
        
		DocumentContainer documentContainer = null;
		Set<DocumentContainer> documents = null;
        
        News news = null;
        Event event = null;
        FaqContainer faqContainer = null;
		
        if (ownerClass.equals(News.class)){
            news = (News)sess.load(ownerClass, ownerCode);
			documents = news.getDocuments();
		}
		if (ownerClass.equals(Event.class)){
            event = (Event)sess.load(ownerClass, ownerCode);
			documents = event.getDocuments();
		}
        if (ownerClass.equals(FaqContainer.class)){
            faqContainer = (FaqContainer)sess.load(ownerClass, ownerCode);
            documents = faqContainer.getDocuments();
        }

		if (documentCodes != null && documents != null){
			for (int i = 0; i < documentCodes.length; i++){
				Long code = documentCodes[i];
				documentContainer = (DocumentContainer)sess.load(DocumentContainer.class, code);
				documents.add(documentContainer);
			}
			tx = sess.beginTransaction();
			tx.commit();
		}
        if (news != null){
            SysAuditHelper.audit(action, request, news, news.getTitle(), Globals.AUDIT_UPDATE);
        }
        if (event != null){
            SysAuditHelper.audit(action, request, event, event.getTitle(), Globals.AUDIT_UPDATE);
        }
        if (faqContainer != null){
            SysAuditHelper.audit(action, request, faqContainer, faqContainer.getQuestion(),Globals.AUDIT_UPDATE);
        }

        
	}
	/**
	 * Adds documents to a news
	 * @param documentForm
	 * @return
	 * @throws Exception
	 */
	public boolean save (DocumentContainerForm documentForm, Class ownerClass) throws Exception {
		Session sess   = null;
		Transaction tx = null;
		Long documentCodes[] = null;
		boolean result = false;
    	Long ownerCode = documentForm.getOwnerCode();
    	
		try {
			if (ownerCode != null ){
				sess = this.getHibernateSession();
				documentCodes = documentForm.getCodes();
				assignDocuments(ownerCode, ownerClass, documentCodes, sess);
			}
			result = true;
		} catch (Exception e) {
			if (tx != null) {
			    tx.rollback();
			}
			logger.error(e.getMessage(), e);
		} finally {
			//Debe cerrar la conexion si se la abrio en este mismo metodo
			if (sess != null) {
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
			}
		}
    	
		return result;
	}
}
