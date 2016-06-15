package com.alibaba.dubbo.config.annotation;

import java.lang.annotation.*;

/**
 * dubbo consumer
 * 客户端获取dubbo服务的注解，只需要通过注解来获取reference，替代XXX-consumer.xml配置进行
 * @author Bing
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DubboConsumer {
	
	String group() default "";
	//远程服务调用重试次数，不包括第一次调用，不需要重试请设为0
	//不使用默认为-1，则不进行注册，使用默认值
	int retries() default -1;
	/**
	 * 性能调优	对每个提供者的最大连接数，rmi、http、hessian等短连接协议表示限制连接数，dubbo等长连接协表示建立的长连接个数
	 * @return
	 */
	int connections() default -1;
	/**
	 * 性能调优
	 * 	
	 * 缺省使用<dubbo:consumer>的loadbalance	
	 * 负载均衡策略，可选值：random,roundrobin,leastactive，分别表示：随机，轮循，最少活跃调用
	 * @return
	 */
	String loadbalance() default "";
	/**
	 * 服务治理	
	 * 缺省使用<dubbo:consumer>的check	
	 * 启动时检查提供者是否存在，true报错，false忽略
	 * @return
	 */
	boolean check() default true;
	
	/**
	 * 	服务治理	
	 * 服务接口调用失败Mock实现类名，该Mock类必须有一个无参构造函数，
	 * 与Local的区别在于，Local总是被执行，而Mock只在出现非业务异常(比如超时，
	 * 网络异常等)时执行，Local在远程调用之前执行，Mock在远程调用后执行。
	 * @return
	 */
	 String mock() default "";
	
	/**
	 * 	性能调优	
	 * 客户端传输类型设置，如Dubbo协议的netty或mina。
	 * @return
	 */
	String client() default "";
	
	/**
	 * 性能调优	
	 * 集群方式，可选：failover/failfast/failsafe/failback/forking
	 * @return
	 */
	String cluster() default "";
	
	String protocol() default "";
	
    String version() default "";

    int timeout() default -1;
    /**
     * 添加监控注册
     * @return
     */
    String monitor() default "registry";
}
