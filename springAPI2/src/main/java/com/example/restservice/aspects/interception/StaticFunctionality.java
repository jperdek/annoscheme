package com.example.restservice.aspects.interception;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class StaticFunctionality {


    //private Logger logger = Logger.getLogger();
    @Before(value="execution(* initializeStaticPhaseDiagram(..))")
    public void staticPhaseDiagramHandling() {
       // String methodInformation = proceedingJoinPoint.getStaticPart().getSignature().toString();
       System.out.println("Executing aspect");
       System.exit(5);
        // logger.trace("Entering method: " + methodInformation);
        //return proceedingJoinPoint.proceed();
    }
    
    //@After("execution(* *.*(..))")
  //  public void staticPhaseDiagramHandling2(ProceedingJoinPoint proceedingJoinPoint) {
  //      String methodInformation = proceedingJoinPoint.getStaticPart().getSignature().toString();
  //     System.out.println("Executing aspect..............................................................................................................................");
  //     System.exit(5);
       // logger.trace("Entering method: " + methodInformation);
        //return proceedingJoinPoint.proceed();
   // }
}
