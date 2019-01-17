package com.org.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.org.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * role_permission 角色权限表
 */
@Data
@TableName("role_permission")
public class RolePermission extends BaseEntity {

	private static final long serialVersionUID = -4617638898863404815L;

    @ApiModelProperty(value = "角色ID")
	private Long rid;

    @ApiModelProperty(value = "权限ID")
	private Long pid;
}
