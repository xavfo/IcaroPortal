<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>


<html:form action="/system/service/linkCategory" target="iform">
<html:hidden property="action" value="create" />
<!-- Options -->
<div>
	<html:submit style="font-size: 9px; height: 18px;" styleClass="button" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.add" bundle="messages" /></html:submit>
	<html:button property="button" style="font-size: 9px; height: 18px;" styleClass="button" onclick="tree.openAll()" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.openAll" bundle="messages" /></html:button>
	<html:button property="button" style="font-size: 9px; height: 18px;" styleClass="button" onclick="tree.closeAll()" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.closeAll" bundle="messages" /></html:button>
</div>
</html:form>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<table bgcolor="#FFFFFF" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<script type="text/javascript">
			<!--
				tree = new dTree('tree');
			
				tree.add(0,-1,'<bean:message key="service.linkCategory" bundle="systemMenu" /> (${appLanguage.name})');
					
				<c:forEach var="item" items="${linkCategoryList}" varStatus="status">
			       	<c:url value="/system/service/linkCategory.do" var="urlRead">
			            <c:param name="action" value="read" />
			            <c:param name="code" value="${item.code}" />
			        </c:url>
					<c:choose>
						<c:when test="${item.group}">
							tree.add(${item.code}, <c:out value="${item.parent.code}" default="0" />, '${item.name}', '${urlRead}', '', 'iform', '${pageContext.request.contextPath}/images/system/dtree/folder.gif');
						</c:when>
						<c:otherwise>
							tree.add(${item.code}, <c:out value="${item.parent.code}" default="0" />, '${item.name}', '${urlRead}', '', 'iform', '${pageContext.request.contextPath}/images/system/dtree/globe.gif');
						</c:otherwise>
					</c:choose>
				</c:forEach>
			
				document.write(tree);
				//-->
			</script>
		</td>
	</tr>
</table>
