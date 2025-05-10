<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/ajaxtags" prefix="ajax"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/prototype-1.4.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/controls.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/ajaxtags-1.2-beta1.js"></script>

<script language="javascript">
function resetProgress() {
	document.getElementById("layoutCode").options[0].text='<bean:message key="option.none" bundle="messages"/>';
	document.getElementById("layoutCode").options[0].value='';
	//document.getElementById("layoutCode").focus();
}



</script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="portal.section" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> &gt;
			<bean:message key="portal.section" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" style="color:#999999"  noshade="noshade" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.portal.section" bundle="systemHelp" /><br/>
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
			<div class="error"><html:errors/></div>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"/><br/>


<table border="0" cellpadding="0" cellspacing="0" align="center" width="100%">
	<tr>
		<td >
		    <!-- Container TABS -->
			<tiles:insert definition="tabs.system.content.1">
					<tiles:put name="code" value="${sectionForm.code}" />
			</tiles:insert>
			<!-- END Container TABS -->
		</td>
	</tr>
	<tr>
		<td>

			<html:form action="/system/portal/section/save" focus="title" onsubmit="return validateSectionForm(this);" >
			<html:hidden property="action" value="save" />
			<html:hidden property="code" />
			<html:hidden property="languageCode" value="${appLanguage.code}" />
			<html:hidden property="level" value="1" />
			<html:hidden property="group" />
			<html:hidden property="type" value="false" />
			<html:hidden property="main" value="true" />
			<html:hidden property="accessLevelCode" value="1" />
			<html:hidden property="tab" value="${param.tab}" />
			<table class="tabForm" border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td>
						<table border="0" cellpadding="4" cellspacing="0" width="100%">
							<tr>
								<td class="subtitle">
									<c:choose>
										<c:when test="${!empty sectionForm.code && sectionForm.code != 0}">
											<bean:message key="message.edit" bundle="systemHelp" arg0="${sectionForm.title}" />
										</c:when>
										<c:otherwise>
											<bean:message key="message.create" bundle="systemHelp" />
										</c:otherwise>
									</c:choose>
								</td>
								<td align="right" >
									<tiles:insert definition="system.navBar">
										<tiles:put name="save" 		value="true" />
										<tiles:put name="apply"		value="true" />
										<tiles:put name="reset" 	value="true" />
										<tiles:put name="cancel" 	value="true" />
									</tiles:insert>
								</td>
							</tr>
						</table>
							<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="100%">
								<tr>
									<td class="required">* <bean:message key="prompt.title" /> :</td>
									<td><html:text property="title" style="width: 250px;" /></td>
								</tr>
								<tr>
									<td class="required">* <bean:message key="prompt.keywords" /> :</td>
									<td><html:textarea property="keywords" style="width: 250px;" /></td>
								</tr>
								<tr>
									<td class="optional"><bean:message key="prompt.leadinText" /> :</td>
									<td>
										
										<html:hidden property="intro" styleId="intro" />
										<input type="hidden" id="intro___Config" value="SkinPath=${pageContext.request.contextPath}/js/fckeditor/editor/skins/silver/&DefaultLanguage=en&AutoDetectLanguage=true"/>
										<iframe name="intro___Frame" id="intro___Frame" src="${pageContext.request.contextPath}/js/fckeditor/editor/fckeditor.html?InstanceName=intro&Toolbar=Custom" width="100%" height="200" frameborder="0" scrolling="no"></iframe>		</td>
								</tr>
																<tr>
									<td class="optional"><bean:message key="prompt.description" /> :</td>
									<td>
										
										<html:hidden property="description" styleId="description" />
										<input type="hidden" id="description___Config" value="SkinPath=${pageContext.request.contextPath}/js/fckeditor/editor/skins/silver/&DefaultLanguage=en&AutoDetectLanguage=true"/>
										<iframe name="description___Frame" id="description___Frame" src="${pageContext.request.contextPath}/js/fckeditor/editor/fckeditor.html?InstanceName=description&Toolbar=Custom" width="100%" height="200" frameborder="0" scrolling="no"></iframe>		</td>
								</tr>
								<%--tr>
									<td class="optional"><bean:message key="prompt.text" /> :</td>
									<td>
										<html:hidden property="text" styleId="text" />
										<input type="hidden" id="text___Config" value="SkinPath=${pageContext.request.contextPath}/js/fckeditor/editor/skins/silver/&DefaultLanguage=en&AutoDetectLanguage=true"/>
										<iframe name="text___Frame" id="text___Frame" src="${pageContext.request.contextPath}/js/fckeditor/editor/fckeditor.html?InstanceName=text&Toolbar=Custom" width="100%" height="200" frameborder="0" scrolling="no"></iframe>		</td>
								</tr--%>
								<tr>
									<td class="required">* <bean:message key="prompt.orderIndex" /> :</td>
									<td><html:text property="order" style="width: 250px;" /></td>
								</tr>
								<%--tr>
									<td class="optional"><bean:message key="prompt.background" /> :</td>
									<td>
										
										<c:url value="/system/common/colorPicker.jsp" var="url">
											<c:param name="index" value="0" />
											<c:param name="control" value="background" />
										</c:url>
										<table border="0" cellspacing="0" cellpadding="4">
											<tr>
												<td><html:text property="background" style="width: 250px;" onchange="changeColor()" /></td>
												
												<td><a href="javascript:openCenterWindow('${url}','ColorPicker',350,160,'no');" >
													<img src="${pageContext.request.contextPath}/images/system/icons/upload.gif" border="0"/>
												</a></td>					
												<td><a href="javascript:clearControls(0, 'background');" >
													<img src="${pageContext.request.contextPath}/images/system/icons/clean.gif" border="0"/>
												</a></td>
												<td width="35" align="right">
													<table width="25" border="0" cellspacing="0" cellpadding="0">
														<tr><td id="backgroundColor">&nbsp;</td></tr>
													</table> 
												</td>
												<td>[<a href="javascript:changeColor()" >
													<bean:message key="button.refresh" bundle="messages" />
												</a>]</td>
											</tr>
										</table> 
									</td>
								</tr--%>
								<tr>
									<td class="optional"><bean:message key="prompt.image" /> :</td>
								  <td>
								  	<html:hidden property="imageCode" style="width: 20px;" />
									<html:text property="imageName" style="width: 250px;" readonly="true" />
										<a href="javascript:openUploadMediaWindow('/popup/openUploadMediaFile.do', '/content', 'sectionForm', 0, ${initParameters.MEDIA_TYPE_IMAGE},
																		  'imageCode', 'imageName');" >
											<img src="${pageContext.request.contextPath}/images/system/icons/upload.gif" border="0"/>
										</a>
										<a href="javascript:openPreviewMediaWindow('imageCode', 0, ${initParameters.MEDIA_TYPE_IMAGE})" >
											<img src="${pageContext.request.contextPath}/images/system/icons/preview.gif" border="0"/>
										</a>
										<a href="javascript:clearControls(0, 'imageCode|imageName');" >
											<img src="${pageContext.request.contextPath}/images/system/icons/clean.gif" border="0"/>
										</a>
									</td>
								</tr>
								<tr>
									<td class="optional"><bean:message key="prompt.accessImage" /> :</td>
									<td>
										<html:hidden property="accessImageCode" style="width: 250px;" />
										<html:text property="accessImageName" style="width: 250px;" readonly="true"/>
										<a href="javascript:openUploadMediaWindow('/popup/openUploadMediaFile.do', '/content', 'sectionForm', 0, ${initParameters.MEDIA_TYPE_IMAGE},
																		  'accessImageCode', 'accessImageName');" >
											<img src="${pageContext.request.contextPath}/images/system/icons/upload.gif" border="0"/>
										</a>
										<a href="javascript:openPreviewMediaWindow('accessImageCode', 0, ${initParameters.MEDIA_TYPE_IMAGE})" >
											<img src="${pageContext.request.contextPath}/images/system/icons/preview.gif" border="0"/>
										</a>
										<a href="javascript:clearControls(0, 'accessImageCode|accessImageName');" >
											<img src="${pageContext.request.contextPath}/images/system/icons/clean.gif" border="0"/>
										</a>
									</td>
								</tr>
								<%--tr>
									<td class="optional"><bean:message key="prompt.accessLink" /> :</td>
									<td><html:text property="link" style="width: 250px;" /></td>
								</tr--%>
								<%--<tr>
									<td class="required">* <bean:message key="prompt.showOpen" /> :</td>
									<td>
										<html:radio property="showOpen" value="true" /> <bean:message key="label.yes" bundle="messages" />
										<html:radio property="showOpen" value="false" /> <bean:message key="label.no" bundle="messages" />		</td>
								</tr>--%>
								<html:hidden property="showOpen" value="true" />
								<tr>
									<td class="required">* <bean:message key="prompt.enabled" /> :</td>
									<td>
										<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
										<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />		</td>
								</tr>
								<tr>
									<td colspan="2" class="subtitle"><bean:message key="label.menu.info" bundle="messages"/>	</td>
								</tr>
								<tr>
								  <td class="required">* <bean:message key="label.alias" bundle="messages" /> :</td>
								  <td><html:text property="menuAlias" style="width: 250px;" /></td>
							    </tr>
							<%--	<tr>
									<td class="required">* <bean:message key="prompt.module" /> :</td>
									<td>
										<html:select property="moduleCode" styleClass="input" style="width: 200px;" >
											<html:options collection="portalModuleList" property="code" labelProperty="key"/>
										</html:select>		</td>
								</tr>
								<tr>
									<td class="required">* <bean:message key="prompt.layout" /> :</td>
									<td>
										<html:select property="layoutCode" styleClass="input" style="width: 200px;" >
											<html:options collection="layoutList" property="code" labelProperty="name"/>
										</html:select>
									</td>
								</tr>--%>
								<tr>
								<td class="required">* <bean:message key="prompt.module" /> :</td>
									<td>
										<html:select property="moduleCode" styleId="moduleCode" styleClass="input" style="width: 200px;" >
											<html:option value=""><bean:message key="option.none" bundle="messages"/></html:option>
											<html:options collection="portalModuleList" property="code" labelProperty="key"/>
										</html:select>		
									</td>
								</tr>
								<tr>
									<td class="required">* <bean:message key="prompt.layout" /> :</td>
									<td>
										<html:select property="layoutCode" styleId="layoutCode" styleClass="input" style="width: 200px;" >
											<html:option value="" ><bean:message key="option.none" bundle="messages"/></html:option>
										</html:select>
									</td>
								</tr>	
								<tr>
									<td height="30" width="140"></td>
									<td>
										<bean:message key="message.required" bundle="systemHelp" />
									</td>
								</tr>
							</table>
					</td>
				</tr>
			</table>
			</html:form>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"/><br/>


