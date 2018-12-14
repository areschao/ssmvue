package com.chao.ssmvue.core.exception;

import com.chao.ssmvue.core.enums.HttpResultCode;

//没有发现当前登录用户异常
public class NotFoundCurrentUserException extends  RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpResultCode httpResultCode;

    public NotFoundCurrentUserException(){
        super(HttpResultCode.NOT_FOUND_CURRENT_USER.getCodeMsg());
        this.httpResultCode = HttpResultCode.NOT_FOUND_CURRENT_USER;
    }

    public HttpResultCode getHttpResultCode() {
        return httpResultCode;
    }

}
