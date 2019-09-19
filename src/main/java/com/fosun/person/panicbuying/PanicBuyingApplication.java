package com.fosun.person.panicbuying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 注意，此处必须要将springboot的jdbc DataSource自动配置功能关闭，否则会报冲突
 */
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class PanicBuyingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanicBuyingApplication.class, args);
    }

}
