package org.mvnsearch;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bing.SpringBootDubboServerApplication;
import com.bing.activity.api.domain.Activity;
import com.bing.activity.api.service.ActivityService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootDubboServerApplication.class)
@WebAppConfiguration
public class SpringBootDubboServerApplicationTests {
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	RedisConnectionFactory connectionFactory;
	
	@Autowired
	ActivityService activityService;
	
	/*@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps;*/
	
	@Resource(name="redisTemplate")
	private ValueOperations<String,String> valueOps;
	
	@Test
	public void contextLoads() throws UnsupportedEncodingException {
		//redisTemplate.opsForValue().set("aaa", "bbb");
		valueOps.set("name1", "王冰2");
		valueOps.set("name2", "王冰2");
		valueOps.set("name3", "王冰3");
		valueOps.set("name4", "王冰4");
		valueOps.set("name5", "王冰5");
		valueOps.set("name6", "王冰6");
		valueOps.set("name7", "王冰7");
		valueOps.set("name8", "王冰8");
		valueOps.set("name9", "王冰9");
		System.out.println(valueOps.get("name1"));
		System.out.println(valueOps.get("name2"));
		System.out.println(valueOps.get("name3"));
		System.out.println(valueOps.get("name4"));
		System.out.println(valueOps.get("name5"));
		System.out.println(valueOps.get("name6"));
		System.out.println(valueOps.get("name7"));
		System.out.println(valueOps.get("name8"));
		System.out.println(valueOps.get("name9"));
	}
	@Test
	public void cachetest() throws UnsupportedEncodingException {
		System.out.println("第一次执行");
		List<Activity> acts = activityService.getActivitysByEmail("icecream0211@gmail.com", 1, 20);
		System.out.println("第二次执行");
		List<Activity> acts2 = activityService.getActivitysByEmail("icecream0211@gmail.com", 1, 10);
		System.out.println(acts2.size());
		for(Activity act:acts2){
			System.out.println(act.getIdNumber());
		}
	}

}
