package com.example.demo.user.controller;

import com.example.demo.common.config.aspect.annotation.TokenValidate;
import com.example.demo.common.dto.BaseResponse;
import com.example.demo.user.asynctask.AsyncTask;
import com.example.demo.user.dto.request.RegisterRequest;
import com.example.demo.user.dto.response.LoginResponse;
import com.example.demo.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(origins = "*",allowedHeaders = "Content-Type,Access-Token,x-requested-with",allowCredentials = "true",maxAge = 3600,exposedHeaders = "content-type")
@RestController
@RequestMapping("user")
@Api(description = "基础 -- 用户")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private AsyncTask asyncTask;

    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<LoginResponse> login(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) {
        BaseResponse<LoginResponse> baseResponse = new BaseResponse<>();
        LoginResponse loginResponse = userService.login(userName,password);
        if(loginResponse != null){
            baseResponse.setCode(0);
            baseResponse.setMsg("登录成功");
            baseResponse.setData(loginResponse);
        }else{
            baseResponse.setCode(1);
            baseResponse.setMsg("用户名或密码不正确");
        }
        return baseResponse;
    }

    @ApiOperation(value = "注册", notes = "注册")//swagger注释
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> register(@RequestBody RegisterRequest registerRequest) {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        Integer flag = userService.register(registerRequest);
        if(flag == 0){
            baseResponse.setCode(0);
            asyncTask.asyncMethod();//这里可以是异步发送邮件等操作
            baseResponse.setMsg("注册成功");
        }else if(flag == 1){
            baseResponse.setCode(1);
            baseResponse.setMsg("手机号已被注册");
        }else{
            baseResponse.setCode(2);
            baseResponse.setMsg("邮箱已被注册");
        }
        return baseResponse;
    }

    @TokenValidate
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")//swagger注释
    @RequestMapping(value = "/getUser", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Object> getUserInfo(@RequestParam("userId") int userid, @RequestParam("token") String token) {
        asyncTask.asyncMethod();//这里可以是异步记录登录历史记录等操作
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.ok(userid + ":" + token);//只是为了方便测试，这里不可能返回这两个玩意！
        return baseResponse;
    }
}
