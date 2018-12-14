package com.chao.ssmvue.core.utils;

import com.chao.ssmvue.core.entity.HttpResult;
import com.chao.ssmvue.core.enums.HttpResultCode;
import com.chao.ssmvue.core.entity.HttpResult.HttpResultBuilder;

public class HttpResultUtil {

    private HttpResultUtil() {
    }

    public static HttpResult createHttpResultSuccess(
            String operationContentDetails) {
        return createHttpResultSuccess(operationContentDetails, null);
    }

    public static HttpResult createHttpResultSuccess(
            String operationContentDetails, Object returnData) {
        return createHttpResult(HttpResultCode.SUCCESS,
                operationContentDetails, returnData);
    }

    public static HttpResult createHttpResultSuccess(Object returnData){
        return createHttpResult(HttpResultCode.SUCCESS,
                null, returnData);
    }

    public static HttpResult createHttpResultSystemError(Throwable e) {
        return createHttpResult(HttpResultCode.SYSTEM_ERROR, e.getMessage(),
                null);
    }

    public static HttpResult createHttpResultValidationError(
            String operationContentDetails) {
        return createHttpResult(HttpResultCode.VALIDATION_ERROR,
                operationContentDetails, null);
    }


    public static HttpResult createSystemError(String operationContentDetails) {
        return createHttpResult(HttpResultCode.SYSTEM_ERROR,
                operationContentDetails, null);
    }

    public static HttpResult createHttpResult(HttpResultCode httpResultCode) {
        return createHttpResult(httpResultCode, httpResultCode.getCodeMsg(),
                null);
    }

    public static HttpResult createHttpResult(HttpResultCode httpResultCode,
                                              String operationContentDetails) {
        return createHttpResult(httpResultCode, operationContentDetails, null);
    }

    public static HttpResult createNoPermissionResult(){
        return createHttpResult(HttpResultCode.NO_PERMISSION,"权限不足");
    }

    private static HttpResult createHttpResult(HttpResultCode httpResultCode,
                                               String operationContentDetails, Object returnData) {
        int code = httpResultCode.getCode();
        HttpResultBuilder builder = new HttpResultBuilder(code);
        return builder.codeMsg(HttpResultCode.findCodeMsgByCode(code))
                .operationContentDetails(operationContentDetails)
                .returnData(returnData).build();
    }

}
