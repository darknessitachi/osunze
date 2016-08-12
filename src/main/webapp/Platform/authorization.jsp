<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../Include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="${ctx }/js/zTree/3.5/zTreeStyle.css"/>
</head>
<body>
<div class="zTreeDemoBackground left">
	<ul id="treeDemo" class="ztree"></ul>
</div>
<input type="hidden" id="roleId" value="${roleId}"/>
<input type="hidden" id="menuIds" value="${menuIds}"/>
<script type="text/javascript" src="${ctx }/js/zTree/3.5/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx }/js/zTree/3.5/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
	var zTree;
	$(document).ready(function(){
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},callback: {
				onCheck: onCheck
			}
		};
		var zn = '${zTreeNodes}';
		var zTreeNodes = eval(zn);
		$.fn.zTree.init($("#treeDemo"), setting, zTreeNodes);
	});
	
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getCheckedNodes(true);
		var ids = "";
		for (var i = 0; i < nodes.length; i++) {
			var node = nodes[i];
			if (i != nodes.length - 1) {
				ids += node.id+ ",";
			} else {
				ids += node.id;
			}
		}
		$("#menuIds").val(ids);
	}
	</script>
</body>
</html>