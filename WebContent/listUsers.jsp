<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>List of Users</title>
<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
<sb:head />
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<s:include value="/include/navbar.jsp"></s:include>
	<div class="container">

		<h2 class="sub-header">Users</h2>
		<s:if test="userList.size() == 0">
			<p class="lead">There's no users.</p>
		</s:if>
		<s:if test="userList.size() > 0">
			<div class="table-responsive">
				<table class="table table-striped">
					<tr class="even">
						<th>Name</th>
						<th>Grupo de usuarios</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
					<s:iterator value="userList" status="userStatus">
						<tr
							class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
							<td><s:property value="name" /></td>
							<td><s:property value="group.name" /></td>
							<td><s:url id="editURL" action="editUser">
									<s:param name="id" value="%{id}"></s:param>
								</s:url> <s:a href="%{editURL}">Edit</s:a></td>
							<td><s:url id="deleteURL" action="deleteUser">
									<s:param name="id" value="%{id}"></s:param>
								</s:url> <s:a href="%{deleteURL}">Delete</s:a></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</s:if>
		
		<s:url id="addURL" action="addUser"/>
		<s:a href="%{addURL}" cssClass="btn btn-lg btn-success pull-right">Add</s:a>		
	</div>
</body>
</html>