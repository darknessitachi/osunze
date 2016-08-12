package com.skycity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skycity.dao.ScheduleDao;
import com.skycity.entity.Schedule;
import com.skycity.service.ScheduleService;
import com.skycity.util.PageInfo;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired
	private ScheduleDao scheduleDao;
	
	public PageInfo<Schedule> query(PageInfo<Schedule> pageInfo,
			Schedule schedule) {
		return scheduleDao.queryPageInfo(pageInfo, schedule);
	}

}
