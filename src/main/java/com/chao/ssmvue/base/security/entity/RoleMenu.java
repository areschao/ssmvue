package com.chao.ssmvue.base.security.entity;

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
public class RoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long menuId;

    private Long roleId;

    public static final String MENU_ID = "menu_id";

    public static final String ROLE_ID = "role_id";

}
