package com.chao.ssmvue.core.entity;


public class HttpResult {

    private Integer code;
    private String codeMsg;
    private String operationContentDetails;
    private Object returnData;

    public static class HttpResultBuilder {

        //必选参数
        private final Integer code;
        //配置参数
        private String codeMsg;
        private String operationContentDetails;
        private Object returnData;

        public HttpResultBuilder(Integer code) {
            this.code = code;
        }

        public HttpResultBuilder codeMsg(String codeMsg) {
            this.codeMsg = codeMsg;
            return this;
        }

        public HttpResultBuilder operationContentDetails(String operationContentDetails) {
            this.operationContentDetails = operationContentDetails;
            return this;
        }

        public HttpResultBuilder returnData(Object returnData) {
            this.returnData = returnData;
            return this;
        }

        public HttpResult build() {
            return new HttpResult(this);
        }
    }

    private HttpResult(HttpResultBuilder builder) {
        this.code = builder.code;
        this.codeMsg = builder.codeMsg;
        this.operationContentDetails = builder.operationContentDetails;
        this.returnData = builder.returnData;
    }

    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }


    public String getCodeMsg() {
        return codeMsg;
    }


    public void setCodeMsg(String codeMsg) {
        this.codeMsg = codeMsg;
    }


    public String getOperationContentDetails() {
        return operationContentDetails;
    }


    public void setOperationContentDetails(String operationContentDetails) {
        this.operationContentDetails = operationContentDetails;
    }


    public Object getReturnData() {
        return returnData;
    }


    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }


    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", codeMsg='" + codeMsg + '\'' +
                ", operationContentDetails='" + operationContentDetails + '\'' +
                '}';
    }

}
