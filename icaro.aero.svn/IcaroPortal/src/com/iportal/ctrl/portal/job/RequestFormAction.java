package com.iportal.ctrl.portal.job;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.model.Catalogue;
import com.iportal.model.City;
import com.iportal.model.jobs.Position;
import com.iportal.model.jobs.PositionRequest;
import com.iportal.model.jobs.Requester;
import com.iportal.model.jobs.RequesterWorkCity;
import com.iportal.model.portal.Content;
import com.yage.Globals;
import com.yage.struts.action.BaseDispatchAction;


public class RequestFormAction extends BaseDispatchAction {

    private static Log logger = LogFactory.getLog(RequestFormAction.class);
    

  public ActionForward form(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	 ActionMessages messages = new ActionMessages();
    	 Session sess = null;
    	 HttpSession session = request.getSession();
	     MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
	     try{
	    	 sess = getHibernateSession();
	    	 Content content = (Content) sess.get(Content.class, portalBean.getContentCode());
	    	 request.setAttribute("content", content);
	    	 RequestForm requestForm=(RequestForm) form;  
	    	 Long[] codesCity = (Long[]) session.getAttribute("codesCity");
	    	 requestForm.setCityCodes(codesCity);
	    	Position position= (Position) sess.get(Position.class, requestForm.getPosition());
	    	 session.setAttribute("positionName", position.getName());	
	    	 
	     }catch(Exception e) {
	  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
				logger.info(e.getMessage(), e);
	  		}finally {
				if (sess != null)
					try {
					    sess.clear();
						sess.close();
					} catch (Exception e) {
					} 			
			} 		
	     
    	
    	return mapping.findForward(portalBean.getLayoutForward()+"_"+ Globals.FORWARD_LIST);
		
    }
  
  
  public ActionForward step2(
          ActionMapping mapping, 
          ActionForm form,
          HttpServletRequest request, 
          HttpServletResponse response)
  throws Exception {
  	 ActionMessages messages = new ActionMessages();
  	 Session sess = null;
  	 HttpSession session = request.getSession();
	MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
	 try{
	    	 sess = getHibernateSession();
	    	 Content content = (Content) sess.get(Content.class, portalBean.getContentCode());
	    	 request.setAttribute("content", content);
	    	 RequestForm requestForm=(RequestForm) form; 
	    	 if(session.getAttribute("codesCity") !=null){
	    	 Long[] codesCity2 = (Long[]) session.getAttribute("codesCity");
		    	
	    	 requestForm.setCityCodes(codesCity2);
	    	 }else{
		    	 Long[] codesCity= requestForm.getCityCodes();
		    	 session.setAttribute("codesCity", codesCity);
		    	 requestForm.setCityCodes(codesCity);
	    	 }
	     }catch(Exception e) {
	  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
				logger.info(e.getMessage(), e);
	  		}finally {
				if (sess != null)
					try {
					    sess.clear();
						sess.close();
					} catch (Exception e) {
					} 			
			} 		
	     
  	
  	return mapping.findForward(Globals.FORWARD_SUCCESS);
		
  }


  public ActionForward step3(
          ActionMapping mapping, 
          ActionForm form,
          HttpServletRequest request, 
          HttpServletResponse response)
  throws Exception {
  	 ActionMessages messages = new ActionMessages();
  	 Session sess = null;
  	 HttpSession session = request.getSession();
	     MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
	     try{
	    	 sess = getHibernateSession();
	    	 Content content = (Content) sess.get(Content.class, portalBean.getContentCode());
	    	 request.setAttribute("content", content);
	    	 RequestForm requestForm=(RequestForm) form;  
	    	 Long[] codesCity = (Long[]) session.getAttribute("codesCity");
	    	 requestForm.setCityCodes(codesCity);
	    	 
	    	 
	    	 
	     }catch(Exception e) {
	  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
				logger.info(e.getMessage(), e);
	  		}finally {
				if (sess != null)
					try {
					    sess.clear();
						sess.close();
					} catch (Exception e) {
					} 			
			} 		
	     
  	
  	return mapping.findForward(Globals.FORWARD_SUCCESS+"_3");
		
  }
  
