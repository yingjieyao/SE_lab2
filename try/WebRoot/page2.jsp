<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.sql.*" %>
<%@page import="db.*" %>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>

  <div align="center">
  	<font size=10>
  		All Books
  	</font>
  	<br/>
  	<font size=5>
  	<table border="1">
  		<tr>
  			<td>ISBN</td>
  			<td>title</td>
  			<td>authorid</td>
  			<td>publisher</td>
  			<td>publishdata</td>
  			<td>price</td>
  			
  		</tr>
  	
  		<s:iterator value="List" var="Books" status="sta">
  			<tr>
  				<td><s:property value="#Books.ISBN"/></td>
  				<td><s:property value="#Books.title"/></td>
  				<td><s:property value="#Books.authorid"/></td>
  				<td><s:property value="#Books.publisher"/></td>
  				<td><s:property value="#Books.publishdate"/></td>
  				<td><s:property value="#Books.price"/></td>
  			</tr>
  		</s:iterator>
  	</table><br/><br/>
  	  <font size=10>
  		Authors
  	</font>
  	<table border="1">
  		  		<tr>
  			<td>AuthorID</td>
  			<td>Name</td>
  			<td>Age</td>
  			<td>Country</td>
  		</tr>
  		
  		  	<s:iterator value="List2" var="Authors" status="sta">
  			<tr>
  				<td><s:property value="#Authors.Authorid"/></td>
  				<td><s:property value="#Authors.Name"/></td>
  				<td><s:property value="#Authors.Age"/></td>
  				<td><s:property value="#Authors.Country"/></td>

  			</tr>
  		</s:iterator>
  		
  	</table>
  	<a href=index.jsp>返回</a><br>
  	</font>
  	</ul>
  </div>
	
</html>








