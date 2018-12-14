package com.chao.ssmvue.base.security.entity;

import com.chao.ssmvue.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author LuZichao
 * @since 2018-12-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Log extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 内容
     */
    private String optContent;

    /**
     * 客户端ip
     */
    private String clientIp;

    /**
     * 结果
     */
    private String operationContentDetails;


    public static final String LOGIN_NAME = "login_name";

    public static final String ROLE_NAME = "role_name";

    public static final String OPT_CONTENT = "opt_content";

    public static final String CLIENT_IP = "client_ip";

}
