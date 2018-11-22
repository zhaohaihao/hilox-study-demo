package com.hilox.demo.builder;

import com.aliyuncs.exceptions.ClientException;
import com.hilox.demo.sdk.AliSmsSDK;

import java.io.Serializable;

/**
 * 阿里云短信构造类
 * accessKeyId，accessKeySecret寻找步骤参考官方文档
 * https://help.aliyun.com/document_detail/55284.html?spm=5176.10629532.106.1.24981cbeej0YCz
 *
 * Created by Hilox on 2018/11/22 0022.
 */
public class AliSmsBuilder implements Serializable {

    private static final long serialVersionUID = -8277819898310935813L;

    /**
     * 阿里云短信服务accessKeyId
     */
    private String accessKeyId;

    /**
     * 阿里云短信服务accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 必填:待发送手机号
     */
    private String phoneNum;

    /**
     * 必填:短信签名-可在短信控制台中找到
     */
    private String signName;

    /**
     * 必填:短信模板-可在短信控制台中找到
     */
    private String templateCode;

    /**
     * 可选:验证码
     */
    private String verifyCode;

    /**
     * 可选:上行短信扩展码(无特殊需求用户请忽略此字段)
     */
    private String smsUpExtendCode;

    /**
     * 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
     */
    private String outId;

    private String defaultConnectTimeout;

    private String defaultReadTimeout;

    public AliSmsBuilder() {
        // 默认初始化
        accessKeyId = "***";   // 替换成自己的阿里云短信accessKeyId
        accessKeySecret = "***";   // 替换成自己的阿里云短信accessKeySecret
        this.defaultConnectTimeout = "10000";
        this.defaultReadTimeout = "10000";
    }

    public AliSmsBuilder(String accessKeyId, String accessKeySecret) {
        // 自定义初始化
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    public AliSmsBuilder setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public AliSmsBuilder setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
        return this;
    }

    public String getDefaultConnectTimeout() {
        return defaultConnectTimeout;
    }

    public AliSmsBuilder setDefaultConnectTimeout(String defaultConnectTimeout) {
        this.defaultConnectTimeout = defaultConnectTimeout;
        return this;
    }

    public String getDefaultReadTimeout() {
        return defaultReadTimeout;
    }

    public AliSmsBuilder setDefaultReadTimeout(String defaultReadTimeout) {
        this.defaultReadTimeout = defaultReadTimeout;
        return this;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public AliSmsBuilder setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public AliSmsBuilder setSignName(String signName) {
        this.signName = signName;
        return this;
    }

    public String getSignName() {
        return signName;
    }

    public AliSmsBuilder setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
        return this;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public AliSmsBuilder setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
        return this;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public AliSmsBuilder setSmsUpExtendCode(String smsUpExtendCode) {
        this.smsUpExtendCode = smsUpExtendCode;
        return this;
    }

    public String getSmsUpExtendCode() {
        return smsUpExtendCode;
    }

    public AliSmsBuilder setOutId(String outId) {
        this.outId = outId;
        return this;
    }

    public String getOutId() {
        return outId;
    }

    @Override
    public String toString() {
        return "AliSmsBuilder{" +
                "accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", signName='" + signName + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", smsUpExtendCode='" + smsUpExtendCode + '\'' +
                ", outId='" + outId + '\'' +
                ", defaultConnectTimeout='" + defaultConnectTimeout + '\'' +
                ", defaultReadTimeout='" + defaultReadTimeout + '\'' +
                '}';
    }

    /**
     * 发送短信消息
     * @return
     * @throws ClientException
     */
    public String send() throws ClientException {
        return AliSmsSDK.getInstance().sendSmsAli(this);
    }
}
