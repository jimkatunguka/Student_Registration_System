package com.group3.courseenrollment;

import com.group3.courseenrollment.configuration.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableConfigurationProperties(ApplicationProperties.class)
public class CourseEnrollmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseEnrollmentApplication.class, args);
	}



}
