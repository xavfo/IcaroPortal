/*
 * Created Jul 12, 2006
 *	DocumentContainerFacade.java
 */
package com.iportal.biz.facade;

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

import com.iportal.model.Image;
import com.iportal.model.ImageCategory;
import com.yage.struts.action.UploadMediaForm;

/**
 * Objeto Facade para contenedore de im�genes.
 * @author YAGE( hernan)
 * @version 1.0
 *
 */
/**
 * @author hernan
 *
 */
public class ImageContainerFacade extends DocumentContainerFacade {

	/**
	 * 
	 */
	public ImageContainerFacade() {
		super();
	}

	/**
	 * @param context
	 */
	public ImageContainerFacade(ServletContext context) {
		super(context);
	}

    public ImageContainerFacade(ServletContext context, Action action, HttpServletRequest request) {
        super(context);
    }
	/**
	 * @param openedSession
	 * @param context
	 */
	public ImageContainerFacade(Session openedSession, ServletContext context) {
		super(openedSession, context);
	}


	/**
	 * @param openedSession
	 */
	public ImageContainerFacade(Session openedSession) {
		super(openedSession);
	}


	/**
	 * Retrieves all the DocumentContainer items.
	 * 
	 * @return List
	 */
	public List list ( UploadMediaForm listForm) throws HibernateException {

		Session sess = null;
		List results = null;
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<NullableType> types = new ArrayList<NullableType>();

		sql.append("from Image as image ");
		sql.append(" where 1 = 1 ");

		if (listForm.getName() != null && listForm.getName().length() > 0 ) {
			String name = "%" + listForm.getName() + "%";
			sql.append("and image.name like ? ");
			params.add(name);
			types.add(Hibernate.STRING);
		}
		
		if (listForm.getCategoryCode() != null
				&& listForm.getCategoryCode().longValue() > 0L) {
			sql.append("and image.imageCategory.code = ? ");
			params.add(listForm.getCategoryCode());
			types.add(Hibernate.LONG);
		}

		if (listForm.getOrderField() != null) {
			sql.append("order by image.");
			sql.append(listForm.getOrderField());
			if (listForm.getOrderAsc().booleanValue()) {
				sql.append(" asc ");
			} else {
				sql.append(" desc ");
			}
		} else {
			sql.append("order by image.name asc");
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
	 * Crea un nuevo Image y lo devuelve como resultado
	 * a partir de un UploadMediaForm.<br/>
	 * 
	 * @param imageContainerForm forma con los datos para crear el nuevo Image
	 * @param singleTransation indicador de si debe o no crear una transaccion para persistir este objeto o no.
	 * @return DocumentContainer creado exitosamente
	 * @throws Exception si ocurre un error al crear el nuevo objeto.
	 */
	public Image save (UploadMediaForm imageContainerForm, boolean singleTransation) throws Exception {

		Session sess   = null;
		Transaction tx = null;
		
		Image imageContainer = null;
		ImageCategory category = null;
		
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
			

			if (imageContainerForm.getCode() != null
					&& imageContainerForm.getCode().longValue() > 0L) {
				imageContainer = (Image) sess.load(Image.class, imageContainerForm.getCode());
			} else {
				imageContainer = new Image();
				imageContainerForm.setCode(null);
			}

			PropertyUtils.copyProperties(imageContainer, imageContainerForm);
			
			if (imageContainer.getImageCategory() == null || (!imageContainer.getImageCategory().getCode().equals(imageContainerForm.getCategoryCode()))) {
  				category = (ImageCategory) sess.load(ImageCategory.class, imageContainerForm.getCategoryCode());
  				imageContainer.setImageCategory(category);
			} 
  			

			sess.saveOrUpdate(imageContainer);			

			if (imageContainerForm.getCode() != null
					&& imageContainerForm.getCode().longValue() > 0L) {
				imageContainerForm.setCode(imageContainer.getCode());
				
			} 
			
			/*
			 *Si transaction es nulo, entonces debe  
			 *efectuar commit con la transaccion iniciada en
			 *esta secci�n 
			 * */
			if (singleTransation ) {
				tx.commit();	
			}
            
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
		return imageContainer;
	}
	
	
}
