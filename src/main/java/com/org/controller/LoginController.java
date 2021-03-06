package com.org.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.org.base.BaseController;
import com.org.entity.User;
import com.org.service.IUserService;
import com.org.utils.JsonResult;
import com.sun.mail.imap.protocol.ID;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录和注册页面
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 登陆提交页面
     * @param username
     * @param password
     * @param map
     * @return
     */
    @RequestMapping("/loginForm")
    public String mylogin(String username, String password, Map<String, Object> map) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            String simpleName = e.getClass().getSimpleName();
            if ("UnknownAccountException".equals(simpleName)) {
                map.put("msg", "用户不存在");
                return "/login";
            } else if("IncorrectCredentialsException".equals(simpleName)){
                map.put("msg", "密码不正确");
                return "/login";
            }
        }
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            return "redirect:/index";
        }
        return "redirect:/login";
    }

    /**
     * 用户注册
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/postRegister")
    public JsonResult register(User user, String code) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionCode = (String) session.getAttribute("code");
        session.removeAttribute("code");
        if (sessionCode == null) {
            return renderSuccess("验证码过期请重新获取");
        }
        if (!sessionCode.equals(code)) {
            return renderError("验证码错误");
        }

        // 创建盐, 散列加密
        String salt = String.valueOf(System.currentTimeMillis());
        SimpleHash password = new SimpleHash("MD5", user.getPassword(), salt);
        user.setStatus(0); // 状态待审核
        user.setGmtModified(new Date());
        user.setGmtCreate(new Date());
        user.setSalt(salt); // 设置盐
        user.setPassword(password.toString()); // 设置密码
        return iUserService.insert(user) ? renderSuccess("注册成功") : renderSuccess("注册失败");
    }

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/isUsername")
    public Map<String, Object> isUsername(String username) {
        Map<String, Object> map = new HashMap<>();

        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username);

        User user = iUserService.selectOne(wrapper);

        if (user == null) {
            map.put("valid", true);
        } else {
            map.put("valid", false);
        }
        return map;
    }

    /**
     * 判断昵称是否存在
     * @param nickname
     * @return
     */
    @ResponseBody
    @RequestMapping("/isNickname")
    public Map<String,Object> isNickname(String nickname){
        Map<String,Object> map = new HashMap<>();
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("nickname",nickname);
        User user = iUserService.selectOne(wrapper);
        if (user == null) {
            map.put("valid", true);
        } else {
            map.put("valid", false);
        }
        return map;
    }

    /**
     * 判断邮箱是否存在
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping("/isEmail")
    public Map<String, Object> isEmail(String email) {
        Map<String, Object> map = new HashMap<>();

        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("email", email);

        User user = iUserService.selectOne(wrapper);

        if (user == null) {
            map.put("valid", true);
        } else {
            map.put("valid", false);
        }
        return map;
    }

    /**
     * 邮箱发送验证码
     * @param mail
     * @return
     */
    @ResponseBody
    @PostMapping("/sencCode")
    public JsonResult sendCode(@RequestBody String mail) {
        mail = mail.replace("%40", "@");// 字符串替换

        // 判断是否已经注册
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("email", mail);
        User user = iUserService.selectOne(wrapper);
        if (user != null) {
            return renderError("邮箱已注册");
        }
        int code = (int)((Math.random()*9+1)*100000);

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("code", code+"");

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            // 指明邮件的发件人
            mimeMessage.setFrom(new InternetAddress("fanshuye1304@163.com"));
            //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            //邮件的标题
            mimeMessage.setSubject("Bing-Upms 注册验证");
            //邮件的文本内容
            mimeMessage.setContent("<html><head><title>编辑邮件正文</title><style>html{word-wrap:break-word;}body{color:#000000;font-size:14px;font-family:Arial;line-height:1.7;padding:8px 10px;margin:0;background-color:#fff;}pre{white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;font-family:arial;}span.typoRed{border-bottom:2px dotted #ff0000;cursor:pointer;}.nui-scroll{overflow:auto}.nui-scroll::-webkit-scrollbar{width: 8px;height:8px}.nui-scroll::-webkit-scrollbar-track{border-radius:10px;-webkit-box-shadow:inset 0 0 6px rgba(0,0,0,0);}.nui-scroll::-webkit-scrollbar-track:hover{-webkit-box-shadow:inset 0 0 6px rgba(0,0,0,0.4);background-color:rgba(0,0,0,0.01)}.nui-scroll::-webkit-scrollbar-track:active{-webkit-box-shadow:inset 0 0 6px rgba(0,0,0,0.4);background-color:rgba(0,0,0,0.05)}.nui-scroll::-webkit-scrollbar-thumb{background-color: rgba(0, 0, 0, 0.05);border-radius:10px;-webkit-box-shadow:inset 1px 1px 0 rgba(0,0,0,.1)}.nui-scroll:hover::-webkit-scrollbar-thumb{background-color: rgba(0, 0, 0, 0.2);border-radius:10px;-webkit-box-shadow:inset 1px 1px 0 rgba(0,0,0,.1)}.nui-scroll::-webkit-scrollbar-thumb:hover{background-color:rgba(0, 0, 0, 0.4);-webkit-box-shadow:inset 1px 1px 0 rgba(0,0,0,.1)}.nui-scroll::-webkit-scrollbar-thumb:active{background:rgba(0,0,0,0.6);}</style></head><body class=\"nui-scroll\" contenteditable=\"true\"><div style=\"text-align: center;\"><span style=\"font-size: 24px;\"><b>Bing-UPMS 注册验证</b></span></div><div style=\"text-align: left;\"><span style=\"font-size: 18px;\">\u200B欢迎注册Bing-UPMS 验证码:&nbsp;"+code+"</span></div></body></html>", "text/html;charset=UTF-8");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            mailSender.send(mimeMessage); //发送邮件
            return renderSuccess("请查看邮箱验证码");
        } catch (MailException e) {
            return renderError("邮箱发送失败,请重新获取");
        }
    }
}
