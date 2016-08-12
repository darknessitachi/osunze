package com.skycity.framework.interceptor;

import java.text.MessageFormat;
import java.util.List;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.skycity.entity.Permission;
import com.skycity.service.PermissionService;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2016年7月25日 下午8:40:41 
 * 程序的简单说明 
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{
	
	@Autowired
	private PermissionService permissionsService;
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	private static final String PERMISSION_STRING="roles[{0}]";
	private String filterChainDefinitions;
	
	@Override
	public Section getObject() throws Exception {
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
//		section.clear();
//		List<Permission> list = permissionsService.getAllMenus();
//		list.stream().filter(s->s.getType().equals("1")).forEach(s->{
//			section.put(s.getResUrl(),MessageFormat.format(PERMISSION_STRING,"anyRoles[sysadmin,manager]"));
//		});
		return section;
	}

	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
