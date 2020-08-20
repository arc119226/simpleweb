package com.hoyetec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
/**
 * 
*****************************************************************************
*
*    Page/Class Name: ServiceApplication.java
*              Title: api service程式進入點
*        Description: spring boot application main method
*          Copyright: Hoyetec
*            Company: Hoyetec
*        Create Date: 2019年8月23日
*      Last Modifier: Arc Liu
*   Last Modify Date: 2019年8月23日
*             Author: Arc Liu  
*
*****************************************************************************
 */

@SpringBootApplication
public class ServiceApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
}
