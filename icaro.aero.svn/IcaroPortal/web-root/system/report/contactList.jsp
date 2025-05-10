<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/ajaxtags" prefix="ajax"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/prototype-1.4.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/controls.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/ajaxtags-1.2-beta1.js"></script>

<%--<script language="javascript">
var oEditor;

function loadPage() {
	window.open("${pageContext.request.contextPath}/popup/searchPortlet.do" , null,"width=550, height=300, resizable=0, scrollbars=0, menubar=0, status=0, location=0, toolbar=0").oEditor=oEditor;
}

function clean(frm, objTargetName, objLabelName, txtDesc){
	if (typeof txtDesc=='undefined')
		txtDesc="";
	frm.elements[objTargetName].value = "";
	frm.elements[objLabelName].value = txtDesc;
}

function resetProgress() {
	document.getElementById("city").options[0].text='<bean:message key="option.all" bundle="messages"/>';
	document.getElementById("city").options[0].value='';
}
</script>--%>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="service.report.contact" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="service.report" bundle="systemMenu" /> >
			<bean:message key="service.report.contact" bundle="systemMenu" /> 
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<%--BUSCADOR--%>
	<!--inicio buscador-->
						<table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td>
									<html:form action="/system/report/contact">
									<html:hidden property="action" value="list" />
									<html:hidden property="listItems" value="true" />
									
									<table cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td><img src="${pageContext.request.contextPath}/images/search/borde_superior_iz.gif" width="7" height="7" alt="" border="0"></td>
											<td background="${pageContext.request.contextPath}/images/search/tile_superior.gif" width="100%"><img src="${pageContext.request.contextPath}/images/shim.gif" width="100%" height="7" alt="" border="0"></td>
											<td><img src="${pageContext.request.contextPath}/images/search/borde_superior_der.gif" width="7" height="7" alt="" border="0"></td>
										</tr>
										<tr>
											<td background="${pageContext.request.contextPath}/images/search/tile_iz.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="7" height="100%" alt="" border="0"></td>
											<td width="100%" bgcolor="#EBEEF3">
												<table border="0" cellpadding="0" cellspacing="0">
													<tr><td class="subtitle">&nbsp;<img src="${pageContext.request.contextPath}/images/icons/find.gif" width="16" height="16" border="0" alt=""> <bean:message key="label.search" bundle="messages" /></td></tr>
												</table>
												<table border="0" cellpadding="0" cellspacing="2" width="100%">
													<tr>
														<td width="210">
															<bean:message key="label.name" bundle="messages" />:<br>
															<html:text property="name" styleClass="input" style="width: 200px;" maxlength="100" />
														</td>
														<td width="130">
															<bean:message key="prompt.country" />:<br>
																	<html:select property="countryCode" size="1" styleClass="input" style="width: 120px;" >
																		<html:option value=""><bean:message key="option.all" bundle="messages"/></html:option>
																		<html:options collection="countryList" property="code" labelProperty="name"/>
																	</html:select>
														</td>	
														<td width="160"> <bean:message key="prompt.city" />:<br>
															<html:text property="cityName" styleClass="input" style="width: 150px;" maxlength="100" />
<%--															<html:select property="cityCode" styleClass="input" style="width: 200px;" >
																<html:option value="" ><bean:message key="option.all" bundle="messages"/></html:option>
															</html:select>
--%>															
														</td>
														<td>
                                  							<bean:message key="label.from" bundle="messages"/><br>
                                  							<html:text property="fromDate" styleClass="input" styleId="fromDate" style="width: 80px;" maxlength="11"  />  <html:button property="fromBtn" value="..." styleId="fromBtn" style="width: 20px;"/>
                                						</td>
                               		 					<td>
                                  							 <bean:message key="label.to" bundle="messages"/><br>
                                  							<html:text property="toDate" styleClass="input" styleId="toDate" style="width: 80px;" maxlength="11"  />  <html:button property="toBtn" value="..." styleId="toBtn" style="width: 20px;"/>
                                						</td>

														<td>&nbsp;</td>
														
														<td valign="bottom">									
															<html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit>
														</td>
													</tr>
													<%--<tr>
													<td width="130">
													<bean:message key="label.category" bundle="messages" /><br>
													<html:text property="categoryName" styleClass="input" style="width: 200px;" maxlength="100" />
													</td>
												
													</tr>--%>
												</table>				
											</td>
											<td background="${pageContext.request.contextPath}/images/search/tile_der.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="7" height="100%" alt="" border="0"></td>
										</tr>
										<tr>
											<td><img src="${pageContext.request.contextPath}/images/search/borde_inferior_iz.gif" width="7" height="7" alt="" border="0"></td>
											<td background="${pageContext.request.contextPath}/images/search/tile_inferior.gif" width="100%"><img src="${pageContext.request.contextPath}/images/shim.gif" width="100%" height="7" alt="" border="0"></td>
											<td><img src="${pageContext.request.contextPath}/images/search/borde_inferior_der.gif" width="7" height="7" alt="" border="0"></td>
										</tr>
									</table>
									<table cellpadding="0" cellspacing="0" width="100%">
										<tr><td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="10" border="0" alt=""></td></tr>
										<tr>
											<td align="right">
												<table cellpadding="0" cellspacing="0" border="0">
												<tr>
													<td><strong><bean:message key="label.orderBy" bundle="messages" />:</strong></td>
													<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="5" height="15" ></td>
													<td>
														<html:select property="orderField" size="1" styleClass="input" style="width: 110px;" onchange="submitOrder(this)">
															<html:option value="name"><bean:message key="label.name" bundle="messages" /></html:option>									
															<html:option value="cityName"><bean:message key="label.city" bundle="messages" /></html:option>
															<html:option value="country.name"><bean:message key="label.country" bundle="messages" /></html:option>
															<html:option value="catalogue.name"><bean:message key="label.motive" bundle="messages" /></html:option>
															<html:option value="stateName"><bean:message key="label.state" bundle="messages" /></html:option>
															<%--<html:option value="enabled"><bean:message key="label.status" bundle="messages" /></html:option>--%>									
														</html:select>				
													</td>
													<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="5" height="15" ></td>
													<td>
														<html:select property="orderAsc" size="1" styleClass="input" style="width: 70px;" onchange="submitOrder(this)">
															<html:option value="true"><bean:message key="label.asc" bundle="messages" /></html:option>
															<html:option value="false"><bean:message key="label.desc" bundle="messages" /></html:option>
														</html:select>
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
						
