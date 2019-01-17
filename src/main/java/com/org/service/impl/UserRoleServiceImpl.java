package com.org.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.org.dao.UserRoleMapper;
import com.org.entity.UserRole;
import com.org.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * user_role 用户角色关联表 服务实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
	
}
