package com.chao.ssmvue.core.exception;

import com.chao.ssmvue.core.enums.HttpResultCode;

public class NotFoundAnyMenusException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpResultCode httpResultCode;

    public NotFoundAnyMenusException(){
        super(HttpResultCode.NOT_FOUND_ANY_MENUS.getCodeMsg());
        this.httpResultCode = HttpResultCode.NOT_FOUND_ANY_MENUS;
    }

    public HttpResultCode getHttpResultCode() {
        return httpResultCode;
    }

}
