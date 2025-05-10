/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * Consulta las siguiente informacion tipo:
 * <ul>
 * 	<li>Categoria de documentos</li>
 * 	<li>Modo de despligue del documento (link, iframe)</li>
 * 	<li>Tipo de Documento (documento o material de soporte)</li>
 * </ul>
 * 
 * Nota: Se reconstruyo la clase de nuevo no estar consultado los objetos eficientemente.<br/>
 * 
 * @author YAGE(hernan)
 * @version 1.2
 *
 */
public class DocumentContainerTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(DocumentContainerTilesAction.class);
    
    
   
	public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;        
 		List categories = null;
/* 		List docTypes   = null;
 		List modes   = null;*/
        
        try {
            sess = getHibernateSession();
            //Retrieves the category
            StringBuffer hql = new StringBuffer();
            hql.append(" select new com.iportal.biz.RowItem (catalogue.code, catalogue.name)");
            hql.append(" from Catalogue as catalogue ");
            hql.append(" where catalogue.enabled = ? ");
            hql.append(" and catalogue.type.code = ? ");
            hql.append(" order by catalogue.name ");
            
            Query query = sess.createQuery(hql.toString());
            
            query.setParameter(0, Globals.TRUE, Hibernate.BOOLEAN);
            query.setParameter(1, Constants.CATALOGUE_TYPE_DOCUMENT, Hibernate.LONG);
            query.setCacheable(Globals.TRUE.booleanValue());
            categories = query.list();	
			
			//Retrieves the document type
            /*hql.delete(0, hql.length());
            hql.append(" select new com.iportal.biz.RowItem (docType.code, docType.name)");
            hql.append(" from DocumentType as docType ");
            hql.append(" order by docType.name ");
                        
            query = sess.createQuery(hql.toString());
            query.setCacheable(Globals.TRUE.booleanValue());
			docTypes = query.list();
			
			//Retrieves display Mode
            hql.delete(0, hql.length());
            hql.append(" select new com.iportal.biz.RowItem (mode.code, mode.name)");
            hql.append(" from DocumentDisplayMode as mode ");
            hql.append(" order by mode.name ");
                        
            query = sess.createQuery(hql.toString());
            query.setCacheable(Globals.TRUE.booleanValue());
            modes = query.list();*/
			
			
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
		
		//Save the Lists in request scope
		request.setAttribute("categoryList", categories);
		//request.setAttribute("documentTypeList", docTypes);
		//request.setAttribute("displayModeList", modes);
		
		
		return null;
    }

}
