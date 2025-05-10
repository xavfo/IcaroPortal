<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<script language="JavaScript">
<!--
	<c:url value="/system/catalogue/category.do" var="urlList">
   		<c:param name="action" value="list" />
		<c:param name="listItems" value="true" />
	</c:url>
	function refreshTree() {
		if ( document.all )
			window.top.document.all.itree.src = '${urlList}';
		else
			window.top.document.getElementById("itree").src = '${urlList}';
	}
//-->
</script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty productCategoryForm.code && productCategoryForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${productCategoryForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
			<div class="error"><html:errors/></div>		

		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<html:form action="/system/catalogue/category/save" focus="name"  onsubmit="return preValidate(this)">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="codes" value="${productCategoryForm.code}" />
<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td height="30" width="120"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" style="width: 250px;"maxlength="200" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.parent" /> :</td>
		<td>
			<html:select property="parentCode" size="1" styleClass="input" style="width: 250px;">
				<html:option value="0"><bean:message key="option.root" bundle="messages" /></html:option>
				<html:options collection="categoryParentList" property="code" labelProperty="name"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.group" /> :</td>
		<td>
			<html:radio property="group" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="group" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.enabled" /> :</td>
		<td>
			<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />		
		</td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.featured" /> :</td>
		<td>
			<html:radio property="featured" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="featured" value="false" /> <bean:message key="label.no" bundle="messages" />		
		</td>
	</tr>
	<tr>
		<td class="required" width="160">* <bean:message key="prompt.orderIndex" /> :</td>
		<td>
			<html:text property="orderIndex" style="width: 50px;" />
		</td>
	</tr>
	<tr>
		<td class="optional" valign="top"><bean:message key="prompt.detail" /> :</td>
		<td>
			
			<html:hidden property="description" styleId="text" />
			<input type="hidden" id="text___Config" value="SkinPath=${pageContext.request.contextPath}/js/fckeditor/editor/skins/silver/&DefaultLanguage=en&AutoDetectLanguage=true">
			<iframe name="text___Frame" id="text___Frame" src="${pageContext.request.contextPath}/js/fckeditor/editor/fckeditor.html?InstanceName=text&Toolbar=Custom" width="100%" height="300" frameborder="no" scrolling="no"></iframe>	
			<%--
			<html:textarea property="text" styleId="leadinText" style="width: 350px; height: 80px;" />	
			--%>
		</td>
	</tr>					
	<tr>
		<td class="optional"><bean:message key="prompt.image" /> :</td>
		<td>
			<html:text property="image" style="width: 200px;" readonly="true"/>
			<a href="javascript: openUploadWindow('/popup/openUpload.do', '/images/uploaded/category', 'productCategoryForm', 'image','category_image','${initParameters.SEQ_CATEGORY}',0)";"  onMouseOver="MM_swapImage('uploadImg','','${pageContext.request.contextPath}/images/icons/upload_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="uploadImg" src="${pageContext.request.contextPath}/images/icons/upload.gif" width="20" height="20" border="0" alt="<bean:message key="button.upload" bundle="messages" />">
			</a>
			<a href="javascript:openPreview('/popup/openPreview.do', productCategoryForm.image.value)"  onMouseOver="MM_swapImage('previewImg','','${pageContext.request.contextPath}/images/icons/preview_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="previewImg" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />">
			</a>
			<a href="javascript: clearControls(0, 'image');" onMouseOver="MM_swapImage('cleanImg','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="cleanImg" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
			</a>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><hr width="100%" size="1"></td>
	</tr>
	<tr>
		<td></td>
		<td height="50">
			<html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.ok" bundle="messages" /></html:submit>
			<html:cancel styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.cancel" bundle="messages" /></html:cancel>
			<html:reset styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.reset" bundle="messages" /></html:reset>
			<html:button property="button" styleClass="button" style="width: 80px;" onclick="doAction(this.form, 'action', 'delete')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.delete" bundle="messages" /></html:button>
		</td>
	</tr>
</table>
</html:form>
<html:javascript formName="productCategoryForm" dynamicJavascript="true" staticJavascript="false" />

<c:set var="systemMenuLabel" ><bean:message key="cart.category" bundle="systemMenu" /></c:set>
<script language="JavaScript1.2">
<!--
function preValidate (frm) { 
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty productCategoryForm.code && productCategoryForm.code != 0}">
			var msg = 'Se actualizar\u00E1 la ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear una nueva ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateProductCategoryForm(frm);
	  } else { return false; }
	}

refreshTree();
//-->
</script>