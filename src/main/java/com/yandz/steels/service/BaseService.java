package com.yandz.steels.service;

import java.util.List;

public interface BaseService<T> {

	public T getObjectById(int id);

	public boolean addObject(T t);

	public boolean delObject(int id);// 逻辑删除，把status改为0

	public boolean updateObject(T t);

	public List<T> getObjectList();
}
