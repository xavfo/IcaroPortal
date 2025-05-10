/*
 * Created Jul 28, 2006
 *	ContentContainerAction.java
 */
package com.iportal.ctrl.portal.content;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.model.portal.Content;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * @author hernan
 *
 */
public abstract class ContentContainerAction extends BaseDispatchAction {

	/**
	 * 
	 */
	public ContentContainerAction() {
		super();
	}

	/**
	 * Metodo que cuenta el numero de registros activos de un contenedor relacionados
	 * con un contenido. En caso de que el contenido se encuentre inicializado
	 * hace el conteo mediante un filtro de hibernate que permite mayor eficiencia.
	 * @param sess sesion activa de hibernate
	 * @param containerName nombre del contenedor (clase por ejemplo LegalDocumentContainer) o el nombre de la propiedad del contenido con la coleccion de contendores que se desea traer (por ejemplo listOfRelatedLegalDocuments)
	 * @param content contenido en estado persistente con la sesion actual de hibernate o nulo para indicar que se efectue el conteo 
	 * @param contentCode si el <code>content</code> es nulo este debe tener el codigo del contenido del cual se desea contar el numero de contenedores
	 * @param typeCode si se desea filtrar los contenedores de determinado tipo (por ejemplo material de soporte Constants.DOCUMENT_TYPE_SUPPORT) o si no se envia en nulo
	 * @return numero de contenedores activos (isEnable=TRUE) relacionados al contenido actual <code>content</code> o al <code>contentCode</code> 
	 * @throws HibernateException en caso de error al consultar efectuar la consulta de hibernate
	 * @throws IllegalAccessException en caso de error al extraer la propiedad de la coleccion de contenidos
	 * @throws InvocationTargetException en caso de error al extraer la propiedad de la coleccion de contenidos
	 * @throws NoSuchMethodException en caso de error al extraer la propiedad de la coleccion de contenidos
	 */
	protected Long countContentContainer(Session sess, String containerName,
			Content content, Long contentCode, Long typeCode)
			throws HibernateException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		Query query = null;
		if (content == null || !sess.contains(content)) {
			// consultar los documentos relacionados con este contenido
			StringBuffer hql = new StringBuffer();
			hql.append(" select count(cont.code) ");
			hql.append(" from  ");
			hql.append(containerName);
			hql.append(" cont  ");
			hql.append(" inner join cont.contents content ");
			hql.append(" where content.code = ? ");
			hql.append(" and cont.isEnabled = ? ");

			// si necesita filtar tipos de documentos se envia por parametro el
			// tipo de
			// contenedor deseado
			if (typeCode != null && typeCode > 0L) {
				hql.append(" and cont.type.code = :typeCode ");
			}

			query = sess.createQuery(hql.toString());
			query.setLong(0, contentCode);
			query.setBoolean(1, Globals.TRUE);
			if (typeCode != null && typeCode > 0L) {
				query.setLong("typeCode", typeCode);
			}
			query.setCacheable(true);
			// resp = (Long) query.uniqueResult();
		} else {
			// content.getListOfRelatedLegalDocuments()
			StringBuffer filterHql = new StringBuffer();
			filterHql
					.append("select count(this.code) where this.isEnabled=:enabled ");
			if (typeCode != null && typeCode > 0L) {
				filterHql.append(" and this.type.code=:typeCode ");
			}

			query = sess.createFilter(
					PropertyUtils.getProperty(content, containerName),
					filterHql.toString()).setBoolean("enabled", Globals.TRUE);
			if (typeCode != null && typeCode > 0L) {
				query.setLong("typeCode", typeCode);
			}

		}
		
		Long resp = (Long) query.uniqueResult();
		query = null;
		return resp;
	}
	
	
	/**
	 * Llama a las consultas que cuentan el número de contenedores 
	 * relacionados con contenidos para saber si muestra o no accesos
	 * a estos recuros
	 * @param sess sesion activa de hibernate
	 * @param content contenido en estado de persistente si se tiene o nulo
	 * @param contentCode código de contenido si content es nulo 
	 * @throws HibernateException error de hibernate
	 * @throws IllegalAccessException error de acceso ilegal a propiedades de PropertyUtils
	 * @throws InvocationTargetException error de invocation ilegal a propiedades de PropertyUtils
	 * @throws NoSuchMethodException error de metodo no encontrado al llamara a funciones de PropertyUtils
	 */
	protected void setCountContentContainers (HttpServletRequest request, Session sess, Content content, Long contentCode)
			throws HibernateException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		Long documents = null;
		Long support   = null;
		Long faqs      = null;
		Long links     = null;
		
	
		if ((content == null || !sess.contains(content)) ) {
			if (contentCode == null || contentCode <= 0L) {
				throw new IllegalAccessException(" if content is null or not conected,  contentCode must not be null");
			}			
			documents = this.countContentContainer(sess, "DocumentContainer", null, contentCode, Constants.DOCUMENT_TYPE_DOCUMENTS);
			support   = this.countContentContainer(sess, "DocumentContainer", null, contentCode, Constants.DOCUMENT_TYPE_SUPPORT);
			faqs      = this.countContentContainer(sess, "FaqContainer", null, contentCode, null);
			links     = this.countContentContainer(sess, "LinkContainer", null, contentCode, null);

		} else {
			//Content activo y conectado con la sesion actual
			if (contentCode != null && !content.getCode().equals(contentCode)) {
				 throw new IllegalAccessException(" if content conected,  contentCode must be null or equals to content.getCode()");	
			}						
			documents = this.countContentContainer(sess, "listOfRelatedDocuments", content, contentCode, Constants.DOCUMENT_TYPE_DOCUMENTS);
			support   = this.countContentContainer(sess, "listOfRelatedDocuments", content, contentCode, Constants.DOCUMENT_TYPE_SUPPORT);
			faqs      = this.countContentContainer(sess, "listOfRelatedFaq", content, contentCode, null);
			links     = this.countContentContainer(sess, "listOfRelatedLink", content, contentCode, null);
				}
	   
		request.setAttribute("documents", documents);
		request.setAttribute("support", support);
     	request.setAttribute("faqs", faqs);	
     	request.setAttribute("links", links);
	    

	}

}
