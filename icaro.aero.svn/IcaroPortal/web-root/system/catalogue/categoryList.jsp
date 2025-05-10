<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>


<html:form action="/system/catalogue/category" target="iform">
<html:hidden property="action" value="create" />
<!-- Options -->
<div>
	<html:submit style="font-size: 9px; height: 18px;" styleClass="button" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.add" bundle="messages" /></html:submit>
	<html:button property="button" style="font-size: 9px; height: 18px;" styleClass="button" onclick="treePortal.openAll()" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.openAll" bundle="messages" /></html:button>
	<html:button property="button" style="font-size: 9px; height: 18px;" styleClass="button" onclick="treePortal.closeAll()" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.closeAll" bundle="messages" /></html:button>
</div>
</html:form>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<table bgcolor="#FFFFFF" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<script type="text/javascript">
			<!--
				treePortal = new dTree('treePortal');
			
				treePortal.add(0,-1,'<bean:message key="application.name" bundle="messages" /> (${appLanguage.name})', '', '', '', '${pageContext.request.contextPath}/images/system/dtree/globe.gif', '${pageContext.request.contextPath}/images/system/dtree/globe.gif');
					
				<c:forEach var="item" items="${categoryList}" varStatus="status">
			       	<c:url value="/system/catalogue/category.do" var="urlRead">
			            <c:param name="action" value="read" />
			            <c:param name="code" value="${item.code}" />
			        </c:url>
					<c:choose>
						<c:when test="${item.group}">
							treePortal.add(${item.code}, <c:out value="${item.parent.code}" default="0" />, '${item.name}', '${urlRead}', '', 'iform', '${pageContext.request.contextPath}/images/system/dtree/folder.gif');
						</c:when>
						<c:otherwise>
							treePortal.add(${item.code}, <c:out value="${item.parent.code}" default="0" />, '${item.name}', '${urlRead}', '', 'iform');
						</c:otherwise>
					</c:choose>
				</c:forEach>
			
				document.write(treePortal);
				//-->
			</script>
			
		</td>
	</tr>
</table>
