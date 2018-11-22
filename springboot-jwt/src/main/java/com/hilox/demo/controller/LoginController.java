package com.hilox.demo.controller;

import com.hilox.demo.annotations.JwtIgnore;
import com.hilox.demo.config.JwtParam;
import com.hilox.demo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录验证Controller
 * Created by Hilox on 2018/11/16 0016.
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private JwtParam jwtParam;

    // 登录
    @PostMapping("/login")
    @JwtIgnore // 加此注解, 请求不做token验证
    public String login() {
        // 1.用户密码验证我这里忽略, 假设用户验证成功, 取得用户id为5
        Integer userId = 5;
        // 2.验证通过生成token
        String token = JwtUtils.createToken(userId + "", jwtParam);
        if (token == null) {
            log.error("===== 用户签名失败 =====");
            return null;
        }
        log.info("===== 用户{}生成签名{} =====", userId, token);
        return JwtUtils.getAuthorizationHeader(token);
    }

    // 验证
    @PostMapping("/hilox")
    public String hilox() {
        return "Hello World!";
    }
}
