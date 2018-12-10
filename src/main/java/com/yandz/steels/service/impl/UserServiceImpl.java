package com.yandz.steels.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yandz.steels.dao.UserMapper;
import com.yandz.steels.entity.User;
import com.yandz.steels.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public User getObjectById(int id) {
		return userMapper.selectById(id);
	}

	@Override
	public boolean addObject(User t) {
		boolean flag = false;
		try {
			userMapper.insert(t);
			flag = true;
		} catch (Exception e) {
		}
		return flag;
	}

	@Override
	public boolean delObject(int id) {
		boolean flag = false;
		try {
			userMapper.deleteByPrimaryKey(id);
			flag = true;
		} catch (Exception e) {
		}
		return flag;
	}

	@Override
	public boolean updateObject(User t) {
		boolean flag = false;
		try {
			userMapper.updateByPrimaryKey(t);
			flag = true;
		} catch (Exception e) {
		}
		return flag;
	}

	@Override
	public List<User> getObjectList() {
		return userMapper.selectAll();
	}

}
