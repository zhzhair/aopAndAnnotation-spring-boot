package com.example.demo.common.config.mvc.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.config.TokenManager;
import com.example.demo.common.config.aspect.annotation.TokenValidate;
import com.example.demo.common.dto.BaseResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        if(!(o instanceof HandlerMethod)) return true;
        HandlerMethod method = (HandlerMethod)o;
        if(method.hasMethodAnnotation(TokenValidate.class)) {
            String userIdStr = httpServletRequest.getParameter("userId");
            String token = httpServletRequest.getParameter("token");
            if(userIdStr != null && token != null && !"".equals(userIdStr.trim()) && !"".equals(token.trim())) {
                int userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
                if (!TokenManager.validateToken(userId, token)) {
                    BaseResponse baseResponse = new BaseResponse();
                    baseResponse.setCode(-1);
                    baseResponse.setMsg("token过期");
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpServletResponse.getOutputStream());
                    outputStreamWriter.write(JSONObject.toJSONString(baseResponse));
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    return false;
                }
            } else {
                BaseResponse baseResponse = new BaseResponse();
                baseResponse.setCode(-2);
                baseResponse.setMsg("缺少参数userId或token");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpServletResponse.getOutputStream());
                outputStreamWriter.write(JSONObject.toJSONString(baseResponse));
                outputStreamWriter.flush();
                outputStreamWriter.close();
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }
}
