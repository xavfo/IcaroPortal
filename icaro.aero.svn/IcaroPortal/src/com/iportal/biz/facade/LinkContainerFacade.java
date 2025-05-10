/*
 * Created Jul 12, 2006
 *	DocumentContainerFacade.java
 */
package com.iportal.biz.facade;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;

import com.iportal.Constants;
import com.iportal.biz.BaseBussinessLogic;
import com.iportal.ctrl.system.container.LinkContainerForm;
import com.iportal.model.Catalogue;
import com.iportal.model.LinkType;
import com.iportal.model.container.LinkContainer;
import com.iportal.model.portal.Content;
import com.yage.Globals;

/**
 * Objeto Facade para contenedore de linkos.
 * @author YAGE( hernan)
 * @version 1.0
 *
 */
public class LinkContainerFacade extends BaseBussinessLogic {

	
	/**
	 * 
	 */
	public LinkContainerFacade() {
		super();
	}
	
	
	/**
	 * @param openedSession
	 */
	public LinkContainerFacade(Session openedSession) {
		super(openedSession);
	}

	/**
	 * Retrieves all the LinkContainer items.
	 * 
	 * @return List
	 */
	public List list (LinkContainerForm listForm)
	throws HibernateException{
		return listAssigned(null, listForm, null);
	}
	
	public List listAssigned (Long code, LinkContainerForm listForm, Class clazz) throws HibernateException {

		Session sess = null;
		List results = null;
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<NullableType> types = new ArrayList<NullableType>();
		
		sql.append("Select link ");
		sql.append("from LinkContainer as link ");
		if (code == null){
			sql.append(" where 1 = 1 ");
		} else if (clazz.equals(Content.class)){
            sql.append("join link.contents content where content.code = ? ");
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

	
	public List listNotAssigned (Long code, LinkContainerForm listForm, Class clazz) throws HibernateException {

		Session sess = null;
		List results = null;
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<NullableType> types = new ArrayList<NullableType>();

		sql.append("Select link ");
		sql.append("from LinkContainer as link ");
		sql.append("where link not in ( select link from LinkContainer link ");
		if (clazz.equals(Content.class)){
            sql.append("join link.contents content where content.code = ? ) ");
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
	
	
	
	public void addFilters(StringBuffer sql, LinkContainerForm listForm, ArrayList<Object> params, ArrayList<NullableType> types){
		if (listForm.getTitle() != null && listForm.getTitle().length() > 0 ) {
			String title = "%" + listForm.getTitle() + "%";
			sql.append("and link.title like ? ");
			params.add(title);
			types.add(Hibernate.STRING);
		}
  		if (listForm.getEnabledOption() != null && listForm.getEnabledOption().intValue() >  Constants.SEARCH_ALL_OPTION) {
  			sql.append("and link.isEnabled = ? ");
  			if ( listForm.getEnabledOption().intValue() == Constants.TRUE_INT.intValue() )
  				params.add( Globals.TRUE );
  			else
  				params.add( Globals.FALSE );
				types.add(Hibernate.BOOLEAN);
			}

		if (listForm.getCategoryCode() != null
				&& listForm.getCategoryCode().longValue() > 0L) {
			sql.append("and link.category.code = ? ");
			params.add(listForm.getCategoryCode());
			types.add(Hibernate.LONG);
		}
		
		if (listForm.getTypeCode() != null
				&& listForm.getTypeCode().longValue() > 0L) {
			sql.append("and link.type.code = ? ");
			params.add(listForm.getTypeCode());
			types.add(Hibernate.LONG);
		}


		if (listForm.getOrderField() != null) {
			sql.append("order by link.");
			sql.append(listForm.getOrderField());
			if (listForm.getOrderAsc().booleanValue()) {
				sql.append(" asc ");
			} else {
				sql.append(" desc ");
			}
		} else {
			sql.append("order by link.title asc");
		}
		/*if (listForm.getOrderField() != null) {
			sql.append("order by link.index");
			//sql.append("order by index.");
			sql.append(listForm.getOrderField());
			if (listForm.getOrderAsc().booleanValue()) {
				sql.append(" asc ");
			} else {
				sql.append(" desc ");
			}
		} else {
			sql.append("order by link.index asc");
		}*/
	}
	

	public LinkContainer save (LinkContainerForm linkContainerForm, boolean singleTransation) throws Exception {

		Session sess   = null;
		Transaction tx = null;
		
		LinkContainer linkContainer = null;
		Catalogue category = null;
		LinkType type = null;
		
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
			

			if (linkContainerForm.getCode() != null
					&& linkContainerForm.getCode().longValue() > 0L) {
				linkContainer = (LinkContainer) sess.load(LinkContainer.class, linkContainerForm.getCode());
			} else {
				linkContainer = new LinkContainer();
				linkContainerForm.setCode(null);
			}

			PropertyUtils.copyProperties(linkContainer, linkContainerForm);

  			if (linkContainer.getCategory() == null || (!linkContainer.getCategory().getCode().equals(linkContainerForm.getCategoryCode()))) {
  				category = (Catalogue) sess.load(Catalogue.class, linkContainerForm.getCategoryCode());
				linkContainer.setCategory(category);
			}  	
  			
  			if (linkContainer.getType() == null || (!linkContainer.getType().getCode().equals(linkContainerForm.getTypeCode()))) {
  				type = (LinkType) sess.load(LinkType.class, linkContainerForm.getTypeCode());
				linkContainer.setType(type);
			}  

			sess.saveOrUpdate(linkContainer);
			
			/*
			 *Si transaction es nulo, entonces debe  
			 *efectuar commit con la transaccion iniciada en
			 *esta sección 
			 * */
			if (singleTransation ) {
				tx.commit();	
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
		return linkContainer;
	}
}
