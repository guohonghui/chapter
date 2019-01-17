package com.org.controller;

import com.org.base.BaseController;
import com.org.entity.User;
import com.org.service.IPermissionService;
import com.org.vo.MenuVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/***
 * 通用页面跳转
 */
@Controller
public class ActionController extends BaseController {

    @Autowired
    private IPermissionService iPermissionService;

    /**
     * 登陆页面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    /**
     * 主页
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model) {
        //获取登录用户
        User user = SecurityUtils.getSubject().getPrincipals().oneByType(User.class);
        // 获取当前用户菜单
        List<MenuVo> menus = iPermissionService.createMenu(getCurrentLoginId());
        model.addAttribute("menus",menus);
        model.addAttribute("user",user);
        return "/index";
    }

    /**
     * main
     * @return
     */
    @RequestMapping(value = "/main")
    public String index_1() {
        return "/main";
    }

    /**
     * 用户注册界面
     * @return
     */
    @GetMapping(value = "/register")
    public String registerView() {
        return "/register";
    }

}
