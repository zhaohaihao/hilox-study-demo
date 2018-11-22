package com.hilox.demo.sdk;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.hilox.demo.builder.AliSmsBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 阿里云短信SDK
 * Created by Hilox on 2018/11/22 0022.
 */
@Slf4j
public class AliSmsSDK {

    // 产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    /**
     * 私有化构造
     */
    private AliSmsSDK() {}

    /**
     * 静态内部类
     */
    private static class AliSmsSDKHolder {
        private static final AliSmsSDK INSTANCE = new AliSmsSDK();
    }

    /**
     * 选用静态内部类实现单例：
     *     1.防止懒汉式单例多线程额外开销
     *     2.防止饿汉式单例内存资源浪费
     * @return
     */
    public static final AliSmsSDK getInstance() {
        return AliSmsSDKHolder.INSTANCE;
    }

    /**
     * 发送短信验证码(阿里云短信平台)
     * @param aliSmsBuilder
     * @return
     */
    public String sendSmsAli(AliSmsBuilder aliSmsBuilder) throws ClientException {

        log.info("==== 开始发送短信验证码消息体: {} =====", aliSmsBuilder.toString());

        // 设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", aliSmsBuilder.getDefaultConnectTimeout());
        System.setProperty("sun.net.client.defaultReadTimeout", aliSmsBuilder.getDefaultReadTimeout());

        // 初始化acsClient, 暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliSmsBuilder.getAccessKeyId(), aliSmsBuilder.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(aliSmsBuilder.getPhoneNum());
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(aliSmsBuilder.getSignName());
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(aliSmsBuilder.getTemplateCode());

        String verifyCode = aliSmsBuilder.getVerifyCode();
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为 request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        if (!StringUtils.isEmpty(verifyCode)) {
            request.setTemplateParam("{\"code\":\"" + verifyCode + "\"}");
        }

        String smsUpExtendCode = aliSmsBuilder.getSmsUpExtendCode();
        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        if (!StringUtils.isEmpty(smsUpExtendCode)) {
            request.setSmsUpExtendCode(smsUpExtendCode);
        }

        String outId = aliSmsBuilder.getOutId();
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        if (!StringUtils.isEmpty(outId)) {
            request.setOutId(outId);
        }

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        String code = sendSmsResponse.getCode();

        log.info("==== 发送短信验证码成功, 返回code: " + code + " =====");
        return code;
    }
}
