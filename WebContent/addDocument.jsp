<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Document Upload</title>
<s:head />
<sb:head />
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<s:include value="/include/navbar.jsp"></s:include>
	<div class="container">
		<h2 class="page-header">
			<s:if test="document.name==null || document.name==''">Upload Document</s:if>
			<s:else>Edit Document</s:else>
		</h2>
		<s:form action="saveDocument" method="post"
			enctype="multipart/form-data" theme="bootstrap"
			cssClass="form-horizontal">

			<s:if test="document.name==null || document.name==''">
				<s:file name="doc" label="Document" required="true" />
			</s:if>
			<s:else>
				<s:hidden name="docFileName" value="%{document.name}" />
				<s:textfield value="%{document.name}" disabled="true"
					label="Document" />
			</s:else>

			<s:push value="document">
				<s:hidden name="id" />
				<s:select name="privacyId" label="File Privacy"
					list="#{'1':'Public', '2':'Private', '3':'Draft'}" required="true" />

				<s:select name="ownerId" list="userList" listKey="id"
					listValue="name" headerKey="" headerValue="Select"
					label="File Owner" required="true" />

				<s:select name="groupId" list="userGroupList" listKey="id"
					listValue="name" headerKey="" headerValue="Select"
					label="User Group" required="true" />

				<s:textarea name="description" label="Description" cols="20"
					rows="3" />
				<s:submit cssClass="btn btn-lg btn-success pull-right" />
			</s:push>
		</s:form>
	</div>
</body>
</html>