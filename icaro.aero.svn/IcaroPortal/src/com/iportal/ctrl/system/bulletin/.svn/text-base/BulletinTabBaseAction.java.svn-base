/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.bulletin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.iportal.biz.RowItem;
import com.yage.struts.action.BaseDispatchAction;

/**
 * java comment MenuAction
 * 
 * @author YAGE (Jorge Lomas)
 * @version 1.0
 */
public class BulletinTabBaseAction extends BaseDispatchAction {

	
	protected RowItem getRowItemTopic(Long topicCode) throws Exception {
		return getRowItemTopic(topicCode, null);
	}
	
	protected RowItem getRowItemTopic(Long topicCode, Session currentSess) throws Exception {
		Session sess = null;
		RowItem topic = null;
		try {
			if (currentSess != null && currentSess.isConnected())
				sess = currentSess;
			else
				sess = getHibernateSession();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("Select new com.iportal.biz.RowItem (code, name) ");
			sql.append("From Topic ");
			sql.append("Where code = ? ");
			
			List<RowItem> result = (ArrayList<RowItem>)sess.createQuery(sql.toString()).setLong(0, topicCode).list();
			
			if (result != null)
				topic = result.get(0);
			
		} catch (Exception e) {
			throw new Exception("Not exist topic data for code: " + topicCode);
		} finally {
			if (sess != null && currentSess==null)
 				try {
 				    sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
		}
		
		return topic;
	}
}