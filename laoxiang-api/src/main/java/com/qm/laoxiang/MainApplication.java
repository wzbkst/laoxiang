package com.qm.laoxiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * 排除ErrorMvcAutoConfiguration， 通过自定义的errorControllerAdvice处理
 */
@PropertySources(value = {
        @PropertySource(value = {"classpath:important.properties"}, encoding = "utf-8"),
        @PropertySource(value = {"classpath:extend.properties"}, encoding = "utf-8")
})
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class MainApplication {
    public static void main(String[] args){
        SpringApplication.run(MainApplication.class, args);
    }
}
