<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<script language="javascript">

function setValues(code, description) {
	var frm = document.forms[0];
	var idx = frm.formIndex.value;
	var objTarget = frm.controlCode.value;
	var objLabel  = frm.controlDescription.value;

	opener.document.forms[idx].elements[objTarget].value = code;
	opener.document.forms[idx].elements[objLabel].value = description;
	close();
}

</script>

<html:form action="/system/portal/searchMenuItem">
<html:hidden property="action" value="search" />
<html:hidden property="formIndex" />
<html:hidden property="controlCode" />
<html:hidden property="controlDescription" /><!-- Options -->
<div>
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
				<c:choose>
					<c:when test="${empty searchContentForm.treeName}">
						tree.add(0,-1,'Root');
					</c:when>
					<c:otherwise>
						tree.add(0,-1,'searchContentForm.treeName');
					</c:otherwise>
				</c:choose>
				<c:forEach var="item" items="${resultList}" varStatus="status">
					
					<c:set value="javascript:setValues(${item.code}, '${item.name}')" var="urlRead" />
					<c:choose>
						<c:when test="${item.group}">
							<c:choose>
								<c:when test="${item.isFamily}">
									tree.add(${item.code}, <c:out value="${item.parentCode}" default="0" />, "${item.name}", "", "", "", "${pageContext.request.contextPath}/images/system/dtree/folder.gif");
								</c:when>
								<c:otherwise>
									tree.add(${item.code}, <c:out value="${item.parentCode}" default="0" />, "${item.name}", "${urlRead}", "", "", "${pageContext.request.contextPath}/images/system/dtree/folder.gif");
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${item.isFamily}">
									tree.add(${item.code}, <c:out value="${item.parentCode}" default="0" />, "${item.name}");
								</c:when>
								<c:otherwise>
									tree.add(${item.code}, <c:out value="${item.parentCode}" default="0" />, "${item.name}", "${urlRead}");
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			
				document.write(tree);
				//-->
			</script>
		</td>
	</tr>
</table>
