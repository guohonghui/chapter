package com.org.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuVo implements Serializable {

    private static final long serialVersionUID = -3582342366492402720L;

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "上级ID")
    private Long pid;

    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "链接")
    private String url;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "子菜单")
    private List<MenuVo> children;

}
