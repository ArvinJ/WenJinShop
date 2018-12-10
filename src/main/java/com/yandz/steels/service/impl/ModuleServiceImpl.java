package com.yandz.steels.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yandz.steels.dao.ModuleMapper;
import com.yandz.steels.entity.Module;
import com.yandz.steels.service.ModuleService;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
	@Resource
	private ModuleMapper moduleMapper;

	@Override
	public Module getModuleById(int moduleId) {
		//根据id查询记录
		return moduleMapper.selectById(moduleId);
	}

	@Override
	public boolean addModule(Module module) {
		//增加记录
		boolean result = false;
		try {
			moduleMapper.insert(module);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean delModule(int moduleId) {
		//根据id逻辑删除记录
		boolean result = false;
		try {
			moduleMapper.deleteByPrimaryKey(moduleId);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean updateModule(Module module) {
		//根据module对象不为NULL项更新记录
		boolean result = false;
		try {
			moduleMapper.updateByPrimaryKey(module);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Module> getModuleList() {
		//根据查询当前表记录
		return moduleMapper.selectAll();
	}

}
