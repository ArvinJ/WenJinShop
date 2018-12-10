package com.yandz.steels.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * 
 * @Title: Module.java
 * @Package com.yandz.steels.entity
 * @Description: t_module 模块表
 * @author: wenjin.zhu
 * @date: 2018年12月6日 下午2:17:23
 * @version V1.0
 */
public class Module {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Integer id;// 主键标识
	private String moduleName;// 模块名称
	private Integer moduleParent;// 模块上级
	private String moduleHierarchy;// 层级详情
	private Integer moduleLevel;// 级别
	private String iconName;// 图标名
	private String moduleUrl;// 对象链接
	private Date createDate;// 创建时间
	private Date updateDate;// 更新时间
	private Integer status;// 状态 默认为1,逻辑删除状态为0。
	private String createDateStr;
	private String updateDateStr;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName == null ? null : moduleName.trim();
	}

	public Integer getModuleParent() {
		return moduleParent;
	}

	public void setModuleParent(Integer moduleParent) {
		this.moduleParent = moduleParent;
	}

	public String getModuleHierarchy() {
		return moduleHierarchy;
	}

	public void setModuleHierarchy(String moduleHierarchy) {
		this.moduleHierarchy = moduleHierarchy == null ? null : moduleHierarchy.trim();
	}

	public Integer getModuleLevel() {
		return moduleLevel;
	}

	public void setModuleLevel(Integer moduleLevel) {
		this.moduleLevel = moduleLevel;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName == null ? null : iconName.trim();
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl == null ? null : moduleUrl.trim();
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
		return "Module [sdf=" + sdf + ", id=" + id + ", moduleName=" + moduleName + ", moduleParent=" + moduleParent
				+ ", moduleHierarchy=" + moduleHierarchy + ", moduleLevel=" + moduleLevel + ", iconName=" + iconName
				+ ", moduleUrl=" + moduleUrl + ", createDate=" + createDate + ", updateDate=" + updateDate + ", status="
				+ status + ", createDateStr=" + createDateStr + ", updateDateStr=" + updateDateStr + "]";
	}

}