  public ActionForward save(
          ActionMapping mapping, 
          ActionForm form,
          HttpServletRequest request, 
          HttpServletResponse response)
  throws Exception {
  	 ActionMessages messages = new ActionMessages();
  	 Session sess = null;
  	 HttpSession session = request.getSession();
	 MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
	 Requester requester = new Requester();
	 Transaction tx = null;
	 List<String> citiesAvailables = null;
	 City cities = null;
	  
	     try{
	    	 sess = getHibernateSession();
	    	 Content content = (Content) sess.get(Content.class, portalBean.getContentCode());
	    	 request.setAttribute("content", content);
	    	 RequestForm requestForm=(RequestForm) form;
	    	 Long[] codesCity = (Long[]) session.getAttribute("codesCity");
	    	 requestForm.setCityCodes(codesCity);
	    	 requester.setAddress(requestForm.getAddress());
	    	 requester.setBirthDate(requestForm.getBirth());
	    	 requester.setCell(requestForm.getCell());
	    	 City city = (City) sess.get(City.class, requestForm.getCity());
	    	 requester.setCity(city);
	    	 requester.setDateCreated(new GregorianCalendar());
	    	 requester.setEmail(requestForm.getEmail());
	    	 requester.setEnterpriceCity(requestForm.getEnterpriceCity());
	    	 requester.setEnterpriceName(requestForm.getEnterpriceName());
	    	 requester.setFirstName(requestForm.getFirstName());
	    	 requester.setGender(requestForm.getGender());
	    	 requester.setIdentification(requestForm.getIdentification());
	    	 Catalogue catalogue = (Catalogue) sess.get(Catalogue.class, requestForm.getInstructionLevel());
	    	 requester.setInstructionLevel(catalogue);
	    	 requester.setIsWorking(requestForm.getIsWorking());
	    	 requester.setLastName(requestForm.getLastName());
	    	 Catalogue catalogueMarital = (Catalogue) sess.get(Catalogue.class, requestForm.getMaritalStatus());
	    	 requester.setMaritalStatus(catalogueMarital);
	    	 requester.setOfficePhone(requestForm.getOfficePhone());
		     requester.setPhoneNumber(requestForm.getPhoneNumber());
		     Catalogue catalogueSalary = (Catalogue) sess.get(Catalogue.class, requestForm.getSalaryAspiration());
		     requester.setSalaryAspiration(catalogueSalary);
		     requester.setSecondLastName(requestForm.getSecondLastName());
		     requester.setSecondName(requestForm.getSecondName());
		     Catalogue catalogueStudy = (Catalogue) sess.get(Catalogue.class, requestForm.getStudyBranch());
		     requester.setStudyBranch(catalogueStudy);
		     requester.setTravelPossibity(requestForm.getTravelPossibity());
		     requester.setVehicle(requestForm.getVehicle());
		     requester.setAddInfo(requestForm.getAddInfo());
		     requester.setBriefApplication(Globals.FALSE);
		     tx = sess.beginTransaction();
				sess.saveOrUpdate(requester);			
				tx.commit();
		     Long[] cityCodes =  requestForm.getCityCodes();
		     tx = sess.beginTransaction();
	 			if (cityCodes  != null && cityCodes.length > 0){
	 				citiesAvailables = new ArrayList<String>();
		 			for (int i = 0; i < cityCodes .length; i++){
		 				Long code = cityCodes [i];
		 				RequesterWorkCity requesterWorkCity = new RequesterWorkCity();
		 				cities = (City)sess.get(City.class, code);
		 				citiesAvailables.add(cities.getName());
		 				requesterWorkCity.setCity(cities);
		 				requesterWorkCity.setRequester(requester);
		 				sess.saveOrUpdate(requesterWorkCity);
		 				
		 			}
		 		
		 			tx.commit();
	 			}
	 			request.setAttribute("citiesAvailables",citiesAvailables);
	 			Position position= (Position) sess.get(Position.class, requestForm.getPosition());
	 			PositionRequest requested =new PositionRequest();
	 			requested.setCode(null);
	 			requested.setPosition(position);
	 			requested.setRequester(requester);
	 			requested.setExperience(requestForm.getExperience());
	 			tx = sess.beginTransaction();
	 			sess.save(requested);
	 			tx.commit();
		     //requester.setRequesterWorkCity();
	 			 request.setAttribute("requester",requester);
	 			 
	 			 request.setAttribute("jobName", position.getName());
	 			 request.setAttribute("jobDepartment",position.getArea().getName());
	 			 if(requester.getTravelPossibity().booleanValue()==true){
		 				request.setAttribute("travel","si");
		 			 }else{
		 				request.setAttribute("travel","no");
			 			 
		 			 }	 		
	 			 if(requester.getGender().booleanValue()==true){
	 				request.setAttribute("gender","masculino");
	 			 }else{
	 				request.setAttribute("gender","Femenino");
		 			 
	 			 }	 			
	    	 
	 			 if(requester.getIsWorking().booleanValue()==true){
		 				request.setAttribute("working","si");
		 			 }else{
		 				request.setAttribute("working","no");
			 			 
		 			 }	 			
	 			 
	 			 if(requester.getVehicle().booleanValue()==true){
		 				request.setAttribute("vehicle","si");
		 			 }else{
		 				request.setAttribute("vehicle","no");
			 			 
		 			 }	 			
	 			 if( requested.getExperience().booleanValue()==true){
		 				request.setAttribute("experience","si");
		 			 }else{
		 				request.setAttribute("experience","no");
			 			 
		 			 }	 		
	 			session.removeAttribute("codesCity");
	 			session.removeAttribute("positionName");
	    	 
	    	 
	     }catch(Exception e) {
	  			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure"));
				logger.info(e.getMessage(), e);
	  		}finally {
				if (sess != null)
					try {
					    sess.clear();
						sess.close();
					} catch (Exception e) {
					} 			
			} 		
	     
  	
  	return mapping.findForward(Globals.FORWARD_FORM);
		
  }



}