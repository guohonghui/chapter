package com.org.service;

import com.baomidou.mybatisplus.service.IService;
import com.org.entity.Role;
import com.org.entity.UserRole;

import java.util.List;

/**
 * role 角色表 服务类
 */
public interface IRoleService extends IService<Role> {

    /**
     * 删除角色表并且删除角色权限表关联数据
     * @param id
     * @return
     */
    boolean deleteRoleByIdAndPermission(String id);

    /**
     * 分配用户角色
     * @param userRoles
     * @return
     */
    boolean modifyUserRole(List<UserRole> userRoles);
}
