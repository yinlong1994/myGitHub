<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<#if (user.name)??>
  ${user.name}
<#else>
    无名
</#if>

<h1>  ${user.name!"无名"} ${user.age}  ${user.id}</h1>

</body>
</html>