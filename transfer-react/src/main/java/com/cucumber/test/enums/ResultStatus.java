package com.cucumber.test.enums;

public enum ResultStatus {

    SUCCESS(200,"OK"),
    BAD_REQUEST(400,"Bad Request"),
    METHOD_NOT_ALLOWED(405,"Method not Allowed"),
    INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
    BUSINESS_EXCEPTION(501,"Business Exception");

    private final Integer code;
    
    private final String message;

    ResultStatus(Integer code, String message){
        this.code = code;
        this.message = message;
    }   

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
