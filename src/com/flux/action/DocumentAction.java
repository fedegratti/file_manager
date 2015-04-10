package com.flux.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.flux.dao.DAO;
import com.flux.dao.DocumentDAOImpl;
import com.flux.dao.UserDAOImpl;
import com.flux.dao.UserGroupDAOImpl;
import com.flux.domain.Document;
import com.flux.domain.User;
import com.flux.domain.UserGroup;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DocumentAction extends ActionSupport implements
		ModelDriven<Document>, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private Document document = new Document();

	private List<Document> documentList = new ArrayList<Document>();
	private List<User> userList = new ArrayList<User>();
	private List<UserGroup> userGroupList = new ArrayList<UserGroup>();

	private DAO<Document> documentDAO = new DocumentDAOImpl();
	private DAO<User> userDAO = new UserDAOImpl();
	private DAO<UserGroup> userGroupDAO = new UserGroupDAOImpl();

	private long groupId;
	private long ownerId;
	private long documentId;
	private boolean info;

	private HttpServletRequest servletRequest;
	private File doc;
	private String docContentType;
	private String docFileName;
	private InputStream docInputStream;

	private String filePath;

	private String searchField;

	private int searchType;

	public Document getModel() {
		return document;
	}

	public String save() {
		try {
			filePath = servletRequest.getSession().getServletContext().getRealPath("/");

			File fileToCreate = new File(filePath, this.docFileName);
			FileUtils.copyFile(this.doc, fileToCreate);

		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		Date date = new Date();
		document.setUploadDate(date);

		document.setGroup(userGroupDAO.getById(groupId));
		document.setOwner(userDAO.getById(ownerId));
		document.setName(docFileName);

		documentDAO.save(document);
		return SUCCESS;
	}

	public String create() {
		userList = userDAO.list();
		userGroupList = userGroupDAO.list();
		return SUCCESS;
	}

	public String list() {
		documentList = documentDAO.list();

		if (documentId > 0) {
			document = documentDAO.getById(documentId);
		}

		return SUCCESS;
	}

	public String delete() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		try {

			String fileToDelete = filePath
					+ documentDAO.getById(
							Long.parseLong(request.getParameter("id")))
							.getName();

			File file = new File(fileToDelete);

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		documentDAO.delete(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public String edit() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		document = documentDAO.getById(Long.parseLong(request
				.getParameter("id")));

		groupId = document.getGroup().getId();
		ownerId = document.getOwner().getId();

		userList = userDAO.list();
		userGroupList = userGroupDAO.list();

		return SUCCESS;
	}

	public String download() throws Exception {
		filePath = servletRequest.getSession().getServletContext().getRealPath("/");
		File fileToDownload = new File(filePath
				+ documentDAO.getById(documentId).getName());
		docFileName = fileToDownload.getName();
		docInputStream = new FileInputStream(fileToDownload);
		return SUCCESS;
	}

	public String search() {
		System.out.println(searchType);

		switch (searchType) {
		case 1:
			documentList = ((DocumentDAOImpl) documentDAO)
					.searchByName(searchField);
			break;
		case 2:
			documentList = ((DocumentDAOImpl) documentDAO)
					.searchByOwner(searchField);
			break;
		case 3:
			documentList = ((DocumentDAOImpl) documentDAO)
					.searchByDate(searchField);
			break;
		}

		return SUCCESS;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public List<Document> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<UserGroup> getUserGroupList() {
		return userGroupList;
	}

	public void setUserGroupList(List<UserGroup> userGroupList) {
		this.userGroupList = userGroupList;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public String getDocContentType() {
		return this.docContentType;
	}

	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}

	public String getDocFileName() {
		return this.docFileName;
	}

	public long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(long documentId) {
		this.documentId = documentId;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public File getDoc() {
		return this.doc;
	}

	public void setDoc(File doc) {
		this.doc = doc;
	}

	public InputStream getDocInputStream() {
		return this.docInputStream;
	}

	public void setDocInputStream(InputStream docInputStream) {
		this.docInputStream = docInputStream;
	}

	public boolean isInfo() {
		return info;
	}

	public void setInfo(boolean info) {
		this.info = info;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

}
