<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<bean:message key="news.news" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="news.news" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
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
		</td>
	</tr>
</table>

<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">
<table>
	<tr>
		<td class="subtitle">
			<c:if test="${!empty newsForm.code && newsForm.code != 0 && !empty newsForm.title}">
				${newsForm.title}
			</c:if>
		</td>	
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.news">
			<tiles:put name="code" value="${newsForm.code}" />
		</tiles:insert>
	</td>
</tr>
<tr>
	<td>
		<html:form action="/system/news/news/save" focus="title" onsubmit="return preValidate(this);">
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
										<td class="required">* <bean:message key="prompt.title" /> :</td>
										<td><html:text property="title" styleClass="input" style="width: 630px;" /></td>
									</tr>			
									<tr>
										<td class="required">* <bean:message key="prompt.leadinText" /> :</td>
										<td>
											<html:textarea property="leadingText" styleId="leadinText" style="width: 630px; height: 80px;" />	
										</td>
									</tr>	
									<tr>
										<td class="required">* <bean:message key="prompt.detail" /> :</td>
										<td>
											
											<html:hidden property="text" styleId="text" />
											<input type="hidden" id="text___Config" value="SkinPath=${pageContext.request.contextPath}/js/fckeditor/editor/skins/silver/&DefaultLanguage=en&AutoDetectLanguage=true">
											<iframe name="text___Frame" id="text___Frame" src="${pageContext.request.contextPath}/js/fckeditor/editor/fckeditor.html?InstanceName=text&Toolbar=Custom" width="100%" height="300" frameborder="no" scrolling="no"></iframe>	
											<%--
											<html:textarea property="text" styleId="leadinText" style="width: 350px; height: 80px;" />	
											--%>
										</td>
									</tr>					
									<tr>
										<td class="required">* <bean:message key="prompt.category" /> :</td>
										<td>
											<html:select property="categoryCode" size="1" styleClass="input" style="width: 350px;">
												<html:options collection="newsCategoryList" property="code" labelProperty="name"/>
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="optional"> <bean:message key="prompt.publishPeriod" /> :</td>
										<td>
											<table>
												<tr>
													<td class="optional"><bean:message key="prompt.from" /> :</td>
													<td><html:text property="fromDate" styleId="fromDate" style="width: 100px;" readonly="true"/> <input type="button" name="fromBtn" id="fromBtn" value="..." style="width: 20px;"></td>
													<td class="optional"><bean:message key="prompt.to" /> :</td>
													<td><html:text property="toDate" styleId="toDate" style="width: 100px;"readonly="true" /> <input type="button" name="toBtn" id="toBtn" value="..." style="width: 20px;"></td>
												</tr>	
											</table>
										</td>
									</tr>
									
									<tr>
										<td class="required">* <bean:message key="prompt.dateNews" /> :</td>
										<td><html:text property="creationDate" styleId="creationDate" style="width: 100px;" readonly="true"/> <input type="button" name="btn" id="btn" value="..." style="width: 20px;"></td>
									</tr>		
									<tr>
										<td class="optional"><bean:message key="prompt.introImage" /> :</td>
										<td>
											<html:hidden property="introImageCode" style="width: 250px;" />
											<html:text property="introImageName" style="width: 250px;" readonly="true"/>
											<a href="javascript: openUploadMediaWindow('/popup/openUploadMediaFile.do', '/noticias', 'newsForm', 0, ${initParameters.MEDIA_TYPE_IMAGE},
																			  'introImageCode', 'introImageName');"  onMouseOver="MM_swapImage('uploadImg','','${pageContext.request.contextPath}/images/icons/upload_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="uploadImg" src="${pageContext.request.contextPath}/images/icons/upload.gif" width="20" height="20" border="0" alt="<bean:message key="button.upload" bundle="messages" />">
											</a>
											<a href="javascript: openPreviewMediaWindow('introImageCode', 0, ${initParameters.MEDIA_TYPE_IMAGE})"  onMouseOver="MM_swapImage('previewImg','','${pageContext.request.contextPath}/images/icons/preview_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="previewImg" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />">
											</a>
											<a href="javascript: clearControls(0, 'introImageCode|introImageName');" onMouseOver="MM_swapImage('cleanImg','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="cleanImg" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
											</a>
										</td>
									</tr>	
									<tr>
										<td class="optional"><bean:message key="prompt.mainImage" /> :</td>
										<td>
											<html:hidden property="mainImageCode" style="width: 250px;" />
											<html:text property="mainImageName" style="width: 250px;" readonly="true"/>
											<a href="javascript: openUploadMediaWindow('/popup/openUploadMediaFile.do', '/noticias', 'newsForm', 0, ${initParameters.MEDIA_TYPE_IMAGE},
																			  'mainImageCode', 'mainImageName');"  onMouseOver="MM_swapImage('uploadImg2','','${pageContext.request.contextPath}/images/icons/upload_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="uploadImg2" src="${pageContext.request.contextPath}/images/icons/upload.gif" width="20" height="20" border="0" alt="<bean:message key="button.upload" bundle="messages" />">
											</a>
											<a href="javascript: openPreviewMediaWindow('mainImageCode', 0, ${initParameters.MEDIA_TYPE_IMAGE})"  onMouseOver="MM_swapImage('previewImg2','','${pageContext.request.contextPath}/images/icons/preview_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="previewImg2" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />">
											</a>
											<a href="javascript: clearControls(0, 'mainImageCode|mainImageName');" onMouseOver="MM_swapImage('cleanImg2','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="cleanImg2" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
											</a>
										</td>
									</tr>
									<html:hidden property="isLocalInfo" value="true" />																
									<%--tr>
										<td class="optional"><bean:message key="prompt.type" /> :</td>
										<td>
											<html:radio property="isLocalInfo" value="true" /> <bean:message key="label.internal" bundle="messages" />
											<html:radio property="isLocalInfo" value="false" /> <bean:message key="label.external" bundle="messages" />
										</td>										
									</tr--%>	
									<tr>
										<td class="optional"><bean:message key="prompt.enabled" /> :</td>
										<td>
											<html:radio property="isEnabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
											<html:radio property="isEnabled" value="false" /> <bean:message key="label.no" bundle="messages" />
										</td>										
									</tr>	
									<tr>
										<td class="optional"><bean:message key="prompt.publishHome" /> :</td>
										<td>
											<html:radio property="publishHome" value="true" /> <bean:message key="label.yes" bundle="messages" />
											<html:radio property="publishHome" value="false" /> <bean:message key="label.no" bundle="messages" />
										</td>										
									</tr>	
									<html:hidden property="index" value="1" />
									<%--tr>
										<td class="required">* <bean:message key="prompt.orderIndex" /> :</td>
										<td><html:text property="index" style="width: 250px;" /></td>
									</tr--%>							
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
		<html:javascript formName="newsForm" dynamicJavascript="true" staticJavascript="false" />
		<script language="JavaScript1.2">
			function preValidate (frm) { 
		    	if (bCancel) { 
		      		return true; 
				}
			  <c:choose>
			  	<c:when test="${!empty newsForm.code && newsForm.code != 0}">
					var msg = 'Se actualizar\u00E1 el ${systemMenuLabel}. ¿Desea continuar?';
				</c:when>
				<c:otherwise>
					var msg = 'Est\u00E1 a punto de crear un nuevo ${systemMenuLabel}.  ¿Desea continuar?';
				</c:otherwise>
			  </c:choose>
			  if( confirm (msg)){
				  return validateNewsForm(frm);
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
			inputField  	: "fromDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "fromBtn"
		}
	);
	Calendar.setup(
		{
			inputField  	: "toDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "toBtn"
		}
	);
	Calendar.setup(
		{
			inputField  	: "creationDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "btn"
		}
	);
	
</script>
