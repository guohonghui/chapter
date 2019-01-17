package com.org.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.org.dao.PermissionMapper;
import com.org.dao.RolePermissionMapper;
import com.org.entity.Permission;
import com.org.entity.RolePermission;
import com.org.service.IPermissionService;
import com.org.service.IRolePermissionService;
import com.org.vo.MenuVo;
import com.org.vo.ZNodesVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * permission 权限表 服务实现类
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private IRolePermissionService iRolePermissionService;

    @Override
    public List<MenuVo> createMenu(Long uid) {
        // 创建菜单对象
        List<MenuVo> menus = new ArrayList<>();

        // 查询父菜单
        List<Permission> parentMenu = baseMapper.findParentMenu(uid);
        parentMenu.forEach(p -> {
            MenuVo menu = new MenuVo();
            BeanUtils.copyProperties(p, menu);
            menus.add(menu);
        });

        // 查询子菜单
        menus.forEach(m -> {
            List<MenuVo> temp = new ArrayList<>();
            List<Permission> children = baseMapper.findSubMenu(uid, m.getId());
            children.forEach(c -> {
                MenuVo menu = new MenuVo();
                BeanUtils.copyProperties(c, menu);
                temp.add(menu);
            });
            m.setChildren(temp);
        });
        return menus;
    }

    @Override
    public List<ZNodesVo> findPermissionZTreeNodes(Long roleId) {
        // 查询所有权限列表
        EntityWrapper<Permission> permissionEntityWrapper = new EntityWrapper<>();
        permissionEntityWrapper.orderBy("sort", true);
        List<Permission> permissions = baseMapper.selectList(permissionEntityWrapper);

        // 查询角色拥有那些权限
        EntityWrapper<RolePermission> rolePermissionEntityWrapper = new EntityWrapper<>();
        rolePermissionEntityWrapper.eq("rid", roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(rolePermissionEntityWrapper);

        List<ZNodesVo> zNodes = new ArrayList<>();
        permissions.forEach(p -> {
            ZNodesVo node = new ZNodesVo();
            BeanUtils.copyProperties(p, node);
            // 设置展开节点
            if (p.getType() == 0 || p.getType() == 1) {
                node.setOpen(true);
            }
            // 设置角色拥有的权限选中
            rolePermissions.forEach(rp -> {
                if (p.getId() == rp.getPid()) {
                    node.setChecked(true);
                }
            });
            zNodes.add(node);
        });
        return zNodes;
    }

    @Transactional
    @Override
    public boolean modifyRolePermission(List<RolePermission> rolePermissions) {
        // 删除原表角色权限
        EntityWrapper<RolePermission> rolePermissionEntityWrapper = new EntityWrapper<>();
        rolePermissionEntityWrapper.eq("rid", rolePermissions.get(0).getRid());
        rolePermissionMapper.delete(rolePermissionEntityWrapper);

        // 新增角色权限 并返回处理结果
        return iRolePermissionService.insertBatch(rolePermissions);
    }

    @Transactional
    @Override
    public boolean deletePermissionRole(String id) {
        // 删除关联表数据
        EntityWrapper<RolePermission> rolePermissionEntityWrapper = new EntityWrapper<>();
        rolePermissionEntityWrapper.eq("pid", id);
        rolePermissionMapper.delete(rolePermissionEntityWrapper);

        // 删除权限表数据
        Integer result = baseMapper.deleteById(Long.valueOf(id));

        return result >= 1 ? true : false;
    }

}
