package com.bing.activity.api.service;

import java.util.List;

import com.bing.activity.api.domain.Activity;

public interface ActivityService {
	public List<Activity> getActivitysByEmail(String email,int startpage,int pagesize);
	public boolean insertActivity(Activity act);
	public boolean insertActivity2(Activity act);
}
