package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.restservice.aspects.interception.DiagramCoordinationCommand;
import com.example.restservice.aspects.interception.StaticFunctionality;

@Configuration
@EnableAspectJAutoProxy
@SpringBootApplication
@ComponentScan
public class RestServiceApplication {

    @Bean StaticFunctionality staticFunctionality() {  
        return new StaticFunctionality();
    }
    
  public static void main(String[] args) {
    new DiagramCoordinationCommand().initializeStaticPhaseDiagram();
    SpringApplication.run(RestServiceApplication.class, args);
  }
}
