
package com.iportal.ctrl.system.report;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;

import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;


public class ContactAction extends BaseDispatchAction {
    
	private static Log logger = LogFactory.getLog(ContactAction.class);
    
    
    public ActionForward list (
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	  ContactForm listForm = (ContactForm) form;
          
          Session sess = null;
          List results = null;
          //HttpSession session = null;
          //boolean isYageUser = false;
          ArrayList<Object> params = new ArrayList<Object>();
          ArrayList<NullableType> types = new ArrayList<NullableType>();
          sess = getHibernateSession();
          
          if (listForm.getListItems().booleanValue()) {
	          try {
	      		StringBuffer sql = new StringBuffer();
	      		//session = request.getSession();
	      		//SysUser sysUser = (SysUser) session.getAttribute(Constants.SYSTEM_USER_KEY);
	      			
	      		sql.append("from Contact as contact ");
	             sql.append("where 1 = 1 ");
	
	
	             
	             if (listForm.getName() != null && listForm.getName().length() > 0) {
	                String name = "%" + listForm.getName() + "%";
	                sql.append(" and ( contact.name  like ?   ");
	                sql.append(" or contact.lastName like ? ) ");
	                 
	                params.add( name );
	                params.add( name );
	                types.add(Hibernate.STRING);
	                types.add(Hibernate.STRING);
	             
	             }
	             if (listForm.getCountryCode() != null && listForm.getCountryCode().longValue() > 0L){
	            	 sql.append(" and  contact.country.code =  ?   ");            	  
	                 params.add( listForm.getCountryCode() );
	                 types.add(Hibernate.LONG);
	             }
	             if (listForm.getCityName() != null && listForm.getCityName().length() > 0) {
	            	 String name = "%" + listForm.getCityName() + "%";
	            	 
	            	 sql.append(" and  contact.cityName like  ?   ");
	            	 params.add( name );
	                 types.add(Hibernate.STRING);
			   		   
	                //sql.append("and contact.contacCity.name like ?  ");
	
	                // String cityName = "%" + city.getName() + "%";
	            	/*String cityName = "%" + country.getName().toUpperCase().replaceAll("\\s{1,}","")+ "%";
	                cityName = cityName.replaceAll("[.,;\\-]", "");
	                cityName = cityName.replace('�','A').replace('�','E').replace('�','I').replace('�','O').replace('�','U');
	                sql.append("and replace(replace(replace(replace(replace(replace(replace(replace(upper(contact.cityName),' ',''),'.',''),',',''),'�','A'),'�','E'),'�','I'),'�','O'),'�','U') like ?  ");
	                 params.add( cityName );
	                 types.add(Hibernate.STRING);*/
	
	            }
	             
	            if (listForm.getFrom () != null && listForm.getFrom ().toString().length() > 0) {
	            	sql.append(" and contact.date >= ? ");
	                params.add(listForm.getFrom());
	                types.add(Hibernate.CALENDAR);
	            }
	             
	            if (listForm.getTo () != null && listForm.getTo ().toString().length() > 0) {
	            	sql.append(" and contact.date <= ? ");
	                params.add(listForm.getTo());
	                types.add(Hibernate.CALENDAR);
	            }
	      			
	             if (listForm.getCompany() != null && listForm.getCompany().length() > 0) {
	                 String name = "%" + listForm.getCompany() + "%";
	                 sql.append(" and  contact.company  like ?   ");
	                  
	                 params.add( name );
	                 types.add(Hibernate.STRING);
	              }
	
	             if (listForm.getPosition() != null && listForm.getPosition().length() > 0) {
	                 String name = "%" + listForm.getPosition() + "%";
	                 sql.append(" and  contact.position  like ?   ");
	                  
	                 params.add( name );
	                 types.add(Hibernate.STRING);
	              }
	
	             if ( listForm.getOrderField() != null ) {
	          		sql.append("order by contact.");
	          		sql.append(listForm.getOrderField());
	          		if ( listForm.getOrderAsc().booleanValue() ) {
	          		    sql.append(" asc ");
	          		} else {
	          		    sql.append(" desc ");
	          		}
	      		}
	      		
	      	
	
	  			Query query = sess.createQuery(sql.toString());
	  			/*
	  			if (isYageUser) {
	  				query.setLong(0, sysUser.getAccessMode().getCode().longValue());
	  			}
	             */
	             
	             for (int i = 0; i < types.size(); i++) {
	                 query.setParameter(i, params.get(i), types.get(i));
	             }
	  			results = query.list();
	            
	      		
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

	  				//Save the List of results in request scope
	  		request.setAttribute("contactList", results);
         }
          
  		
  		

  		return mapping.findForward(Globals.FORWARD_LIST);

    }
}