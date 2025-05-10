<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<%@ taglib uri="/ajaxtags" prefix="ajax"%>


<c:url value="/system/bulletin/bulletin.do" var="urlLoad">
	<c:param name="action" value="load" />							
</c:url>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/prototype-1.4.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/controls.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/ajaxtags-1.2-beta1.js"></script>

<script language="javascript">
function resetProgress() {
	document.getElementById("bulletinCode").options[0].text='<bean:message key="option.none" bundle="messages"/>';
	document.getElementById("bulletinCode").options[0].value='';
	//document.getElementById("layoutCode").focus();
}

function send(frm){	
	var button =  document.getElementById('inputSubmit');
	button.click();
}
function cancel(frm) {
	frm.action.value="save";
	var button =  document.getElementById('inputCancel');
	button.click();
}

function previewBulletin(frm){
	var bulletinUrl = '${urlLoad}&code=' + frm.bulletinCode.value;
	openView(bulletinUrl);
}
</script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="bulletin.sendBulletin" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="bulletin.bulletin" bundle="systemMenu" /> >
			<bean:message key="bulletin.sendBulletin" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>

<table border="0" cellpadding="2" cellspacing="2">
	<tr>
		<td>
			<table class="step1" border="0" cellpadding="4" cellspacing="1" width="144">
				<tr bgcolor="#FFFFFF">
					<td class="stepLabel">
						<bean:message key="label.step1" bundle="messages" /><br>Seleccionar Bolet&iacute;n
					</td>
				</tr>
				<tr><td height="10"></td></tr>
			</table>
		</td>
		<td>
			<table class="stepOff" border="0" cellpadding="4" cellspacing="1" width="144">
				<tr bgcolor="#FFFFFF">
					<td>
						<bean:message key="label.step2" bundle="messages" /><br>Vista Previa/ Env&iacute;o
					</td>
				</tr>
				<tr><td height="10"></td></tr>
			</table>
		</td>		
		<td>
			<table class="stepOff" border="0" cellpadding="4" cellspacing="1" width="144">
				<tr bgcolor="#FFFFFF">
					<td>
						<bean:message key="label.step3" bundle="messages" /><br>Confirmaci&oacute;n de Env&iacute;o
					</td>
				</tr>
				<tr><td height="10"></td></tr>
			</table>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">

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

<html:form action="/system/bulletin/step2" onsubmit="return validateSendBulletinForm(this);" >
<html:hidden property="action" value="step2" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle"></td>
		<td align="right">
			<span style="display:none"><html:submit styleId="inputSubmit" ><bean:message key="button.ok" bundle="messages" /></html:submit></span>
			<span style="display:none"><html:cancel styleId="inputCancel"><bean:message key="button.cancel" bundle="messages" /></html:cancel></span>
			<table border="0" cellpadding="2" cellspacing="0">
				<tr>
					<td align="center"><a href="javascript: send(document.forms[0]);"><img src="${pageContext.request.contextPath}/images/system/icons/continuar.gif" width="27" height="26" border="0" alt=""></a></td>
					<td align="center"><a href="javascript: document.forms[0].reset(); "><img src="${pageContext.request.contextPath}/images/system/icons/restaurar.gif" alt="<bean:message key="button.reset" bundle="messages" />" width="26" height="27" border="0" /></a></td>
					<td align="center"><a href="javascript: previewBulletin(document.forms[0]);"><img src="${pageContext.request.contextPath}/images/system/icons/vista_previa.gif" alt="<bean:message key="button.preview" bundle="messages" />" width="26" height="27" border="0" /></a></td>
				</tr>
				<tr>
					<td align="center"><a href="javascript: send(document.forms[0]);"><bean:message key="button.next" bundle="messages" /></a></td>
					<td align="center"><a href="javascript: document.forms[0].reset(); "><bean:message key="button.reset" bundle="messages" /></a></td>
					<td align="center"><a href="javascript: previewBulletin(document.forms[0]);"><bean:message key="button.preview" bundle="messages" /></a></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr><td colspan="2" height="25"></td></tr>
	<tr>
	<td class="required">* <bean:message key="prompt.topic" /> :</td>
		<td>
			<html:select property="topicCode" styleClass="input" style="width: 350px;" >
				<html:option value=""><bean:message key="option.none" bundle="messages"/></html:option>
				<html:options collection="topicList" property="code" labelProperty="name"/>
			</html:select>		
		</td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.bulletin" /> :</td>
		<td>
			<html:select property="bulletinCode" styleClass="input" style="width: 350px;" >
				<html:option value="" ><bean:message key="option.none" bundle="messages"/></html:option>
			</html:select>
		</td>
	</tr>	
	<tr><td colspan="2" height="25"></td></tr>
</table>
</html:form>
<html:javascript formName="sendBulletinForm" dynamicJavascript="true" staticJavascript="false" />

<ajax:select
  baseUrl="${pageContext.request.contextPath}/ajax/dropdown/bulletin.do"
  source="topicCode"
  target="bulletintCode"
  parameters="code={topicCode}"
  postFunction="resetProgress"
  parser="new ResponseXmlParser()" />
 
<c:set var="targetLayout">0</c:set>	
<c:if test = "${!empty sendBulletinForm.bulletinCode && sendBulletinForm.bulletinCode != 0}">
	<c:set var="targetLayout">${sendBulletinForm.bulletinCode}</c:set>	
</c:if>

<script type="text/javascript">	
	function actionAjax(){
		var ajaxObj = new AjaxJspTag.Select("<%= request.getContextPath() %>/ajax/dropdown/bulletin.do", {
			parameters: "code={topicCode}",
			postFunction: resetProgress,
			parser: new ResponseXmlParser(),
			target: "bulletinCode",
			source: "topicCode"
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
		document.getElementById("bulletinCode").value = ${targetLayout};
	}
	actionAjax();
	var timeout = window.setTimeout(loadlayout, 0);
</script>
