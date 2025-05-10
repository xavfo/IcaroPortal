<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<script language="javascript">
function clean(frm, objTargetName, objLabelName, txtDesc){
	if (typeof txtDesc=='undefined')
		txtDesc="";
	frm.elements[objTargetName].value = "";
	frm.elements[objLabelName].value = txtDesc;
}

function newImage(obj){	
	var prefix = 'product-' + document.forms[0].sellerCode.value;
	openUploadWindow('/popup/openUpload.do', '/images/uploaded/product', 'productForm', obj, prefix,'${initParameters.SEQ_PRODUCT}',0)
}
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<bean:message key="cart.product" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="cart.product" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="cart" bundle="systemMenu" /> &gt;
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

<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">
<table>
	<tr>
		<td class="subtitle">
			<c:if test="${!empty productForm.code && productForm.code != 0 && !empty productForm.name}">
				${productForm.name}
			</c:if>
		</td>	
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.product">
			<tiles:put name="code" value="${productForm.code}" />
		</tiles:insert>
	</td>
</tr>
<tr>
	<td>
		<html:form action="/system/catalogue/product/save" focus="name" onsubmit="return preValidate(this);">
		<html:hidden property="level" value="1" />
		<html:hidden property="action" value="save" />
		<html:hidden property="tab" value ="${param.tab}" />
		<html:hidden property="code" />
		<table class="tabForm" border="0" cellpadding="0" cellspacing="4" width="100%">		
		<tr>
			<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0"></td>
		</tr>
		<tr>
		<td>		
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
			            <table cellpadding="0" cellspacing="0" border="0" width="100%" >
						<tr>
						<td valign="top">
						<!--inicio seccion general information-->
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
						<table cellpadding="0" cellspacing="0" align="center"  width="100%">
							<tr>
								<td>
								  <table cellpadding="0" cellspacing="4" border="0">
								  	<tr>
										<td class="required">* <bean:message key="prompt.name" /> :</td>
										<td><html:text property="name" styleClass="input" style="width: 350px;" /></td>
									</tr>	
									<tr>
										<td class="optional"><bean:message key="prompt.externalCode" /> :</td>
										<td><html:text property="externalCode" styleClass="input" style="width: 100px;" /></td>
									</tr>	
									<tr>
										<td class="required">* <bean:message key="prompt.brand" /> :</td>
										<td>
											<html:select property="brandCode" size="1" styleClass="input" style="width: 350px;">
												<html:options collection="brandList" property="code" labelProperty="name"/>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="required">* <bean:message key="prompt.category" /> :</td>
										<td>
											<html:hidden property="categoryCode" styleClass="input" style="width: 20px;" />
											<html:text property="categoryName" styleClass="input" style="width: 265px;" readonly="true" />
											<input type="button" value="..." onclick="openSearch('/popup/searchCategory.do', 'categoryForm', new Array('categoryCode'),new Array('categoryName'), 0)" />
											<input type="button" value="Vaciar" onclick="clean(this.form, 'categoryCode', 'categoryName');" />
										</td>
									</tr>
									<c:choose>
									<c:when test="${initParameters.SYS_ROLE_SELLER == sysUser.role.code }">
										<html:hidden property="sellerCode" value="${sysUser.seller.code}" />
									</c:when>
									<c:otherwise>
									<tr>
										<td class="required">* <bean:message key="prompt.seller" /> :</td>
										<td>
											<html:select property="sellerCode" size="1" styleClass="input" style="width: 350px;">
												<html:options collection="sellerList" property="code" labelProperty="name"/>
											</html:select>
										</td>
									</tr>
									</c:otherwise>
									</c:choose>
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
										<td class="required">* <bean:message key="prompt.showRoom" /> :</td>
										<td>
											<html:radio property="showRoom" value="true" /> <bean:message key="label.yes" bundle="messages" />
											<html:radio property="showRoom" value="false" /> <bean:message key="label.no" bundle="messages" />
										</td>										
									</tr>	
									<tr>
										<td class="required">* <bean:message key="prompt.onSale" /> :</td>
										<td>
											<html:radio property="onSale" value="true" /> <bean:message key="label.yes" bundle="messages" />
											<html:radio property="onSale" value="false" /> <bean:message key="label.no" bundle="messages" />
										</td>										
									</tr>	
									<tr>
										<td class="required">* <bean:message key="prompt.onClearance" /> :</td>
										<td>
											<html:radio property="onClearance" value="true" /> <bean:message key="label.yes" bundle="messages" />
											<html:radio property="onClearance" value="false" /> <bean:message key="label.no" bundle="messages" />
										</td>										
									</tr>	
									<tr>
										<td class="optional"> <bean:message key="prompt.clearancePeriod" /> :</td>
										<td>
											<table>
												<tr>
													<td class="optional"><bean:message key="prompt.clearanceSince" /> :</td>
													<td><html:text property="clearanceSinceDate" styleId="clearanceSinceDate" style="width: 100px;" readonly="true"/> <input type="button" name="fromBtn" id="fromBtn" value="..." style="width: 20px;"></td>
													<td class="optional"><bean:message key="prompt.clearanceUntil" /> :</td>
													<td><html:text property="clearanceUntilDate" styleId="clearanceUntilDate" style="width: 100px;"readonly="true" /> <input type="button" name="toBtn" id="toBtn" value="..." style="width: 20px;"></td>
												</tr>	
											</table>
										</td>
									</tr>
									<tr>
										<td class="required">* <bean:message key="prompt.taxType" /> :</td>
										<td>
											<html:select property="taxTypeCode" size="1" styleClass="input" style="width: 350px;">
												<html:options collection="taxTypeList" property="code" labelProperty="name"/>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="optional"><bean:message key="prompt.features" /> :</td>
										<td>
											<html:textarea property="features" styleId="input" style="width: 630px; height: 80px;" />	
										</td>
									</tr>	
									<tr>
										<td class="optional"><bean:message key="prompt.description" /> :</td>
										<td>											
											<html:hidden property="description" styleId="text" />
											<input type="hidden" id="text___Config" value="SkinPath=${pageContext.request.contextPath}/js/fckeditor/editor/skins/silver/&DefaultLanguage=en&AutoDetectLanguage=true">
											<iframe name="text___Frame" id="text___Frame" src="${pageContext.request.contextPath}/js/fckeditor/editor/fckeditor.html?InstanceName=text&Toolbar=Custom" width="100%" height="300" frameborder="no" scrolling="no"></iframe>	
											<%--
											<html:detailarea property="detail" styleId="leadindetail" style="width: 350px; height: 80px;" />	
											--%>
										</td>
									</tr>	
									<tr>
										<td class="optional"><bean:message key="prompt.slogan" /> :</td>
										<td>
											<html:textarea property="slogan" styleId="input" style="width: 630px; height: 80px;" />	
										</td>
									</tr>	
									<tr>
										<td class="required">* <bean:message key="prompt.regularPrice" /> :</td>
										<td><html:text property="regularPrice" styleClass="input" style="width: 100px;" /></td>
									</tr>	
									<tr>
										<td class="optional"><bean:message key="prompt.salePrice" /> :</td>
										<td><html:text property="salePrice" styleClass="input" style="width: 100px;" /></td>
									</tr>	
									<tr>
										<td class="optional"><bean:message key="prompt.clearancePrice" /> :</td>
										<td><html:text property="clearancePrice" styleClass="input" style="width: 100px;" /></td>
									</tr>	
									<%--tr>
										<td class="optional"><bean:message key="prompt.quotaPrice" /> :</td>
										<td><html:text property="quotaPrice" styleClass="input" style="width: 100px;" /></td>
									</tr>													
									<tr>
										<td class="optional"><bean:message key="prompt.numberOfQuotas" /> :</td>
										<td><html:text property="numberOfQuotas" styleClass="input" style="width: 100px;" /></td>
									</tr --%>							
									<tr>
										<td class="required">* <bean:message key="prompt.stock" /> :</td>
										<td><html:text property="stock" styleClass="input" style="width: 100px;" onchange="javascript:this.form.maxStock.value=this.value;" /></td>
									</tr>	
									<tr>
										<td class="required">* <bean:message key="prompt.stockMin" /> :</td>
										<td><html:text property="minStock" styleClass="input" style="width: 100px;" /></td>
									</tr>
									<tr>
										<td class="optional"><bean:message key="prompt.stockMax" /> :</td>
										<td><html:text property="maxStock" styleClass="input" style="width: 100px;" /></td>
									</tr>	
									<tr>
										<td class="optional"><bean:message key="prompt.modelNumber" /> :</td>
										<td><html:text property="modelNumber" styleClass="input" style="width: 100px;" /></td>
									</tr>	
									<tr>
										<td class="required">* <bean:message key="prompt.shippingWeight" /> :</td>
										<td>
											<html:text property="shippingWeight" styleClass="input" style="width: 100px;" maxlength="12"/>
											<br /><span class="tiny"><bean:message key="dialog.product.weight" bundle="systemHelp" /></span>	
										</td>
									</tr>	
									<tr>
										<td class="required">* <bean:message key="prompt.shippingWidth" /> :</td>
										<td>
											<html:text property="shippingWidth" styleClass="input" style="width: 100px;" maxlength="12" />
											<br /><span class="tiny"><bean:message key="dialog.product.dimensions" bundle="systemHelp" /></span>	
										</td>
									</tr>
									<tr>
										<td class="required">* <bean:message key="prompt.shippingHeight" /> :</td>
										<td>
											<html:text property="shippingHeight" styleClass="input" style="width: 100px;" maxlength="12" />
											<br /><span class="tiny"><bean:message key="dialog.product.dimensions" bundle="systemHelp" /></span>	
										</td>
									</tr>
									<tr>
										<td class="required">* <bean:message key="prompt.shippingDepth" /> :</td>
										<td>
											<html:text property="shippingDepth" styleClass="input" style="width: 100px;" maxlength="12" />
											<br /><span class="tiny"><bean:message key="dialog.product.dimensions" bundle="systemHelp" /></span>	
										</td>
									</tr>
									<tr>
										<td class="optional"><bean:message key="prompt.image" /> (1) :</td>
										<td>
											<html:text property="image1" style="width: 250px;" readonly="true"/>
											<a href="javascript: newImage('image1')"  onMouseOver="MM_swapImage('uploadImg','','${pageContext.request.contextPath}/images/icons/upload_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="uploadImg" src="${pageContext.request.contextPath}/images/icons/upload.gif" width="20" height="20" border="0" alt="<bean:message key="button.upload" bundle="messages" />">
											</a>
											<a href="javascript:openPreview('/popup/openPreview.do', productForm.image1.value)"  onMouseOver="MM_swapImage('previewImg','','${pageContext.request.contextPath}/images/icons/preview_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="previewImg" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />">
											</a>
											<a href="javascript: clearControls(0, 'image1');" onMouseOver="MM_swapImage('cleanImg','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="cleanImg" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
											</a>
										</td>
									</tr>	
									<tr>
										<td class="optional"><bean:message key="prompt.image" /> (2) :</td>
										<td>
											<html:text property="image2" style="width: 250px;" readonly="true"/>
											<a href="javascript: newImage('image2')"  onMouseOver="MM_swapImage('uploadImg','','${pageContext.request.contextPath}/images/icons/upload_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="uploadImg" src="${pageContext.request.contextPath}/images/icons/upload.gif" width="20" height="20" border="0" alt="<bean:message key="button.upload" bundle="messages" />">
											</a>
											<a href="javascript:openPreview('/popup/openPreview.do', productForm.image2.value)"  onMouseOver="MM_swapImage('previewImg','','${pageContext.request.contextPath}/images/icons/preview_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="previewImg" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />">
											</a>
											<a href="javascript: clearControls(0, 'image2');" onMouseOver="MM_swapImage('cleanImg','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="cleanImg" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
											</a>
										</td>
									</tr>	
									<tr>
										<td class="optional"><bean:message key="prompt.image" /> (3) :</td>
										<td>
											<html:text property="image3" style="width: 250px;" readonly="true"/>
											<a href="javascript: newImage('image3')"  onMouseOver="MM_swapImage('uploadImg','','${pageContext.request.contextPath}/images/icons/upload_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="uploadImg" src="${pageContext.request.contextPath}/images/icons/upload.gif" width="20" height="20" border="0" alt="<bean:message key="button.upload" bundle="messages" />">
											</a>
											<a href="javascript:openPreview('/popup/openPreview.do', productForm.image3.value)"  onMouseOver="MM_swapImage('previewImg','','${pageContext.request.contextPath}/images/icons/preview_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="previewImg" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />">
											</a>
											<a href="javascript: clearControls(0, 'image3');" onMouseOver="MM_swapImage('cleanImg','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="cleanImg" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
											</a>
										</td>
									</tr>				
									<tr>
										<td height="30" width="170"></td>
										<td><bean:message key="message.required" bundle="systemHelp" /></td>
									</tr>
								  </table>															
								</td>
							</tr>
						</table>
						</td>			            
						</tr>						
						</table>                           										
					</td>
				</tr>
				<tr>
					<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
				</tr>
			</table>	
		</td>
		</tr>
		</table>
		</html:form>
		<html:javascript formName="productForm" dynamicJavascript="true" staticJavascript="false" />
		<script language="JavaScript1.2">
			function preValidate (frm) { 
		    	if (bCancel) { 
		      		return true; 
				}
			  <c:choose>
			  	<c:when test="${!empty productForm.code && productForm.code != 0}">
					var msg = 'Se actualizar\u00E1 el ${systemMenuLabel}. ¿Desea continuar?';
				</c:when>
				<c:otherwise>
					var msg = 'Est\u00E1 a punto de crear un nuevo ${systemMenuLabel}.  ¿Desea continuar?';
				</c:otherwise>
			  </c:choose>
			  if( confirm (msg)){
				  return validateProductForm(frm);
			  } else { return false; }
			}
		</script>
	</td>
</tr>
<tr>
	<td background="${pageContext.request.contextPath}/images/tables/file_bottom.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="7" border="0" alt=""></td>
</tr>
<tr>
	<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
</tr>
</table>
<script language="JavaScript1.2">
	Calendar.setup(
		{
			inputField  	: "clearanceSinceDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "fromBtn"
		}
	);
	Calendar.setup(
		{
			inputField  	: "clearanceUntilDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "toBtn"
		}
	);
	
</script>

