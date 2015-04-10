package com.flux.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.flux.dao.DAO;
import com.flux.dao.UserDAOImpl;
import com.flux.dao.UserGroupDAOImpl;
import com.flux.domain.User;
import com.flux.domain.UserGroup;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;

	private User user = new User();

	private List<User> userList = new ArrayList<User>();
	private List<UserGroup> userGroupList = new ArrayList<UserGroup>();

	private DAO<User> userDAO = new UserDAOImpl();
	private DAO<UserGroup> userGroupDAO = new UserGroupDAOImpl();

	private long groupId;

	public User getModel() {
		return user;
	}

	/**
	 * To save or update user.
	 * 
	 * @return String
	 */
	public String save() {
		user.setGroup(userGroupDAO.getById(groupId));

		userDAO.save(user);
		return SUCCESS;
	}

	public String create() {
		userGroupList = userGroupDAO.list();
		return SUCCESS;
	}

	public String list() {
		userList = userDAO.list();
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
		user = userDAO.getById(Long.parseLong(request.getParameter("id")));

		groupId = user.getGroup().getId();
		userGroupList = userGroupDAO.list();

		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

}
