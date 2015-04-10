<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<s:url id="documentsURL" action="listDocuments" />
<s:url id="usersURL" action="listUsers" />
<s:url id="groupsURL" action="listUserGroups" />

<nav class="navbar navbar-inverse navbar-fixed-top">
<div class="container-fluid">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#navbar" aria-expanded="false"
			aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>

		<s:a cssClass="navbar-brand" href="%{documentsURL}">Document Manager</s:a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav navbar-right">
			<li><s:a href="%{documentsURL}">Documents</s:a></li>
			<li><s:a href="%{usersURL}">Users</s:a></li>
			<li><s:a href="%{groupsURL}">Groups</s:a></li>
		</ul>
		<s:form cssClass="navbar-form navbar-right" action="searchDocument"
			theme="bootstrap">
			<s:textfield name="searchField" placeholder="Search..."
				cssClass="form-control" />
			<s:select name="searchType" list="#{1:'Name', 2:'Owner', 3:'Date'}" />
			<s:submit cssClass="btn btn-info" value="Search"
				action="%{searchType}" />
		</s:form>
	</div>
</div>
</nav>