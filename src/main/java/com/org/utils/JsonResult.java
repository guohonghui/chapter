package com.org.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Describe: 封装Json返回信息
 */
@Data
public class JsonResult {

    @ApiModelProperty(value = "成功标志")
    private boolean success;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "消息")
    private String msg;

    @ApiModelProperty(value = "data对象")
    private Object obj;

}
