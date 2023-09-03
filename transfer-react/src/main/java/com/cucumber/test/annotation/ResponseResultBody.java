package com.cucumber.test.annotation;


import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseResultBody {
}
