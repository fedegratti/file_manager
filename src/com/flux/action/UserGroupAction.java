package com.flux.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.flux.dao.DAO;
import com.flux.dao.UserGroupDAOImpl;
import com.flux.domain.UserGroup;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserGroupAction extends ActionSupport implements
		ModelDriven<UserGroup> {

	private static final long serialVersionUID = 1L;

	private UserGroup userGroup = new UserGroup();
	private List<UserGroup> userGroupList = new ArrayList<UserGroup>();
	private DAO<UserGroup> userDAO = new UserGroupDAOImpl();

	public UserGroup getModel() {
		return userGroup;
	}

	public String create() {
		return SUCCESS;
	}

	public String save() {
		userDAO.save(userGroup);
		return SUCCESS;
	}

	public String list() {
		userGroupList = userDAO.list();
		return SUCCESS;
	}

	public String delete() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		userDAO.delete(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public String edit() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		userGroup = userDAO.getById(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public List<UserGroup> getUserGroupList() {
		return userGroupList;
	}

	public void setUserGroupList(List<UserGroup> userGroupList) {
		this.userGroupList = userGroupList;
	}

}
