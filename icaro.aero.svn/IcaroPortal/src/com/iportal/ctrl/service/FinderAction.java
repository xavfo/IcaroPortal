/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;

import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.action.BaseDispatchAction;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * comment ContactAction
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class FinderAction extends BaseDispatchAction {

    
    private static Log logger = LogFactory.getLog(FinderAction.class);

    
    public ActionForward forward (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	    
    	
		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }
    
    public ActionForward find (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	         
    	SearchForm searchForm = (SearchForm) form;
    	ActionMessages messages = new ActionMessages();
        
        Session sess = null;
        List sectionList = null;        
        List documentList = null;
        //Section section = null;

        try {
    		sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
    		
        	/*section = (Section) sess.load(Section.class, searchForm.getSectionCode()); 
		    if (section.getParent() != null) {
		        Hibernate.initialize( section.getParent().getSections() );
		    }*/
       	   
    		String text = "%" + searchForm.getText() + "%";

            StringBuffer sql = null;
     		ArrayList<String> params = null;
     		ArrayList<NullableType> types = null;
     		Query query = null;
            
            if ( searchForm.getCategoryCode() != null && 
            	 (searchForm.getCategoryCode().longValue() == 0	||
				  searchForm.getCategoryCode().longValue() == 1) ) {
            	
            	sql = new StringBuffer();
            	params = new ArrayList<String>();
         		types = new ArrayList<NullableType>();
        		sql.append("from Section as section "); 
        		sql.append("where section.name like ?  ");
    		    params.add(text);
    		    types.add(Hibernate.STRING);
        		sql.append("or section.leadinTitle like ?  "); 
    		    params.add(text);
    		    types.add(Hibernate.STRING);
        		sql.append("or section.leadinText like ?  "); 
    		    params.add(text);
    		    types.add(Hibernate.STRING);
        		sql.append("or section.content1 like ?  "); 
    		    params.add(text);
    		    types.add(Hibernate.STRING);
        		sql.append("or section.content2 like ?  ");
    		    params.add(text);
    		    types.add(Hibernate.STRING);
        		sql.append("or section.content3 like ?  "); 
    		    params.add(text);
    		    types.add(Hibernate.STRING);  
    		    
    			Object[] arrayParams = params.toArray();
    			query = sess.createQuery(sql.toString());
    			for (int i = 0; i < types.size(); i++) {
    			    query.setParameter(i, arrayParams[i], (Type) types.get(i) );
    			}
    		    sectionList = query.list();
            }                                           
              
            
            	if ( searchForm.getCategoryCode() != null && 
                 	 (searchForm.getCategoryCode().longValue() == 0	||
     				  searchForm.getCategoryCode().longValue() == 5) ) {
                 	
                 	sql = new StringBuffer();
                 	params = new ArrayList<String>();
              		types = new ArrayList<NullableType>();
             		sql.append("from Document as document "); 
             		sql.append("where document.name like ?  ");
         		    params.add(text);
         		    types.add(Hibernate.STRING);
         		    
         			Object[] arrayParams = params.toArray();
        			query = sess.createQuery(sql.toString());
         			for (int i = 0; i < types.size(); i++) {
         			    query.setParameter(i, arrayParams[i], (Type) types.get(i));
         			}
         			documentList = query.list();
               }
			
    		
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
		
		// Look up for messages
		/*if (section == null) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(""));
		} else if (!section.getEnabled().booleanValue()) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("", section.getTitle()));
		}*/

		// Report any messages we have discovered to the failure page
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
			return mapping.findForward( Globals.FORWARD_FAILURE );
		}
        
		// Save the List of results in request scope
		request.setAttribute("find", new Boolean(true));
		//request.setAttribute("section", section);
		//request.setAttribute("documentSet", section.getDocuments());		
		request.setAttribute("sectionList", sectionList);		
		request.setAttribute("documentList", documentList);

		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }    
          
}
