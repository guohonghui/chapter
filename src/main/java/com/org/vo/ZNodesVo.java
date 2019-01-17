package com.org.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Describe: zTree 树实体
 */
@Data
public class ZNodesVo implements Serializable {

    private static final long serialVersionUID = -1786596406786154589L;

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "上级ID")
    private Long pid;

    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "是否打开")
    private boolean open;

    @ApiModelProperty(value = "是否选中")
    private boolean checked;

    @ApiModelProperty(value = "是否显示")
    private boolean isParent;
}
