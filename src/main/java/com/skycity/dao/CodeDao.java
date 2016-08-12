package com.skycity.dao;

import com.skycity.entity.Code;

public interface CodeDao extends BaseDao<Code>{
	
	public int delCode(Code code);
	
	public Code getCode(Code code);
	
	public int getCodeCount(Code code);
	
}
