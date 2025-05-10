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
		<td class="title" width="300"><bean:message key="portal.menu" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> >
			<bean:message key="portal.menu" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<!-- Inicio Mensajes -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td><ul>
			<html:messages id="message" message="true">
				<li class="message"><c:out value="${message}" escapeXml="false" /></li>
			</html:messages>
			<ul>
			<div class="error"><html:errors/></div>		
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="25" border="0"><br>
<!-- Fin Mensajes -->


<table border="0" cellpadding="0" cellspacing="0" align="center" width="100%">
	<tr>
		<td >
			<!-- TABS -->
			<tiles:insert definition="tabs.system.menus">
				<tiles:put name="code" value="${currentMenu.code}" />
			</tiles:insert>
			<!-- END TABS -->
		</td>
	</tr>
	<tr>
		<td>
			<html:form action="/system/portal/menu/item/save" focus="name" onsubmit="return validateMenuItemForm(this);">
			<html:hidden property="action" value="save" />
			<html:hidden property="code" />
			<html:hidden property="group" value="true" />
			<html:hidden property="itemCode" />
			<html:hidden property="level" />
			<html:hidden property="tab" value ="${param.tab}" />
			<table class="tabForm" border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td class="subtitle">
									<c:choose>
										<c:when test="${!empty menuItemForm.code && menuItemForm.code != 0}">
											<bean:message key="message.menuItem.edit" bundle="systemHelp" arg0="${menuItemForm.name}"  arg1="${currentMenu.name}" />
										</c:when>
										<c:otherwise>
											<bean:message key="message.menuItem.create" bundle="systemHelp" arg0="${currentMenu.name}" />
										</c:otherwise>
									</c:choose>
								</td>
								<td align="right">
									<tiles:insert definition="system.navBar">
										<tiles:put name="save" 		value="true" />
										<tiles:put name="apply"		value="true" />
										<tiles:put name="reset" 	value="true" />
										<%--<tiles:put name="preview" 	value="true" />--%>
										<tiles:put name="cancel" 	value="true" />
									</tiles:insert>
								</td>
							</tr>
						</table>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td class="required" width="160">* <bean:message key="prompt.name" /> :</td>
								<td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
							</tr>
							<%-- tr>
								<td class="optional" width="160"><bean:message key="prompt.parent" /> :</td>
								<td>
									<html:hidden property="parentCode" styleClass="input" style="width: 20px;" />
									<html:text property="parentDescription" styleClass="input" style="width: 200px;" readonly="true"/>
									<c:url value="/system/portal/searchMenuItem.do" var="urlParent">
										<c:param name="action" value="tree" />
										<c:param name="code" value="${menuItemForm.code}" />
										<c:param name="itemCode" value="${menuItemForm.itemCode}" />
										<c:param name="formIndex" value="0" />
										<c:param name="controlCode" value="parentCode" />
										<c:param name="controlDescription" value="parentDescription" />
									</c:url>
									<input type="button" value="..." onclick="openCenterWindow('${urlParent}', 'searchParent', 650, 500, 'yes')" />
									<input type="button" value="Vaciar" onclick="clean(this.form, 'parentCode', 'parentDescription', 'Root');" />
								</td>
							</tr --%>
							<tr>
								<td class="required" width="160">* <bean:message key="prompt.displayMode" /> :</td>
								<td>
									<html:select property="displayModeCode" styleClass="input" style="width: 200px;" >
										<html:options collection="displayModeList" property="code" labelProperty="key"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<td class="required" width="160">* <bean:message key="prompt.content" /> :</td>
								<td>
									<html:hidden property="contentCode" styleClass="input" style="width: 20px;" />
									<html:text property="contentDescription" styleClass="input" style="width: 200px;" readonly="true" />
									<c:url value="/system/portal/searchContent.do" var="url">
										<c:param name="formIndex" value="0" />
										<c:param name="controlCode" value="contentCode" />
										<c:param name="controlDescription" value="contentDescription" />
									</c:url>
									<input type="button" value="..." onclick="openCenterWindow('${url}', 'searchContent', 650, 500, 'yes')" />
									<input type="button" value="Vaciar" onclick="clean(this.form, 'contentCode', 'contentDescription');" />
								</td>
							</tr>
							<tr>
								<td class="required" width="160">* <bean:message key="prompt.orderIndex" /> :</td>
								<td>
									<html:text property="order" style="width: 50px;" />
								</td>
							</tr>
							<tr>
								<td class="optional"><bean:message key="prompt.width" />:</td>
						        <td ><html:text property="menuWidth" maxlength="4" style="width: 50px;"/></td>
						    </tr>
							<tr>
								<td class="optional"><bean:message key="prompt.x" /> :</td>
								<td><html:text property="menuX" style="width: 50px;" /></td>
							</tr>
							<tr>
								<td class="optional"><bean:message key="prompt.y" /> :</td>
								<td><html:text property="menuY" style="width: 50px;" /></td>
							</tr>
							<tr>
								<td class="required" width="160">* <bean:message key="prompt.enabled" /> :</td>
								<td>
									<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
									<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
								</td>
							</tr>
							<tr>
								<td  width="160"></td>
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
<html:javascript formName="menuItemForm" dynamicJavascript="true" staticJavascript="false" />




