package com.yandz.steels.entity;

import java.io.Serializable;

/**
 * 
 * 
 * @Title: User.java
 * @Package com.yandz.steels.entity
 * @Description: t_user 系统用户表
 * @author: wenjin.zhu
 * @date: 2018年12月6日 下午3:29:54
 * @version V1.0
 */
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 5680934584700838354L;

	private Integer id; // 主键标识
	private String name;// 用户名称
	private String pass;// 用户密码
	private Integer rolePrivLevel;// 角色权限等级
	private Integer roleId; // 所属角色
	private String idCard;// 身份证
	private Integer isOnline; // 是否在线
	private Integer isLocked; // 是否锁定
	private String mobile; // 手机号
	private String email; // 邮箱

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass == null ? null : pass.trim();
	}

	public Integer getRolePrivLevel() {
		return rolePrivLevel;
	}

	public void setRolePrivLevel(Integer rolePrivLevel) {
		this.rolePrivLevel = rolePrivLevel;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard == null ? null : idCard.trim();
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pass=" + pass + ", rolePrivLevel=" + rolePrivLevel + ", roleId="
				+ roleId + ", idCard=" + idCard + ", isOnline=" + isOnline + ", isLocked=" + isLocked + ", mobile="
				+ mobile + ", email=" + email + ", createDateStr=" + createDateStr + ", updateDateStr=" + updateDateStr
				+ ", status=" + status + "]";
	}

	
}