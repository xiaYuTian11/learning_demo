package com.example.demo;

import com.example.demo.listener.MyFirstListener;
import com.example.demo.properties.TmwProperties;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
// @EnableAutoConfiguration
// @RestController
@ComponentScan(basePackages = "com.example")
@EnableConfigurationProperties(value = TmwProperties.class)
public class DemoApplication {
    // @RequestMapping("/")
    // String home() {
    //     return "hello world  11111";
    //
    // }

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");  // 关闭 dev-toll
        // SpringApplication.run(DemoApplication.class, args);
        // SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        // springApplication.setBannerMode(Banner.Mode.OFF);
        // springApplication.run(args);
        new SpringApplicationBuilder(DemoApplication.class)
                // .sources(Parent.class)
                // .child(Application.class)
                .bannerMode(Banner.Mode.OFF)  // 关闭 springboot banner
                .listeners(new MyFirstListener())
                .web(WebApplicationType.SERVLET)
                .addCommandLineProperties(false) // 禁用命令行参数
                .run(args);
    }

}
