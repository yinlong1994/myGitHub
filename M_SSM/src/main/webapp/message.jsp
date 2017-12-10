<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手机短信发送列表</title>
<style type="text/css">
div{
border: 1px solid red;
}
</style>
</head>
<body>
	<div>
		<h1>新增/修改</h1>
		<form action="save">
			<input placeholder="标题" name="title"><br />
			<textarea rows="5" cols="30" placeholder="请输入短信内容" name="content"></textarea>
			<input type="submit" value="发送短信">
		</form>
	</div>
	<hr/>
	<div>
		<h1>短信列表</h1>
		<p><a href="query?page=1&count=20">刷新数据</a></p>
		<table width="80%" align="center" border="1">
		<tr>
		<th>序号</th>
		<th>标题</th>
		<th>内容</th>
		<th>时间</th>
		<th>操作</th>
		</tr>
		<c:forEach items="${list }" var="m">
		<tr class="tr_msg">
		<td>${m.id }</td>
		<td>${m.title }</td>
		<td>${m.content }</td>
		<td>${m.createtime }</td>
		<td><a href="">修改</a>&nbsp;&nbsp;<a href="del?id=${m.id }">删除</a></td>
		</tr>
		</c:forEach>
		<tfoot>
		<tr>
		<td align="center" colspan="5">
		<button>首页</button>
		<button>上一页</button>
		<button>下一页</button>
		<button>尾页</button>
		</td>
		</tr>
		</tfoot>
		</table>
		
	</div>
</body>
</html>