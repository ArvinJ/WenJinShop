package com.yandz.steels.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yandz.steels.dao.RoleMapper;
import com.yandz.steels.entity.Role;
import com.yandz.steels.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper roleMapper;

	@Override
	public Role getObjectById(int id) {
		return roleMapper.selectById(id);
	}

	@Override
	public boolean addObject(Role t) {
		boolean result = false;
		try {
			roleMapper.insert(t);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean delObject(int id) {
		boolean result = false;
		try {
			roleMapper.deleteByPrimaryKey(id);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean updateObject(Role t) {
		boolean result = false;
		try {
			roleMapper.updateByPrimaryKey(t);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Role> getObjectList() {
		return roleMapper.selectAll();
	}

}
