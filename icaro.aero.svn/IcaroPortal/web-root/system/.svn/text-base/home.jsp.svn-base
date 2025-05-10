<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>System Home</h1>
<!--${pageContext.request.contextPath}-->

<table cellpadding="0" cellspacing="0" border="0" width="100%">
			
	<!--inicio cabecera lista-->								
	
	<tr>
		<th background="${pageContext.request.contextPath}/images/tables/tile_titulo_tabla.gif" width="50">#</th>
		<th bgcolor="White" width="1"><img src="${pageContext.request.contextPath}/images/shim.gif" height="23" width="1"></th>
		<th background="${pageContext.request.contextPath}/images/tables/tile_titulo_tabla.gif" width="100" nowrap>sesionId</th>
		<th bgcolor="White" width="1"><img src="${pageContext.request.contextPath}/images/shim.gif" height="23" width="1"></th>
		<th background="${pageContext.request.contextPath}/images/tables/tile_titulo_tabla.gif" width="100" nowrap>name</th>
		<th bgcolor="White" width="1"><img src="${pageContext.request.contextPath}/images/shim.gif" height="23" width="1"></th>
		<th background="${pageContext.request.contextPath}/images/tables/tile_titulo_tabla.gif">User</th>
		<th bgcolor="White" width="1"><img src="${pageContext.request.contextPath}/images/shim.gif" height="23" width="1"></th>
		<th background="${pageContext.request.contextPath}/images/tables/tile_titulo_tabla.gif" width="60">role</th>
		<th bgcolor="White" width="1"><img src="${pageContext.request.contextPath}/images/shim.gif" height="23" width="1"></th>
	</tr>
	<!--fin cabecera lista-->
	
	<!--inicio codigo repetitivo de registros lista-->			
	<c:forEach var="item" items="${applicationScope.loggedUserInventory.allElements}" varStatus="status">
		<c:set value="row${status.count % 2}" var="sclass" />
		  <tr>
			<td align="right">&nbsp;&nbsp;${status.count}&nbsp;&nbsp;</td>
			<td bgcolor="#cccccc"><img src="${pageContext.request.contextPath}/images/shim.gif" height="10" width="1"></td>
			<td align="center">&nbsp;&nbsp;${item.session.id}&nbsp;&nbsp;</td>
			<td bgcolor="#cccccc"><img src="${pageContext.request.contextPath}/images/shim.gif" height="10" width="1"></td>
			<td align="center">&nbsp;&nbsp;${item.userIdentity.fullName}&nbsp;&nbsp;</td>
			<td bgcolor="#cccccc"><img src="${pageContext.request.contextPath}/images/shim.gif" height="10" width="1"></td>
			<td align="center">&nbsp;&nbsp;${item.userIdentity.userName}&nbsp;&nbsp;</td>
			<td bgcolor="#cccccc"><img src="${pageContext.request.contextPath}/images/shim.gif" height="10" width="1"></td>
			<td align="center">&nbsp;&nbsp;${item.userIdentity.roleName}&nbsp;&nbsp;</td>
			<td bgcolor="#cccccc"><img src="${pageContext.request.contextPath}/images/shim.gif" height="10" width="1"></td>
			</tr>
	</c:forEach>
</table>
