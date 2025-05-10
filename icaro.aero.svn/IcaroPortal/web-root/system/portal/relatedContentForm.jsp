<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<script language="javascript">
function clean(frm, objTargetName, objLabelName, txtDesc){
	if (typeof txtDesc=='undefined')
		txtDesc="";
	frm.elements[objTargetName].value = "";
	frm.elements[objLabelName].value = txtDesc;
}
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" nowrap="nowrap">${currentContent.title} - <bean:message key="link.content.related" bundle="messages" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> >
			<bean:message key="portal.content.${currentContent.level}" bundle="systemMenu" />
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
			<tiles:insert definition="tabs.system.content.${currentContent.level}">
				<tiles:put name="code" value="${currentContent.code}" />
			</tiles:insert>
			<!-- END Container TABS -->
		</td>
	</tr>
	<tr>
		<td>
			<html:form action="/system/portal/content/related/save" focus="name" onsubmit="return validateRelatedContentForm(this);">
			<html:hidden property="tab" value="2" />
			<html:hidden property="action" value="save" />
			<html:hidden property="code" />
			<html:hidden property="contentCode" value="${currentContent.code}"/>
			<html:hidden property="itemCode" />
			<table class="tabForm" border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td>
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td class="subtitle">
									<c:choose>
										<c:when test="${!empty relatedContentForm.code && relatedContentForm.code != 0}">
											<bean:message key="message.edit" bundle="systemHelp" arg0="${relatedContentForm.name}"/>
										</c:when>
										<c:otherwise>
											<bean:message key="message.create" bundle="systemHelp"/>
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
						<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
						<table align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
							<tr>
								<td class="required">* <bean:message key="prompt.name" /> :</td>
								<td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
							</tr>
							<tr>
								<td class="required">* <bean:message key="prompt.relatedContent" /> :</td>
								<td>
									<html:hidden property="relatedCode" />
									<html:text property="relatedDescription" styleClass="input" style="width: 200px;" readonly="true"/>
									<c:url value="/system/portal/searchContent.do" var="url">
										<c:param name="formIndex" value="0" />
										<c:param name="controlCode" value="relatedCode" />
										<c:param name="controlDescription" value="relatedDescription" />
									</c:url>
									<input type="button" value="..." onclick="openCenterWindow('${url}', 'searchRelated', 650, 500, 'yes')" />
									<input type="button" value="Vaciar" onclick="clean(this.form, 'relatedCode', 'relatedDescription');" />
								</td>
							</tr>
							<tr>
								<td class="required">* <bean:message key="prompt.enabled" /> :</td>
								<td>
									<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
									<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
								</td>
							</tr>
							<tr>
								<td height="30" width="120"></td>
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
<html:javascript formName="relatedContentForm" dynamicJavascript="true" staticJavascript="false" />