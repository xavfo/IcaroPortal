<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<bean:message key="cart.establishment" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="cart.establishment" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="cart" bundle="systemMenu" /> &lt;
			${systemMenuLabel}
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>

<html:form action="/system/catalogue/establishment/save" focus="name" onsubmit="return preValidate(this)">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
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
		<div class="error"><html:errors/></div>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty establishmentForm.code && establishmentForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${establishmentForm.name}" />
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
		<td class="optional"><bean:message key="prompt.image" /> :</td>
		<td>
			<html:text property="image" style="width: 200px;" readonly="true"/>
			<a href="javascript: openUploadWindow('/popup/openUpload.do', '/images/uploaded/establishment', 'establishmentForm', 'image','establishment_image','${initParameters.SEQ_ESTABLISHMENT_IMAGE}',0)";"  onMouseOver="MM_swapImage('uploadImg','','${pageContext.request.contextPath}/images/icons/upload_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="uploadImg" src="${pageContext.request.contextPath}/images/icons/upload.gif" width="20" height="20" border="0" alt="<bean:message key="button.upload" bundle="messages" />">
			</a>
			<a href="javascript:openPreview('/popup/openPreview.do', establishmentForm.image.value)"  onMouseOver="MM_swapImage('previewImg','','${pageContext.request.contextPath}/images/icons/preview_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="previewImg" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />">
			</a>
			<a href="javascript: clearControls(0, 'image');" onMouseOver="MM_swapImage('cleanImg','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="cleanImg" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
			</a>
		</td>
	</tr>
	
	<tr>
		<td class="required">* <bean:message key="prompt.category" /> :</td>
		<td>
		<span id="cityList" class="normal" >
	<html:select property="categoryCode" size="1" styleClass="input" style="width: 250px;"  >
			<html:option value="">
				<bean:message key="option.none" bundle="messages" />
			</html:option>
		<c:if test="${!empty establishmentCategoryList}">
			<html:options collection="establishmentCategoryList" property="code" labelProperty="name"/>
		</c:if>
	</html:select>
		</span>
		
										</td>
									</tr>
	
	
		<tr>
		<td class="optional" valign="top"><bean:message key="prompt.detail" /> :</td>
		<td>
			
			<html:hidden property="description" styleId="text" />
			<input type="hidden" id="text___Config" value="SkinPath=${pageContext.request.contextPath}/js/fckeditor/editor/skins/silver/&DefaultLanguage=en&AutoDetectLanguage=true">
			<iframe name="text___Frame" id="text___Frame" src="${pageContext.request.contextPath}/js/fckeditor/editor/fckeditor.html?InstanceName=text&Toolbar=Custom" width="100%" height="300" frameborder=0 scrolling="no"></iframe>	
			<%--
			<html:textarea property="text" styleId="leadinText" style="width: 350px; height: 80px;" />	
			--%>
		</td>
	</tr>	
	
		<tr>
		<td class="required">* <bean:message key="prompt.city" />:</td>
		<td>
		<span id="cityList" class="normal" >
		<html:select property="cityCode" size="1" styleClass="input" style="width: 250px;"  >
			<html:option value="">
				<bean:message key="option.none" bundle="messages" />
			</html:option>
		<c:if test="${!empty cityList}">
			<html:options collection="cityList" property="code" labelProperty="name"/>
		</c:if>
		</html:select>
		</span>
		</td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.address" /> :</td>
		<td><html:textarea property="address" styleClass="input" cols="40" rows="4" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.phone1" /> :</td>
		<td><html:text property="phone1" styleClass="input" style="width: 100px;" /></td>
	</tr>	
	<tr>
		<td class="optional"><bean:message key="prompt.phone2" /> :</td>
		<td><html:text property="phone2" styleClass="input" style="width: 100px;" /></td>
	</tr>	
	<tr>
		<td class="optional"><bean:message key="prompt.fax" /> :</td>
		<td><html:text property="fax" styleClass="input" style="width: 100px;" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.email" /> :</td>
		<td><html:text property="email" styleClass="input" style="width: 100px;" /></td>
	</tr>	
	<tr>
		<td class="optional"><bean:message key="prompt.scheduleBussines" /> :</td>
		<td><html:textarea property="schedule" styleClass="input" cols="40" rows="4" /></td>
	</tr>
	<tr>
		<td class="optional" nowrap valign="top"> <bean:message key="prompt.contactName" /> :</td>
		<td><html:text property="contactName" styleClass="input" style="width: 350px;" /></td>
	</tr>
	<tr>
		<td class="optional" nowrap valign="top"> <bean:message key="prompt.contactPosition" /> :</td>
		<td><html:text property="contactPosition" styleClass="input" style="width: 350px;" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.lastContact" /> :</td>
		<td>
		<html:text property="lastContact" styleId="lastContact" style="width: 100px;" readonly="true"/> 
		<input type="button" name="btn" id="btn" value="..." style="width: 20px;">
		</td>
	</tr>	
	<tr>
		<td class="required"><bean:message key="prompt.enabled" /> :</td>
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
<html:javascript formName="establishmentForm" dynamicJavascript="true" staticJavascript="false" />

<script language="JavaScript1.2">
	function preValidate (frm) { 
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty establishmentForm.code && establishmentForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un nuevo ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateEstablishmentForm(frm);
	  } else { return false; }
	}
</script>
<script language="JavaScript1.2">
	Calendar.setup(
		{
			inputField  	: "lastContact",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "btn"
		}	
	);
	
</script>
