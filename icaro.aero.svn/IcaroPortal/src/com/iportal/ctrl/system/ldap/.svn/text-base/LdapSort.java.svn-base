package com.iportal.ctrl.system.ldap;

import java.util.List;

import com.iportal.Constants;

public class LdapSort {
	int subPivote = 0; 

    private String getValue(String orderField, LdapUserBean object){
        String val = object.getUsername();
        if (orderField.equals(Constants.NAME)){
            val = object.getUsername();
        }else if (orderField.equals(Constants.STATUS)){
            val = object.getStatusLabel().toString();
        }else {
            val = object.getRegisteredLabel().toString();
        }
        return val;
    }
    
	public int particion( List<LdapUserBean> a,int bajo, 
			int alto, int subPivote, String orderField, Boolean order){
	
		String pivote = "0";
		int i, j;
		LdapUserBean temp;
		i = bajo;
		j = alto + 1;
        LdapUserBean userBean = (LdapUserBean)a.get(bajo);
        LdapUserBean u1, u2;
        pivote = getValue(orderField, userBean);
		//pivote = (String)a.get(bajo);
		String si = null;
		String sj = null;

		do {
			if (order.booleanValue()){
				do {
					i = i + 1;
                    u1 = (LdapUserBean)a.get(i);
					//si = u1.getUsername();
                    si = getValue(orderField, u1);
				} while (i != alto && si.compareTo(pivote) < 0);
				
				do {
					j = j -1;
                    u2 = (LdapUserBean)a.get(j);
					//sj = u2.getUsername();
                    sj = getValue(orderField, u2);
				} while (sj.compareTo(pivote)> 0);
			} else {
				do {
					i = i + 1;
                    u1 = (LdapUserBean)a.get(i);
					//si = u1.getUsername();
                    si = getValue(orderField, u1);
				} while (i != alto && si.compareTo(pivote) > 0);
				
				do {
					j = j -1;
                    u2 = (LdapUserBean)a.get(j);
					//sj = u2.getUsername();
                    sj = getValue(orderField, u2);
				} while (sj.compareTo(pivote) < 0);
			}
			
			if (i < j){
				temp = a.get(i);
				a.set(i, a.get(j));
				a.set(j, temp);
			}
		} while (i < j);

		temp = a.get(bajo);
		a.set(bajo, a.get(j));
		a.set(j, temp);
		
		subPivote = j;
		return subPivote;
	}

	public void sort(List<LdapUserBean> a, int bajo, int alto, String orderField, Boolean orderAsc){
		if (bajo < alto){
			subPivote = particion ( a, bajo, alto, subPivote, orderField, orderAsc);
			sort(a, bajo, subPivote -1, orderField, orderAsc);
			sort(a, subPivote + 1, alto, orderField, orderAsc );
		}
	}

}
