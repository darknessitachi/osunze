package com.skycity.service;

import java.util.List;

import com.skycity.entity.Code;
import com.skycity.util.PageInfo;

public interface CodeService {
	
	public PageInfo<Code> query(PageInfo<Code> pageInfo,Code code);
	
	public List<Code> getList(String name);
	
	public int addCode(Code code);
	
	public int updateCode(Code code);
	
	public int deleteCode(Code code);
	
	public Code getCode(Code code);
	
	public int getCodeCount(Code code);
	
	/**
	 * 从字典集合列表中根据字典值得到字典名称
	 * @param codeList
	 * 			字典集合
	 * @param codeType
	 * 			字典值
	 * @return
	 * 			字典名称
	 */
	public String getCodeName(List<Code> codeList,String codeType);
}
