package com.hilox.demo.sdk;

import com.aliyuncs.exceptions.ClientException;
import com.hilox.demo.builder.AliSmsBuilder;
import com.hilox.demo.enums.AliSmsCodeEnum;
import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * 测试用例模板
 * Created by Hilox on 2018/11/22 0022.
 */
public class AliSmsSDKTest {

    @Test
    public void sendSmsAli() {
        AliSmsBuilder aliSmsBuilder = new AliSmsBuilder();
        String code;
        try {
            code = aliSmsBuilder.setPhoneNum("150****8879")    // 替换成自己的手机号
                    .setSignName("阿里云短信测试专用")  // 替换成自己的阿里云短信服务签名
                    .setTemplateCode("SMS_108565014") // 替换成自己的阿里云短信模板编号
                    .setVerifyCode("Hilox") // 替换成自己随机生成的验证码
                    .send();
        } catch (ClientException e) {
            e.printStackTrace();
            // 短信发送异常提示
            return;
        }

        // 短信异常码处理
        if (code == null || !"OK".equals(code)) {
            String errorMsg = AliSmsCodeEnum.getMsgByCode(code);
            if (!StringUtils.isEmpty(errorMsg)) {
                // 对应短信异常错误提示
                return;
            }
            // 短信发送异常提示
        }
    }
}
