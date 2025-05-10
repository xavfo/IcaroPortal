<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">	
		<c:choose>
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_JOB}">
				<bean:message key="information.catalogues.job" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.job" bundle="systemMenu" /></c:set>
			</c:when>			
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_NEWS}">
				<bean:message key="information.catalogues.newsClasificator" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.newsClasificator" bundle="systemMenu" /></c:set>
			</c:when>
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_EVENT}">
				<bean:message key="information.catalogues.event" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.event" bundle="systemMenu" /></c:set>
			</c:when>
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_DOCUMENT}">
				<bean:message key="information.catalogues.document" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.document" bundle="systemMenu" /></c:set>
			</c:when>
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_LINK}">
				<bean:message key="information.catalogues.link" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.link" bundle="systemMenu" /></c:set>
			</c:when>			
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_FAQ}">
				<bean:message key="information.catalogues.faq" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.faq" bundle="systemMenu" /></c:set>
			</c:when>	
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_SERVICE_AREA}">
				<bean:message key="information.catalogues.serviceArea" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.serviceArea" bundle="systemMenu" /></c:set>
			</c:when>			
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_LEGAL_DOC}">
				<bean:message key="information.catalogues.legalDocument" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.legalDocument" bundle="systemMenu" /></c:set>
			</c:when>
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_SPECIES}">
				<bean:message key="information.catalogues.species" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.species" bundle="systemMenu" /></c:set>
			</c:when>	
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_MARITAL_STATUS}">
				<bean:message key="information.catalogues.maritalStatus" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.maritalStatus" bundle="systemMenu" /></c:set>
			</c:when>	
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_INSTRUCTION_LEVEL}">
				<bean:message key="information.catalogues.instructionLevel" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.instructionLevel" bundle="systemMenu" /></c:set>
			</c:when>	
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_STUDY_BRANCH}">
				<bean:message key="information.catalogues.studyBranch" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.studyBranch" bundle="systemMenu" /></c:set>
			</c:when>	
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_SALARY_ASPIRATION}">
				<bean:message key="information.catalogues.salaryAspiration" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.salaryAspiration" bundle="systemMenu" /></c:set>
			</c:when>
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_PASSENGER}">
				<bean:message key="information.catalogues.passengerOption" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.passengerOption" bundle="systemMenu" /></c:set>
			</c:when>
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_ESTABLISHMENT}">
				<bean:message key="information.catalogues.establishment" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.establishment" bundle="systemMenu" /></c:set>
			</c:when>				
		</c:choose>
		</td>
		<td align="right">
			<bean:message key="information" bundle="systemMenu" /> >
			<bean:message key="information.catalogues" bundle="systemMenu" /> >	
			${systemMenuLabel}
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

<html:form action="/system/information/catalogue">
<html:hidden property="orderField" value="name" />
<html:hidden property="action" />
<html:hidden property="code" />
<html:hidden property="typeCode"/>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		</td>
		<td align="left">
			<tiles:insert definition="system.navBar">
				<tiles:put name="add" 		value="true" />
				<tiles:put name="edit" 		value="true" />
				<tiles:put name="delete" 	value="true" />
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
	  <th width="80"><bean:message key="label.enabled" bundle="messages" /></th>
	</tr>
	<c:forEach var="item" items="${catalogueList}" varStatus="status">	
		<c:url value="/system/information/catalogue.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
		</c:url> 	
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
			<td><a href="${urlRead}" class="item">${item.name}</a></td>
			<td>
				<c:choose>
					<c:when test="${item.enabled == true}">
						<bean:message key="label.yes" bundle="messages" />
					</c:when>
					<c:otherwise>
						<bean:message key="label.no" bundle="messages" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>
</html:form>