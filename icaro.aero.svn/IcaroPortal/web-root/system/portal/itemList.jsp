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

<script language="javascript">
function resetProgress() {
	document.getElementById("categoryCode").options[0].text='<bean:message key="option.none" bundle="messages"/>';
	document.getElementById("categoryCode").options[0].value='0';
	document.getElementById("categoryCode").focus();
}
</script>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="portal.item" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> >
			<bean:message key="portal.item" bundle="systemMenu" />
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
			<ul>
			<div class="error"><html:errors/></div>		
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="25" border="0"><br>
<!-- Fin Mensajes -->


<!-- Inicio Buscador -->
<html:form action="/system/portal/item" focus="titleSearch">
<html:hidden property="tab" value="1" />
<html:hidden property="action" value="list" />
<html:hidden property="level" value="3" />
<html:hidden property="search" value="true" />

<table cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td><img src="${pageContext.request.contextPath}/images/search/borde_superior_iz.gif" width="7" height="7" alt="" border="0"></td>
		<td background="${pageContext.request.contextPath}/images/search/tile_superior.gif" width="100%"><img src="${pageContext.request.contextPath}/images/shim.gif" width="100%" height="7" alt="" border="0"></td>
		<td><img src="${pageContext.request.contextPath}/images/search/borde_superior_der.gif" width="7" height="7" alt="" border="0"></td>
	</tr>
	<tr>
		<td background="${pageContext.request.contextPath}/images/search/tile_iz.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="7"  alt="" border="0"></td>
		<td width="100%" bgcolor="#EBEEF3">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr><td class="subtitle">&nbsp;<img src="${pageContext.request.contextPath}/images/icons/find.gif" width="16" height="16" border="0" alt=""> <bean:message key="label.search" bundle="messages" /></td></tr>
			</table>
			
			<table border="0" cellpadding="3" cellspacing="2" >
				<tr>
					<td>
						<bean:message key="label.name" bundle="messages" /><br />
						<html:text property="titleSearch" styleClass="input" style="width: 120px;"  maxlength="100" />
					</td>
					<td >
						<bean:message key="label.section" bundle="messages" /><br>
						<html:select property="sectionCode" styleClass="input" >
							<html:option value=""><bean:message key="option.none" bundle="messages"/></html:option>
							<html:options collection="sectionList" property="code" labelProperty="name"/>
						</html:select>
					</td>
					<td>
						<bean:message key="label.category" bundle="messages" /><br>
						<html:select property="categoryCode" styleClass="input"  >
							<html:option value=""><bean:message key="option.none" bundle="messages"/></html:option>
						</html:select>
					</td>
					
					<td width="130">
						<bean:message key="label.enabled" bundle="messages" /><br>
						<html:radio property="enabledSearch" value="true" /> <bean:message key="label.yes" bundle="messages" />
						<html:radio property="enabledSearch" value="false" /> <bean:message key="label.no" bundle="messages" />
					</td>
					<td valign="bottom">
						<html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit>
					</td>
				</tr>
			</table>
	  </td>
		<td background="${pageContext.request.contextPath}/images/search/tile_der.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="7" alt="" border="0"></td>
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
				<TD><img src="${pageContext.request.contextPath}/images/shim.gif" width="5" height="15" ></td>
				<td>
					<html:select property="orderField" size="1" styleClass="input" style="width: 110px;" onchange="submitOrder(this)">
						<html:option value="parent"><bean:message key="label.parent" bundle="messages" /></html:option>									
						<html:option value="title"><bean:message key="label.title" bundle="messages" /></html:option>
						<html:option value="enabled"><bean:message key="label.status" bundle="messages" /></html:option>
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
<!-- Fin Buscador -->
</html:form>
<html:form action="/system/portal/item">
<html:hidden property="tab" value="1" />
<html:hidden property="action" value="list" />
<html:hidden property="level" value="3" />
<html:hidden property="titleSearch"/>
<html:hidden property="sectionCode"/>
<html:hidden property="categoryCode"/>
<c:if test="${ !empty contentForm.enabledSearch}">
	<html:hidden property="enabledSearch"/>
