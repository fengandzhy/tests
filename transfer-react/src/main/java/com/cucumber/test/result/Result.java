package com.cucumber.test.result;


import com.cucumber.test.enums.ResultStatus;

@SuppressWarnings("unused")
public class Result<T> {

    
    private final Integer code;
    
    private final String message;
    
    private final T data;

    private Result(ResultStatus resultStatus,T data){
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }   

    public static <T> Result<T> success(T data){
        return new Result<>(ResultStatus.SUCCESS,data);
    }   

    public static <T> Result<T> failure(ResultStatus resultStatus,T data){
        if(resultStatus == null){
            return new Result<>(ResultStatus.INTERNAL_SERVER_ERROR,null);
        }
        return new Result<>(resultStatus,data);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
