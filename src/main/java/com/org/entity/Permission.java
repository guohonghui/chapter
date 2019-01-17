package com.org.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.org.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * permission 权限表
 */
@Data
@TableName("permission")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 7025157976722208080L;

    @ApiModelProperty(value = "上级ID")
	private Long pid;

    @ApiModelProperty(value = "权限名")
	private String name;

    @ApiModelProperty(value = "类型 0、目录 1、菜单 2、按钮")
	private Integer type;

    @ApiModelProperty(value = "排序")
	private Integer sort;

    @ApiModelProperty(value = "地址")
	private String url;

    @ApiModelProperty(value = "权限编码")
	private String permCode;

    @ApiModelProperty(value = "图标")
	private String icon;

    @ApiModelProperty(value = "描述")
	private String description;

    @ApiModelProperty(value = "状态 0、禁用 1、正常")
	private Integer status;

}
