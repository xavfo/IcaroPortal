package com.iportal.ctrl.system.portal.content;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.biz.RowItem;
import com.iportal.biz.system.portal.ContentFacade;
import com.iportal.model.portal.Content;
import com.yage.Globals;

public class ContentDocumentFacade extends ContentFacade {

	private static Log logger = LogFactory.getLog(ContentDocumentFacade.class);
	
	public ContentDocumentFacade() {
		super();
	}
    
	public ContentDocumentFacade(Session openedSession) {
        super(openedSession);
    }



    public void saveContent(Long code, HttpServletRequest request, Session sess) throws Exception {
        RowItem content = getRowItem(code, sess);
        if (content != null){
            request.setAttribute("currentContent", content);
        }
    }

    
    public Content assignContent(Long code, HttpServletRequest request,
           Object objectRelated, boolean singleTransaction) throws Exception {
    
        Content content = null;
        
        if (code != null && !code.equals(new Long(0))){
            Session sess   = null;
            Transaction tx = null;
            
            try {
                
                if (this.openedSession != null && this.openedSession.isOpen()) {
                    //Si la session esta abierta solo debe usarse una referencia a la conexion
                    sess = this.openedSession; 
                } else { //Abrir una nueva conexion q debe cerrarse luego
                    sess = this.getHibernateSession();
                }
                
                //si transaction es nulo, entonces se debe iniciar una transaccion
                if (singleTransaction) {
                    tx = sess.beginTransaction();
                }
                content = (Content)sess.load(Content.class, code);
                RowItem cont = (RowItem) sess.createQuery("select new com.iportal.biz.RowItem(c.code, c.title, c.level) from Content c where c.code=?").setLong(0, code).setCacheable(Globals.TRUE).uniqueResult();
                if (cont != null){
                    request.setAttribute("currentContent", cont);
                }
                
                sess.saveOrUpdate(content);
                
                if (singleTransaction ) {
                    tx.commit();    
                }
            } catch (Exception e){
                if (tx != null){
                    tx.rollback();
                }
                logger.error(e.getMessage(), e);
            }finally {
                //Debe cerrar la conexion si se la abrio en este mismo metodo
                if (sess != null && (this.openedSession == null || !this.openedSession.isOpen())) {
                    try {
                        sess.clear();
                        sess.close();

                    } catch (Exception e) {
                    }
                }
            }
        }
        return content;
    }
    

    /*private List initialize(List list){
        if (list != null){
            for (int i = 0; i < list.size(); i++){
                LegalDocumentContainer legalDoc = (LegalDocumentContainer)list.get(i);
                Hibernate.initialize(legalDoc.getCategories());
            }
        }
        return list;
    }*/



}
