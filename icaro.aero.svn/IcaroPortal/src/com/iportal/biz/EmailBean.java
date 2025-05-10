/*
 * Created Jul 31, 2006
 *	MapSiteBean.java
 */
package com.iportal.biz;

import java.util.ArrayList;


/**
 * @author hernan
 *
 */
public class EmailBean {
	
	private String groupName;
	
	private ArrayList<EmailItem> mailList;
	
	private Integer count;	
	
	
	public EmailBean(String groupName, Integer count) {
		super();
		this.groupName = groupName;
		this.count = count;
	}
	
	public EmailBean(String groupName, ArrayList<EmailItem> mailList, Integer count) {
		super();
		this.groupName = groupName;
		this.mailList = mailList;
		this.count = count;
	}

	/**
	 * 
	 */
	public EmailBean() {
		super();
		this.clear();
	}
	
	private void clear () {
		this.groupName  = null;
		this.mailList = new ArrayList<EmailItem>();
		this.count = null;
	}

	public Integer getCount() {
		return count;
	}

	public String getGroupName() {
		return groupName;
	}

	public ArrayList<EmailItem> getMailList() {
		return mailList;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setMailList(ArrayList<EmailItem> mailList) {
		this.mailList = mailList;
	}
	
	public void addEmailItem(EmailItem emailItem){
		this.mailList.add(emailItem);
		this.count++;
	}
	
}
