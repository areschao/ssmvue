package com.chao.ssmvue.base.security.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.chao.ssmvue.core.entity.BaseEntity;
import com.chao.ssmvue.core.utils.interfaces.TreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

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
@Accessors
public class Menu extends BaseEntity implements TreeEntity<Long,Menu>{

    private static final long serialVersionUID = 1L;

    private String className;

    private String description;

    private String icon;

    private String indexUrl;

    private String methodName;

    private String name;

    private Long parentId;

    private String parentIds;

    private Integer seq;

    private Integer type;

    @TableField(exist = false)
    private List<Menu> childList;

    @TableLogic
    private String status;

    @TableField(exist = false)
    private String parentName;

    public static final String CLASS_NAME = "class_name";

    public static final String DESCRIPTION = "description";

    public static final String ICON = "icon";

    public static final String INDEX_URL = "index_url";

    public static final String METHOD_NAME = "method_name";

    public static final String NAME = "name";

    public static final String PARENT_ID = "parent_id";

    public static final String PARENT_IDS = "parent_ids";

    public static final String SEQ = "seq";

    public static final String STATUS = "status";

    public static final String TYPE = "type";

}
