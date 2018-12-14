package com.chao.ssmvue.core.enums;

public enum HttpResultCode {
    SYSTEM_ERROR(-1,"系统错误"),
    SUCCESS(0,"成功"),
    VALIDATION_ERROR(1,"提交参数异常"),
    TRANSFORM_ERROR(2,"数据转换异常"),
    NOT_FOUND_CURRENT_USER(3,"没有发现当前登录用户"),
    NOT_FOUND_ANY_ROLES(4,"用户没有任何角色"),
    NOT_FOUND_ANY_MENUS(5,"没有发现任何菜单"),
    NO_PERMISSION(6,"权限不足"),

    UNKNOW_LOGINNAME_PASSWORD(10,"用户名或密码错误"),
    INPUT_OLD_PASSWORD_ERROR(11,"旧密码输入错误"),
    ALREADY_LOGIN_ERROR(12,"重复登录"),
    RANDCODE_ERROR(13,"验证码错误")
    ;

    private int code;
    private String codeMsg;

    HttpResultCode(int code, String codeMsg) {
        this.code = code;
        this.codeMsg = codeMsg;
    }

    public int getCode() {
        return code;
    }

    public String getCodeMsg() {
        return codeMsg;
    }

    public static String findCodeMsgByCode(int code){
        for(HttpResultCode hrc:HttpResultCode.values()){
            if(equalsCode(hrc,code)){
                return hrc.getCodeMsg();
            }
        }
        throw new NullPointerException("没有发现类型");
    }

    private static boolean equalsCode(HttpResultCode hrc, int code){
        return hrc.getCode() == code;
    }
}
