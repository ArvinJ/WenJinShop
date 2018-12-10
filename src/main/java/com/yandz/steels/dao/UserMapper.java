package com.yandz.steels.dao;

import com.yandz.steels.entity.User;

public interface UserMapper extends BaseDao<User> {
	public User findByName(String name);
}