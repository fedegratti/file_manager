<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Add User</title>
<s:head />
<sb:head />
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<s:include value="/include/navbar.jsp"></s:include>
	<div class="container">
		<h2 class="page-header">
			<s:if test="user.name==null || user.name==''">Add User</s:if>
			<s:else>Edit User</s:else>
		</h2>
		<div class="col-md-6 col-md-offset-3">
			<s:form action="saveUser" theme="bootstrap"
				cssClass="form-horizontal">
				<s:push value="user">
					<s:hidden name="id" />
					<s:textfield name="name" label="User Name" required="true" />

					<s:select name="groupId" list="userGroupList" listKey="id"
						listValue="name" headerKey="" headerValue="Select"
						label="User Group" required="true" />

					<s:submit cssClass="btn btn-lg btn-success pull-right" />
				</s:push>
			</s:form>
		</div>
	</div>
</body>
</html>