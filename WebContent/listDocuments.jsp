<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>List of documents</title>
<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
<sj:head />
<sb:head />
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<s:include value="/include/navbar.jsp"></s:include>
	<div class="container">
		<s:set name="privacyList" value="{'Public','Private','Draft'}" />
		<h2 class="sub-header">Documents</h2>
		<s:if test="documentList.size() == 0">
			<p class="lead">There's no documents.</p>
		</s:if>
		<s:if test="documentList.size() > 0">
			<div class="table-responsive">
				<table class="table table-striped">
					<tr class="even">
						<th>Name</th>
						<th>Upload Date</th>
						<th>Privacy</th>
						<th>Owner</th>
						<th>Description</th>
						<th>Group</th>
						<th>Info</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
					<s:iterator value="documentList" status="userStatus">
						<tr
							class="<s:if test="#documentStatus.odd == true ">odd</s:if><s:else>even</s:else>">
							<td><s:url id="downloadURL" action="downloadDocument">
									<s:param name="documentId" value="%{id}" />
								</s:url> <s:a href="%{downloadURL}">
									<s:property value="name" />
								</s:a></td>
							<td><s:date name="uploadDate" format="dd-MM-yyyy" /></td>
							<td><s:property value="#privacyList[privacyId-1]" /></td>
							<td><s:property value="owner.name" /></td>
							<td><s:property value="description" /></td>
							<td><s:property value="group.name" /></td>
							<td><s:url id="infoURL" action="listDocuments">
									<s:param name="documentId" value="%{id}" />
									<s:param name="info" value="true" />
								</s:url> <s:a href="%{infoURL}">Info</s:a></td>
							<td><s:url id="editURL" action="editDocument">
									<s:param name="id" value="%{id}"></s:param>
								</s:url> <s:a href="%{editURL}">Edit</s:a></td>
							<td><s:url id="deleteURL" action="deleteDocument">
									<s:param name="id" value="%{id}"></s:param>
								</s:url> <s:a href="%{deleteURL}">Delete</s:a></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</s:if>
		<s:url id="addURL" action="addDocument" />
		<s:a href="%{addURL}" cssClass="btn btn-lg btn-success pull-right">Add</s:a>
	</div>
	<s:if test="info==true">
		<div class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						</button>
						<h4 class="modal-title">
								<s:url id="downloadInfoURL" action="downloadDocument">
									<s:param name="documentId" value="%{document.id}" />
								</s:url> <s:a href="%{downloadInfoURL}">
									<s:property value="document.name" />
								</s:a>
						</h4>
					</div>
					<div class="modal-body">


						<p>
							<strong>Upload Date: </strong>
							<s:date name="document.uploadDate" format="dd-MM-yyyy" />
						</p>
						<p>
							<strong>Owner: </strong>
							<s:property value="document.owner.name" />
						</p>
						<p>
							<strong>Description: </strong>
							<s:property value="document.description" />
						</p>
						<p>
							<strong>User Group: </strong>
							<s:property value="document.group.name" />
						</p>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			$(document).ready(function() {
				$(".modal.fade").modal('show');
				$('.hide-modal').click(function() {
					$("#DemoModal").modal('hide');
				});
			});
		</script>
	</s:if>

</body>
</html>