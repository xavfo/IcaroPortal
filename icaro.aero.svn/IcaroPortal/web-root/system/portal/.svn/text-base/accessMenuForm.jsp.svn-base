<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>



<script language="JavaScript1.2">	

	public: function enableFields() {
		frm = document.forms[0];	
		
		frm.url.readOnly = true;
		frm.relatedCode.disabled = true;
		frm.relatedName.disabled = true;
		frm.relatedButtom.disabled = true;
		document.getElementById('pathLbl').className = 'optional';
		document.getElementById('contentLbl').className = 'optional';
		
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
            frm.relatedCode.value = '';
            frm.relatedName.value = '';
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

        <c:forEach var="item" items="${urlList}" >
          if(frm.accessUrlCode.value == ${item.code}) { 
            document.getElementById('contentLbl').innerHTML = '* ${item.name}';
            frm.className.value = "${item.className}";
            if(null != frm.className.value && "" != frm.className.value ) {
	            frm.url.value = '${item.path}';
            }
            
          }
        </c:forEach>
        if(null == frm.className.value || "" == frm.className.value ) {
	        
            frm.url.readOnly = false;
            /*frm.url.value = '';*/
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
		<td class="title" width="300"><bean:message key="portal.menu" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> &gt;
			<bean:message key="portal.menu" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<!-- Inicio Mensajes -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td><ul>
			<html:messages id="message" message="true">
				<li class="message"><c:out value="${message}" escapeXml="false" /></li>
			</html:messages>
			</ul>
			<span class="error"><html:errors/></span>		
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="25" border="0"><br>
<!-- Fin Mensajes -->


<table border="0" cellpadding="0" cellspacing="0" align="center" width="100%">
	<tr>
		<td >
			<!-- TABS -->
			<tiles:insert definition="tabs.system.menus">
				<tiles:put name="code" value="${currentMenu.code}" />
			</tiles:insert>
			<!-- END TABS -->
		</td>
	</tr>
	<tr>
		<td>
			<html:form action="/system/portal/menu/access/save" focus="name" onsubmit="return validateAccessMenuForm(this);">
			<html:hidden property="action" value="save" />
			<html:hidden property="code" />
			<html:hidden property="itemCode" />
			<html:hidden property="tab" value ="${param.tab}" />
            <html:hidden property="className" />
			<table class="tabForm" border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td class="subtitle">
									<c:choose>
										<c:when test="${!empty accessMenuForm.code && accessMenuForm.code != 0}">
											<bean:message key="message.accessMenu.edit" bundle="systemHelp" arg0="${accessMenuForm.name}"  arg1="${currentMenu.name}" />
										</c:when>
										<c:otherwise>
											<bean:message key="message.accessMenu.create" bundle="systemHelp" arg0="${currentMenu.name}" />
										</c:otherwise>
									</c:choose>
								</td>
								<td align="right">
									<tiles:insert definition="system.navBar">
										<tiles:put name="save" 		value="true" />
										<tiles:put name="apply"		value="true" />
										<tiles:put name="reset" 	value="true" />
										<%--<tiles:put name="preview" 	value="true" />--%>
										<tiles:put name="cancel" 	value="true" />
									</tiles:insert>
								</td>
							</tr>
						</table>
						<table cellpadding="1" cellspacing="0" border="0" width="100%">
                            <tr>
                                <td class="required">* <bean:message key="prompt.name" /> :</td>
                                <td><html:text property="name" styleClass="input" style="width: 200px;" />
                                  <input type="button" value="..." title="Elejir existente"
                                    onclick="openSearch('/popup/searchAccess.do', 'accessMenuForm', new Array('accessCode','relatedCode','accessUrlCode'),new Array('name','relatedName','url','path'), 0)" />
                                </td>
                            </tr>
                            <tr>
                                <td class="required" width="160">* <bean:message key="prompt.displayMode" /> :</td>
                                <td>
                                  <html:select property="displayModeCode" styleClass="input" style="width: 200px;" >
                                    <html:options collection="displayModeList" property="code" labelProperty="key"/>
                                  </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td class="optional"> <bean:message key="prompt.image" /> :</td>
                                <td><html:text property="path" styleClass="input" style="width: 200px;" readonly="true" />
                                  <a href="javascript: openUploadWindow('/popup/openUpload.do', '/images/uploaded/access', 'accessMenuForm', 'path','access','${initParameters.SEQ_ACCESS}',0)" >
                                    <img src="${pageContext.request.contextPath}/images/system/icons/upload.gif" border="0" title="A\u00F1adir nuevo" style="vertical-align:middle" /></a>
									
									<a href="javascript:openPreview('/popup/openPreview.do', accessMenuForm.path.value)"  onMouseOver="MM_swapImage('previewImg','','${pageContext.request.contextPath}/images/icons/preview_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="previewImg" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />">
			</a>
			<a href="javascript: clearControls(0, 'path');" onMouseOver="MM_swapImage('cleanImg','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="cleanImg" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
			</a>
                                  <html:hidden property="accessCode" styleClass="input" style="width: 20px;" />
                                  <!--  input type="button" value="Vaciar" onclick="clean(this.form, 'accessCode', 'accessDescription');" / -->
                                </td>
                            </tr>
                            <tr>
                                <td class="optional"> <bean:message key="prompt.relation" /> :</td>
                                <td>
                                  <html:select property="accessUrlCode" size="1" styleClass="input" style="width: 200px;" onchange="enableFields()">
                                    <html:options collection="urlList" property="code" labelProperty="name"/>
                                  </html:select>
                                </td>
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
                                    <c:param name="option" value="" />
                                    <c:param name="className" value="" />
                                  </c:url>
                                  <input type="button" name="relatedButtom" value="..." onclick="openSearchWindow('${url}', document.forms[0])" />
                                  <input type="button" value="Vaciar" onclick="clean(this.form, 'relatedCode', 'relatedName');" />
                                </td>
                            </tr>
                            <tr>
                                <td class="required" id="pathLbl">* <bean:message key="prompt.url" /> :</td>
                                <td><html:text property="url" styleClass="input" style="width: 200px;" /></td>
                            </tr>
                            <tr>
                                <td class="required" width="160">* <bean:message key="prompt.orderIndex" /> :</td>
                                <td><html:text property="order" style="width: 50px;" /></td>
                            </tr>
                            <tr>
                                <td class="required" width="160">* <bean:message key="prompt.enabled" /> :</td>
                                <td>
                                  <html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
                                  <html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
                                </td>
                            </tr>
                            <%-- Campos sin uso por el momento
                            <tr>
                                <td class="required" width="160">* <bean:message key="prompt.access" /> :</td>
                                <td><html:text property="accessDescription" styleClass="input" style="width: 200px;"/></td>
                            </tr>             
							<tr>
								<td class="required">* <bean:message key="prompt.width" />:</td>
						        <td ><html:text property="width" maxlength="4" style="width: 50px;"/></td>
						    </tr>
							<tr>
								<td class="optional"> <bean:message key="prompt.height" />:</td>
						        <td ><html:text property="height" maxlength="4" style="width: 50px;"/></td>
						    </tr>
							--%>
							<tr>
		<td class="optional"><bean:message key="prompt.description" /> :</td>
		<td><html:textarea property="description" style="width: 450px; height: 80px;" /></td>
	</tr>
							<tr>
								<td  width="160"></td>
								<td><bean:message key="message.required" bundle="systemHelp" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</html:form>
		</td>
	</tr>
</table>
<html:javascript formName="accessMenuForm" dynamicJavascript="true" staticJavascript="false" />




