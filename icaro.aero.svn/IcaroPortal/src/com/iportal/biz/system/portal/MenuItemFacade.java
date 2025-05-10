package com.iportal.biz.system.portal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.BaseBussinessLogic;
import com.iportal.biz.RowItem;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.Language;
import com.iportal.model.portal.Content;
import com.iportal.model.portal.DisplayMode;
import com.iportal.model.portal.Menu;
import com.iportal.model.portal.MenuItem;
import com.iportal.model.portal.MenuLanguage;
import com.yage.Globals;

public class MenuItemFacade extends BaseBussinessLogic{

	private static Log logger = LogFactory.getLog(MenuItem.class);
    private Action action;
    private HttpServletRequest request;
   
	
	public MenuItemFacade() {
		super();
	}

    public MenuItemFacade(Action action, HttpServletRequest request) {
        super();
        this.action = action;
        this.request = request;
    }
    
	public MenuBean getMenuByLanguage(Long menuCode, HttpServletRequest request) 
	throws Exception {
		Session sess = null;
		MenuBean menuBean = null;
		try {
			if(menuCode == null)
				throw new Exception("Null is not a valid value to code in MenuItem Object");	
					
			if (menuCode.longValue() < 1L )
				throw new Exception("0 is not a valid value to code in MenuItem Object");
			
			Language language = (Language) request.getSession()
													.getAttribute(Constants.LANGUAGE_KEY);
			
			if(language == null )				
				throw new Exception("Language is undefined");
			
			sess = getHibernateSession();
			
			Menu menu = (Menu) sess.load(Menu.class, menuCode);
			
			if (menu == null) 
				throw new Exception(menuCode + " is not a valid value to code in MenuItem Object");
				
			MenuLanguage menuLanguage = menu.getMenuLanguage(language);
			menuBean = new MenuBean();
			PropertyUtils.copyProperties(menuBean, menu);
			if (menuLanguage != null)
				menuBean.setName(menuLanguage.getName());
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
			//throw new Exception("Unexpected error while trying to get all Menues");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
		return menuBean;
	}
	
	
	public List<RowItem> getAllByTree(SearchContentForm searchForm, HttpServletRequest request)
	throws Exception{
		Session sess = null;
		List<RowItem> result = null;
		List<Long> offspringList = null;
		try {
			
			Language language = (Language) request.getSession()
								 						.getAttribute(Constants.LANGUAGE_KEY);
			
			if(language == null )				
				throw new Exception("Language is undefined");
			
			sess = getHibernateSession();
			
			MenuItem currentItem = (MenuItem) sess.load(MenuItem.class, searchForm.getCode());
			offspringList = new ArrayList<Long>();
			findChildrenCodes(offspringList, currentItem);
			
			StringBuffer sql = new StringBuffer();
			
			sql.delete(0, sql.length());
			sql.append("Select new com.iportal.biz.RowItem(menuItem.code, menuItem.parent.code, menuItem.name, menuItem.level, menuItem.group, null) ");
			sql.append("FROM MenuItem as menuItem ");
			sql.append("Where menuItem.menuLanguage.menu.code = :menuCode ");
			sql.append("  AND menuItem.menuLanguage.language.code = :languageCode ");
			sql.append("  AND menuItem.code not in ( :codes ) ");

			Query query = sess.createQuery(sql.toString());
			query.setLong("menuCode", searchForm.getItemCode());
			query.setLong("languageCode", language.getCode());
			query.setParameterList("codes", offspringList, Hibernate.LONG);
			
			result = (ArrayList<RowItem>)query.list();
			
			sql.delete(0, sql.length());
			sql.append("Select new com.iportal.biz.RowItem(menuItem.code, menuItem.parent.code, menuItem.name, menuItem.level, menuItem.group, menuItem.code) ");
			sql.append("FROM MenuItem as menuItem ");
			sql.append("Where menuItem.menuLanguage.menu.code = :menuCode ");
			sql.append("  AND menuItem.menuLanguage.language.code = :languageCode ");
			sql.append("  AND menuItem.code in ( :codes ) ");
			
			query = sess.createQuery(sql.toString());
			query.setLong("menuCode", searchForm.getItemCode());
			query.setLong("languageCode", language.getCode());
			query.setParameterList("codes", offspringList, Hibernate.LONG);
			
			List<RowItem> familyList = (ArrayList<RowItem>)query.list();
			if (familyList != null)
				result.addAll(familyList);
			
			MenuLanguage menuLang = ((Menu) sess.load(Menu.class, searchForm.getItemCode()))
																				.getMenuLanguage(language);
			if (menuLang != null)
				searchForm.setTreeName(menuLang.getName());
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get all Menues");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
		return result;
	}
	
	
	public List<MenuItem> getAll(MenuItemForm menuItemForm, HttpServletRequest request)
	throws Exception{
		Session sess = null;
		List result = null;
		List<MenuItem> menuItemList = null;
		try {
			
			if (menuItemForm.getItemCode() != null && menuItemForm.getItemCode().longValue()>0)
				menuItemForm.setCode(menuItemForm.getItemCode());
			
			if(menuItemForm.getCode() == null)
				throw new Exception("Null is not a valid value to code in MenuItem Object");	
					
			if (menuItemForm.getCode().longValue() < 1L )
				throw new Exception("0 is not a valid value to code in MenuItem Object");
				

			Language language = (Language) request.getSession()
								 						.getAttribute(Constants.LANGUAGE_KEY);
			
			if(language == null )				
				throw new Exception("Language is undefined");
			
			sess = getHibernateSession();

			StringBuffer sql = new StringBuffer();
			
			sql.append("FROM MenuItem as menuItem ");
			sql.append("WHERE menuItem.level = ? ");
			sql.append("  AND menuItem.menuLanguage.menu.code = ? ");
			sql.append("  AND menuItem.menuLanguage.language.code = ? ");
			sql.append("Order by menuItem.code ");
			
			Query query = sess.createQuery(sql.toString());
			query.setInteger(0, 1);
			query.setLong(1, menuItemForm.getCode());
			query.setLong(2, language.getCode());
			
			result = query.list();
			
			if (result != null)
				menuItemList = new ArrayList<MenuItem>();
			
			Iterator it = result.iterator();
			while (it.hasNext()) {
				MenuItem currentItem = (MenuItem) it.next();
				findChildren(menuItemList, currentItem);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get all Menues");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
		return menuItemList;
	}
	
	
	public List<MenuItem> getAllMenuItems(MenuItemForm menuItemForm, HttpServletRequest request)
	throws Exception{
		Session sess = null;
		List result = null;
		List<MenuItem> menuItemList = null;
		try {
			
			if (menuItemForm.getItemCode() != null && menuItemForm.getItemCode().longValue()>0)
				menuItemForm.setCode(menuItemForm.getItemCode());
			
			if(menuItemForm.getCode() == null)
				throw new Exception("Null is not a valid value to code in MenuItem Object");	
					
			if (menuItemForm.getCode().longValue() < 1L )
				throw new Exception("0 is not a valid value to code in MenuItem Object");
				

			Language language = (Language) request.getSession()
								 						.getAttribute(Constants.LANGUAGE_KEY);
			
			if(language == null )				
				throw new Exception("Language is undefined");
			
			sess = getHibernateSession();

			StringBuffer sql = new StringBuffer();
			
			sql.append("FROM MenuItem as menuItem ");
			sql.append("WHERE menuItem.level = ? ");
			sql.append("  AND menuItem.menuLanguage.menu.code = ? ");
			sql.append("  AND menuItem.menuLanguage.language.code = ? ");
			sql.append("Order by menuItem.code ");
			
			Query query = sess.createQuery(sql.toString());
			query.setInteger(0, 1);
			query.setLong(1, menuItemForm.getCode());
			query.setLong(2, language.getCode());
			
			result = query.list();
			
			if (result != null)
				menuItemList = new ArrayList<MenuItem>();
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get all Menues");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
		return menuItemList;
	}
	
	public void findChildren(List<MenuItem> list, MenuItem menuItem) {
		//adds the menu to the end of the list
        list.add(menuItem);
        
        try {
        	// initializes the lazy set
            Hibernate.initialize(menuItem.getMenuItems());
            
            if (menuItem.getMenuItems() != null) {
            	Iterator it = menuItem.getMenuItems().iterator();
            	
            	while (it.hasNext()) {
					MenuItem subMenu = (MenuItem) it.next();
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
	
	
	private void findChildrenCodes(List<Long> list, MenuItem menuItem) {
		//adds the menu to the end of the list
        list.add(menuItem.getCode());
        
        try {
        	// initializes the lazy set
            Hibernate.initialize(menuItem.getMenuItems());
            
            if (menuItem.getMenuItems() != null) {
            	Iterator it = menuItem.getMenuItems().iterator();
            	
            	while (it.hasNext()) {
					MenuItem subMenu = (MenuItem) it.next();
					if (subMenu.getGroup().booleanValue()) {
						findChildrenCodes(list, subMenu);
					} else {
						list.add(subMenu.getCode());
					}
				}
            }
        } catch (Exception e) {
        	logger.info(e.getMessage(), e);
		}
	}
	
	
	public MenuItem getByCode(MenuItemForm menuItemForm, HttpServletRequest request) 
	throws Exception{
		
		Session sess = null;
		MenuItem menuItem = null;
		try {
			Long code = null;
			
			if( menuItemForm.getCode() != null && menuItemForm.getCode().longValue() > 0L ) //The item come from a tab.				
				code = menuItemForm.getCode();
			else 	
				code = menuItemForm.getCodes()[0];
				
			if ( code==null )
				throw new Exception("Null is not a valid value to code in MenuItem Object");
			
			if (code.intValue() < 1)
				throw new Exception(code + " is not a valid value to code in MenuItem Object");
			
			sess = getHibernateSession();
			
			menuItem = (MenuItem) sess.get(MenuItem.class, code);
			
			if ( menuItem == null )
				throw new Exception("Does not exist MenuItem Data for the code : " + code);
			
			PropertyUtils.copyProperties(menuItemForm, menuItem);
			
			if ( menuItem.getParent()!= null ){
				menuItemForm.setParentCode(menuItem.getParent().getCode());
				menuItemForm.setParentDescription(menuItem.getParent().getName());
			}
			
			if (menuItem.getDisplayMode()!= null){
				menuItemForm.setDisplayModeCode(menuItem.getDisplayMode().getCode());
			}
			
			if (menuItem.getContent()!= null){
				menuItemForm.setContentCode(menuItem.getContent().getCode());
				menuItemForm.setContentDescription(menuItem.getContent().getTitle());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get all Contents");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
		
		return menuItem;
	}
	
	public void save(MenuItemForm menuItemForm, HttpServletRequest request) 
	throws Exception{
		MenuItem menuItem = null;
		MenuItem parent = null;
		MenuItem oldParent = null;
		DisplayMode displayMode=null;
		Content content = null;
		MenuLanguage menuLanguage = null;
		List<Long> childrenCodeList = null;
		Session sess = null;
		Transaction tx = null;
		try {
			Integer level = 1;
			Integer oldLevel = 1;
			
			Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);
			
			if(language == null )				
				throw new Exception("Language is undefined");
			
			sess = getHibernateSession();
			
			
			if( menuItemForm.getCode() != null && menuItemForm.getCode().longValue() > 0L ) {				
				menuItem = (MenuItem) sess.get(MenuItem.class, menuItemForm.getCode());
				childrenCodeList = new ArrayList<Long>();
				findChildrenCodes(childrenCodeList, menuItem);
				if (childrenCodeList.size()>0)
					childrenCodeList.remove(0);
				if (menuItem.getParent()!= null && !menuItem.getParent().getCode().equals(menuItemForm.getParentCode()))
					oldParent = menuItem.getParent();
			} else {
				menuItem = new MenuItem();
				menuItemForm.setCode(null);
			}
			
			PropertyUtils.copyProperties(menuItem, menuItemForm);
			
			if (menuItem.getLevel()!= null && menuItem.getLevel().intValue()>0) 
				oldLevel = menuItem.getLevel();
			
			if (menuItemForm.getParentCode()!= null && menuItemForm.getParentCode().longValue()>0){
				
				if (childrenCodeList!=null &&  childrenCodeList.contains(menuItemForm.getParentCode()))
					throw new Exception ("The MenuItem with code: " + menuItemForm.getParentCode() + " is not valid to Parent by MenuItem:" + menuItem.getCode());
				
				parent = (MenuItem) sess.get(MenuItem.class, menuItemForm.getParentCode());
				level = parent.getLevel()+1;
			}
			
			if (menuItemForm.getDisplayModeCode()!= null && menuItemForm.getDisplayModeCode().longValue()>0){
				displayMode = (DisplayMode) sess.get(DisplayMode.class, menuItemForm.getDisplayModeCode());
			}
			
			if (menuItemForm.getContentCode()!= null && menuItemForm.getContentCode().longValue()>0){
				content = (Content) sess.get(Content.class, menuItemForm.getContentCode());
			}
			
			menuLanguage = ((Menu) sess.load(Menu.class, menuItemForm.getItemCode()))
																		.getMenuLanguage(language);
			menuItem.setParent(parent);
			menuItem.setDisplayMode(displayMode);
			menuItem.setContent(content);
			menuItem.setMenuLanguage(menuLanguage);
			menuItem.setLevel(level);
			
			tx = sess.beginTransaction();
				sess.saveOrUpdate(menuItem);
				
				if (parent!=null && !parent.getGroup().booleanValue()){
					parent.setGroup(Globals.TRUE);
					sess.saveOrUpdate(parent);
				}
				
				if (oldLevel != level && childrenCodeList!=null 
						&& childrenCodeList.size()>0) {
					StringBuffer sql = new StringBuffer();
					sql.append("Update MenuItem set level = level + :newLevel  ");
					sql.append("Where code in ( :codes )  ");
					
					int updateEntity = sess.createQuery(sql.toString())
															.setInteger("newLevel", level - oldLevel)
															.setParameterList("codes", childrenCodeList, Hibernate.LONG)
															.executeUpdate();
					
				}
				
				if (oldParent != null)
					if (totalChildren(oldParent.getCode(), sess) == 0){
						oldParent.setGroup(Globals.FALSE);
			    		sess.saveOrUpdate(oldParent);
					}
				
				
			tx.commit();
            
            // Audit Transaction
            if (menuItemForm.getCode() == null ) {
                SysAuditHelper.audit(action, request, menuItem, menuItem.getName(),Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(action, request, menuItem, menuItem.getName(), Globals.AUDIT_UPDATE);
            }
			
			if (menuItemForm.getCode() == null) {
				sess.refresh(menuItem);
				menuItemForm.setCode(menuItem.getCode());
			}
			
		} catch (Exception e) {
			if (tx!=null)
				tx.rollback();
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get save MenuItem");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
	}
	
	
	public void delete(Long[] codes)
	throws Exception{
		
		Transaction tx = null;
		Session sess = null;
		MenuItem menuItem = null;
		List<MenuItem> parentList = new ArrayList<MenuItem>();
		try {
			if ( codes != null && codes.length > 0) {
				sess = getHibernateSession();
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	menuItem = (MenuItem) sess.load(MenuItem.class, codes[i]);
			    	if (menuItem.getParent() != null && !parentList.contains(menuItem.getParent()))
			    		parentList.add(menuItem.getParent());
			    	sess.delete(menuItem);
                    SysAuditHelper.audit(action, request, menuItem, menuItem.getName(), Globals.AUDIT_DELETE, sess);
			    }
			    
			    MenuItem parent = null;
			    Long totalChildren = null;
			    for (int i=0; i<parentList.size(); i++){
			    	parent = parentList.get(i);
			    	totalChildren = totalChildren(parent.getCode(), sess);
			    	if (totalChildren.longValue()==0){
			    		parent.setGroup(Globals.FALSE);
			    		sess.saveOrUpdate(parent);
			    	}
			    }
				tx.commit();
		    }
		} catch (Exception e) {
			if (tx != null) {
 			    tx.rollback();
 			}
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to delete Contents");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}

	}
	
	public Long totalChildren(Long parentCode, Session sess) 
	throws Exception{
		
		Long totalChildren = null;
		try {
			if(parentCode == null)
				throw new Exception("Null is not a valid value to parentCode in MenuItem Object");
			
			if(parentCode < 1)
				throw new Exception(parentCode + " is not a valid value to parentCode in MenuItem Object");
			
			StringBuffer sql = new StringBuffer();
			sql.append("Select count(*) ");
			sql.append("From MenuItem ");
			sql.append("Where parent = ? ");
			
			totalChildren = (Long)sess.createQuery(sql.toString())
													.setLong(0, parentCode)
													.iterate().next();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get a number of children to MenuItem by code: " + parentCode);
		} 
		return totalChildren;
	}
	
}
