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
				<bean:message key="information.catalogues.news" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.news" bundle="systemMenu" /></c:set>
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
			<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_REQUEST_REASON}">
				<bean:message key="information.catalogues.requestReason" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.requestReason" bundle="systemMenu" /></c:set>
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

<html:form action="/system/information/catalogue/save" focus="name" onsubmit="return preValidate(this)">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="typeCode" />
<table border="0" cellpadding="4" cellspacing="0" width="100%">
<tr>
  <td align="right">
  	<tiles:insert definition="system.navBar">
		<tiles:put name="save" 		value="true" />
		<tiles:put name="apply"		value="true" />
		<tiles:put name="reset" 	value="true" />
		<tiles:put name="cancel" 	value="true" />
	</tiles:insert>
  </td>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		<html:messages id="message" message="true">
			<div class="message"><c:out value="${message}" escapeXml="false" /></div>
		</html:messages>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty catalogueForm.code && catalogueForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${catalogueForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.description" /> :</td>
		<td><html:textarea property="description" styleClass="input" cols="40" rows="4" /></td>
	</tr>
	<c:if test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_REQUEST_REASON}"> 
	<tr>
		<td class="required">* <bean:message key="prompt.email" /> :</td>
		<td><html:text property="email" styleClass="input" style="width: 200px;" /></td>
	</tr>
	</c:if>
	<tr>
		<td class="optional"><bean:message key="prompt.enabled" /> :</td>
		<td>
			<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>
	</tr>	
	<tr>
		<td height="30" width="170"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
	</tr>
</table>
</html:form>

<html:javascript formName="catalogueForm" dynamicJavascript="true" staticJavascript="false" />



<script language="JavaScript1.2">
	function preValidate (frm) { 
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty catalogueForm.code && catalogueForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el(la) ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un(a) nuevo(a) ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  
	  <c:choose>
	  	<c:when test="${catalogueForm.typeCode eq initParameters.CATALOGUE_TYPE_REQUEST_REASON}">	  					var mail=validar(frm.email.value);
		</c:when> 
		<c:otherwise>						
				var mail=true;
		</c:otherwise>		
	</c:choose>

	  if(mail){
	  	if( confirm (msg)){
	  		return validateCatalogueForm(frm);
		}
		else { return false; }		
				
	  } else { return false; }
	}
</script>
<SCRIPT LANGUAGE="JavaScript">
    function validar(direccion) {
       if (direccion.indexOf("@") != -1)

          return true;

       else {

          alert('Debe escribir una direcci\u00F3n v\u00E1lida');

          return false;

       }

    }

  </SCRIPT>