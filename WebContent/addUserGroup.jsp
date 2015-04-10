<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Add User Group</title>
<s:head />
<sb:head />
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<s:include value="/include/navbar.jsp"></s:include>
	<div class="container">
		<h2 class="page-header">
			<s:if test="userGroup.name==null || userGroup.name==''">Add User Group</s:if>
			<s:else>Edit User Group</s:else>
		</h2>
		<div class="col-md-7 col-md-offset-2">
			<s:form action="saveUserGroup" theme="bootstrap"
				cssClass="form-horizontal">
				<s:push value="userGroup">
					<s:hidden name="id" />
					<s:textfield name="name" label="User Group Name" required="true" />
					<s:submit cssClass="btn btn-lg btn-success pull-right" />
				</s:push>
			</s:form>
		</div>
	</div>
</body>
</html>