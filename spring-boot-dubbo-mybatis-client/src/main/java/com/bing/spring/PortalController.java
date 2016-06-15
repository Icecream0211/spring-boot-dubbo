package com.bing.spring;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.DubboConsumer;
import com.bing.activity.api.domain.Activity;
import com.bing.activity.api.service.ActivityService;

/**
 * portal controller
 *
 * @author linux_china
 */
@RestController
public class PortalController  {
	@DubboConsumer(timeout=10000)
	private ActivityService activityService;
    @RequestMapping(value="/activitys",method=RequestMethod.GET)
    public List<Activity> activitys(@RequestParam String email,@RequestParam int page,@RequestParam int pagesize) {
        return activityService.getActivitysByEmail(email, page, pagesize);
    }
    @RequestMapping(value="/activitys" ,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
    public boolean newActivitys(@RequestBody Activity activity) {
    	return activityService.insertActivity(activity);
    }
}
