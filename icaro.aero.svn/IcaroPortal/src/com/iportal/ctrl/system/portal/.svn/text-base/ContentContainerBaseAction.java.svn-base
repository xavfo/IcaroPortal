/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

import org.hibernate.Session;

import com.iportal.biz.RowItem;
import com.iportal.biz.system.portal.ContentFacade;
import com.yage.struts.action.BaseDispatchAction;

/**
 * java comment MenuAction
 * 
 * @author YAGE (Jorge Lomas)
 * @version 1.0
 */
public class ContentContainerBaseAction extends BaseDispatchAction {

	protected RowItem getRowItemContent(Long contentCode, Session sess) throws Exception {
		ContentFacade contentFacade = new ContentFacade();
		RowItem content = contentFacade.getRowItem(contentCode, sess);
		return content;
	}
}