<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="portal.sri.documents.options" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu"/> >
			<bean:message key="portal.category"  bundle="systemMenu"/> >
			<bean:message key="portal.sri.documents.options" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		<html:messages id="message" message="true">
			<div class="message"><c:out value="${message}" escapeXml="false" /></div>
		</html:messages>
		<div class="error"><html:errors/></div>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>

<c:url var="urlSpecies" value="/system/portal/content/species.do">
	<c:param name="type" value="1"/>	
	<c:param name="itemCode" value="${code}"/>	
	<c:param name="action" value="listBound"/>	
</c:url>

<c:url var="urlStatistics" value="/system/portal/content/statistics.do">
	<c:param name="type" value="1"/>	
	<c:param name="itemCode" value="${code}"/>	
	<c:param name="action" value="listBound"/>	
</c:url>

<c:url var="urlLegalDocs" value="/system/portal/content/legalDoc.do">
	<c:param name="type" value="1"/>	
	<c:param name="itemCode" value="${code}"/>	
	<c:param name="action" value="listBound"/>	
</c:url>



<table border="0" cellpadding="0" cellspacing="0" align="center" width="100%">
	<tr>
		<td >
		    <!-- Container TABS -->
			<tiles:insert definition="tabs.system.content.${currentContent.level}">
				<tiles:put name="code" value="${code}" />
				<tiles:put name="tab" value="3" />
			</tiles:insert>
			<!-- END Container TABS -->
		</td>
	</tr>
	<tr>
		<td>
			<table class="tabForm" border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td style="padding-top:50px">
						<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
						<table align="center" border="0" cellpadding="4" cellspacing="0">
							<tr>
								<td class="subtitle" height="40"><bean:message key="portal.sri.documents" bundle="systemMenu" /></td>
							</tr>
							<tr>
								<td class="normal"><a href="${urlLegalDocs}"> <bean:message key="prompt.legal.documents" /></a></td>
							</tr>
							<tr>
								<td class="normal"><a href="${urlStatistics}"><bean:message key="prompt.statistic" /></a> </td>
							</tr>
							<tr>
								<td class="normal"><a href="${urlSpecies}"><bean:message key="prompt.form.request.others" /></a></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