</c:if>
<html:hidden property="orderField"/>
<html:hidden property="orderAsc"/>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		</td>
		<td>
			<tiles:insert definition="system.navBar">
				<tiles:put name="add" 		value="true" />
				<tiles:put name="edit" 		value="true" />
				<tiles:put name="delete" 	value="true" />
				<tiles:put name="formIndex" value="1" />
			</tiles:insert>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<!-- List data -->
<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
<c:choose>
	<c:when test="${empty requestScope.contentList && param.search }">
		<tr><td><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="20" border="0"></td></tr>
		<tr><td><h3 class="message"><bean:message key="message.emptyResults" /></h3></td></tr>
	</c:when>
	<c:otherwise>
	<tr>
		<th width="30">#</th>
		<th width="20"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
		<th width="150"><bean:message key="label.item" bundle="messages" /></th>
		<th width="150"><bean:message key="label.parent" bundle="messages" /></th>
		<th width="150"><bean:message key="label.order" bundle="messages" /></th>
		<th width="110"><bean:message key="prompt.enabled" /></th>
	</tr>
	<c:forEach var="item" items="${contentList}" varStatus="status">		
		<c:url value="/system/portal/item.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
			<c:param name="tab" value="1" />
		</c:url>
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td align="center">${status.count}</td>
			<td align="center" width="20"><html:checkbox property="codes" value="${item.code}" /></td>
			<td><a href="${urlRead}" class="item">${item.title}</a></td>
			<td>
				<c:if test="${ !empty item.parent && !empty item.parent.parent }">
					${item.parent.parent.title} &raquo; ${item.parent.title}
				</c:if>
				<%--c:forEach var="item" items="${content.hierarchy}" varStatus="hStatus">
					${hStatus} ${item.title}
					&raquo;
				</c:forEach --%>
			</td>
			<td align="center">${item.order}</td>
			<td align="center">
				<c:choose>
					<c:when test="${item.enabled == true}">
						<bean:message key="label.yes" bundle="messages" />
					</c:when>
					<c:otherwise>
						<bean:message key="label.no" bundle="messages" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
	</c:otherwise>
	</c:choose>	
</table>

<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<table align="center">
	<tr>
		<td>
			<tiles:insert definition="system.pager">
				<tiles:put name="styleClass" value="pageItem" />
				<tiles:put name="selectedClass" value="pageSelItem" />
				<tiles:put name="totalPages" value="${contentForm.totalPages}" />
				<tiles:put name="currentPage" value="${contentForm.pageNumber}" />
				<tiles:put name="formIndex" value="1" />
			</tiles:insert>
		</td>
	</tr>
</table>
</html:form>
<ajax:select
  baseUrl="${pageContext.request.contextPath}/ajax/dropdown/layout.do"
  source="sectionCode"
  target="categoryCode"
  parameters="code={sectionCode}"
  postFunction="resetProgress"
  parser="new ResponseXmlParser()" />
 
<c:set var="targetObj">0</c:set>	
<c:if test = "${!empty contentForm.categoryCode && contentForm.categoryCode != 0}">
	<c:set var="targetObj">${contentForm.categoryCode}</c:set>	
</c:if>

<script type="text/javascript">
	function actionAjax(){
		var ajaxObj = new AjaxJspTag.Select("<%= request.getContextPath() %>/ajax/dropdown/category.do", {
			parameters: "code={sectionCode}",
			postFunction: resetProgress,
			parser: new ResponseXmlParser(),
			target: "categoryCode",
			source: "sectionCode"
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
	
	function loadCategory(){
		document.getElementById("categoryCode").value = ${targetObj};
	}
	actionAjax();
	var timeout = window.setTimeout(loadCategory, 0);
</script>