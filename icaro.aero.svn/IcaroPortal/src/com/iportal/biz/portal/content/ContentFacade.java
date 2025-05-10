package com.iportal.biz.portal.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.biz.BaseBussinessLogic;
import com.iportal.biz.RowItem;
import com.iportal.model.portal.Content;

public class ContentFacade extends BaseBussinessLogic {

	private static Log logger = LogFactory.getLog(ContentFacade.class);


	

	public ContentFacade() {
		super();
	}

	public ContentFacade(Session openedSession) {
		super(openedSession);
	}

	public Content read(Long code) 
    throws Exception {
	
	Session sess = null;
	
	try {
	
		//Long[] codes = form.getCodes();
		//Long code = null;
		
		/*for (int i = 0; i < codes.length; i++) {
	    	if(codes[i].longValue() > 0)  //A veces llega un code basura '0' que debe ser ignorado.
	    		code = codes[i]; 
		}*/
		
		//code = form.getCode();
		if (this.openedSession != null && this.openedSession.isOpen()) {
			//Si la session esta abierta solo debe usarse una referencia a la conexion
			sess = this.openedSession; 
		} else { //Abrir una nueva conexion q debe cerrarse luego
			sess = this.getHibernateSession();
		}
	
		Content content = (Content) sess.get(Content.class, code);
	/*	Hibernate.initialize(content.getListOfRelatedLink());
		Hibernate.initialize(content.getListOfRelatedFaq());
		Hibernate.initialize(content.getListOfRelatedDocuments());*/
		Hibernate.initialize(content.getContents());
		sess.createFilter(content.getContents(),"order by this.order asc");
		
		
		//return (Event) sess.get(Event.class, code);
		return content;

	}catch (Exception e) {
		logger.error(e.getMessage(),e);
		throw new Exception("Unexpected error while trying to read Event");
		
	} finally {
		if (sess != null && (this.openedSession == null || !this.openedSession.isOpen())) {
			try {
				sess.clear();
				sess.close();
			} catch (Exception e) {
			}
		}
	
	}
}
	
	
	public ArrayList<RowItem> getHierarchy(RowItem current) throws Exception {
		Session sess = null;
		ArrayList<RowItem> navigator = null;
		ArrayList<RowItem> aux = new ArrayList<RowItem>();
		aux.add(current);
		
    	//select new com.iportal.biz.portal.bean.MenuPortalBean (
    	StringBuffer hql = new StringBuffer(); 
    	hql.append(" select new com.iportal.biz.RowItem(content.code, parent.code, content.title )");
    	hql.append(" from Content content");
    	hql.append(" where content.code = ?");
   
    	if (current.getParentCode() != null && current.getParentCode() > 0L) {
    		
    		try {
    	    	if (this.openedSession != null && this.openedSession.isOpen()) {
    				//Si la session esta abierta solo debe usarse una referencia a la conexion
    				sess = this.openedSession; 
    			} else { //Abrir una nueva conexion q debe cerrarse luego
    				sess = this.getHibernateSession();
    			}
    			RowItem item = null;
	    		Long code = current.getParentCode();
	    		Query query = sess.createQuery(hql.toString());
	    		query.setCacheable(true);
	    		while (code != null && code > 0L) {
	    			query.setLong(0, code);
	    			item = (RowItem) query.uniqueResult();
	    			aux.add(item);
	    			code = item.getParentCode();
	    		}
        	}catch (Exception e) {
        		throw e;
        	} finally {
        		if (sess != null && (this.openedSession == null || !this.openedSession.isOpen())) {
        			try {
        				sess.clear();
        				sess.close();
        			} catch (Exception e) {
        			}
        		}
        	}
    	}
    	navigator = new ArrayList<RowItem>(aux.size()); 
    	
		if (aux.size() > 0) {
			for (int i = aux.size(); i > 0; i--) {
				navigator.add( aux.get(i-1) );
			}
		}
		
		return navigator;
	}	
	  
	  
	  
	  public void findChildren(List<Content> list, Content content) {
			//adds the menu to the end of the list
	        list.add(content);
	        
	        try {
	        	// initializes the lazy set
	            Hibernate.initialize(content.getContents());
	            
	            if (content.getContents() != null) {
	            	Iterator it = content.getContents().iterator();
	            	
	            	while (it.hasNext()) {
						Content subcontent = (Content) it.next();
						if (subcontent.getGroup().booleanValue()) {
							findChildren(list, subcontent);
						} else {
							list.add(subcontent);
						}
					}
	            }
	        } catch (Exception e) {
	        	logger.info(e.getMessage(), e);
			}
	}
	    
	
	
	
	
	
	
	
	
	
	
	
	
	


}
