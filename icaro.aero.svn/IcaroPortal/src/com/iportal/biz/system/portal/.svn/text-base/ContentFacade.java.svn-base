package com.iportal.biz.system.portal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;

import com.iportal.Constants;
import com.iportal.biz.BaseBussinessLogic;
import com.iportal.biz.RowItem;
import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.model.Image;
import com.iportal.model.Language;
import com.iportal.model.portal.AccessLevel;
import com.iportal.model.portal.Content;
import com.iportal.model.portal.Layout;
import com.yage.Globals;

public class ContentFacade extends BaseBussinessLogic{

	private static Log logger = LogFactory.getLog(ContentFacade.class);
	
    protected HttpServletRequest request;
    protected Action action;
    
	public ContentFacade() {
		super();
	}
    

	public ContentFacade(Session openedSession) {
        super(openedSession);
    }

    public ContentFacade(HttpServletRequest request, Action action) {
        this.request = request;
        this.action = action;
    }

    public List<Content> search(SearchContentForm searchContentForm, HttpServletRequest request)
	throws Exception{
		Session sess = null;
		List<Content> result = null;
		try {
			
			HttpSession session = request.getSession();
			Language language = (Language)session.getAttribute(Constants.LANGUAGE_KEY);
			
			StringBuffer sql = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			List<NullableType> types = new ArrayList<NullableType>();
			
			sql.append("FROM Content as content ");
			sql.append("WHERE content.language.code = ? ");
			types.add(Hibernate.LONG);
			params.add(language.getCode());
			
			if (searchContentForm.getTitle()!=null && searchContentForm.getTitle().length()>0 ) {
				sql.append(" AND  UPPER(content.title) like ? ");
				types.add(Hibernate.STRING);
				params.add("%".concat(searchContentForm.getTitle().toUpperCase()).concat("%"));
			}
			
			if (searchContentForm.getLevel() != null &&  searchContentForm.getLevel().intValue() > 0) {
				if (searchContentForm.getEqual().booleanValue())
					sql.append(" AND  content.level = ? ");
				else
					sql.append(" AND  content.level <> ? ");
				types.add(Hibernate.INTEGER);
				params.add(searchContentForm.getLevel());
			}
			
			if (searchContentForm.getActualCode()!=null && searchContentForm.getActualCode().longValue()>0 ) {
				sql.append(" AND  content.code <> ? ");
				types.add(Hibernate.LONG);
				params.add(searchContentForm.getActualCode());
			}
			
			if (searchContentForm.getEnabled()!=null) {
				sql.append(" AND  content.enabled = ? ");
				types.add(Hibernate.BOOLEAN);
				params.add(searchContentForm.getEnabled());
			}
			
			sess = getHibernateSession();
			Query query = sess.createQuery(sql.toString());
			
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, params.get(i), (Type) types.get(i));
			}
			
			result = (ArrayList<Content>) query.list();
			
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
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Content> getPage(ContentForm contentForm, HttpServletRequest request)
	throws Exception{
		Session sess = null;
		List<Content> result = null;
		try {
			Integer level = contentForm.getLevel();
			if (!isValidLevel(level)) 
				throw new Exception(level + " is not valid value to level in content Object");
			
			/*Integer page = contentForm.getPageNumber();
			Integer pageSize = Constants.PAGE_SIZE;
			
			if (page == null)
				page = 1;
			
			if (page<1)
				throw new Exception(page + " is not a valid value to pageNumber");*/

			HttpSession session = request.getSession();
			Language language = (Language)session.getAttribute(Constants.LANGUAGE_KEY);
			
			StringBuffer sql = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			List<NullableType> types = new ArrayList<NullableType>();
			
			sql.append("FROM Content as content ");
			sql.append("WHERE 1 = 1 ");
			
			if (level < 3) {
				sql.append(" and content.level = ? ");
				types.add(Hibernate.INTEGER);
				params.add(level);
			}			
			else
				sql.append(" and content.level > 2 ");
			
						
			sql.append(" AND  content.language.code = ? ");
			types.add(Hibernate.LONG);
			params.add(language.getCode());
			
			if (contentForm.getTitleSearch()!=null && contentForm.getTitleSearch().length()>0 ) {
				sql.append(" AND UPPER(content.title) like ? ");
				types.add(Hibernate.STRING);
				params.add("%".concat(contentForm.getTitleSearch().toUpperCase()).concat("%"));
			}
			
			if (contentForm.getSectionCode()!=null && contentForm.getSectionCode().longValue()>0 && level.compareTo(1)>0) {
				if (level == 2){
					sql.append(" AND  content.parent.code = ? ");
				} else if (level == 3) {
					sql.append(" AND  content.parent.parent.code = ? ");
				}
				types.add(Hibernate.LONG);
				params.add(contentForm.getSectionCode());
			}
			
			if (contentForm.getCategoryCode()!=null && contentForm.getCategoryCode().longValue()>0 && level.compareTo(2)>0) {
					sql.append(" AND  content.parent.code = ? ");
				types.add(Hibernate.LONG);
				params.add(contentForm.getCategoryCode());
			}
			
			if (contentForm.getEnabledSearch()!= null) {
				sql.append(" AND  content.enabled = ? ");
				types.add(Hibernate.BOOLEAN);
				params.add(contentForm.getEnabledSearch());
			}
			
			sql.append("ORDER BY content.");
			if (contentForm.getOrderField()!=null && contentForm.getOrderField().length()>0) 
				sql.append(contentForm.getOrderField());
			else
				sql.append("parent");
			sql.append(", content.order ");
			if (contentForm.getOrderAsc().booleanValue())
				sql.append(" asc ");
			else
				sql.append(" desc ");
			
			sess = getHibernateSession();
			Query query = sess.createQuery(sql.toString());
			
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, params.get(i), (Type) types.get(i));
			}
			
