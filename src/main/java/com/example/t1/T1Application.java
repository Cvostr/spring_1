package com.example.t1;

import com.example.t1.config.DefaultAppConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class T1Application implements CommandLineRunner {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(DefaultAppConfig.class);

		context.getBean(AppService.class).work();
	}

	@Override
	public void run(String... args) {


	}
}
