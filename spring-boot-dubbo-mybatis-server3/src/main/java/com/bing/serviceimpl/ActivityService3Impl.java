package com.bing.serviceimpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.DubboService;
import com.bing.activity.api.domain.Activity;
import com.bing.activity.api.service.ActivityService3;
import com.bing.domain.ActivityExample;
import com.bing.mapper.ActivityMapper;
import com.github.pagehelper.PageHelper;

@Component
@DubboService(interfaceClass=ActivityService3.class)
public class ActivityService3Impl implements ActivityService3 {
	@Autowired
	private ActivityMapper mapper;
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public List<Activity> getActivitysByEmail(String email, int startpage, int pagesize) {
		if(StringUtils.isEmpty(email)){
			throw new RuntimeException("email不能为空");
		}
		if(pagesize!=0){
			PageHelper.startPage(startpage, pagesize);
		}
		ActivityExample exam = new ActivityExample();
		exam.createCriteria().andEmailEqualTo(email);
		return mapper.selectByExample(exam);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,timeout=1000)
	public boolean insertActivity(Activity act) {
		mapper.insert(act);
		insertActivity2(act);
		return true;
	}
	@Override
	public boolean insertActivity2(Activity act) {
		throw new RuntimeException("插入异常");
	}
}
