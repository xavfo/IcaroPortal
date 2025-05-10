<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

	<div id="page">
		<h5>Elementos en el Contexto de la aplicacion</h5>
		<ul>
		<%
			java.util.Enumeration names = application.getAttributeNames();
			
			String element = "";
			boolean emptyMenuList = true;
			while( names.hasMoreElements() ) {
				element = (String) names.nextElement();
				if (element.startsWith(com.iportal.Constants.MAIN_MENU_KEY)) {
					emptyMenuList = false;
				%>
				<li>
				    <strong><%= element %></strong>:&nbsp; <%= application.getAttribute(element).getClass().getName() %><br>
				</li>
			<%
				}
			}
			if (emptyMenuList) {
				pageContext.setAttribute("emptyMenuList", new Boolean(emptyMenuList));
			}
			%>
		</ul>	
		<c:if test="${ itemsRemoved > 0 }">
			<p>Se han eliminado de manera exitosa el siguiente n&uacute;meor de menus: <strong>${itemsRemoved}</strong> </p>
		</c:if>
		<c:if test="${emptyMenuList}">
			<p>Al momento no existen elementos de menu Registrados en el Contexto de la aplicaci&oacute;n.</p>	
		</c:if>
		<c:url value="/system/admin/cleanMainMenuKeys.do" var="urlClean"></c:url>
		<a href="${urlClean}" class="item">Limpiar Menus de Memoria</a>
	</div>	