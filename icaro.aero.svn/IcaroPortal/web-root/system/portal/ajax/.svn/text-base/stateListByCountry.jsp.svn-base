<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%  Long presentValue = (Long)org.apache.commons.beanutils.PropertyUtils.getSimpleProperty(request.getAttribute("theForm"),(String)request.getAttribute("targetProperty"));
	pageContext.setAttribute("presentValue", presentValue); %>
<c:set var="base" value="style=\"width: 178px;\" class=\"textForm\""/>
<c:if test="${fn:startsWith(theForm.class.name, 'com.iportal.cart.ctrl.system')}">
  <c:set var="base" value="style=\"width: 250px;\" class=\"normal\""/>
</c:if>
<span id="applications" class="normal">
  <select name="${targetProperty}" size="1" ${base} ${extra}>
	<c:forEach var="item" items="${listOfOptions}" varStatus="status">
		<c:choose>
		<c:when test="${presentValue != item.code}">
			<option value="${item.code}">${item.name}</option>
		</c:when>
		<c:otherwise>
			<option value="${item.code}" selected="selected">${item.name}</option>
		</c:otherwise>
		</c:choose>
	</c:forEach>
  </select>
</span>
<c:remove var="listOfOptions" />
<c:if test="${!empty targetSecProperty }" >
<%  Long presentValue2 = (Long)org.apache.commons.beanutils.PropertyUtils.getSimpleProperty(request.getAttribute("theForm"), (String)request.getAttribute("targetSecProperty"));
	pageContext.setAttribute("presentValue2", presentValue2); %>
<span id="listOfSecOptions" class="normal" >
<select name="${targetSecProperty}" size="1" ${base} ${extra2}>
	<option value=""><bean:message key="option.none" bundle="messages" /></option>
	<c:forEach var="item" items="${listOfSecOptions}" varStatus="status">
		<c:choose>
		<c:when test="${presentValue2 != item.code}">
			<option value="${item.code}">${item.name}</option>
		</c:when>
		<c:otherwise>
			<option value="${item.code}" selected="selected">${item.name}</option>
		</c:otherwise>
		</c:choose>
	</c:forEach>
</select>
</span>
<c:remove var="listOfSecOptions" />
</c:if>
<c:if test="${!empty targetTerProperty }" >
<%  Long presentValue3 = (Long)org.apache.commons.beanutils.PropertyUtils.getSimpleProperty(request.getAttribute("theForm"), (String)request.getAttribute("targetTerProperty"));
    pageContext.setAttribute("presentValue3", presentValue3); %>
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
</c:if>