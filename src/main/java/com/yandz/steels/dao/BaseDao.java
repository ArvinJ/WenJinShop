package com.yandz.steels.dao;

import java.util.List;

public interface BaseDao<T> {
	// 添加
	int insert(T t);

	// 查询所有
	List<T> selectAll();

	// 根据id 逻辑删除
	int deleteByPrimaryKey(Integer id);

	// 根据id 查询记录
	T selectById(Integer id);

	// 根据对象非空项 进行更新
	int updateByPrimaryKey(T t);
}
