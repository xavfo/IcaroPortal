<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" nowrap="nowrap">
			${currentContent.title} -
			<bean:message key="link.content.related" bundle="messages" />
		</td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> >
			<bean:message key="portal.content.${currentContent.level}" bundle="systemMenu" />
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
		    <!-- Container TABS -->
			<tiles:insert definition="tabs.system.content.${currentContent.level}">
				<tiles:put name="code" value="${currentContent.code}" />
			</tiles:insert>
			<!-- END Container TABS -->
		</td>
	</tr>
	<tr>
		<td>
			<table class="tabForm" border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td>
						<html:form action="/system/portal/content/related">
						<html:hidden property="action" value="list"/>
						<html:hidden property="level" value="3"/>
						<html:hidden property="itemCode" value="${currentContent.code}"/>
						<html:hidden property="tab" value ="${param.tab}" />
						<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
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
						<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
						<!-- List data -->
						<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
							
							<tr>
								<th width="30">#</th>
								<th width="20"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
								<th><bean:message key="label.alias" bundle="messages" /></th>
								<th><bean:message key="label.relatedContent" bundle="messages" /></th>
								<th><bean:message key="label.enabled" bundle="messages" /></th>
							</tr>
							<c:forEach var="item" items="${relatedList}" varStatus="status">
								<c:url value="/system/portal/content/related.do" var="urlRead">
									<c:param name="action" value="read" />
									<c:param name="code" value="${item.code}" />
									<c:param name="tab" value="${param.tab}" />
									<c:param name="itemCode" value="${currentContent.code}" />
								</c:url>
								<tr bgcolor="#F0F0F0">
									<td width="30">${status.count}</td>
									<td width="20">
										<html:multibox property="codes" value="${item.code}" />
									</td>
									<td>
										<a href="${urlRead}" class="item">${item.name}</a>
									</td>
									<td>
										${item.related.title}
									</td>
									<td align="center">
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
						</html:form>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>