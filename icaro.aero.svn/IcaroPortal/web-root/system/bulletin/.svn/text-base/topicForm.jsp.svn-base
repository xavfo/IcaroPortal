<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="bulletin.topic" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="bulletin.topic" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		<html:messages id="message" message="true">
			<div class="message"><c:out value="${message}" escapeXml="false" /></div>
		</html:messages>
		<div class="error"><html:errors/></div>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>

<table border="0" cellpadding="0" cellspacing="0" align="center" width="100%">
	<tr>
		<td >
		    <!-- Container TABS -->
			<tiles:insert definition="tabs.bulletin">
				<tiles:put name="code" value="${topicForm.code}" />
			</tiles:insert>
			<!-- END Container TABS -->
		</td>
	</tr>
	<tr>
		<td>
			<html:form action="/system/bulletin/topic/save" focus="name" onsubmit="return validateTopicForm(this);">
			<html:hidden property="action" value="save" />
			<html:hidden property="code" />
			<html:hidden property="tab" value="${param.tab}"/>
			<table class="tabForm" border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td>
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td class="subtitle">
									<c:choose>
										<c:when test="${!empty topicForm.code && topicForm.code != 0}">
											<bean:message key="message.edit" bundle="systemHelp" arg0="${topicForm.name}" />
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
							</tr>
						</table>
						<table align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
							<tr>
								<td height="30" width="170"></td>
								<td><bean:message key="message.required" bundle="systemHelp" /></td>
							</tr>
							<tr>
								<td class="required">* <bean:message key="prompt.name" /> :</td>
								<td><html:text property="name" styleClass="input" style="width: 350px;" /></td>
							</tr>
							<tr>
								<td class="required">* <bean:message key="prompt.enabled" /> :</td>
								<td>
									<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
									<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
								</td>
							</tr>	
							<tr>
								<td class="optional"><bean:message key="prompt.description" /> :</td>
								<td><html:textarea property="description" styleClass="input" style="height: 60px; width: 350px;" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</html:form>
			<html:javascript formName="topicForm" dynamicJavascript="true" staticJavascript="false" />
		</td>
	</tr>
</table>



















