package com.iportal.ctrl.system.jobs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;


public class AreaManager{
	
	public static List getAllAreas(AreaForm areaForm, Session sess){			
		
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<NullableType> types = new ArrayList<NullableType>();			

		sql.append("from com.iportal.model.jobs.Area as area ");
		sql.append("where 1 = 1 ");
		    		
		if (areaForm.getName() != null && areaForm.getName().length() != 0L ) {
   		    String name = "%" + areaForm.getName() + "%";
   			sql.append("and area.name like ? ");
   		    params.add(name);
   		    types.add(Hibernate.STRING);  
		}
		
		
		if ( areaForm.getOrderField() != null ) {
    		sql.append("order by area.");
    		sql.append(areaForm.getOrderField());
    		if ( areaForm.getOrderAsc().booleanValue() ) {
    		    sql.append(" asc ");
    		} else {
    		    sql.append(" desc ");
    		}
		}
		
		if ( areaForm.getOrderField() == null ) {
    		sql.append("order by area.name asc");
		}
				
		Object[] arrayParams = params.toArray();
		Query query = sess.createQuery(sql.toString());
		for (int i = 0; i < types.size(); i++) {
			query.setParameter(i, arrayParams[i], (Type) types.get(i));
		}
					
		List results = query.list();		
		
		return results;
		
	}
}
