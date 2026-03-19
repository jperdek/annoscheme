package com.crustlab.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetricAspect {

    //private static final Logger LOG = LoggerFactory.getLogger(MetricAspect.class);

   // private final MetricsService metricsService;

   /* @Autowired
    public MetricAspect(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Pointcut("execution(@com.crustlab.aop.LogMetric com.crustlab.aop.model.SampleData *(..)) && @annotation(logMetric)")
    private void logMetricAnnotatedPointcut(LogMetric logMetric) {

    }

    @AfterReturning(
            value = "logMetricAnnotatedPointcut(logMetric)",
            argNames = "joinPoint,logMetric"
    )
    public void afterReturningLogMetricAnnotatedMethod(JoinPoint joinPoint, LogMetric logMetric) {
        LOG.info(String.format("@AfterReturning advice called for method: '%s'", AopUtils.getMethodSignature(joinPoint)));
        System.exit(5);
        metricsService.logMetric(logMetric.value());
    }

    @AfterThrowing(
            value = "logMetricAnnotatedPointcut(logMetric)",
            argNames = "joinPoint,logMetric"
    )
    public void afterThrowingLogMetricAnnotatedMethod(JoinPoint joinPoint, LogMetric logMetric) {
        LOG.info(String.format("@AfterThrowing advice called for method: '%s'", AopUtils.getMethodSignature(joinPoint)));
        System.exit(5);
        metricsService.logErrorMetric(logMetric.value());
    }
*/
}
