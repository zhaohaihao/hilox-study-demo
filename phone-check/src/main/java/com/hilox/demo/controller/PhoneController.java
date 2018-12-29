package com.hilox.demo.controller;

import com.hilox.demo.util.PhoneUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hilox on 2018/12/29 0029.
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {

    /**
     * 获取展示手机号码
     * @return
     */
    @GetMapping("/getPhone")
    public String getPhone() {
        // 写死, 模拟用户手机号
        String phone = "15012348879";
        return PhoneUtils.blurPhone(phone);
    }
}
