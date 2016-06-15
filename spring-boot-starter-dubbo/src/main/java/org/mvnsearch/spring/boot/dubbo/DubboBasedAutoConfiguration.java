package org.mvnsearch.spring.boot.dubbo;

import com.alibaba.dubbo.config.annotation.DubboConsumer;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import org.springframework.beans.BeansException;

/**
 * dubbo based auto configuration
 * dubbo自动配置基类，客户端获取引用类的封装，referenceBean
 * @author Bing
 */
public class DubboBasedAutoConfiguration {

    /**
     * 生成Dubbo的reference bean
     *
     * @param interfaceClazz interface class
     * @param version        版本号
     * @param timeout        超时时间
     * @param <T>            服务接口
     * @return reference bean
     * @throws BeansException
     */
    protected <T> ReferenceBean<T> getConsumerBean(Class<T> interfaceClazz, String version, Integer timeout) throws BeansException {
        ReferenceBean<T> consumerBean = new ReferenceBean<T>();
        String canonicalName = interfaceClazz.getCanonicalName();
        consumerBean.setInterface(canonicalName);
        consumerBean.setId(canonicalName);
        consumerBean.setVersion(version);
        consumerBean.setTimeout(timeout);
        return consumerBean;
    }
    
    protected <T> ReferenceBean<T> getConsumerBean(Class<T> interfaceClazz, DubboConsumer dubboConsumer) throws BeansException {
    	ReferenceBean<T> consumerBean = new ReferenceBean<T>();
    	String canonicalName = interfaceClazz.getCanonicalName();
    	consumerBean.setInterface(canonicalName);
    	consumerBean.setId(canonicalName);
    	consumerBean.setVersion(dubboConsumer.version());
    	
    	/**
    	 * 新增加
    	 */
    	if(!(dubboConsumer.timeout()==-1)){
    		consumerBean.setTimeout(dubboConsumer.timeout());
    	}
    	consumerBean.setCheck(dubboConsumer.check());
    	if(!dubboConsumer.client().equals("")){
    		consumerBean.setClient(dubboConsumer.client());
    	}
    	if(!dubboConsumer.cluster().equals("")){
    		consumerBean.setCluster(dubboConsumer.cluster());
    	}
    	if(!(dubboConsumer.retries()==-1)){
    		consumerBean.setRetries(dubboConsumer.retries());
    	}
    	if(!(dubboConsumer.connections()==-1)){
    		consumerBean.setConnections(dubboConsumer.connections());
    	}
    	if(!dubboConsumer.loadbalance().equals("")){
    		consumerBean.setLoadbalance(dubboConsumer.loadbalance());
    	}
    	if(!(dubboConsumer.mock().equals(""))){
    		if(dubboConsumer.mock().equals("true")||dubboConsumer.mock().equals("false")){
    			consumerBean.setMock(Boolean.valueOf(dubboConsumer.mock()));
    		}else{
    			consumerBean.setMock(dubboConsumer.mock());
    		}
    	}
    	if(!dubboConsumer.protocol().equals("")){
    		consumerBean.setProtocol(dubboConsumer.protocol());
    	}
    	return consumerBean;
    }
}
