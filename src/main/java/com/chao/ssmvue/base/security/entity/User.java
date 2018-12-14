package com.chao.ssmvue.base.security.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.chao.ssmvue.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LuZichao
 * @since 2018-11-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String loginName;

    private String name;

    private String password;

    private String phone;

    @TableLogic
    private String status;

    public static final String LOGIN_NAME = "login_name";

    public static final String NAME = "name";

    public static final String PASSWORD = "password";

    public static final String PHONE = "phone";

    public static final String STATUS = "status";

}
