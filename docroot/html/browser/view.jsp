<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page import="it.infn.ct.BrowserPortlet" %>

<portlet:defineObjects />

<div id="app-container">
 	<!-- App goes here -->
</div>


<% 
	String rootPath = renderRequest.getContextPath(); 
	String loginOutput = BrowserPortlet.login();
%>

<script>
	var isomorphicDir="<%=rootPath%>/js/isomorphic/";
	var loginOutputRow = '<%= loginOutput %>';
	var loginOutput = JSON.parse(loginOutputRow);
	if (loginOutput.id) {
		var access_token = loginOutput.id;
	} else {
		alert("Some error occuring while logging into gLibrary :(");
	}
</script>
<script src="<%=rootPath%>/js/isomorphic/system/modules/ISC_Core.js"></script>
<script src="<%=rootPath%>/js/isomorphic/system/modules/ISC_Foundation.js"></script>
<script src="<%=rootPath%>/js/isomorphic/system/modules/ISC_Containers.js"></script>
<script src="<%=rootPath%>/js/isomorphic/system/modules/ISC_Grids.js"></script>
<script src="<%=rootPath%>/js/isomorphic/system/modules/ISC_Forms.js"></script>
<script src="<%=rootPath%>/js/isomorphic/system/modules/ISC_DataBinding.js"></script>
<script src="<%=rootPath%>/js/isomorphic/skins/Enterprise/load_skin.js"></script>
<script src="<%=rootPath%>/js/app.js"></script>
