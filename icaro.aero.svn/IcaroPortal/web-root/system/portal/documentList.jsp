<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="portal.document" bundle="systemMenu" /></td>
		<td align="right">											
			<bean:message key="portal.document" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.portal.document" bundle="systemHelp" />
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
		<td align="right">
			<c:choose>
				<c:when test="${!empty product}">
					<c:url value="/system/catalog/product.do" var="urlBack">
						<c:param name="action" value="read" />
						<c:param name="code" value="${product.code}" />
					</c:url>
					<bean:message key="label.backTo" bundle="messages" />:<a href="${urlBack}" class="back"> ${product.name}</a>
				</c:when>
				<c:when test="${!empty institution}">
					<c:url value="/system/CMS2/institution.do" var="urlBack">
						<c:param name="action" value="read" />
						<c:param name="code" value="${institution.code}" />
					</c:url>
					<bean:message key="label.backTo" bundle="messages" />:<a href="${urlBack}" class="back"> ${institution.name}</a>
				</c:when>
				<c:otherwise>
					<c:url value="/system/CMS2/licitation.do" var="urlBack">
						<c:param name="action" value="read" />
						<c:param name="code" value="${licitation.code}" />
					</c:url>
					<bean:message key="label.backTo" bundle="messages" />:<a href="${urlBack}" class="back"> ${licitation.name}</a>
				</c:otherwise>
			</c:choose>			
		</td>		
	</tr>
	<tr>
		<td></td>
		<td align="right">
			<c:choose>
				<c:when test="${!empty product}">
					<c:url value="/system/catalog/product.do" var="urlBack">
						<c:param name="action" value="list" />
					</c:url>
					<a href="${urlBack}" class="back"><bean:message key="label.listAll" bundle="messages" /> <bean:message key="catalog.product" bundle="systemMenu" /></a>				</c:when>
				<c:when test="${!empty institution}">
					<c:url value="/system/CMS2/institution.do" var="urlBack">
						<c:param name="action" value="list" />
					</c:url>
					<a href="${urlBack}" class="back"><bean:message key="label.listAll" bundle="messages" /> <bean:message key="CMS2.institution" bundle="systemMenu" /></a>
				</c:when>
				<c:otherwise>
					<c:url value="/system/CMS2/licitation.do" var="urlBack">
						<c:param name="action" value="list" />
					</c:url>
					<a href="${urlBack}" class="back"><bean:message key="label.listAll" bundle="messages" /> <bean:message key="licitation.licitation" bundle="systemMenu" /></a>
				</c:otherwise>
			</c:choose>			
		</td>		
	</tr>	
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">			
			<c:choose>
				<c:when test="${!empty product}">
					<bean:message key="message.projectDocument" bundle="systemHelp" arg0="${product.name}" />
				</c:when>
				<c:when test="${!empty institution}">
					<bean:message key="message.institutionDocument" bundle="systemHelp" arg0="${institution.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.licitationDocument" bundle="systemHelp" arg0="${licitation.name}" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<html:form action="/system/portal/document">
<html:hidden property="action" value="" />
<html:hidden property="productCode" value="${product.code}" />
<html:hidden property="licitationCode" value="${licitation.code}" />
<html:hidden property="institutionCode" value="${institution.code}" />
<!-- Options -->
<div>
	<html:button property="button" styleClass="button" onclick="doAction(this.form, 'action', 'create')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.add" bundle="messages" /></html:button>
	<html:button property="button" styleClass="button" onclick="doAction(this.form, 'action', 'delete')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.delete" bundle="messages" /></html:button>
</div>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<!-- List data -->
<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
	<tr>
		<th width="30">#</th>
		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
		<th><bean:message key="label.name" bundle="messages" /></th>
		<th width="200"><bean:message key="label.documentType" bundle="messages" /></th>		
	</tr>
	<c:forEach var="item" items="${documentList}" varStatus="status">
		<c:url value="/system/portal/document.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
		</c:url>
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
			<td><a href="${urlRead}" class="item">${item.name}</a></td>
			<td>${item.type.name}</td>
		</tr>
	</c:forEach>
</table>
</html:form>