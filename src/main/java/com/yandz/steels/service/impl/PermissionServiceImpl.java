package com.yandz.steels.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yandz.steels.dao.PermissionMapper;
import com.yandz.steels.entity.Permission;
import com.yandz.steels.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionMapper permissionMapper;


	@Override
	public boolean addObject(Permission t) {
		boolean flag = false;
		try {
			permissionMapper.insert(t);
			flag = true;
		} catch (Exception e) {
		}

		return flag;
	}

	@Override
	public boolean delObject(int id) {
		// 此外id 是roleId
		boolean flag = false;
		try {
			permissionMapper.deleteByPrimaryKey(id);
			flag = true;
		} catch (Exception e) {
		}

		return flag;
	}

	@Override
	public boolean updateObject(Permission t) {
		boolean flag = false;
		try {
			permissionMapper.updateByPrimaryKey(t);
			flag = true;
		} catch (Exception e) {
		}

		return flag;
	}

	@Override
	public List<Permission> getObjectList() {
		return permissionMapper.selectAll();
	}

	@Override
	public Permission getObjectById(int id) {
		return null;
	}

	@Override
	public List<Permission> getPermissionByRoleId(int roleId) {
		
		return permissionMapper.selectPermissionByRoleId(roleId);
	}

}