<script type="text/javascript" language="JavaScript1.2">
  Calendar.setup(
    {
      inputField    : "fromDate",
      ifFormat      : "%Y-%m-%d",
      dateFormat    : "%Y-%m-%d",
      weekNumbers   : false,
      align         : "Bl",
      button        : "fromBtn"
    }
  );  
  Calendar.setup(
    {
      inputField    : "toDate",
      ifFormat      : "%Y-%m-%d",
      dateFormat    : "%Y-%m-%d",
      weekNumbers   : false,
      align         : "Bl",
      button        : "toBtn"
    }
  );  
</script>						
<%--						<ajax:select
  baseUrl="${pageContext.request.contextPath}/ajax/dropdown/city.do"
  source="countryCode"
  target="cityCode"
  parameters="code={countryCode}"
  postFunction="resetProgress"
  parser="new ResponseXmlParser()" />
 
<c:set var="targetLayout">0</c:set>	
<c:if test = "${!empty contactReportForm.countryCode && contactReportForm.countryCode != 0}">
	<c:set var="targetLayout">${contactReportForm.countryCode}</c:set>	
</c:if>

<script type="text/javascript">	
	function actionAjax(){
		var ajaxObj = new AjaxJspTag.Select("<%= request.getContextPath() %>/ajax/dropdown/city.do", {
			parameters: "code={countryCode}",
			postFunction: resetProgress,
			parser: new ResponseXmlParser(),
			target: "cityCode",
			source: "countryCode"
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
	
	function loadCity(){
		document.getElementById("cityCode").value = ${targetLayout};
	}
	actionAjax();
	var timeout = window.setTimeout(loadCity, 0);
	
	//InitializeAPI() ;
	//var oEditor = FCKeditorAPI.GetInstance('intro') ;
	
	
</script>--%>
						
						
						
						
						
						
						
						
						
						
						


<%--BUSCADOR--%>

<hr width="100%" size="1" color="#999999" noshade>
<html:form action="/system/report/contact">
<html:hidden property="action" value="" />
<html:hidden property="code" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<%--<tr>
		<td>
		</td>
		<td align="left">
			<tiles:insert definition="system.navBar">
				<tiles:put name="add" 		value="true" />
				<tiles:put name="edit" 		value="true" />
				<tiles:put name="delete" 	value="true" />
			</tiles:insert>
		</td>
	</tr>--%>
</table>
<br />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>			
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
	</tr>
</table>
<!-- FIN Options -->


<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<!-- List data -->
<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
	<tr>
		<th width="30">#</td>
		<%--<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></td>--%>
		<th width="150"><bean:message key="label.creationDate" bundle="messages" /></td>
		<th width="150"><bean:message key="label.name" bundle="messages" /></td>
		<th width="150"><bean:message key="label.motive" bundle="messages" /></td>
		<th width="150"><bean:message key="label.email" bundle="messages" /></td>
		<th width="150"><bean:message key="label.phone" bundle="messages" /></td>
		<th width="150"><bean:message key="label.address" bundle="messages" /></td>
		<th width="150"><bean:message key="label.city" bundle="messages" /></td>
		<th width="100"><bean:message key="label.country" bundle="messages" /></td>
		<!--th width="150"><bean:message key="label.company" bundle="messages" /></td-->
		<th><bean:message key="label.comments" bundle="messages" /></td>
	</tr>
	<!--Fin Header-->
	<c:forEach var="item" items="${contactList}" varStatus="status">
		
	<!-- detalle registros -->
	<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
		<td align="center" style="padding-left:5px">${status.count}</td>
		<td style="padding-left:5px" ><fmt:formatDate value="${item.date.time}" pattern="${initParameters.DATE_FORMAT}" /></td>
		<td style="padding-left:5px" >${item.name} ${item.lastName}</td>
		<td style="padding-left:5px" >${item.catalogue.name}</td>
		<td style="padding-left:5px" >${item.email}</td>
		<td style="padding-left:5px" >${item.phone}</td>
		<td style="padding-left:5px" >${item.address}</td>
		<td width="150" style="padding-left:5px">${item.cityName} - ${item.stateName}</td>
		<td style="padding-left:5px" >${item.country.name}</td>
		<!--td style="padding-left:5px" >${item.company}<c:if test="${empty item.position}"><br>${item.position}</c:if></td!-->		
		<td style="padding-left:5px">${item.comment}</td>
	</tr>
	<!-- FIN detalle registros -->
	</c:forEach>
	<c:if test="${empty requestScope.contactList && contactReportForm.listItems }">
		<tr>
			<td height="20" align="center" colspan="10" class="message"><bean:message key="message.empty.records" /></td>
		</tr>	
	</c:if>  
		 
</table>
					
</html:form>












