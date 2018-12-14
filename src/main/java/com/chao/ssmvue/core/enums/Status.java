package com.chao.ssmvue.core.enums;

public enum Status {

    正常("1"),
    删除("-1");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
