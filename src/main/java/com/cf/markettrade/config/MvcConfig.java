package com.cf.markettrade.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * URL-mapping configuration class
 * 
 * @author pangio
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = Logger.getLogger(MvcConfig.class);

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
        logger.info("Mapping URL with views...");
		registry.addViewController("/statistics").setViewName("statistics");
		registry.addViewController("/error").setViewName("error");
	}
}
