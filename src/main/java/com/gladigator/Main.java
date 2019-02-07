package com.gladigator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(exclude = { WebMvcAutoConfiguration.class , SecurityAutoConfiguration.class })
@ImportResource(locations = {"classpath:com/gladigator/Configs/sah-servlet.xml", "classpath:com/gladigator/Configs/dao-context.xml", "classpath:com/gladigator/Configs/security-context.xml", "classpath:com/gladigator/Configs/service-context.xml"})
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
