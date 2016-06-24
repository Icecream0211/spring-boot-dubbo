package com.bing.serviceimpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.DubboConsumer;
import com.alibaba.dubbo.config.annotation.DubboService;
import com.bing.activity.api.domain.Activity;
import com.bing.activity.api.service.ActivityService;
import com.bing.activity.api.service.ActivityService2;
import com.bing.activity.api.service.ActivityService3;
import com.bing.domain.ActivityExample;
import com.bing.mapper.ActivityMapper;
import com.github.pagehelper.PageHelper;

@Component
@DubboService(interfaceClass=ActivityService.class)
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityMapper mapper;
	
	/*@DubboConsumer
	private ActivityService2 activityService2;
	
	@DubboConsumer
	private ActivityService3 activityService3;*/
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	@Cacheable(cacheNames = "activeityemail", key = "#email+#startpage+#pagesize")
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
		//activityService2.insertActivity(act);
		//activityService3.insertActivity2(act);
		return true;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,timeout=1000)
	@Override
	public boolean insertActivity2(Activity act) {
		throw new RuntimeException("插入异常");
	}
	@Override
	public void cleancache() {
		
	}
}