<html:javascript formName="sectionForm" dynamicJavascript="true" staticJavascript="false" />

<ajax:select
  baseUrl="${pageContext.request.contextPath}/ajax/dropdown/layout.do"
  source="moduleCode"
  target="layoutCode"
  parameters="code={moduleCode}"
  postFunction="resetProgress"
  parser="new ResponseXmlParser()" />
 
<c:set var="targetLayout">0</c:set>	
<c:if test = "${!empty sectionForm.layoutCode && sectionForm.layoutCode != 0}">
	<c:set var="targetLayout">${sectionForm.layoutCode}</c:set>	
</c:if>

<script type="text/javascript">	
	function actionAjax(){
		var ajaxObj = new AjaxJspTag.Select("<%= request.getContextPath() %>/ajax/dropdown/layout.do", {
			parameters: "code={moduleCode}",
			postFunction: resetProgress,
			parser: new ResponseXmlParser(),
			target: "layoutCode",
			source: "moduleCode"
		});

	    var params = buildParameterString(ajaxObj.options.parameters);
		var obj = ajaxObj; // required because 'this' conflict with Ajax.Request
	    var aj = new Ajax.Request(ajaxObj.url, {
	      asynchronous: true,
    	  method: 'get',
	      parameters: params,
    	  onSuccess: function(request) {
        	obj.options.parser.load(request);
	        var results = obj.options.parser.itemList;
    	    obj.options.handler(request, {target: obj.options.target, items: results});
	      },
    	  onFailure: function(request) {
        	if (obj.options.errorFunction != null) obj.options.errorFunction(request);
	      },
    	  onComplete: function(request) {
        	if (obj.options.postFunction != null) obj.options.postFunction(request);
	      }
    	});
		
		
	}
	
	function loadlayout(){
		document.getElementById("layoutCode").value = ${targetLayout};
	}
	actionAjax();
	var timeout = window.setTimeout(loadlayout, 0);
	
	
</script>
