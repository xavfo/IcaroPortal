/*
 * Created Jul 31, 2006
 *	MapSiteBean.java
 */
package com.iportal.biz;

import java.util.ArrayList;
import java.util.List;

import com.iportal.model.bulletin.Bulletin;


/**
 * @author hernan
 *
 */
public class BulletinBean {
	
	private Long topicCode;
	
	private Bulletin bulletin;
	
	private List<EmailBean> emailBeans;	
	
	
	/**
	 * @param code
	 * @param name
	 * @param level
	 */
	public BulletinBean(Long topicCode, Long bulletinCode) {
		super();
		this.topicCode = topicCode;		
	}

	/**
	 * 
	 */
	public BulletinBean() {
		super();
		this.clear();
	}
	
	private void clear () {
		this.topicCode  = null;
		this.bulletin =null;
		this.emailBeans = new ArrayList<EmailBean>();
	}

	public Bulletin getBulletin() {
		return bulletin;
	}

	public List<EmailBean> getEmailBeans() {
		return emailBeans;
	}

	public Long getTopicCode() {
		return topicCode;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}

	public void setEmailBeans(List<EmailBean> emailBeans) {
		this.emailBeans = emailBeans;
	}

	public void setTopicCode(Long topicCode) {
		this.topicCode = topicCode;
	}


}
