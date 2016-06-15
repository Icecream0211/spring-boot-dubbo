package org.mvnsearch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bing.SpringBootDubboServerApplication3;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootDubboServerApplication3.class)
@WebAppConfiguration
public class SpringBootDubboServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
