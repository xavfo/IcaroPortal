package com.iportal.ctrl.system.jobs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;


public class PositionManager{
	
	public static List getAllPositions(PositionForm positionForm, Session sess){			
		
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<NullableType> types = new ArrayList<NullableType>();

		sql.append("from Position as position ");
		sql.append("where 1 = 1 ");
		    		
		if (positionForm.getName() != null && positionForm.getName().length() != 0L ) {
   		    String name = "%" + positionForm.getName() + "%";
   			sql.append("and position.name like ? ");
   		    params.add(name);
   		    types.add(Hibernate.STRING);  
		}
		
		
		if ( positionForm.getOrderField() != null ) {
    		sql.append("order by position.");
    		sql.append(positionForm.getOrderField());
    		if ( positionForm.getOrderAsc().booleanValue() ) {
    		    sql.append(" asc ");
    		} else {
    		    sql.append(" desc ");
    		}
		}
		
		if ( positionForm.getOrderField() == null ) {
    		sql.append("order by position.name asc");
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
