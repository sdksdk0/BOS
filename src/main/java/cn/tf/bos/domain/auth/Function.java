package cn.tf.bos.domain.auth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Function {
	
	private String id; // uuid
	private String name; // 功能名称
	private String description; // 功能描述

	// 菜单项功能
	private String generateMenu; // 是否生成菜单
	private int zindex; // 菜单项优先级
	private String page; // 点击菜单 跳转页面

	private Function parentFunction;// 父功能点
	private Set<Function> childrenFunctions = new HashSet<Function>(); // 子功能点

	// 一个Function 属于很多 Role
	private Set<Role> roles = new HashSet<Role>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenerateMenu() {
		return generateMenu;
	}

	public void setGenerateMenu(String generateMenu) {
		this.generateMenu = generateMenu;
	}

	public int getZindex() {
		return zindex;
	}

	public void setZindex(int zindex) {
		this.zindex = zindex;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Function getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}

	public Set<Function> getChildrenFunctions() {
		return childrenFunctions;
	}

	public void setChildrenFunctions(Set<Function> childrenFunctions) {
		this.childrenFunctions = childrenFunctions;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getInfo() {
		return name + "(" + id + ")";
	}

	public String getParentId() {
		if (parentFunction == null) {
			// 这是根节点
			return "0";
		} else {
			return parentFunction.getId();
		}
	}

	public boolean getClick() {
		if (page == null || page.trim().length() == 0) {
			// 没有page属性
			return false;
		} else {
			return true;
		}
	}
}
