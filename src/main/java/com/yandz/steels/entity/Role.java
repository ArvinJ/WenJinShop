package com.yandz.steels.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * 
 * @Title: Role.java
 * @Package com.yandz.steels.entity
 * @Description: t_role 角色表
 * @author: wenjin.zhu
 * @date: 2018年12月6日 下午2:16:16
 * @version V1.0
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 5829767282887952893L;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Integer id; // 主键标识
	private String roleName; // 角色名称
	private String roleDescription; // 角色描述
	private Integer rolePrivLevel; // 角色权限等级
	private Date createDate; // 创建时间
	private Date updateDate; // 更新时间
	private String createDateStr;
	private String updateDateStr;
	private Integer status; // 状态

	public String getCreateDateStr() {
		try {
			createDateStr = sdf.format(createDate);
		} catch (Exception e) {
		}
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getUpdateDateStr() {
		try {
			updateDateStr = sdf.format(updateDate);
		} catch (Exception e) {
		}
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription == null ? null : roleDescription.trim();
	}

	public Integer getRolePrivLevel() {
		return rolePrivLevel;
	}

	public void setRolePrivLevel(Integer rolePrivLevel) {
		this.rolePrivLevel = rolePrivLevel;
	}

	@JsonIgnore
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Role [sdf=" + sdf + ", id=" + id + ", roleName=" + roleName + ", roleDescription=" + roleDescription
				+ ", rolePrivLevel=" + rolePrivLevel + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", createDateStr=" + createDateStr + ", updateDateStr=" + updateDateStr + ", status=" + status + "]";
	}

}