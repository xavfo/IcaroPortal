/*
 * Created Nov 21, 2006
 *	UpdateContentAction.java
 */
package com.iportal.ctrl.test;

import java.sql.CallableStatement;
import java.util.ArrayList;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.ctrl.portal.content.ContentContainerAction;
import com.iportal.model.portal.Content;
import com.yage.Globals;

/**
 * @author hernan
 *
 */
public class UpdateContentAction extends ContentContainerAction {

	private static Log logger = LogFactory.getLog(UpdateContentAction.class);
	
	public ActionForward update(
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception { 
	       if ( isCancelled(request) ) {
	           return mapping.findForward(Globals.FORWARD_CANCEL);
	       }

	       ActionMessages messages = new ActionMessages();
	       Session sess = null;
	       
	       //BaseForm baseForm = (BaseForm) form;
	       //MenuItem menuItem = null;
	       //ArrayList<Content> contentTemp = new ArrayList();
	       //ArrayList<MenuItem> navigator  = new ArrayList();
	       //Content contentaux = null;
	       //Query query = null;
	       //List linkList   =  new ArrayList();	
	       
//	       	List<Long> result = null;
	       	List<Content> contents = null;
	       
//			Long legalDocuments = null;
//			Long documents = null;
//			Long support   = null;
//			Long faqs      = null;
//			Long links     = null;
//			
//			Long speciesdocuments = null;
//			Long speciesrequest   = null;
//			Long speciesform      = null;
			Long min = null;
			Long max = null;
			if (request.getParameter("min") != null) {
				min = new Long(request.getParameter("min"));	
			}
			if (request.getParameter("max") != null) {
				max = new Long(request.getParameter("max"));
			}
			
			Transaction tx =null;

	       try {
	    	   
	           sess = getHibernateSession();
	           //OPCION1  Actualizacion solo con Hibernate 
	           /*result = sess.createQuery(" from Content c where c.code > ? and  c.code < ? order by c.code").setLong(0, min).setLong(1, max).list();
	           tx = sess.beginTransaction();
		       int row = 0;
	           for (Content content : result ) {
		    	   
		       		if(content != null){
		       			documents = this.countContentContainer(sess, "listOfRelatedDocuments", content, null, Constants.DOCUMENT_TYPE_DOCUMENTS);
		       			legalDocuments = this.countContentContainer(sess, "listOfRelatedLegalDocuments", content, null, null);
		    			support   = this.countContentContainer(sess, "listOfRelatedDocuments", content, null, Constants.DOCUMENT_TYPE_SUPPORT);
		    			faqs      = this.countContentContainer(sess, "listOfRelatedFaq", content, null, null);
		    			links     = this.countContentContainer(sess, "listOfRelatedLink", content, null, null);
		    			speciesform      = this.countSpecies(sess, content, null, Constants.SPECIES_TYPE_FORMS);
		    			speciesrequest   = this.countSpecies(sess, content, null, Constants.SPECIES_TYPE_REQUESTS);
		    			speciesdocuments = this.countSpecies(sess, content, null, Constants.SPECIES_TYPE_OTHERS);
		    			
		    			content.setDocumentsNumber(documents);
		    			content.setFaqsNumber(faqs);
		    			content.setLegalDocumentsNumber(legalDocuments);
		    			content.setLinksNumber(links);
		    			content.setSpeciesDocumentsNumber(speciesdocuments);
		    			content.setSpeciesFormsNumber(speciesform);
		    			content.setSpeciesRequestsNumber(speciesrequest);
		    			content.setSupportDocumentsNumber(support);
		    			
		    			sess.update(content);
					}
		       		if (row%3 == 0) {
		       			sess.flush();
		       		}
		       		row++;
				}*/
	           boolean justList = request.getParameter("justList") != null;
	           if (!justList ) {
		           /* OPCION2 con jdbc y prepared Statements
	        	   result = sess.createQuery(" select  c.code from Content c where c.code > ? and  c.code < ? order by c.code").setLong(0, min).setLong(1, max).list();
		           tx = sess.beginTransaction();
		           
		           StringBuffer sql = new StringBuffer ();
		           sql.append(" UPDATE tb_content c SET c.CONTENT_LEGAL_DOCS_COUNT = ?, ");
		           sql.append(" c.CONTENT_DOCS_COUNT = ?,");
		           
		           
		           sql.append(" c.CONTENT_SUPPORT_DOCS_COUNT = ?,");
		           sql.append(" c.CONTENT_FAQS_COUNT = ?,");
		           sql.append(" c.CONTENT_LINKS_COUNT = ?,");
		           sql.append(" c.CONTENT_SPECIES_DOC_COUNT = ?,");
		           sql.append(" c.CONTENT_SPECIES_REQUEST_COUNT = ?,");
		           sql.append(" c.CONTENT_SPECIES_FORM_COUNT = ?");
		           sql.append("  WHERE c.content_code = ? ");
	           
		           PreparedStatement pstmt = sess.connection().prepareStatement(sql.toString());
		           
			       int row = 0;
		           for (Long code : result ) {
			    	   
			       		if(code != null){
			       			documents = this.countContentContainer(sess, "DocumentContainer", null, code, Constants.DOCUMENT_TYPE_DOCUMENTS);
			       			legalDocuments = this.countContentContainer(sess, "LegalDocumentContainer", null, code, null);
			    			support   = this.countContentContainer(sess, "DocumentContainer", null, code, Constants.DOCUMENT_TYPE_SUPPORT);
			    			faqs      = this.countContentContainer(sess, "FaqContainer", null, code, null);
			    			links     = this.countContentContainer(sess, "LinkContainer", null, code, null);

			    			speciesdocuments = this.countSpecies(sess, null, code, Constants.SPECIES_TYPE_OTHERS);
			    			speciesrequest   = this.countSpecies(sess, null, code, Constants.SPECIES_TYPE_REQUESTS);
			    			speciesform      = this.countSpecies(sess, null, code, Constants.SPECIES_TYPE_FORMS);	
			    			
			    			pstmt.setLong(1, legalDocuments.longValue());
			    			pstmt.setLong(2, documents.longValue());
			    			pstmt.setLong(3, support.longValue());
			    			pstmt.setLong(4, faqs.longValue());
			    			pstmt.setLong(5, links.longValue());
			    			pstmt.setLong(6, speciesdocuments.longValue());
			    			pstmt.setLong(7, speciesrequest.longValue());
			    			pstmt.setLong(8, speciesform.longValue());
			    			
			    			pstmt.setLong(9, code.longValue());
			    			
			    			pstmt.execute();
						}
			       		row++;
					}		    
		           tx.commit();
		           */   
	        	   
	        	   //OPCION 3 llamar a store procedure desde JDBC
	        	   CallableStatement stmt = sess.connection().prepareCall("{ call TB_PRC_CONTENT_COUNT } ");
	        	   boolean isOk = stmt.execute();
	        	   logger.info("EJECUTAR STORE PROCEDURE: "+isOk);
	           }

		       	
	           StringBuffer hql = new StringBuffer();
	          /* hql.append("select new com.iportal.biz.portal.bean.ContentCountBean (");
	           hql.append("c.code,");
	           hql.append("c.title,");
	           hql.append("c.legalDocumentsNumber,");
	           hql.append("c.documentsNumber,");
	           hql.append("c.supportDocumentsNumber,");
	           hql.append("c.faqsNumber,");
	           hql.append("c.linksNumber,");
	           hql.append("c.speciesDocumentsNumber,");
	           hql.append("c.speciesRequestsNumber,");
	           hql.append("c.speciesFormsNumber)");*/
	           hql.append(" from Content c ");
	           hql.append(" where 1 = 1 ");
	           if (min != null) {
	        	   hql.append(" and c.code >= :min ");
	           }
	           if (max != null) {
	        	   hql.append(" and  c.code <= :max ");
	           }
	           hql.append(" order by c.code ");
	           
	           Query q = sess.createQuery(hql.toString());
	           if (min != null) {
	        	 q.setLong("min", min);  
	           }
	           if(max != null) {
	        	   q.setLong("max", max);
	           }
	           
	           contents = (ArrayList<Content>)q.list();
	  		} catch (Exception e) {
	  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
				logger.error(e.getMessage(), e);
				if (tx != null) {
					tx.rollback();
					tx = null;
				}
	  		}finally {
	  			tx = null;
				if (sess != null)
					try {
						sess.flush();
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
			request.setAttribute("contents", contents);
			
			return mapping.findForward(Globals.FORWARD_SUCCESS);    
		}

}
