package com.iportal.biz.system.portal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.biz.BaseBussinessLogic;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.portal.Content;
import com.iportal.model.portal.MenuItem;
import com.iportal.model.portal.RelatedContent;
import com.yage.Globals;

public class RelatedContentFacade extends BaseBussinessLogic{

	private static Log logger = LogFactory.getLog(MenuItem.class);
	private Action action;
    private HttpServletRequest request;
    
	public RelatedContentFacade() {
		super();
	}
    
    public RelatedContentFacade(Action action, HttpServletRequest request) {
        super();
        this.action = action;
        this.request = request;
    }
    
	public Content getContent(Long contentCode)
	throws Exception{
		Content content = null;
		try {
		ContentFacade contentFacade = new ContentFacade();
		ContentForm contentForm = new ContentForm();
		contentForm.setCode (contentCode);
		
		content =  contentFacade.getByCode(contentForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get Content Data to code: " + contentCode);
		} 
		return content;
	}
	
	public List<RelatedContent> getAll(RelatedContentForm relatedForm)
	throws Exception{
		Session sess = null;
		List<RelatedContent> result = null;
		try {
			
			if (relatedForm.getItemCode() != null && relatedForm.getItemCode().longValue()>0)
				relatedForm.setCode(relatedForm.getItemCode());
			
			if(relatedForm.getCode() == null)
				throw new Exception("Null is not a valid value to code in RelatedContent Object");	
					
			if (relatedForm.getCode().longValue() < 1L )
				throw new Exception(relatedForm.getCode() + " is not a valid value to code in RelatedContent Object");
				
			sess = getHibernateSession();

			StringBuffer sql = new StringBuffer();
			
			sql.append("FROM RelatedContent as related ");
			sql.append("WHERE related.content.code = ? ");
			
			sess = getHibernateSession();
			Query query = sess.createQuery(sql.toString()).setLong(0, relatedForm.getCode());
			result = (ArrayList<RelatedContent>)query.list();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get all RelatedContents");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
		return result;
	}
	
	public RelatedContent getByCode(RelatedContentForm relatedForm) 
	throws Exception{
		
		Session sess = null;
		RelatedContent related = null;
		try {
			Long code = null;
			
			if( relatedForm.getCode() != null && relatedForm.getCode().longValue() > 0L ) //The item come from a tab.				
				code = relatedForm.getCode();
			else 	
				code = relatedForm.getCodes()[0];
				
			if ( code==null )
				throw new Exception("Null is not a valid value to code in RelatedContent Object");
			
			if (code.intValue() < 1)
				throw new Exception(code + " is not a valid value to code in RelatedContent Object");
			
			sess = getHibernateSession();
			
			related = (RelatedContent) sess.get(RelatedContent.class, code);
			
			if ( related == null )
				throw new Exception("Does not exist RelatedContent Data for the code : " + code);
			
			PropertyUtils.copyProperties(relatedForm, related);
			
				
			if (related.getContent()!= null){
				relatedForm.setContentCode(related.getContent().getCode());
			}
			
			if (related.getRelated() != null) {
				relatedForm.setRelatedCode(related.getRelated().getCode());
				relatedForm.setRelatedDescription(related.getRelated().getTitle());
			} 
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get all Contents");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
		
		return related;
	}
	
	public void save(RelatedContentForm relatedForm, HttpServletRequest request) 
	throws Exception{
		RelatedContent relatedContent  = null;
		Content content = null;
		Content related = null;
		Session sess = null;
		Transaction tx = null;
		try {
			sess = getHibernateSession();
			if( relatedForm.getCode() != null && relatedForm.getCode().longValue() > 0L ) {				
				relatedContent = (RelatedContent) sess.get(RelatedContent.class, relatedForm.getCode());
			} else {
				relatedContent = new RelatedContent();
				relatedForm.setCode(null);
			}
			
			PropertyUtils.copyProperties(relatedContent, relatedForm);
			
			if (relatedForm.getContentCode()!=null && relatedForm.getContentCode()>0){
				content = (Content) sess.load(Content.class, relatedForm.getContentCode());
			}
			
			if (relatedForm.getRelatedCode()!=null && relatedForm.getRelatedCode()>0){
				related = (Content) sess.load(Content.class, relatedForm.getRelatedCode());
			}
			
			relatedContent.setContent(content);
			relatedContent.setRelated(related);
			
			tx = sess.beginTransaction();
			sess.saveOrUpdate(relatedContent);
			tx.commit();

            // Audit Transaction
            if (relatedForm.getCode() == null ) {
                SysAuditHelper.audit(action, request, relatedContent, relatedContent.getName(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(action, request, relatedContent, relatedContent.getName(), Globals.AUDIT_UPDATE);
            }

			relatedForm.setCode(relatedContent.getCode());
			
		} catch (Exception e) {
			if (tx!=null)
				tx.rollback();
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get save RelatedContent");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
	}
	
	
	public void delete(Long[] codes)
	throws Exception{
		
		Transaction tx = null;
		Session sess = null;
		RelatedContent related = null;
		try {
			if ( codes != null && codes.length > 0) {
				sess = getHibernateSession();
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	related = (RelatedContent) sess.load(RelatedContent.class, codes[i]);
			    	sess.delete(related);
                    SysAuditHelper.audit(action, request, related, related.getName(), Globals.AUDIT_DELETE, sess);
			    }
				tx.commit();
		    }
		} catch (Exception e) {
			if (tx != null) {
 			    tx.rollback();
 			}
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to delete Contents");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}

	}
}
