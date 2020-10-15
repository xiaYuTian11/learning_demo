package com.example.uidgenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * https://blog.csdn.net/a13794479495/article/details/83541569
 * https://blog.csdn.net/m0_37367413/article/details/87341352
 * https://www.cnblogs.com/zxporz/p/11668615.html
 * 伪共享： https://www.cnblogs.com/cyfonly/p/5800758.html
 * https://www.bilibili.com/video/BV1PV411U78p?from=search&seid=18278000554349117417
 */
@SpringBootApplication
@ComponentScan({"com.baidu.fsg","com.example.uidgenerator"})
@MapperScan("com.example.uidgenerator")
@EnableTransactionManagement(proxyTargetClass = true)
public class UidGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(UidGeneratorApplication.class, args);
	}

}
