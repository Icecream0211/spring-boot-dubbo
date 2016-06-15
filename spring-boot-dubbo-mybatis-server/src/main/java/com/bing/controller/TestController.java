package com.bing.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bing.activity.api.domain.Activity;
import com.bing.activity.api.service.ActivityService;
import com.bing.domain.ActivityExample;
import com.bing.mapper.ActivityMapper;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping
public class TestController {

	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping("/likeName")
	public List<Activity> likeName(@RequestParam String name) {
		PageHelper.startPage(1, 1);
		PageHelper.startPage(3, 5, true);
		ActivityExample exam = new ActivityExample();
		exam.createCriteria().andEmailEqualTo("icecream0211@gmail.com");
		return activityMapper.selectByExample(exam);
	}
	
	@RequestMapping("/add")
	public List<Activity> addAct() {
		Activity act  = new Activity();
		act.setActivityId(1);
		act.setIdNumber("410526198902111652");
		act.setEmail("aaaaaa@gmail.com");
		act.setName("Bing");
		act.setPhoneNum("18721623282");
		act.setStatus("1");
		activityService.insertActivity(act);
		return null;
	}

	/*@RequestMapping("/pathtest")
	public String pathTest() {
		ClassPathResource classPathResource = new ClassPathResource("static/something.txt");
		ClassPathResource classPathResource2 = new ClassPathResource("something2.txt");
		ClassPathResource classPathResource3 = new ClassPathResource("source/something3.txt");
		try { 
			InputStream in = classPathResource.getInputStream();
			//System.out.println(classPathResource.getFile().getAbsolutePath()); 
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			System.out.println(br.readLine());
			InputStream in2 = classPathResource2.getInputStream();
			//System.out.println(classPathResource.getFile().getAbsolutePath()); 
			BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
			System.out.println(br2.readLine());
			InputStream in3 = classPathResource3.getInputStream();
			//System.out.println(classPathResource.getFile().getAbsolutePath()); 
			BufferedReader br3 = new BufferedReader(new InputStreamReader(in3));
			System.out.println(br3.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return "aaaa";
	}*/
}
