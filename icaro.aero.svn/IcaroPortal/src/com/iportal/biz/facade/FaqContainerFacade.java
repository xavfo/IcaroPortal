/*
 * Created Jul 12, 2006
 *	DocumentContainerFacade.java
 */
package com.iportal.biz.facade;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
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
import com.iportal.ctrl.system.container.FaqContainerForm;
import com.iportal.model.Catalogue;
import com.iportal.model.container.FaqContainer;
import com.iportal.model.portal.Content;
import com.yage.Globals;

/**
 * Objeto Facade para contenedore de faqos.
 * @author YAGE( hernan)
 * @version 1.0
 *
 */
public class FaqContainerFacade extends BaseBussinessLogic {

	private Action action;
    private HttpServletRequest request;
	/**
	 * 
	 */
	public FaqContainerFacade() {
		super();
	}
	
    public FaqContainerFacade(Action action, HttpServletRequest request) {
        super();
        this.action = action;
        this.request = request;
    }
	
    public FaqContainerFacade(Action action, HttpServletRequest request, Session openedSession) {
        super(openedSession);
        this.action = action;
        this.request = request;
    }
    
	/**
	 * @param openedSession
	 */
	public FaqContainerFacade(Session openedSession) {
		super(openedSession);
	}

	/**
	 * Retrieves all the FaqContainer items.
	 * 
	 * @return List
	 */
	public List list (FaqContainerForm listForm)
	throws HibernateException{
		return listAssigned(null, listForm, null);
	}
	
	
	public List listNotAssigned (Long code, FaqContainerForm listForm, Class clazz) throws HibernateException {
		Session sess = null;
		List results = null;
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<NullableType> types = new ArrayList<NullableType>();

		sql.append("Select faq ");
		sql.append("from FaqContainer as faq ");
		sql.append("where faq not in ( select faq from FaqContainer faq ");
		if (clazz.equals(Content.class)){
            sql.append("join faq.contents content where content.code = ? ) ");
		}
		params.add(code);
		types.add(Hibernate.LONG);
		
		addFilters(sql, listForm, params, types);
		
		try {
			if (this.openedSession != null && this.openedSession.isOpen()) {
				//Si la session esta abierta solo debe usarse una referencia a la conexion
				sess = this.openedSession; 
			} else { //Abrir una nueva conexion q debe cerrarse luego
				sess = this.getHibernateSession();
			}

			Object[] arrayParams = params.toArray();
			Query query = sess.createQuery(sql.toString());
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, arrayParams[i],  types.get(i));
			}
			results = query.list();
            
		} catch (HibernateException e) {
			throw e;
		} finally {
			
			//Debe cerrara la conexion si se la abrio en este mismo metodo
			if (sess != null && (this.openedSession == null || !this.openedSession.isOpen())) {
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
			}
		}

		
		return results;
	}
	
	
	public List listAssigned (Long code, FaqContainerForm listForm, Class clazz) throws HibernateException {

		Session sess = null;
		List results = null;
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<NullableType> types = new ArrayList<NullableType>();

		sql.append("Select faq ");
		sql.append("from FaqContainer as faq ");
		
		if (code == null){
			sql.append(" where 1 = 1 ");
		} else if (clazz.equals(Content.class)){
            sql.append("join faq.contents content where content.code = ? ");
            params.add(code);
			types.add(Hibernate.LONG);
		}
		
		addFilters(sql, listForm, params, types);
		
		try {
			if (this.openedSession != null && this.openedSession.isOpen()) {
				//Si la session esta abierta solo debe usarse una referencia a la conexion
				sess = this.openedSession; 
			} else { //Abrir una nueva conexion q debe cerrarse luego
				sess = this.getHibernateSession();
			}

			Object[] arrayParams = params.toArray();
			Query query = sess.createQuery(sql.toString());
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, arrayParams[i],  types.get(i));
			}

			results = query.list();

		} catch (HibernateException e) {
			throw e;
		} finally {
			
			//Debe cerrara la conexion si se la abrio en este mismo metodo
			if (sess != null && (this.openedSession == null || !this.openedSession.isOpen())) {
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
			}
		}

		
		return results;
	}

	private void addFilters(StringBuffer sql, FaqContainerForm listForm, ArrayList<Object> params, ArrayList<NullableType> types){
		
		if (listForm.getQuestion() != null && listForm.getQuestion().length() > 0 ) {
			String name = "%" + listForm.getQuestion().toUpperCase() + "%";
			sql.append("and UPPER(faq.question) like ? ");
			params.add(name);
			types.add(Hibernate.STRING);
		}
  		if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() >  Constants.SEARCH_ALL_OPTION) {
  			sql.append("and faq.isEnabled = ? ");
  			if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
  				params.add( Globals.TRUE );
  			else
  				params.add( Globals.FALSE );
				types.add(Hibernate.BOOLEAN);
			}

		if (listForm.getCategoryCode() != null
				&& listForm.getCategoryCode().longValue() > 0L) {
			sql.append("and faq.category.code = ? ");
			params.add(listForm.getCategoryCode());
			types.add(Hibernate.LONG);
		}
		
		if (listForm.getOrderField() != null) {
            sql.append("order by faq.");
			sql.append(listForm.getOrderField());
			if (listForm.getOrderAsc().booleanValue()) {
				sql.append(" asc ");
			} else {
				sql.append(" desc ");
			}
		} else {
			sql.append("order by faq.index asc");
			
		}
	}
	

	public FaqContainer save (FaqContainerForm faqContainerForm, boolean singleTransation) throws Exception {

		Session sess   = null;
		Transaction tx = null;
		
		FaqContainer faqContainer = null;
		Catalogue category = null;
		
		try {
			if (this.openedSession != null && this.openedSession.isOpen()) {
				//Si la session esta abierta solo debe usarse una referencia a la conexion
				sess = this.openedSession; 
			} else { //Abrir una nueva conexion q debe cerrarse luego
				sess = this.getHibernateSession();
			}

			//si transaction es nulo, entonces se debe iniciar una transaccion
			if (singleTransation) {
				tx = sess.beginTransaction();
			}
			

			if (faqContainerForm.getCode() != null
					&& faqContainerForm.getCode().longValue() > 0L) {
				faqContainer = (FaqContainer) sess.load(FaqContainer.class, faqContainerForm.getCode());
			} else {
				faqContainer = new FaqContainer();
				faqContainerForm.setCode(null);
			}

			PropertyUtils.copyProperties(faqContainer, faqContainerForm);

  			if (faqContainer.getCategory() == null || (!faqContainer.getCategory().getCode().equals(faqContainerForm.getCategoryCode()))) {
  				category = (Catalogue) sess.load(Catalogue.class, faqContainerForm.getCategoryCode());
				faqContainer.setCategory(category);
			}  			

			sess.saveOrUpdate(faqContainer);
			
			/*
			 *Si transaction es nulo, entonces debe  
			 *efectuar commit con la transaccion iniciada en
			 *esta sección 
			 * */
			if (singleTransation ) {
				tx.commit();	
			}
			
              // Audit Transaction
            if (faqContainerForm.getCode() == null ) {
                SysAuditHelper.audit(action, request, faqContainer, faqContainer.getQuestion(),Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(action, request, faqContainer, faqContainer.getQuestion(), Globals.AUDIT_UPDATE);
            }          

		} catch (Exception e) {
			if (tx != null && singleTransation) {
				//si transaction debe hacer rollback
				tx.rollback();
			}
			throw e;
		} finally {
			//Debe cerrara la conexion si se la abrio en este mismo metodo
			if (sess != null && (this.openedSession == null || !this.openedSession.isOpen())) {
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
			}
		}
		return faqContainer;
	}
}
