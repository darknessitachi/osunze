package com.skycity.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skycity.dao.CodeDao;
import com.skycity.entity.Code;
import com.skycity.service.CodeService;
import com.skycity.util.PageInfo;


@Service("codeService")
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeDao codeDao;
	
	public PageInfo<Code> query(PageInfo<Code> pageInfo, Code code) {
		return codeDao.queryPageInfo(pageInfo, code);
	}

	@Transactional
	public int addCode(Code code) {
		return codeDao.add(code);
	}

	@Transactional
	public int updateCode(Code code) {
		return codeDao.modify(code);
	}

	@Transactional
	public int deleteCode(Code code) {
		return codeDao.delCode(code);
	}

	public Code getCode(Code code){
		return codeDao.getCode(code);
	}

	public int getCodeCount(Code code) {
		return codeDao.getCodeCount(code);
	}

	public List<Code> getList(String name) {
		Code code  = new Code();
		code.setParentCode(name);
		return codeDao.queryAll(code);
	}

	@Override
	public String getCodeName(List<Code> codeList, String codeType) {
		Optional<Code> opt=codeList.stream().filter(s->codeType.equalsIgnoreCase(s.getCodeType())).findFirst();
		if(opt==null){
			return "";
		}else{
			return opt.get().getCodeName();
		}
	}
}