			/*page--;
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);*/
			
			result = query.list();
			
			/*query =  sess.createQuery("SELECT count(*) " + sql.toString());
			for (int i = 0; i < types.size(); i++) {
				query.setParameter(i, params.get(i), (Type) types.get(i));
			}
			
			Long totalRows = (Long) query.iterate().next();
			
			contentForm.setTotalPages( PageHelper.getTotalPages(totalRows, pageSize) );*/
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get page of Contents");
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
	
	public Content getByCode(ContentForm contentForm)
	throws Exception{
		Session sess= null;
		Content content = null;
		Long code = null;
		try {
			if(contentForm.getCode() != null && contentForm.getCode().longValue() > 0L ) //The item come from a tab.				
				code = contentForm.getCode();
			else if (contentForm.getCodes() != null) 	
				code = contentForm.getCodes()[0];
			
			sess = getHibernateSession();
			content = getByCode(code, sess);
			
			PropertyUtils.copyProperties(contentForm, content);
			
			if (content.getParent()!= null){
				contentForm.setParentCode(content.getParent().getCode());
				contentForm.setParentDescription(content.getParent().getTitle());
			}
			
			if (content.getImage()!= null){
				contentForm.setImageCode(content.getImage().getCode());
				contentForm.setImageName(content.getImage().getName());
			}
			
			if (content.getAccessImage()!= null){
				contentForm.setAccessImageCode(content.getAccessImage().getCode());
				contentForm.setAccessImageName(content.getAccessImage().getName());
			}
			
			
			if (content.getModule() != null)
				contentForm.setModuleCode(content.getModule().getCode());
			
			if (content.getLayout() != null)
				contentForm.setLayoutCode(content.getLayout().getCode());

			if (content == null) 
				throw new Exception("Does not exist data in content to code " + code);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get the Content data for code: " + code);
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
		
		return content;
	}
	
	public Content getByCode(Long code) 
	throws Exception{
		return getByCode(code, null);
	}
	
	public Content getByCode(Long code, Session sessHibernate) 
	throws Exception{
		Session sess = null;
		Content content = null;
		try {
			if (code==null)
				throw new Exception("Null is not a valid value to code in content Object");
			
			if (code.intValue() < 1) 
				throw new Exception(code + " is not a valid value to code in content Object");
			
			if (sessHibernate!=null)
				sess = sessHibernate;
			else
				sess = getHibernateSession();
			content = (Content) sess.get(Content.class, code);
						
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get the Content data for code: " + code);
		} finally {
			if (sess != null && sessHibernate == null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}

		return content;
	}
	
	public RowItem getRowItem(Long code)
	throws Exception{
		return getRowItem(code, null);
	}
	
	public RowItem getRowItem(Long code, Session openSession) 
	throws Exception{
		RowItem content = null;
		Session sess = null;
		try {
			if (code==null)
				throw new Exception("Null is not a valid value to code in content Object");
			
			if (openSession == null)
				sess = getHibernateSession();
			else
				sess = openSession;
			
			StringBuffer str = new StringBuffer();
			str.append("Select new com.iportal.biz.RowItem (code, title, level) ");
			str.append("From Content ");
			str.append("Where code = ? ");
			
			List<RowItem> result = (ArrayList<RowItem>)sess.createQuery(str.toString()).setLong(0, code).list();
			
			if (result != null)
				content = result.get(0);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to get the Content data for code: " + code);
		} finally {
			if (sess != null && openSession==null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
		return content;
	}
	
	public void save(ContentForm contentForm) 
	throws Exception{
		Content content = null;
		Image image = null;
		Image accessImage = null;
		Content parent = null;
		Language language = null;
		AccessLevel accessLevel = null;
		Layout layout = null;
		
		Transaction tx = null;
		Session sess = null;
		try {
			sess = getHibernateSession();
			
			if ( !isValidLevel( contentForm.getLevel() ) ) 
				throw new Exception(contentForm.getLevel() + " is not valid value to level in content Object");
			
			if (contentForm.getCode()!=null && contentForm.getCode().longValue() > 0 ){
				content = (Content) sess.get(Content.class, contentForm.getCode());
			} else {
				content = new Content();
				contentForm.setCode(null);
			}
			PropertyUtils.copyProperties(content, contentForm);
			
			if ( contentForm.getParentCode()!=null && contentForm.getParentCode().longValue()>0 ) {
				parent = (Content)sess.load(Content.class, contentForm.getParentCode());
				if (parent.getLevel().intValue() < 3 &&(parent.getGroup() == null || !parent.getGroup().booleanValue())) {
					parent.setGroup(Globals.TRUE);	
				}
				if (parent.getLevel() > 2) {
					content.setLevel(parent.getLevel()+1);
				}				
			}
			
			if ( contentForm.getImageCode()!=null && contentForm.getImageCode().longValue()>0 ) {
				image = (Image)sess.load(Image.class, contentForm.getImageCode());
			}
			if ( contentForm.getAccessImageCode()!=null && contentForm.getAccessImageCode().longValue()>0 ) {
				accessImage = (Image)sess.load(Image.class, contentForm.getAccessImageCode());
			}
			if ( contentForm.getLanguageCode()!=null && contentForm.getLanguageCode().longValue()>0 ) {
				language = (Language)sess.load(Language.class, contentForm.getLanguageCode());
			}
			if ( contentForm.getAccessLevelCode()!=null && contentForm.getAccessLevelCode()>0 ) {
				accessLevel = (AccessLevel) sess.load(AccessLevel.class, contentForm.getAccessLevelCode());
			}
			if ( contentForm.getLayoutCode()!=null && contentForm.getLayoutCode()>0 ) {
				layout = (Layout) sess.load(Layout.class, contentForm.getLayoutCode());
			}
			
			content.setParent(parent);
			content.setImage(image);
			content.setAccessImage(accessImage);
			content.setLanguage(language);
			content.setAccessLevel(accessLevel);
			content.setLayout(layout);
			
			tx = sess.beginTransaction();
			sess.saveOrUpdate(content);
            tx.commit();
            
            // Audit Transaction
            if ( contentForm.getCode() == null ) {
                SysAuditHelper.audit(action, request, content, content.getTitle(), Globals.AUDIT_INSERT);
            } else {
                SysAuditHelper.audit(action, request, content, content.getTitle(), Globals.AUDIT_UPDATE);
            }
            
            
			if (contentForm.getCode()==null) {
				sess.refresh(content);
				PropertyUtils.copyProperties(contentForm, content);
			}
			
		} catch (Exception e) {
			if (tx != null) {
 			    tx.rollback();
 			}
			logger.error(e.getMessage(), e);
			throw new Exception("Unexpected error while trying to save Content");
		} finally {
			if (sess != null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
	}
	
	
	public void delete(Long[] codes) throws Exception{
		
		Transaction tx = null;
		Session sess = null;
		Content content = null;
		HashSet<Content> parentList = new HashSet<Content>(); 
		try {
			if ( codes != null && codes.length > 0) {
				sess = getHibernateSession();
				tx = sess.beginTransaction();
			    for (int i = 0; i < codes.length; i++) {
			    	content = (Content) sess.load(Content.class, codes[i]);
					sess.delete(content);
					if (content.getParent() != null) {
						parentList.add(content.getParent());
					}
                    // Audit Transaction
					SysAuditHelper.audit(action, request, content, content.getTitle(), Globals.AUDIT_DELETE, sess);
			    }
			    
			    for (Content parent : parentList) {
			    	if (parent.getContents().size() == 0) {
			    		parent.setGroup(Globals.FALSE);
			    		sess.update(parent);
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
	
	private boolean isValidLevel(Integer level) {
		if (level != null && level>0 && level < 4)
			return true;
		return false;
	}
	
}
