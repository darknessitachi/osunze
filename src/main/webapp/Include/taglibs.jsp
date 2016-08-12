<%@ taglib prefix="z" uri="skycity"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="/osunze"/>

<link rel="stylesheet" type="text/css" href="${ctx }/Style/Default.css">
<link rel="stylesheet" type="text/css" href="${ctx }/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/js/themes/icon.css" />

<script type="text/javascript" src="${ctx }/Framework/Main.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/Framework/myjs/Currency.js"></script>
<script type="text/javascript" src="${ctx }/My97DatePicker/WdatePicker.js"></script>

<link rel="shortcut icon" href="${ctx}/Include/favicon.ico" type="image/x-icon" />


<shiro:notAuthenticated>
<script language="javascript">
	location.href="${ctx}/Login.jsp";
</script>
</shiro:notAuthenticated>
