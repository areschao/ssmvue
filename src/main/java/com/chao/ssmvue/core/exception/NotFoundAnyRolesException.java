package com.chao.ssmvue.core.exception;

import com.chao.ssmvue.core.enums.HttpResultCode;

public class NotFoundAnyRolesException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpResultCode httpResultCode;

    public NotFoundAnyRolesException(){
        super(HttpResultCode.NOT_FOUND_ANY_ROLES.getCodeMsg());
        this.httpResultCode = HttpResultCode.NOT_FOUND_ANY_ROLES;
    }

    public HttpResultCode getHttpResultCode() {
        return httpResultCode;
    }

}
