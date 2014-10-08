<%@page import="java.sql.*"%>
<%@page import="db.DbPool"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<body>
	<center>
		<s:form action="delete" method="post">
			输入书名
			<s:textfield name="book.title" lab1="name" />
			<s:submit value="删   除" />
		</s:form>
		<s:form action="queryau" method="post">
			输入作者名字
			<s:textfield name="author.name" lab1="name" />
			<s:submit value="查询作者" />
		</s:form>
		<s:form action="querytit" method="post">
			输入书名
			<s:textfield name="book.title" lab1="name" />
			<s:submit value="查询题目" />
		</s:form>
		<s:form action="all" method="post">
			显示所有
			<s:submit value="所有图书" />
		</s:form>
	</center>

</body>
</html>
