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
			<bean:message key="portal" bundle="systemMenu" /> &gt;
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
			</ul>
			<span class="error"><html:errors/></span>		
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="25" border="0"><br>
<!-- Fin Mensajes -->

<table border="0" cellpadding="0" cellspacing="0" align="center" width="100%">
	<tr>
		<td >
		    <!-- Container TABS -->
			<tiles:insert definition="tabs.system.menus">
				<tiles:put name="code" value="${currentMenu.code}" />
			</tiles:insert>
			<!-- END Container TABS -->
		</td>
	</tr>
	<tr>
		<td>
			<html:form action="/system/portal/menu/access">
			<html:hidden property="action" value="" />
			<html:hidden property="itemCode" value="${currentMenu.code}" />
			<html:hidden property="tab" value="2" />
			<table class="tabForm" border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td class="subtitle">
									<c:choose>
										<c:when test="${!empty currentMenu && currentMenu.code != 0}">
											<bean:message key="message.edit" bundle="systemHelp" arg0="${currentMenu.name}" />
										</c:when>
										<c:otherwise>
											<bean:message key="message.create" bundle="systemHelp" />
										</c:otherwise>
									</c:choose>
								</td>
							 </tr>
						 </table><br />
						 <br />
						 <table cellpadding="0" cellspacing="0" border="0" width="100%">
						 	<tr>
								<td>
									<tiles:insert definition="system.navBar">
										<tiles:put name="add" 		value="true" />
										<tiles:put name="edit" 		value="true" />
										<tiles:put name="delete" 	value="true" />
									</tiles:insert>
								</td>
							</tr>
						</table>
						<!-- List data -->
						<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
							<tr>
								<th align="left">
									<input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"> 
									<bean:message key="label.banner" bundle="messages" />
								</th>
                                <th><bean:message key="label.access.related" bundle="messages" /></th>
								<th><bean:message key="prompt.displayMode" /></th>
								<th><bean:message key="label.enabled" bundle="messages" /></th>
							</tr>
							<c:forEach var="item" items="${accessMenuList}" varStatus="status">
								<c:url value="/system/portal/menu/access.do" var="urlRead">
									<c:param name="action" value="read" />
									<c:param name="code" value="${item.code}" />
									<c:param name="tab" value="2" />
									<c:param name="itemCode" value="${currentMenu.code}" />
								</c:url>
								<tr bgcolor="#F0F0F0">
									<td>
										<html:multibox property="codes" value="${item.code}" /> 
										<a href="${urlRead}" class="item">${item.access.name}</a>
									</td>
                                    <td>${item.access.accessUrl.name}</td>
									<td><bean:message key="${item.displayMode.key}" bundle="database" /></td>
									<td width="80" align="center">
										<c:choose>
											<c:when test="${item.enabled == true}">
												<bean:message key="label.yes" bundle="messages" />
											</c:when>
											<c:otherwise>
												<bean:message key="label.no" bundle="messages" />
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
			</html:form>
		</td>
	</tr>
</table>