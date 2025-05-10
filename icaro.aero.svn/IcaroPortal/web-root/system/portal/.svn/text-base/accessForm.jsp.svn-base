<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>


<script language="JavaScript1.2">	

	function enableFields() {
		frm = document.forms[0];	
		
		frm.url.readOnly = true;
		frm.relatedCode.disabled = true;
		frm.relatedName.disabled = true;
		frm.relatedButtom.disabled = true;
		frm.relatedCode.value = '';
			frm.relatedName.value = '';
		document.getElementById('pathLbl').className = 'optional';
		document.getElementById('contentLbl').className = 'optional';
		
		frm.relatedCode.disabled = false;
		frm.relatedName.disabled = false;
		frm.relatedButtom.disabled = false;
		
		document.getElementById('contentLbl').className = 'required';
					
		<c:forEach var="item" items="${urlList}" >
			if(frm.accessUrlCode.value == ${item.code}) { 
				document.getElementById('contentLbl').innerHTML = '* ${item.name}';
				frm.url.value = '${item.path}';
				frm.className.value = "${item.className}";
			}
		</c:forEach>
		if(null == frm.className.value || "" == frm.className.value ) {
            frm.url.readOnly = false;
            frm.url.value = '';
            frm.className.value = '';
            document.getElementById('pathLbl').className = 'required';
            document.getElementById('contentLbl').innerHTML = '* Contenido';
        } else {
            frm.relatedCode.disabled = false;
            frm.relatedName.disabled = false;
            frm.relatedButtom.disabled = false;
            
            document.getElementById('contentLbl').className = 'required';      
        }
    
	}
	
	function clean(frm, objTargetName, objLabelName, txtDesc){
		if (typeof txtDesc=='undefined')
			txtDesc="";
		frm.elements[objTargetName].value = "";
		frm.elements[objLabelName].value = txtDesc;
	}
	
	function openSearchWindow(pUrl,objForm) {	

		var obj = objForm.elements['className'];	
		var newUrsl = pUrl + obj.value;
		//alert(newUrsl);
		openCenterWindow(newUrsl, 'searchContent', 650, 500, 'yes');
	}
	
	function initPage() {
		frm = document.forms[0];	
		
		frm.url.readOnly = true;
		frm.relatedCode.disabled = true;
		frm.relatedName.disabled = true;
		frm.relatedButtom.disabled = true;
		document.getElementById('pathLbl').className = 'optional';
		document.getElementById('contentLbl').className = 'optional';
		
		frm.relatedCode.disabled = false;
		frm.relatedName.disabled = false;
		frm.relatedButtom.disabled = false;
		
		document.getElementById('contentLbl').className = 'required';
					
		<c:forEach var="item" items="${urlList}" >
			if(frm.accessUrlCode.value == ${item.code}) { 
				document.getElementById('contentLbl').innerHTML = '* ${item.name}';
				frm.url.value = '${item.path}';
                frm.className.value = "${item.className}";
			}
		</c:forEach>

		if(null == frm.className.value || "" == frm.className.value ) {
            frm.url.readOnly = false;
            frm.url.value = '';
            frm.className.value = '';
            document.getElementById('pathLbl').className = 'required';
            document.getElementById('contentLbl').innerHTML = '* Contenido';
        } else {
            frm.relatedCode.disabled = false;
            frm.relatedName.disabled = false;
            frm.relatedButtom.disabled = false;
            
            document.getElementById('contentLbl').className = 'required';      
        }
	}
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<bean:message key="portal.access" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="portal.access" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> &gt;
			${systemMenuLabel}
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
<html:form action="/system/portal/access/save" focus="name" onsubmit="return preValidate(this)">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="className" />
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
				<c:when test="${!empty accessForm.code && accessForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${accessForm.name}" />
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
	<%--<tr>
		<td class="optional"><bean:message key="prompt.description" /> :</td>
		<td><html:textarea property="description" style="width: 450px; height: 80px;" /></td>
	</tr>--%>
	<%-- tr>
		<td class="required">* <bean:message key="prompt.type" /> :</td>
		<td>
			<html:select property="accessTypeCode" size="1" styleClass="input" style="width: 250px;">
				<html:options collection="typeList" property="code" labelProperty="name"/>
			</html:select>
		</td>
	</tr --%>
	<tr>
		<td class="required">* <bean:message key="prompt.image" /> :</td>
		<td><html:text property="path" styleClass="input" style="width: 200px;" readonly="true" />
			<a href="javascript: openUploadWindow('/popup/openUpload.do', '/images/uploaded/access', 'accessForm', 'path','access','${initParameters.SEQ_ACCESS}',0)" >
				<img src="${pageContext.request.contextPath}/images/system/icons/upload.gif" border="0" style="vertical-align: middle">
			</a>
			<a href="javascript:openPreview('/popup/openPreview.do', accessForm.path.value)"  onMouseOver="MM_swapImage('previewImg','','${pageContext.request.contextPath}/images/icons/preview_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="previewImg" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />">
			</a>
			<a href="javascript: clearControls(0, 'path');" onMouseOver="MM_swapImage('cleanImg','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="cleanImg" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
			</a>
			<%--<a href="javascript: openPreviewMediaWindow('accessImageCode', 0, ${initParameters.MEDIA_TYPE_BANNER})" >
				<img src="${pageContext.request.contextPath}/images/system/icons/preview.gif" border="0">
			</a>--%>
			<%--<a href="javascript:openPreview('/popup/openPreview.do', clientForm.logo.value)"><img src="<%= request.getContextPath() %>/images/icons/find.gif" width="16" height="16" border="0" align="absmiddle"></a>--%>
		</td>
	</tr>
	<%--tr>
		<td class="required">* <bean:message key="prompt.width" />:</td>
        <td ><html:text property="width" maxlength="4" style="width: 50px;"/></td>
    </tr>
	<tr>
		<td class="required">* <bean:message key="prompt.height" />:</td>
        <td ><html:text property="height" maxlength="4" style="width: 50px;"/></td>
    </tr --%>
	<tr>
		<td class="required">* <bean:message key="prompt.relation" /> :</td>
		<td>
			<html:select property="accessUrlCode" size="1" styleClass="input" style="width: 250px;" onchange="enableFields()">
				<html:options collection="urlList" property="code" labelProperty="name"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="required" id="pathLbl">* <bean:message key="prompt.url" /> :</td>
		<td><html:text property="url" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required" id="contentLbl" width="160">* <bean:message key="prompt.content" /> :</td>
		<td>
			<html:hidden property="relatedCode" styleClass="input" style="width: 20px;" />
			<html:text property="relatedName" styleClass="input" style="width: 200px;" readonly="true" />
			<c:url value="/system/portal/generalSearch.do" var="url">
				<c:param name="formIndex" value="0" />
				<c:param name="controlCode" value="relatedCode" />
				<c:param name="controlDescription" value="relatedName" />
				<c:param name="className" value="" />
			</c:url>
			<input type="button" name="relatedButtom" value="..." onclick="openSearchWindow('${url}', document.forms[0])" />
			<input type="button" value="Vaciar" onclick="clean(this.form, 'relatedCode', 'relatedName');" />
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
<html:javascript formName="accessForm" dynamicJavascript="true" staticJavascript="false" />

<script language="JavaScript1.2">
	function preValidate (frm) { 
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty accessForm.code && accessForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un nuevo ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
	  	  document.accessForm.url.disabled = false;
		  return validateAccessForm(frm);
	  } else { return false; }
	}
	
	initPage();
	
</script>
