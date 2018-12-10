package com.yandz.steels.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Title: Permission.java
 * @Package com.yandz.steels.entity
 * @Description: t_permission 表 实体类
 * @author: wenjin.zhu
 * @date: 2018年12月6日 上午11:29:41
 * @version V1.0
 */
public class Permission {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Integer roleId; // 角色id
	private Integer moduleId;// 模块id
	private Integer operationId;// 操作权限允许操作值
	/**
	 * 值为1，增删改查; 值为2，查; 值为3，增查; 值为4，增删查; 值为5，增改查;6,不允许操作;
	 */

	private Date createDate; // 创建时间
	private Date updateDate;// 更新时间
	private String createDateStr;
	private String updateDateStr;
	private Integer status;// 状态

	public String getCreateDateStr() {
		try {
			createDateStr = sdf.format(createDate);
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getOperationId() {
		return operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
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
		return "Permission [sdf=" + sdf + ", roleId=" + roleId + ", moduleId=" + moduleId + ", operationId="
				+ operationId + ", createDate=" + createDate + ", updateDate=" + updateDate + ", createDateStr="
				+ createDateStr + ", updateDateStr=" + updateDateStr + ", status=" + status + "]";
	}

}