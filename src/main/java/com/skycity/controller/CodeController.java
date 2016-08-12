package com.skycity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skycity.entity.Code;
import com.skycity.framework.UIFacade;
import com.skycity.framework.i18n.LangUtil;
import com.skycity.framework.utility.JsonUtil;
import com.skycity.framework.utility.StringUtil;
import com.skycity.service.CodeService;
import com.skycity.util.PageInfo;

@Controller("Code")
@RequestMapping("/Code/")
public class CodeController extends UIFacade<Code> {
	@Autowired
	private CodeService codeService;

	@RequestMapping("queryList")
	public void queryList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		String pageIndex = $V("page");
		String pageSize = $V("rows");
		page.setPageSize(Integer.parseInt(pageSize));
		page.setPageNo(Integer.parseInt(pageIndex));
		Code qrcode = new Code();
		qrcode.setQrCode($V("qrCode"));
		qrcode.setParentCode($V("parentCode"));
		PageInfo<Code> pageInfo = codeService.query(page, qrcode);
		response.getWriter().write(objectMapper.writeValueAsString(pageInfo));
	}
	
	@RequestMapping("saveCode")
	public void saveCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		try {
			Code code = objectMapper.readValue($V("_SEND_DATA"), Code.class);
			if("add".equals(code.getOperator())){
				if(StringUtil.isNull(code.getParentCode())){
					code.setParentCode("0");
				}
				int totalCount = codeService.getCodeCount(code);
				if(totalCount>0){
					Response.setFailedMessage(LangUtil.get("Code.HaveSameCode"));
				}else{
					codeService.addCode(code);
					Response.setSuccessMessage(LangUtil.get("Code")+LangUtil.get("Common.AddSuccess"));
				}
			}else{
				codeService.updateCode(code);
				Response.setSuccessMessage(LangUtil.get("Code")+LangUtil.get("Common.ModifySuccess"));
			}
			response.getWriter().write(Response.toJson());
		}catch (Exception e) {
			Response.setFailedMessage(LangUtil.get("Code")+LangUtil.get("Common.AddOrUpdateFailed")+",cause["+e.getMessage()+"]");
			response.getWriter().write(Response.toJson());
		}
	}
	
	@RequestMapping("updCode")
	public String updCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		try {
			Code cd = new Code();
			cd.setCodeType($V("codeType"));
			cd.setParentCode($V("parentCode"));
			Code code = codeService.getCode(cd);
			request.setAttribute("code", code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/Platform/CodeDialog";
	}
	
	@RequestMapping("delCode")
	public void delCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		String[] ids =StringUtil.splitEx($V("IDs"),",");
		String parentCode = $V("parentCode");
		boolean isAllDelete = true;
		if(StringUtil.isNotEmpty(parentCode)){
			for(String id:ids){
				Code subcode = new Code();
				subcode.setCodeType(id);
				subcode.setParentCode(parentCode);
				Code curCode = codeService.getCode(subcode);
				if("Y".equals(curCode.getSystem())){
					isAllDelete = false;
					break;
				}else{
					codeService.deleteCode(subcode);
				}
			}
		}else{
			for(String id:ids){
				Code pCode = new Code();
				pCode.setCodeType(id);
				Code curCode = codeService.getCode(pCode);
				if("Y".equals(curCode.getSystem())){
					isAllDelete = false;
					break;
				}else{
					codeService.deleteCode(pCode);
					Code subcode = new Code();
					subcode.setParentCode(id);
					codeService.deleteCode(subcode);
				}
			}
		}
		if(isAllDelete){
			Response.setSuccessMessage(LangUtil.get("Code")+LangUtil.get("Common.DeleteSuccess"));
			response.getWriter().write(Response.toJson());
		}else{
			Response.setFailedMessage("Code is System Code,Can't be allow Delete!");
			response.getWriter().write(Response.toJson());
		}
	}
	
	@RequestMapping("codeItem")
	public String addCodeItem(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		String codeType = $V("codeType");
		request.setAttribute("codeType", codeType);
		return "/Platform/CodeItem";
	}
	
	@RequestMapping("getListByCodeName")
	public void getListByCodeName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		List<Code> codeList = codeService.getList($V("name"));
		String jsonStr = JsonUtil.list2json(codeList);
		response.getWriter().write(jsonStr);
	}
}
