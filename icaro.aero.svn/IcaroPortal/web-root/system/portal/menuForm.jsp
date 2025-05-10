<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="portal.menu" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> >
			<bean:message key="portal.menu" bundle="systemMenu" />
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
			<tiles:insert definition="tabs.system.menus">
				<tiles:put name="code" value="${menuForm.code}" />
			</tiles:insert>
			<!-- END Container TABS -->
		</td>
	</tr>
	<tr>
		<td>
			<html:form action="/system/portal/menu/save" focus="name" onsubmit="return validateMenuForm(this);">
			<html:hidden property="action" value="save" />
			<html:hidden property="code" />
			<html:hidden property="menuLanguageCode" />
			<html:hidden property="tab" value ="${param.tab}" />
		
			<table class="tabForm" border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td class="subtitle">
									<c:choose>
										<c:when test="${!empty menuForm.code && menuForm.code != 0}">
											<bean:message key="message.edit" bundle="systemHelp" arg0="${menuForm.name}" />
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
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td class="required">* <bean:message key="prompt.name" /> :</td>
								<td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
							</tr>
							<tr>
								<td class="required">* <bean:message key="prompt.enabled" /> :</td>
								<td>
									<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
									<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
								</td>
							</tr>
							<tr>
								<td width="120"></td>
								<td><bean:message key="message.required" bundle="systemHelp" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</html:form>
		</td>
	</tr>
</table>
<html:javascript formName="menuForm" dynamicJavascript="true" staticJavascript="false" />