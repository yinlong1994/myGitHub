<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${user.name}  ${user.age}  ${user.id}</h1>
<#if user.age lt 12>
	${user.name} 是小孩
<#elseif user.age lt 18>
	${user.name} 是大孩
<#else>
	${user.name} 是大人
</#if>
</body>
</html>