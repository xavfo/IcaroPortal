/**
 * 
 */
package com.iportal.view;

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
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.RowItem;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.biz.portal.content.ContentFacade;
import com.iportal.model.portal.Content;
import com.yage.struts.action.BaseTilesAction;

/**
 * @author moni
 *
 */
public class NavigationTreeTilesAction extends BaseTilesAction {
    
	private static Log logger = LogFactory.getLog(NavigationTreeTilesAction.class);
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        HttpSession session = request.getSession();
        List<RowItem> navigator = null;
        
        
        //MenuLanguage mLang = null;
        try {
        	MenuPortalBean menuPortalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
        	navigator = (ArrayList<RowItem>) session.getAttribute("hierarchyPath"); 
        	

        	boolean changePath = false;
        	if (navigator != null) {
        		Long lastCode = navigator.get(navigator.size()-1).getCode();
        		if(!menuPortalBean.getContentCode().equals(lastCode)) {
        			changePath = true;
        		}
        	} else {
        		changePath = true;
        	}
        	
        	if ( changePath ) {
            	Content content = (Content) request.getAttribute("content");
            	if (content != null) {
            		ArrayList<Content> group = (ArrayList<Content>)content.getHierarchy(); 
            		navigator = new ArrayList<RowItem>(group.size());
            		for (Content c: group) {
            			navigator.add(new RowItem(c.getCode(), c.getTitle()));
            		}
            		 
            	} else if (menuPortalBean != null){
            		//Debe consultar la jerarqu�a usando un query
            		
            		ContentFacade facade = new ContentFacade();
            		navigator = facade.getHierarchy(new RowItem(menuPortalBean.getContentCode(), menuPortalBean.getContentParentCode(), menuPortalBean.getContentTitle() ));
            		
            	}
            	//setea barra de navegacion
            	session.setAttribute("hierarchyPath", navigator);
        		
        	}

            
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
		
		
		return null;
    }

}
