package com.crustlab.aop;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
    "com.crustlab.aop", "battleship", "org.annoscheme.common.annotation", 
"com.crustlab.aop.aspect.dynamicCapture", "battleshipmy"})
@EntityScan({
    "com.crustlab.aop.*", "battleship", "battleship.Battleship", "org.annoscheme.common.annotation.*", 
"com.crustlab.aop.aspect.dynamicCapture.*", "battleshipmy.*"})
public class MyConfig {
}
