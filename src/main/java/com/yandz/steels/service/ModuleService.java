package com.yandz.steels.service;

import java.util.List;

import com.yandz.steels.entity.Module;

public interface ModuleService {
	public Module getModuleById(int moduleId);

	public boolean addModule(Module module);

	public boolean delModule(int moduleId);// 逻辑删除，把status改为0

	public boolean updateModule(Module module);

	public List<Module> getModuleList();
}
