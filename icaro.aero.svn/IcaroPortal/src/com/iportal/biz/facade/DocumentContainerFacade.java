/*
 * Created Jul 12, 2006
 *	DocumentContainerFacade.java
 */
package com.iportal.biz.facade;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
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
import com.iportal.ctrl.system.container.DocumentContainerForm;
import com.iportal.model.Catalogue;
import com.iportal.model.DocumentDisplayMode;
import com.iportal.model.DocumentType;
import com.iportal.model.container.DocumentContainer;
import com.yage.Globals;
/**
 * Objeto Facade para contenedore de documentos.
 * @author YAGE( hernan)
 * @version 1.0
 *
 */
/**
 * @author hernan
 *
 */
public class DocumentContainerFacade extends BaseBussinessLogic {

	protected ServletContext context;
    protected HttpServletRequest request;
    protected Action action;
	
	/**
	 * 
	 */

	public DocumentContainerFacade() {
		super();
		context = null;
	}
 

	/**
	 * @param context
	 */
	public DocumentContainerFacade(ServletContext context) {
		super();
		this.context = context;
	}

	/**
	 * @param openedSession
	 */
	public DocumentContainerFacade(Session openedSession) {
		super(openedSession);
	}

	public DocumentContainerFacade(Session openedSession, ServletContext context) {
		super(openedSession);
		this.context = context;
	}
  
    public DocumentContainerFacade(Session openedSession, ServletContext context, Action action, HttpServletRequest request) {
        super(openedSession);
        this.context = context;
        this.request = request;
        this.action = action;
    }
    /*
    public DocumentContainerFacade(ServletContext context, Action action, HttpServletRequest request) {
        this.context = context;
        this.request = request;
        this.action = action;
    }
    */
    
	/**
	 * Retrieves all the DocumentContainer items.
	 * 
	 * @return List
	 */
	public List list ( DocumentContainerForm listForm) throws HibernateException {

		return this.list(listForm.getCodes(), listForm.getTitle(), listForm.getKeywords(), listForm.getEnabledOption(), listForm.getCategoryCode(),listForm.getDocTypeCode(), listForm.getDisplayModeCode(), listForm.getOrderField(), listForm.getOrderAsc());
	}
	

