package com.crustlab.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crustlab.aop.model.SampleData;
import com.crustlab.aop.service.SomeService;

@RestController
@RequestMapping(
        value = "/aop",
        produces = APPLICATION_JSON_VALUE
)
public class TestController {

    private final SomeService someService;

    @Autowired
    public TestController(SomeService someService) {
        this.someService = someService;
    }

    @GetMapping("metric/regular")
    public void runRegularMetric() {
        someService.foo();
    }

    @GetMapping("metric/error")
    public void runErrorMetric() {
        someService.bar();
    }

    @GetMapping("metric/around")
    public void runAroundMetric() {
        someService.bar();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
    public SampleData errorHandler() {
        return new SampleData(-1L, "ERROR !!!");
    }

}
