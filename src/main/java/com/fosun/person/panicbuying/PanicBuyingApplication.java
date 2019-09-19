package com.fosun.person.panicbuying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class PanicBuyingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanicBuyingApplication.class, args);
    }

}
