/*
 * Created on Jan 10, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.biz.portal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.iportal.biz.portal.bean.AccessItemBean;
import com.iportal.biz.portal.bean.AccessItemsList;

/**
 * @author FernandoT
 *
 */
public class MainMenuManager {
	private static Logger logger = Logger.getLogger(MainMenuManager.class);
	
	public static void writeToFile(String fileName, String content, String path)  {
		String fileDir = path + "flash" + File.separator;
		fileName = fileDir + fileName;
		logger.info("fileName " + fileName);
		try {
			//FileWriter file = new FileWriter(fileName);
			OutputStream outputSream = new FileOutputStream(fileName);
			OutputStreamWriter writer = new OutputStreamWriter(outputSream, "UTF-8");
	        BufferedWriter out = new BufferedWriter(writer);
	        logger.info(writer.getEncoding());
	        out.write(content);
	        out.close();
	    } catch (IOException e) {
	    	logger.error(e.getMessage(), e);
	    }
	}
		

	public static StringBuffer createXmlBuffer(AccessItemsList listAccess, Session sess, HttpServletRequest request){
				    
		StringBuffer  result = new StringBuffer();
		if (listAccess != null){
			
			try {
				//HttpSession session = request.getSession();
				
				result.append("<?xml version='1.0' encoding='utf-8'?>");
				result.append("\n");
				result.append("<menu>");
				result.append("\n");						

				for (int i = 0; i < listAccess.getResults().size(); i++){					
					AccessItemBean item = (AccessItemBean)listAccess.getResults().get(i);				
					result.append("<item id='");
					result.append(i);
					result.append("' text='");
					result.append(item.getName());
					result.append("' url='");
					if(!item.getExternal()){
					result.append(request.getContextPath());
					}					
					result.append(item.getUrl());
					if(item.getRelatedCode()!=null){
						result.append(item.getRelatedCode());	
					}	
					if(!item.getExternal() || i==0){
						result.append("' target='_self'");
						}
					else{
						result.append("' target='_blank'");
						
					}
					result.append("/>");
					result.append("\n");					
					
					
					
				}
				
				result.append("</menu>");
			
				
			} catch (Exception e) {
	             logger.error(e.getMessage(), e);
	 		}
		}
		return result;
	}

	
	public static void main(String args[]){
		writeToFile("c:\\myfile.txt", "Mein Inhalt", "c:\\proyectos\\");
	}
	
	

}
