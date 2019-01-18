package com.org.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.org.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * user 用户表
 */
@Data
@TableName("user")
public class User extends BaseEntity {

	private static final long serialVersionUID = -5882173494187305325L;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "昵称")
	private String nickname;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "盐")
	private String salt;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "-1、禁用0、待审 1、正常")
	private Integer status;

}
