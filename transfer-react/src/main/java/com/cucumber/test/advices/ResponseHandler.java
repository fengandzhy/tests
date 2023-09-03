package com.cucumber.test.advices;



import com.cucumber.test.annotation.ResponseResultBody;
import com.cucumber.test.result.Result;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


import java.lang.annotation.Annotation;



@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;    
    
    /**
     * This method is used to determine which controller methods will apply a uniform response format. 
     * Typically, if a method is marked with @ResponseResultBody, that format will be applied. 
     * If a controller class is marked with @ResponseResultBody, then all methods within that controller will uniformly use the same response format.      
     * */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(),ANNOTATION_TYPE)
                || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    /**
     * This method aims to capture the content returned by the controllers which has been marked @ResponseResultBody and wrap it in a uniform format. 
     * */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return Result.success(body);
    }    
}
