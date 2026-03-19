package com.crustlab.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AroundAdviceAspect {

    //private static final Logger LOG = LoggerFactory.getLogger(AroundAdviceAspect.class);

     @Pointcut(value="execution(* foo2(..))")
    private void aaa(){
    }
  
    @Before(value="aaa()")
    public void aroundMetricMethods() {
     //   LOG.info("@Around advice called for method");
    }

    @Before(value="execution(* foo(..))")
    public void aroundMetricMethods2() {
      //  LOG.info("@Around advice called for method Foo");   
    }
    
    @Before("execution(* *..*.*(..)) && @annotation(org.annoscheme.common.annotation.Action)")
	public void printme21() {
		System.out.println("Aspect: ACTION ANNNOTATION INtercepted");
	}
	
	@Before("execution(* *..*.*(..)) && @annotation(Action)")
	public void printme31() {
		System.out.println("Aspect: ACTION ANNNOTATION INtercepted2");
	}
}
