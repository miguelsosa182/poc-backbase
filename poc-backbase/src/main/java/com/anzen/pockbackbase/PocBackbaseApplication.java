package com.anzen.pockbackbase;

import org.apache.camel.component.restlet.RestletComponent;
import org.restlet.ext.spring.SpringServerServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import org.restlet.Component;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ImportResource("classpath:backbase-integration-service.xml")
public class PocBackbaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocBackbaseApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean restletServletBean() {

		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new SpringServerServlet(),
				"/rs/*");
		Map<String, String> initParams = new HashMap<>(1);
		initParams.put(SpringServerServlet.Component_BEAN_PARAM_NAME, "restletComponent");
		servletRegistrationBean.setInitParameters(initParams);
		servletRegistrationBean.setName("RestletServlet");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

	@Bean
	public Component restletComponent() {
		return new Component();
	}

	@Bean
	public RestletComponent restletComponentService() {
		return new RestletComponent(restletComponent());
	}
}
