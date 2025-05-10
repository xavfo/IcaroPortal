<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-tiles" prefix="tiles"%>


<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td class="title" width="300"><bean:message key="administration.sysParam" bundle="systemMenu" /> <c:set
      var="systemMenuLabel">
      <bean:message key="administration.sysParam" bundle="systemMenu" />
    </c:set></td>
    <td align="right"><bean:message key="administration" bundle="systemMenu" /> &gt; ${systemMenuLabel}</td>
  </tr>
</table>

<hr width="100%" size="1" color="#999999" noshade>
<html:form action="/system/admin/sysParam/save" focus="name" onsubmit="return preValidate(this)">
  <html:hidden property="action" value="save" />
  <html:hidden property="code" />
  <table border="0" cellpadding="4" cellspacing="0" width="100%">
    <tr>
      <td align="right"><tiles:insert definition="system.navBar">
        <tiles:put name="save" value="true" />
        <tiles:put name="apply" value="true" />
        <tiles:put name="reset" value="true" />
        <tiles:put name="cancel" value="true" />
      </tiles:insert></td>
    </tr>
  </table>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td><html:messages id="message" message="true">
        <div class="message"><c:out value="${message}" escapeXml="false" /></div>
      </html:messages>
      <div class="error"><html:errors /></div>
      </td>
    </tr>
  </table>
  <img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td class="subtitle"><c:choose>
        <c:when test="${!empty sysParamForm.code && sysParamForm.code != 0}">
          <bean:message key="message.edit" bundle="systemHelp" arg0="${sysParamForm.name}" />
        </c:when>
        <c:otherwise>
          <bean:message key="message.create" bundle="systemHelp" />
        </c:otherwise>
      </c:choose></td>
    </tr>
  </table>
  <img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
  <br>



  <table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
    <tr>
      <td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5"
        border="0"><br>
      </td>
    </tr>
    <tr>
      <td class="required">* <bean:message key="prompt.name" /> :</td>
      <td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
    </tr>

    <tr>
      <td class="required">* <bean:message key="prompt.value" /> :</td>
      <td><html:text property="value" styleClass="input" style="width: 200px;" /></td>
    </tr>
    <tr>
      <td class="required">* <bean:message key="prompt.type" />:</td>
      <td><span id="applications" class="normal"> <html:select property="type" size="1" styleClass="input"
        style="width: 250px;">
        <html:options collection="typesList" property="name" labelProperty="name"/>
      </html:select> </span></td>
    </tr>
    <tr>
      <td height="30" width="170"></td>
      <td><bean:message key="message.required" bundle="systemHelp" /></td>
    </tr>
    <tr>
      <td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5"
        border="0"><br>
      </td>
    </tr>
  </table>

</html:form>
<html:javascript formName="sysParamForm" dynamicJavascript="true" staticJavascript="false" />

<script language="JavaScript1.2">
	function initPage() {		
		retrieveURL(CONTEXT_PATH + '/system/admin/sysParam.do?ask=COMMAND_NAME_1&action=listStates',document.sysParamForm)

	}

	function preValidate (frm) { 
		
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty sysParamForm.code && sysParamForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el item de ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un nuevo item de ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateSysParamForm(frm);
	  } else { return false; }
	}
	
	//initPage();
</script>
