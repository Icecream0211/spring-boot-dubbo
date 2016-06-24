package org.mvnsearch.spring.boot.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * dubbo auto configuration
 *dubbo自动配置，完成服务端以及客户端的必要的文件配置，比如ApplicationConfig，protocolCOnfig配置等
 *
 *添加dubbo埋点，以及健康检查埋点
 * @author Bing
 */
@Configuration
@EnableConfigurationProperties({DubboProperties.class,DubboConsumerProperties.class,DubboProviderProperties.class,DubboRegistryProperties.class})
public class DubboAutoConfiguration {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private DubboProperties properties;
    
    @Autowired
    private DubboProviderProperties providerProperties;
    
    
    @Autowired
    private DubboConsumerProperties consumerProperties;
    
    @Autowired
    private DubboRegistryProperties registryProperties;
    
    
    @Bean
    @ConditionalOnMissingBean
    public ConsumerConfig dubboConsumerConfig() {
    	ConsumerConfig consumerConfig = new ConsumerConfig();
    	consumerConfig.setTimeout(consumerProperties.getTimeout());
    	consumerConfig.setCheck(consumerProperties.isCheck());
    	return consumerConfig;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public ConsumerMonitorConfig dubboConsumerMonitorConfig(){
    	System.out.println("初始化Consumer Monitor Config--->" + consumerProperties.getMonitor());
    	ConsumerMonitorConfig  conMonitorConfig = new ConsumerMonitorConfig();
    	conMonitorConfig.setProtocol(consumerProperties.getMonitor());
    	return conMonitorConfig;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public ProviderMonitorConfig dubboProviderMonitorConfig(){
    	System.out.println("初始化Provider Monitor Config--->" + providerProperties.getMonitor());
    	ProviderMonitorConfig  proMonitorConfig = new ProviderMonitorConfig();
    	proMonitorConfig.setProtocol(providerProperties.getMonitor());
    	return proMonitorConfig;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public ProviderConfig dubboProviderConfig() {
    	ProviderConfig providerConfig = new ProviderConfig();
    	providerConfig.setTimeout(providerProperties.getTimeout());
    	return providerConfig;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public ApplicationConfig dubboApplicationConfig() {
        ApplicationConfig appConfig = new ApplicationConfig();
        appConfig.setName(properties.getApp());
        return appConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public ProtocolConfig dubboProtocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(properties.getProtocol());
        protocolConfig.setPort(properties.getPort());
        protocolConfig.setThreads(properties.getThreads());
        return protocolConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public RegistryConfig dubboRegistryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        if(this.registryProperties!=null){
        	if(registryProperties.getId()!=null&&registryProperties.getId().trim().length()!=0){
        		registryConfig.setId(registryProperties.getId());
        	}
        	if(registryProperties.getAddress()!=null&&registryProperties.getAddress().trim().length()!=0){
        		registryConfig.setAddress(registryProperties.getAddress());
        	}
        	if(registryProperties.getFile()!=null&&registryProperties.getFile().trim().length()!=0){
        		registryConfig.setFile(registryProperties.getFile());
        	}
        	if(registryProperties.getPassword()!=null&&registryProperties.getPassword().trim().length()!=0){
        		registryConfig.setPassword(registryProperties.getPassword());
        	}
        	if(registryProperties.getUsername()!=null&&registryProperties.getUsername().trim().length()!=0){
        		registryConfig.setUsername(registryProperties.getUsername());
        	}
        	if(registryProperties.getProtocol()!=null&&registryProperties.getProtocol().trim().length()!=0){
        		registryConfig.setProtocol(registryProperties.getProtocol());
        	}
        	if(registryProperties.getTransport()!=null&&registryProperties.getTransport().trim().length()!=0){
        		registryConfig.setTransporter(registryProperties.getTransport());
        	}
        	
        	if(registryProperties.getCheck()!=null){
        		registryConfig.setCheck(registryProperties.getCheck());
        	}
        	if(registryProperties.getDynamic()!=null){
        		registryConfig.setDynamic(registryProperties.getDynamic());
        	}
        	if(registryProperties.getRegister()!=null){
        		registryConfig.setRegister(registryProperties.getRegister());
        	}
        	
        	if(registryProperties.getSubscribe()!=null){
        		registryConfig.setSubscribe(registryProperties.getSubscribe());
        	}
        	
        	if(registryProperties.getPort()!=null){
        		registryConfig.setPort(registryProperties.getPort());
        	}
        	if(registryProperties.getSession()!=null){
        		registryConfig.setSession(registryProperties.getSession());
        	}
        	if(registryProperties.getTimeout()!=null){
        		registryConfig.setTimeout(registryProperties.getTimeout());
        	}
        }
        registryConfig.setAddress(properties.getRegistry());
        return registryConfig;
    }

    @Bean
    public DubboOperationEndpoint dubboOperationEndpoint() {
        return new DubboOperationEndpoint();
    }

    @Bean
    public DubboHealthIndicator dubboHealthIndicator() {
        return new DubboHealthIndicator();
    }

    @Bean
    public DubboEndpoint dubboEndpoint() {
        return new DubboEndpoint();
    }

    @Bean
    public DubboMetrics dubboConsumerMetrics() {
        return new DubboMetrics();
    }

}
