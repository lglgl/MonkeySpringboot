package cn.dahouzi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class  })
public class Application  {
	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		System.out.println("@SpringBootApplication正在启动");
		context = SpringApplication.run(Application.class, new String[] { "--debug" });
	}


}
