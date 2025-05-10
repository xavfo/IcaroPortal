<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<jsp:useBean id="calendar" scope="page" class="com.iportal.biz.CalendarBean"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/prototype-1.4.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/controls.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/ajaxtags-1.2-beta1.js"></script>

<c:if test="${!empty eventDateForm.year}">
	<c:set target="${calendar}" property="year" value="${eventDateForm.year}" />
	<c:set target="${calendar}" property="month" value="${eventDateForm.month - 1}" />
</c:if>
<script language="JavaScript1.2">
<!--
	function updateCalendar() {
		document.eventDateForm.action.value = 'upd';
		document.eventDateForm.submit();
	}
//-->
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">			
			<bean:message key="event.event" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="event.event" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="event.event" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.portal.section" bundle="systemHelp" /><br/>
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
			<div class="error"><html:errors/></div>
		</td>
	</tr>
</table>

<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.events">
			<tiles:put name="code" value="${eventForm.code}" />
		</tiles:insert>
	</td>
</tr>
<tr>
	<td>
		<html:form action="/system/event/eventDate/save" onsubmit="return preValidate(this);">
		<html:hidden property="level" value="1" />
		<html:hidden property="action" value="save" />
		<html:hidden property="tab" value="${param.tab}" />
		<html:hidden property="code" />
		<html:hidden property="eventCode" value="${eventForm.code}" />
		<html:hidden property="startHour"  value=""/>
		<html:hidden property="startMinute"  value=""/>
		<html:hidden property="endHour" value=""/>
		<html:hidden property="endMinute" value=""/>
		
		
		
		<table class="tabForm" border="0" cellpadding="0" cellspacing="4" width="100%">		
		<tr>
			<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0"></td>
		</tr>
		<tr>
		<td>		
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
			            <table cellpadding="0" cellspacing="0" border="0" width="100%" >
						<tr>
						<td valign="top">
						<!--inicio seccion general information-->
						<table border="0" cellpadding="4" cellspacing="0" width="100%">
						<tr>
						  <td align="right">
						  	<tiles:insert definition="system.navBar">
								<tiles:put name="save" 		value="true" />
								<tiles:put name="apply"		value="true" />
								<tiles:put name="reset" 	value="true" />
								<tiles:put name="cancel" 	value="true" />
							</tiles:insert>
						  </td>
						</table>
						<table cellpadding="0" cellspacing="0" align="center"  width="100%">
							<tr>
								<td>
								  <table cellpadding="0" cellspacing="4" border="0">
									<tr>
										<td class="required">* <bean:message key="prompt.city" /> :</td>
										<td>
											<html:hidden property="cityCode" />
											<html:hidden property="countryCode"/>
											<html:text property="cityName"     styleClass="input" style="width: 120px;" maxlength="100" readonly="true"/> - <html:text property="countryName"     styleClass="input" style="width: 120px;" maxlength="100" readonly="true"/>
											<a href="javascript: openSearch('/popup/searchCity.do', 'contactFormRequestForm', new Array('cityCode','countryCode'),new Array('cityName','countryName'), 0)" class="button"><bean:message key="button.find" bundle="messages" /></a>
										</td>
									</tr>
									<tr>
										<td class="required">* <bean:message key="prompt.date" /> :</td>
										<td>	  <table cellpadding="0" cellspacing="0">
								          <tr>
								            <td>
											
								              <table border="0" cellpadding="0" cellspacing="0" width="100%">
								                <tr>
												  <td class="label" height="35" width="40"><bean:message key="label.year" bundle="messages" />&nbsp;</td>
								                  <td width="120"> 
												  		<html:select property="year" size="1" styleClass="input" style="width: 80px;" onchange="updateCalendar()">
												  		<html:options collection="yearList" property="value" labelProperty="label"/>
														</html:select>
												</td>
								                  <td class="label" height="35" width="45">
												  					<bean:message key="label.month" bundle="messages" />&nbsp;</td>
								                  <td> <html:select property="month" size="1" styleClass="input" style="width: 150px;" onchange="updateCalendar()">
																<html:option value="1"><bean:message key="label.january" bundle="messages" /></html:option>
																<html:option value="2"><bean:message key="label.february" bundle="messages" /></html:option>								
																<html:option value="3"><bean:message key="label.march" bundle="messages" /></html:option>
																<html:option value="4"><bean:message key="label.april" bundle="messages" /></html:option>
																<html:option value="5"><bean:message key="label.may" bundle="messages" /></html:option>
																<html:option value="6"><bean:message key="label.june" bundle="messages" /></html:option>
																<html:option value="7"><bean:message key="label.july" bundle="messages" /></html:option>
																<html:option value="8"><bean:message key="label.august" bundle="messages" /></html:option>
																<html:option value="9"><bean:message key="label.september" bundle="messages" /></html:option>
																<html:option value="10"><bean:message key="label.october" bundle="messages" /></html:option>
																<html:option value="11"><bean:message key="label.november" bundle="messages" /></html:option>
																<html:option value="12"><bean:message key="label.dicember" bundle="messages" /></html:option>
															</html:select>
													</td>
								                </tr>
								            </table>
											</td>
								          </tr>
								          <tr>
								            <td><table bgcolor="#CCCCCC" border="0" cellpadding="4" cellspacing="1">
								                <tr bgcolor="E0E0E0">
								                  <td align="center" width="50"><strong><bean:message key="label.sunday" bundle="messages" /></strong></td>
								                  <td align="center" width="50"><strong><bean:message key="label.monday" bundle="messages" /></strong></td>
								                  <td align="center" width="50"><strong><bean:message key="label.tuesday" bundle="messages" /></strong></td>
								                  <td align="center" width="50"><strong><bean:message key="label.wednesday" bundle="messages" /></strong></td>
								                  <td align="center" width="50"><strong><bean:message key="label.thursday" bundle="messages" /></strong></td>
								                  <td align="center" width="50"><strong><bean:message key="label.friday" bundle="messages" /></strong></td>
								                  <td align="center" width="50"><strong><bean:message key="label.saturday" bundle="messages" /></strong></td>
								                </tr>
								                <c:forEach begin="1" end="6" varStatus="w">
								                <tr bgcolor="F3F3F3"> <c:forEach begin="1" end="7" varStatus="d">
								                  <td> <c:if test="${!empty calendar.days[w.count - 1][d.count - 1]}"> <html:multibox property="date"><c:out value="${calendar.year}-${calendar.month + 1}-${calendar.days[w.count - 1][d.count - 1]}" /></html:multibox> <c:out value="${calendar.days[w.count - 1][d.count - 1]}" /> </c:if> </td>
								                </c:forEach> </tr>
								                </c:forEach>
								            </table></td>
								          </tr>
								        </table></td>
									</tr>
									<tr>
										<td class="required">* <bean:message key="prompt.schedule" /> :</td>
										<td><html:textarea property="schedule" styleClass="input" style="width: 350px; height: 80px;" /></td>
									</tr>
									<tr>
										<td class="required">* <bean:message key="prompt.location" /> :</td>
										<td><html:textarea property="location" styleClass="input" style="width: 350px; height: 80px;" /></td>
									</tr>
									<tr>
										<td class="required">* <bean:message key="prompt.responsibleEmail" /> :</td>
										<td><html:text property="responsibleEmail" styleClass="input" style="width: 350px;" /></td>
									</tr>
									<tr>
										<td class="optional"><bean:message key="prompt.email" /> :</td>
										<td><html:text property="optionalEmail" styleClass="input" style="width: 350px;" /></td>
									</tr>
									<tr>
										<td class="optional"><bean:message key="prompt.notes" />:</td>
										<td><html:textarea property="notes" styleClass="input" style="width: 350px; height: 80px;" /></td>
									</tr>
									<tr>
										<td height="30" width="170"></td>
										<td><bean:message key="message.required" bundle="systemHelp" /></td>
									</tr>
								  </table>															
								</td>
							</tr>
						</table>
						</td>			            
						</tr>						
						</table>                           										
					</td>
				</tr>
				<tr>
					<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
				</tr>
			</table>	
		</td>
		</tr>
		</table>
		</html:form>
		<html:javascript formName="eventDateForm" dynamicJavascript="true" staticJavascript="false" />
		<script language="JavaScript1.2">
			function preValidate (frm) { 
		    	if (bCancel) { 
		      		return true; 
				}
			  <c:choose>
			  	<c:when test="${!empty eventDateForm.code && eventDateForm.code != 0}">
					var msg = 'Se actualizar\u00E1 el ${systemMenuLabel}. ¿Desea continuar?';
				</c:when>
				<c:otherwise>
					var msg = 'Est\u00E1 a punto de crear un nuevo ${systemMenuLabel}.  ¿Desea continuar?';
				</c:otherwise>
			  </c:choose>
			  if( confirm (msg)){
				  return validateEventDateForm(frm);
			  } else { return false; }
			}
		</script>
	</td>
</tr>
<tr>
	<td background="${pageContext.request.contextPath}/images/tables/file_bottom.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="7" border="0" alt=""></td>
</tr>
<tr>
	<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
</tr>
</table>

