package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.atguigu.myrule.MySelfRule;

@SpringBootApplication
@EnableEurekaClient
/**
 * @RibbonClient 注解作用：用来加载我们自定义的Ribbon配置类，来实现自定义的负载均衡策略,不自定义Ribbon配置类时，该注解可以不用加
 * 在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置策略生效，
 * 注意：1.自定义的配置类（MySelfRule.java）不能在@ComponentScan注解扫描的当前包及其子包下，
 *       也就是说不能在@SpringBootApplication（@SpringBootApplication注解中包含了@ComponentScan注解）注解标注的启动类所在的当前包及其子包下
 *       ，否则我们自定义的配置类（MySelfRule.java）会被所有的Ribbon客户端所共享，也就达不到我们特殊定制化的目的了
 *
 */
//@RibbonClient(name="MICROSERVICECLOUD-DEPT")
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
public class DeptConsumer80_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(DeptConsumer80_App.class, args);
	}
}
