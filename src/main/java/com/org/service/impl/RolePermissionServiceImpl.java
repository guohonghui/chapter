package com.org.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.org.dao.RolePermissionMapper;
import com.org.entity.RolePermission;
import com.org.service.IRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * role_permission 角色权限表 服务实现类
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {
	
}
