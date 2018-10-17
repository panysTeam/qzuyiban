<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	function findUser(){
		window.history.href="http://127.0.0.1:8080/qzuyiban/user";
	}
</script>
<h1>你好！spring boot</h1>
<a id="user" name="user" href="http://127.0.0.1:8080/qzuyiban/user" onclick="findUser()">查询用户信息</a>
</body>
</html>