	/**
	 * Retrieves all the DocumentContainer items.
	 * 
	 * @return List
	 */	
	public List list ( Long[] codes, String title, String keywords, Integer enabledOption, Long categoryCode, Long docTypeCode, Long displayModeCode,  String orderField, Boolean orderAsc) throws HibernateException {

		Session sess = null;
		List results = null;
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<NullableType> types = new ArrayList<NullableType>();

		sql.append("from DocumentContainer as doc ");
		sql.append(" where 1 = 1 ");

		if (title != null && title.length() > 0 ) {
			String name = "%" + title + "%";
			sql.append("and doc.title like ? ");
			params.add(name);
			types.add(Hibernate.STRING);
		}		
		
		if (keywords != null && keywords.length() > 0 ) {
			String key = "%" + keywords.toUpperCase() + "%";
			sql.append(" and ( UPPER(doc.title) like ? or UPPER(doc.keywords) like ? or UPPER(doc.description) like ?) ");
			params.add(key);
			params.add(key);
			params.add(key);
			types.add(Hibernate.STRING);
			types.add(Hibernate.STRING);
			types.add(Hibernate.STRING);
		}
		
  		if (enabledOption != null && enabledOption.intValue() >  Constants.SEARCH_ALL_OPTION) {
  			sql.append("and doc.isEnabled = ? ");
  			if ( enabledOption.intValue() == Constants.TRUE_INT.intValue() )
  				params.add( Globals.TRUE );
  			else
  				params.add( Globals.FALSE );
				types.add(Hibernate.BOOLEAN);
			}

		if (categoryCode != null
				&& categoryCode.longValue() > 0L) {
			sql.append("and doc.category.code = ? ");
			params.add(categoryCode);
			types.add(Hibernate.LONG);
		}

		if (docTypeCode != null
				&& docTypeCode.longValue() > 0L) {
			sql.append("and doc.type.code = ? ");
			params.add(docTypeCode);
			types.add(Hibernate.LONG);
		}

		if (displayModeCode != null
				&& displayModeCode.longValue() > 0L) {
			sql.append("and doc.displayMode.code = ? ");
			params.add(displayModeCode);
			types.add(Hibernate.LONG);
		}


		if (orderField != null) {
			sql.append("order by doc.");
			sql.append(orderField);
			if (orderAsc.booleanValue()) {
				sql.append(" asc ");
			} else {
				sql.append(" desc ");
			}
		} else {
			sql.append("order by doc.title asc");
		}

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


	/**
	 * Crea un nuevo DocumentContainer y lo devuelve como resultado
	 * a partir de un DocumentContainerForm.<br/>
	 * 
	 * @param documentContainerForm forma con los datos para crear el nuevo DocumentContainer
	 * @param singleTransation indicador de si debe o no crear una transaccion para persistir este objeto o no.
	 * @return DocumentContainer creado exitosamente
	 * @throws Exception si ocurre un error al crear el nuevo objeto.
	 */
	public DocumentContainer save (DocumentContainerForm documentContainerForm, boolean singleTransation) throws Exception {

		Session sess   = null;
		Transaction tx = null;
		
		DocumentContainer documentContainer = null;
		Catalogue category = null;
		DocumentType type  = null;
		DocumentDisplayMode  mode  = null;
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
			

			if (documentContainerForm.getCode() != null
					&& documentContainerForm.getCode().longValue() > 0L) {
				documentContainer = (DocumentContainer) sess.load(DocumentContainer.class, documentContainerForm.getCode());
			} else {
				documentContainer = new DocumentContainer();
				documentContainerForm.setCode(null);
			}

			PropertyUtils.copyProperties(documentContainer, documentContainerForm);

  			if (documentContainer.getCategory() == null || (!documentContainer.getCategory().getCode().equals(documentContainerForm.getCategoryCode()))) {
  				category = (Catalogue) sess.load(Catalogue.class, documentContainerForm.getCategoryCode());
				documentContainer.setCategory(category);
			}

  			if (documentContainer.getType() == null || (!documentContainer.getType().getCode().equals(documentContainerForm.getDocTypeCode()))) {
  				type = (DocumentType) sess.load(DocumentType.class, documentContainerForm.getDocTypeCode());
				documentContainer.setType(type);
			}

  			if (documentContainer.getDisplayMode() == null || (!documentContainer.getDisplayMode().getCode().equals(documentContainerForm.getDisplayModeCode()))) {
  				mode = (DocumentDisplayMode) sess.load(DocumentDisplayMode.class, documentContainerForm.getDisplayModeCode());
				documentContainer.setDisplayMode(mode);
			}
  			
  			
			//guarda el tamaño del archivo en bytes
  			
			File file = new File(this.context.getRealPath(documentContainer.getPath()));
			documentContainer.setSize(file.length());
			
			String key = file.getName().substring(file.getName().lastIndexOf('.')+1);
			
	    	documentContainer.setExtension(key);
			sess.saveOrUpdate(documentContainer);
            
            
			/*
			 *Si transaction es nulo, entonces debe  
			 *efectuar commit con la transaccion iniciada en
			 *esta sección 
			 * */
			if (singleTransation ) {
				tx.commit();	
			}
            
            documentContainerForm.setCode(documentContainer.getCode());
            
		} catch (Exception e) {
			if (tx != null && singleTransation ) {
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
		return documentContainer;
	}

	/**
	 * @return Returns the context.
	 */
	public ServletContext getContext() {
		return context;
	}

	/**
	 * @param context The context to set.
	 */
	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	
}
