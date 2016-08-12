package com.skycity.service;

import com.skycity.entity.Schedule;
import com.skycity.util.PageInfo;

public interface ScheduleService {
	
	public PageInfo<Schedule> query(PageInfo<Schedule> pageInfo, Schedule schedule);
}
