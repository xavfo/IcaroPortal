<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%  Long presentValue = (Long)org.apache.commons.beanutils.PropertyUtils.getSimpleProperty(request.getAttribute("theForm"),(String)request.getAttribute("targetTerProperty"));
    pageContext.setAttribute("presentValue3", presentValue); %>
<c:set var="base" value="style=\"width: 178px;\" class=\"textForm\""/>
<c:if test="${fn:startsWith(theForm.class.name, 'com.iportal.cart.ctrl.system')}">
  <c:set var="base" value="style=\"width: 250px;\" class=\"normal\""/>
</c:if>
<span id="listOfTerOptions" class="normal" >
<select name="${targetTerProperty}" size="1" ${base} ${extra3}>
	<option value=""><bean:message key="option.none" bundle="messages" /></option>
	<c:forEach var="item" items="${listOfTerOptions}" varStatus="status">
		<c:choose>
		<c:when test="${presentValue3 != item.code}">
			<option value="${item.code}">${item.name}</option>
		</c:when>
		<c:otherwise>
			<option value="${item.code}" selected="selected">${item.name}</option>
		</c:otherwise>
		</c:choose>
	</c:forEach>
</select>
</span>
<c:remove var="listOfTerOptions" />