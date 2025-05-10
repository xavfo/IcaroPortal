package com.iportal.view;

import java.util.ArrayList;
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
import com.iportal.model.Language;
import com.iportal.model.portal.Menu;
import com.iportal.model.portal.MenuItem;
import com.iportal.model.portal.MenuLanguage;
import com.yage.struts.action.BaseTilesAction;

public class MenuPortalTilesAction extends BaseTilesAction {

private static Log logger = LogFactory.getLog(MenuPortalTilesAction.class);
    
	public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response )
     
       
    throws Exception {
        
        Session sess = null;     
		List<MenuItem> menuItemList = null;
		MenuItem menuItem = null;
		
        String menuCode = (String)  context.getAttribute("menuCode");
       // String menuItemCode=request.getParameter("code");
        

       	try {
  			  
       		sess = getHibernateSession();
		  	
			if (menuCode != null && menuCode.length() > 0){
	       		Menu menu = (Menu)sess.load(Menu.class,Long.valueOf(menuCode));
			  	menuItemList = menu != null ? getAll(menu, request, sess): null;
			}
 			 		 				
  				
			/*if (menuItemCode != null && menuItemCode.length() > 0){
				menuItem = (MenuItem)sess.load(MenuItem.class, Long.valueOf(menuItemCode));
				if (menuItem.getGroup().booleanValue())
					Hibernate.initialize(menuItem.getMenuItems());
			}*/
  		

        	} catch (Exception e) {
  				logger.error(e.getMessage(), e);
  			}finally{
  				if (sess != null)
  	 				try {
  	 				    sess.clear();
  	 					sess.close();
  	 				} catch (Exception e) {
  	 				}
		}
  			

       	request.setAttribute("menuItemList", menuItemList);
		request.setAttribute("menuItem", menuItem);

		return null;
    }
    
    @SuppressWarnings("unchecked")
	public List<MenuItem> getAll( Menu menu, HttpServletRequest request,  Session sess )
    
    throws Exception{

    	List<MenuItem> result = null;
		List<MenuItem> menuItemList =  new ArrayList<MenuItem>();
		
		Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);
		
		if(language == null )				
			throw new Exception("Language is undefined");
		
		MenuLanguage mLang = menu.getMenuLanguage(language);
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("FROM MenuItem as menuItem ");
		sql.append("WHERE menuItem.level = ? ");
		sql.append("AND menuItem.menuLanguage.code = ? ");
		
		Query query = sess.createQuery(sql.toString());
		query.setInteger(0, 1);
		query.setLong(1, mLang.getCode());
		result = query.list();
		
		request.setAttribute("parents", result);
		
		
		/*Iterator it = result.iterator();
		while (it.hasNext()) {
			MenuItem currentItem = (MenuItem) it.next();
			findChildren(menuItemList, currentItem);
		}*/
		
		for (MenuItem currentItem : result){
			if (currentItem.getGroup().booleanValue()) {
				findChildren(menuItemList, currentItem);
			} else {
				menuItemList.add(currentItem);
			}
		}
		
		return menuItemList;
	}
    
    
    private void findChildren(List<MenuItem> list, MenuItem menuItem) {
		//adds the menu to the end of the list
        list.add(menuItem);
        
        try {
        	// initializes the lazy set
           	Hibernate.initialize(menuItem.getMenuItems());
            
            if (menuItem.getMenuItems() != null) {

            	for (MenuItem subMenu : menuItem.getMenuItems()){
					if (subMenu.getGroup().booleanValue()) {
						findChildren(list, subMenu);
					} else {
						list.add(subMenu);
					}
				}
            }
        } catch (Exception e) {
        	logger.info(e.getMessage(), e);
		}
    }
}
