package com.org.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.org.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * user_role 用户角色关联表
 */
@Data
@TableName("user_role")
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 4790136853840188717L;

	@ApiModelProperty(value = "用户ID")
	private Long uid;

	@ApiModelProperty(value = "角色ID")
	private Long rid;

}
