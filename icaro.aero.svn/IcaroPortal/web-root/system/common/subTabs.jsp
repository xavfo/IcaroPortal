<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<tiles:importAttribute />
<c:if test="${! empty param.subTab}" >
	<c:set var="subTab" value="${param.subTab}" />
</c:if>
<table border="0" cellpadding="0" cellspacing="0" width="100%" align="right" >
	<tr valign="top">
		<td class="tabSpace" height="16" ><img src="${pageContext.request.contextPath}/images/system/newLayout/GIFS/SHIM.GIF" width="1" height="1" border="0"></td>
		<c:choose>
			<c:when test="${empty code || code == 0}">
				<c:set var="key" value="${items[0].value}" />
				<td class="tabActive" width="90"><bean:message name="key" bundle="messages" /></td>
			</c:when>
			<c:otherwise>
				<c:forEach var="item" items="${items}" varStatus="status">
					<c:set var="key" value="${item.value}" />
					<c:url var="link" value="${item.link}" >
						<c:param name="code" value="${code}" />
						<%--<c:param name="button" value="${button}" />--%>
						<c:param name="subTab" value="${status.count}" />
						<c:param name="tab" value="${param.tab}" />
					</c:url>
					<c:choose>
						<c:when test="${subTab == status.count}">
							<td class="tabActive" width="110"><bean:message name="key" bundle="messages" /></td>
						</c:when>
						<c:otherwise>
							<td class="tab" width="110"><a href="<c:out value="${link}" />" class="tab"><bean:message name="key" bundle="messages" /></a></td>
						</c:otherwise>
					</c:choose>
					<td class="tabSpace" width="5"><img src="${pageContext.request.contextPath}/images/system/newLayout/GIFS/SHIM.GIF" width="1" height="1" border="0"></td>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<td class="tabSpace" width="5"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="100%" height="1" border="0"></td>
	</tr>
</table>

