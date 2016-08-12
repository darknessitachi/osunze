package com.skycity.dao.impl;

import org.springframework.stereotype.Repository;

import com.skycity.dao.CodeDao;
import com.skycity.entity.Code;

@Repository("codeDao")
public class CodeDaoImpl extends BaseDaoImpl<Code> implements CodeDao {
	public int delCode(Code code) {
		return getSqlSession().delete("code.delCode", code);
	}

	public Code getCode(Code code) {
		return getSqlSession().selectOne("code.getCode", code);
	}
	
	public int getCodeCount(Code code){
		return getSqlSession().selectOne("code.countByCode",code);
	}
}
