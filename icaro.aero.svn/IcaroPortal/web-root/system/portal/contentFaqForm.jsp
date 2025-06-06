<%@ page language="java" %>
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">			
			${currentContent.name} - 
			<bean:message key="service.faq" bundle="systemMenu" /><br />
			<c:set var="systemMenuLabel" ><bean:message key="service.faq" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> >
			<bean:message key="portal.content.${currentContent.level}" bundle="systemMenu" /> >
			${systemMenuLabel}
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<ul>
			<html:messages id="message" message="true">
				<li class="message"><c:out value="${message}" escapeXml="false" /></li>
			</html:messages>
			<ul>
			<div class="error"><html:errors/></div>		
	</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.content.${currentContent.level}">
			<tiles:put name="code" value="${currentContent.code}" />
		</tiles:insert>
	</td>
</tr>
<tr>
	<td>
		<html:form action="/system/portal/content/faq/save" focus="question" onsubmit="return preValidate(this)">
		<html:hidden property="action" value="save" />
		<html:hidden property="code" />
		<html:hidden property="itemCode" value="${currentContent.code}" />
		<html:hidden property="tab" value="${param.tab}"  />
		<html:hidden property="reset" value="true"  />
		

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
						  <td class="subtitle">
							<c:choose>
								<c:when test="${!empty faqContainerForm.code && faqContainerForm.code != 0}">
									<bean:message key="message.edit" bundle="systemHelp" arg0="${faqContainerForm.question}" />
								</c:when>
								<c:otherwise>
									<bean:message key="message.create" bundle="systemHelp" />
								</c:otherwise>
							</c:choose>
						  </td>
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
								  <table cellpadding="0" cellspacing="0" align="center"  width="100%">
									<tr>
										<td>
										  <table cellpadding="0" cellspacing="4" border="0">
											<%--<tr>
												<td class="required">* <bean:message key="prompt.question" /> :</td>
												<td><html:textarea property="question" cols="50" rows="4" /></td>
											</tr>			
											<tr>
												<td class="required">* <bean:message key="prompt.answer" /> :</td>
												<td><html:textarea property="answer" cols="50" rows="6" /></td>
											</tr>--%>
											
											<tr>
											<td class="required">* <bean:message key="prompt.question" /> :</td>
											<td>
												<html:hidden property="question" styleId="question" />
												<input type="hidden" id="question___Config" value="SkinPath=${pageContext.request.contextPath}/js/fckeditor/editor/skins/silver/&DefaultLanguage=en&AutoDetectLanguage=true">
												<iframe name="question___Frame" id="question___Frame" src="${pageContext.request.contextPath}/js/fckeditor/editor/fckeditor.html?InstanceName=question" width="700" height="300" frameborder="no" scrolling="no"></iframe>
												<%--<input type="button" value="portlet" onclick="loadPage()">--%>
											</td>
										</tr>	
										<tr>
										<td class="required">* <bean:message key="prompt.answer" /> :</td>
											<td>
												<html:hidden property="answer" styleId="answer" />
												<input type="hidden" id="answer___Config" value="SkinPath=${pageContext.request.contextPath}/js/fckeditor/editor/skins/silver/&DefaultLanguage=en&AutoDetectLanguage=true">
												<iframe name="answer___Frame" id="answer___Frame" src="${pageContext.request.contextPath}/js/fckeditor/editor/fckeditor.html?InstanceName=answer" width="700" height="300" frameborder="no" scrolling="no"></iframe>
												<%--<input type="button" value="portlet" onclick="loadPage()">--%>
											</td>
										</tr>		
											<tr>
												<td class="required">*<bean:message key="prompt.category" /> :</td>
												<td>
													<html:select property="categoryCode" size="1" styleClass="input" style="width: 250px;">
														<html:options collection="faqCategoryList" property="code" labelProperty="name"/>
													</html:select>
												</td>
											</tr>
											<tr>
												<td class="optional"><bean:message key="prompt.enabled" /></td>
												<td>
													<html:radio property="isEnabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
													<html:radio property="isEnabled" value="false" /> <bean:message key="label.no" bundle="messages" />
												</td>		
											</tr>
											
											<tr>
												<td class="optional"> <bean:message key="prompt.orderIndex" /> :</td>
												<td><html:text property="index" style="width: 250px;" /></td>
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
		<html:javascript formName="faqContainerForm" dynamicJavascript="true" staticJavascript="false" />
		<script language="JavaScript1.2">
			function preValidate (frm) { 
		    	if (bCancel) { 
		      		return true; 
				}
			  <c:choose>
			  	<c:when test="${!empty documentContainerForm.code && documentContainerForm.code != 0}">
					var msg = 'Se actualizar\u00E1 el documento. �Desea continuar?';
				</c:when>
				<c:otherwise>
					var msg = 'Est\u00E1 a punto de crear un nuevo documento.  �Desea continuar?';
				</c:otherwise>
			  </c:choose>
			  if( confirm (msg)){
				  return validateFaqContainerForm(frm);
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
