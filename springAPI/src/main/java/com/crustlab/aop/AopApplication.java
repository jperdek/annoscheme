package com.crustlab.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan({
    "com.crustlab.aop.*", "battleship", "battleship.Battleship", "org.annoscheme.common.annotation.*", 
"com.crustlab.aop.aspect.dynamicCapture.*", "battleshipmy.*"})
@EntityScan({
    "com.crustlab.aop.*", "battleship", "battleship.Battleship", "org.annoscheme.common.annotation.*", 
"com.crustlab.aop.aspect.dynamicCapture.*", "battleshipmy.*"})
@Configuration
@Import(MyConfig.class) 
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }
}
