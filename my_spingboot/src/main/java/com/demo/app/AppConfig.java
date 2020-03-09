package com.demo.app;

import com.demo.web.UserController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author TMW
 * @since 2020/3/8 13:17
 */
@Configuration
@ComponentScan(basePackageClasses = UserController.class)
public class AppConfig {

}
