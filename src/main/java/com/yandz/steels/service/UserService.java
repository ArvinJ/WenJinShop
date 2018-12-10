package com.yandz.steels.service;

import com.yandz.steels.entity.User;

public interface UserService extends BaseService<User> {

	public User findByName(String name);
}
