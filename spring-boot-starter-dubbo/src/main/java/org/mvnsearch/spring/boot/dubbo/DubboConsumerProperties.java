package org.mvnsearch.spring.boot.dubbo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.dubbo.consumer")
public class DubboConsumerProperties {
	/**
	 * 默认30秒
	 */
	private Integer timeout = 30000;
	private boolean check = true;
	/**
	 * 监控方式；默认写registry
	 * 或者直接写地址，不推荐
	 */
	private String monitor;
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public String getMonitor() {
		return monitor;
	}
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
}
