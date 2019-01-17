package com.org.service;

import com.baomidou.mybatisplus.service.IService;
import com.org.entity.User;

/**
 * user 用户表 服务类
 */
public interface IUserService extends IService<User> {

    /**
     * 删除用户表并且删除用户角色关联表数据
     * @param id
     * @return
     */
    boolean deleteUserByIdAndRole(String id);

}
