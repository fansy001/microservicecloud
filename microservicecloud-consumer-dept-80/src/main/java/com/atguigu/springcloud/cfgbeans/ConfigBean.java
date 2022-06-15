package com.atguigu.springcloud.cfgbeans;

import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;

@Configuration
public class ConfigBean //boot -->spring   applicationContext.xml --- @Configuration配置   ConfigBean = applicationContext.xml
{
	/**
	 * @LoadBalanced 作用： 让这个RestTemplate在请求时拥有客户端负载均衡的能力，RestTemplate 这个可以理解成为客服端
	 *   默认的负载均衡的机制是轮询
	 * @return
	 */
	@Bean
	@LoadBalanced//Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端       负载均衡的工具。
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	/**
	 * 定义负载均衡的策略
	 * @return
	 */
	@Bean
	public IRule myRule()
	{
		//return new RoundRobinRule(); //轮询策略
		//return new RandomRule();//随机策略
		return new RetryRule();	//先按照轮询策略，如果获取服务失败则在指定的时间内会进行重试，获取当前可用的服务然后进行轮询
	}
}

//@Bean
//public UserServcie getUserServcie()
//{
//	return new UserServcieImpl();
//}
// applicationContext.xml == ConfigBean(@Configuration)
//<bean id="userServcie" class="com.atguigu.tmall.UserServiceImpl">