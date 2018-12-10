package com.yandz.steels.dao;

import com.yandz.steels.entity.Module;
import java.util.List;

public interface ModuleMapper {
	int insert(Module record);

	List<Module> selectAll();

	int deleteByPrimaryKey(Integer id);

	int insertSelective(Module m);

	Module selectById(Integer id); 

	int updateByPrimaryKey(Module m);
}