package com.yandz.steels.service;

import java.util.List;

import com.yandz.steels.entity.Permission;

public interface PermissionService extends BaseService<Permission> {
	public List<Permission> getPermissionByRoleId(int roleId);
}
