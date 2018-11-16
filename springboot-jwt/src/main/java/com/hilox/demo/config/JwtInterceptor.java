package com.hilox.demo.config;

import com.hilox.demo.constants.JwtConstant;
import com.hilox.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt 拦截器
 * Created by Hilox on 2018/11/16 0016.
 */
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtParam jwtParam;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authHeader = request.getHeader(JwtConstant.AUTH_HEADER_KEY);

        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // 校验头格式校验
        if (!JwtUtils.validate(authHeader)) {
            // TODO 这里自行抛出异常
            log.info("===== 无校验头或校验头格式异常 =====");
            return false;
        }

        // token解析
        final String authToken = JwtUtils.getRawToken(authHeader);
        Claims claims = JwtUtils.parseToken(authToken, jwtParam.getBase64Secret());
        if (claims == null) {
            log.info("===== token解析异常 =====");
            return false;
        }
        // request.setAttribute("CLAIMS", claims);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
