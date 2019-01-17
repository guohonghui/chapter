package com.org.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.org.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * role 角色表
 */
@Data
@TableName("role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 5963855538995763596L;

    @ApiModelProperty(value = "角色名")
	private String name;

    @ApiModelProperty(value = "排序")
	private Integer sort;

    @ApiModelProperty(value = "描述")
	private String description;

    @ApiModelProperty(value = "状态")
	private Integer status;

}
