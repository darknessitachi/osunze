package com.skycity.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skycity.entity.Schedule;
import com.skycity.framework.UIFacade;
import com.skycity.framework.utility.JsonUtil;
import com.skycity.service.ScheduleService;
import com.skycity.util.PageInfo;

@Controller("Schedule")
@RequestMapping("/Schedule/")
public class ScheduleController extends UIFacade<Schedule>{

	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping("queryList")
	public void queryList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		String pageIndex = $V("page");
		String pageSize = $V("rows");
		page.setPageSize(Integer.parseInt(pageSize));
		page.setPageNo(Integer.parseInt(pageIndex));
		Schedule schedule = new Schedule();
		PageInfo<Schedule> pageInfo = scheduleService.query(page, schedule);
		String jsonStr = JsonUtil.bean2json(pageInfo);
		response.getWriter().write(jsonStr);
	}
}
