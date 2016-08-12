package com.skycity.dao.impl;

import org.springframework.stereotype.Repository;

import com.skycity.dao.ScheduleDao;
import com.skycity.entity.Schedule;

@Repository("scheduleDao")
public class ScheduleDaoImpl extends BaseDaoImpl<Schedule> implements ScheduleDao{

}
