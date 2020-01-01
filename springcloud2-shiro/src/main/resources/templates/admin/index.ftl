<#assign ctx=request.contextPath />
<script type="text/javascript" src="${ctx}/js/jquery-3.4.1.min.js"></script>
<script>
	function logout() {
		window.location.href= "${ctx}/logout";
	}
</script>

<p>${username}登录成功<p/>
<#if shiro.hasPermission("perm1")>有perm1权限</#if>
<#if shiro.hasPermission("perm2")>有perm2权限</#if>
<#if shiro.hasPermission("perm3")>有perm3权限</#if>
<#if shiro.hasPermission("perm4")>有perm4权限</#if>
<div><input type="button" value="注销" onclick="logout();"/></div>