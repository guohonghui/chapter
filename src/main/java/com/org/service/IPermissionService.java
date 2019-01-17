package com.org.service;

import com.baomidou.mybatisplus.service.IService;
import com.org.entity.Permission;
import com.org.entity.RolePermission;
import com.org.vo.MenuVo;
import com.org.vo.ZNodesVo;

import java.util.List;

/**
 * permission 权限表 服务类
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 创建登陆用户菜单
     * @param uid
     * @return
     */
    List<MenuVo> createMenu(Long uid);

    /**
     * 查询所有权限树形展示 并且选中角色拥有的树节点
     * @param roleId 角色ID
     * @return
     */
    List<ZNodesVo> findPermissionZTreeNodes(Long roleId);

    /**
     * 修改角色权限
     * @param rolePermissions
     * @return
     */
    boolean modifyRolePermission(List<RolePermission> rolePermissions);

    /**
     * 删除权限 和 权限角色表数据
     * @param id
     * @return
     */
    boolean deletePermissionRole(String id);
}
