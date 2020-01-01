<!DOCTYPE html>
<head>
	<meta charset="utf-8">
	<#assign ctx=request.contextPath />
	<script type="text/javascript" src="${ctx}/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
		function login() {
			// $("#form").attr("action", ctxPaths + "/adminlogin");
			$("#form").attr("action", "${ctx}/adminlogin");
			$("#form").submit();
		}
	</script>
</head>
	<div align="center">
	    <h2>自定义登录页面</h2>
	    <form id="form" method="post">
	        <label>
	            <span>用户名：</span>
	            <input type="text" name="username" />
	        </label>
	        <br/>
	        <label>
	            <span>密码：</span>
	            <input type="password" name="password" />
	        </label>
	        <br/>
	        <input type="button" value="登录" onclick="login()"/>
	    </form>
	    ${error}
	</div>
</html>