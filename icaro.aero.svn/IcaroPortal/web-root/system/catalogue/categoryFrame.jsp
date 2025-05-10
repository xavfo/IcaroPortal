<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="cart.category" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="cart" bundle="systemMenu" /> &lt;
			<bean:message key="cart.category" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
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



<%--<html:form action="/system/catalogue/brand">
<html:hidden property="action" value="" />
<html:hidden property="code" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		</td>
		<td align="left">
			<tiles:insert definition="system.navBar">
				<tiles:put name="add" 		value="true" />
				<tiles:put name="edit" 		value="true" />
				<tiles:put name="delete" 	value="true" />
				<tiles:put name="formIndex" value="1" />
			</tiles:insert>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>


<!-- List data -->
<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
	<tr>
		<th width="50">#</th>
		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
	  <th width="382"><bean:message key="label.name" bundle="messages" /></th>
	</tr>
	<c:forEach var="item" items="${brandList}" varStatus="status">	
		<c:url value="/system/catalogue/brand.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
		</c:url> 	
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
			<td><a href="${urlRead}" class="item">${item.name}</a></td>
		</tr>
	</c:forEach>
</table>
</html:form>--%>

<c:url value="/system/catalogue/category.do" var="urlAdd">
    <c:param name="action" value="create" />
</c:url>
<c:url value="/system/catalogue/category.do" var="urlList">
    <c:param name="action" value="list" />
    <c:param name="listItems" value="true" />		
</c:url>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td width="250" valign="top"><iframe id="itree" name="itree" width="250" height="650" src="${urlList}" scrolling="Auto" frameborder="0" style="border: 1px solid #999999;"></iframe></td>
		<td width="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="1" border="0"></td>
		<td><iframe id="iform" name="iform" width="100%" height="650" src="${urlAdd}" frameborder="0" style="border: 1px solid #999999;"></iframe></td>
	</tr>
</table>