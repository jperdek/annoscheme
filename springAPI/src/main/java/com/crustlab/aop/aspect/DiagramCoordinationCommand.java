package com.crustlab.aop.aspect;

import org.springframework.stereotype.Service;

@Service
public class DiagramCoordinationCommand {
 
    public void initializeStaticPhaseDiagram() {
        System.out.println("Diagram initialization hook");
    }
}
