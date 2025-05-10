/*
 * Created Jul 18, 2006
 *	ListContainerAction.java
 */
package com.iportal.ctrl.portal.content;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.RowItem;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.biz.portal.content.ContainerForm;
import com.iportal.model.container.FaqContainer;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;

/**
 * 
 * Accion que invoca a buscadores de contenidos relacionados.
 * 
 * @author hernan
 * @version 1.0
 *
 */
public class ListContainerAction extends BaseDispatchAction {
	
	private static Log logger = LogFactory.getLog(ListContainerAction.class);
	
	/**
	 * 
	 */
	public ListContainerAction() {
		super();
	}
	
	/**
	 * Lista el contenedor haciendo un join con el contenido.
	 * 
	 * @param sess conexion hibernate abierta
	 * @param portalBean bean con datos del sesion activa
	 * @return Lista de contenedores activos relacionados al contenido actual
	 */
	private List listContainer (Session sess, String containerName, ContainerForm containerForm ){
	   		
			//consultar los documentos relacionados con este contenido
	   		StringBuffer hql = new StringBuffer();
	   		hql.append(" select cont ");
	   		hql.append(" from  ");
	   		hql.append(containerName);
	   		hql.append(" cont  ");
	   		hql.append(" join cont.contents content ");
	   		hql.append(" where content.code = ? ");
	   		hql.append(" and cont.isEnabled = ? ");
	   		//si necesita filtar tipos de documentos se envia por parametro el tipo de 
	   		//contenedor deseado
	   		if (containerForm.getTypeCode() != null && containerForm.getTypeCode() > 0L){
	   			hql.append(" and cont.type.code = :typeCode ");
	   		}
	   		
	   		/*
	   		 * Asigna on orden a la lista
	   		 */
	   		if (containerForm.getOrderField()!=null && containerForm.getOrderField().length()>0  ) {
	   			hql.append(" Order By cont.");
	   			hql.append(containerForm.getOrderField());
	   			if (containerForm.getOrderAsc())
	   				hql.append(" Asc");
	   			else
	   				hql.append(" Desc");
	   		}
	   		
	   		Query query = sess.createQuery(hql.toString());
	   		query.setLong(0, containerForm.getContentCode());
	   		query.setBoolean(1, Globals.TRUE);
	   		
	   		if (containerForm.getTypeCode() != null && containerForm.getTypeCode() > 0L){
	   			query.setLong("typeCode", containerForm.getTypeCode());
	   		}

	   		
	   		query.setCacheable(true);
	   		return  query.list();
	}
	
	private RowItem getItemContent (Session sess, Long itemContentCode) {
		StringBuffer hql = new StringBuffer();
    	
		hql.append(" select new com.iportal.biz.RowItem ( ");
		hql.append(" content.code, content.title ) ");
		hql.append(" From Content content ");
		hql.append(" Where content.code = ? ");
		
		return (RowItem)sess.createQuery(hql.toString()).setLong(0 ,itemContentCode).uniqueResult();

		
	}
	
	private ActionForward list (ActionMapping mapping, ContainerForm form, HttpServletRequest request, String containerName, String resultAttribute, String forward) {
  		ActionMessages messages = new ActionMessages();
   		List results = null;
   		Session sess = null;
   		
 	   	HttpSession session = request.getSession();
 	   	MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
 	   	
 	   	if (portalBean == null) {
   			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.content.notFound"));
   		} else {
   	 	   	try {
   	 	   		sess = getHibernateSession();
   	 	   		//carga el Item de contenido de ser necesario
   	 	   		if (form.getContentCode() != null && form.getContentCode().longValue() > 0L) {
   	 	   			RowItem itemContent = this.getItemContent(sess, form.getContentCode());
   	 	   			request.setAttribute("itemContent", itemContent);
   	 	   		} else {
   	 	   			form.setContentCode(portalBean.getContentCode());
   	 	   		}
   	 	   		results = this.listContainer(sess, containerName, form);
   	 	   		//regresar a valor anterior
   	 	   		form.setContentCode(null);
   			    request.setAttribute(resultAttribute, results);
   			
   	   		} catch (Exception e) {
   				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
   	   			
   	   			logger.error(e.getMessage(), e);
   	   		}finally {
   				if (sess != null)
   					try {
   					    sess.clear();
   						sess.close();
   					} catch (Exception e) {
   					} 			
   			} 		
   		
   		}
	    
  		if (!messages.isEmpty()) {
  			saveMessages(request, messages);
  			return mapping.findForward(Globals.FORWARD_FAILURE);
  		}
	    
		return mapping.findForward(portalBean.getLayoutForward() +forward+ Globals.FORWARD_LIST);

	}
	
	public ActionForward listDocuments (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
		
		return this.list(mapping,(ContainerForm)form, request, "DocumentContainer", "documents", "_");
		
   }

   public ActionForward listLegalDocuments (
           ActionMapping mapping, 
           ActionForm form,
           HttpServletRequest request, 
           HttpServletResponse response)
   throws Exception {

	   return this.list(mapping, (ContainerForm)form, request, "LegalDocumentContainer", "documents", "_legal_");
	   
  }

   public ActionForward listLink (
           ActionMapping mapping, 
           ActionForm form,
           HttpServletRequest request, 
           HttpServletResponse response)
   throws Exception {
	   
	   return this.list(mapping, (ContainerForm)form, request, "LinkContainer", "links", "_link_");
	   
  }

   public ActionForward listFaq (
           ActionMapping mapping, 
           ActionForm form,
           HttpServletRequest request, 
           HttpServletResponse response)
   throws Exception {
       
	   //return this.list(mapping, request, "FaqContainer", "faqs");
 		ActionMessages messages = new ActionMessages();
  		List<FaqContainer> results = null;
  		Session sess = null;
  		ContainerForm containerForm = (ContainerForm)form;
  		
	   	HttpSession session = request.getSession();
	   	MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);

	   	if (portalBean == null) {
  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.content.notFound"));
  		} else {
  	 	   	try {

  	 	   		sess = getHibernateSession();
   	 	   		if (containerForm.getContentCode() != null && containerForm.getContentCode().longValue() > 0L) {
   	 	   			RowItem itemContent = this.getItemContent(sess, containerForm.getContentCode());
   	 	   			request.setAttribute("itemContent", itemContent);
   	 	   			
   	 	   		} else {
   	 	   			containerForm.setContentCode(portalBean.getContentCode());
   	 	   		}


  		   		results = (ArrayList<FaqContainer>)this.listContainer(sess, "FaqContainer", containerForm);
  		   		//Regresar a valor anterior
  		   		containerForm.setContentCode(null);
  		   		for ( FaqContainer faq : results ) {
  		   			Hibernate.initialize(faq.getDocuments());
  		   			//logger.info(faq.getDocuments().size());
  		   		}
  			    request.setAttribute("faqs", results);
  			    System.out.println("entro aca");
  			
  	   		} catch (Exception e) {
  				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
  	   			
  	   			logger.error(e.getMessage(), e);
  	   		}finally {
  				if (sess != null)
  					try {
  					    sess.clear();
  						sess.close();
  					} catch (Exception e) {
  					} 			
  			} 		
  		
  		}
 		if (!messages.isEmpty()) {
 			saveMessages(request, messages);
 			return mapping.findForward(Globals.FORWARD_FAILURE);
 		}
		return mapping.findForward(portalBean.getLayoutForward() +"_faq_"+ Globals.FORWARD_LIST);
  }


}
