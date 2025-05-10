<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

	<div id="page">
		<h5>Contenidos Portal</h5>
		
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td>
					<ul>
					<html:messages id="message" message="true">
						<li class="message"><c:out value="${message}" escapeXml="false" /></li>
					</html:messages>
					<ul>
					<div class="error"><html:errors/></div>		
			</td>
			</tr>
		</table>

		<c:url value="/system/admin/updateContentContainer.do" var="urlClean">
			<c:param name="action" value="update" />
		</c:url>
		<a href="${urlClean}" class="item">Recalcular contadores de toda la aplicaci&oacute;n</a> Esta Operaci&oacute;n puede toamr un poco de tiempo. Ejecutarla pocas veces al d&iacute;a.
		<c:set value="0" var="min" />
		<c:set value="0" var="max" />
		<c:if test="${!empty param.min}">
			<c:set value="${param.min}" var="min" />
		</c:if>
		<c:if test="${!empty param.max}">
			<c:set value="${param.max}" var="max" />
		</c:if>

		<form  action="${pageContext.request.contextPath}/system/admin/updateContentContainer.do">
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="justList" value="true">
			<input type="text" name="min" value="${min}" style="width: 60px;" maxlength="5"> 
			<input type="text" name="max" value="${max}" style="width: 60px;" maxlength="5"> <input type="submit" value="Listar">
		</form>
		

		<ul>
		<table>
			<tr>
				<th>#</th>
				<th>C&ouml;digo</th>
				<th>T&iuml;tulo</th>
				<th>Base Legal </th>
				<th>Documentos Adjuntos </th>
				<th>Ayuda Multimedia</th>
				<th>FAQs</th>
				<th>Links</th>
				<th>Otros Documentos</th>
				<th>Solicitudes</th>
				<th>Formularios</th>
			</tr>
			<c:forEach var="item" items="${contents}" varStatus="status">				
				<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
					<td>${status.count}</td>
					<td>${item.code}</td>
					<td>${item.title}</td>
					<td>${item.legalDocumentsNumber}</td>
					<td>${item.documentsNumber}</td>
					<td>${item.supportDocumentsNumber}</td>
					<td>${item.faqsNumber}</td>
					<td>${item.linksNumber}</td>
					<td>${item.speciesDocumentsNumber}</td>
					<td>${item.speciesRequestsNumber}</td>
					<td>${item.speciesFormsNumber}</td>
			</c:forEach>
		</table>
		</ul>	
	</div>	