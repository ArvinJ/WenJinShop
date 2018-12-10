package com.yandz.steels.dao;

import java.util.List;

import com.yandz.steels.entity.Permission;

public interface PermissionMapper extends BaseDao<Permission>{
	List<Permission> selectPermissionByRoleId(Integer roleId);
}