package com.tmw;

import com.tmw.api.IHelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class ClientApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(com.tmw.ClientApplication.class);
	    IHelloService helloService = context.getBean(IHelloService.class);
        System.out.println(helloService.sayHi("doudou"));
    }

}
