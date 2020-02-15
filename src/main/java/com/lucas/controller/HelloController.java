package com.lucas.controller;

import com.lucas.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {
    @GetMapping("/hello")

    public String hello(){
        return "hello worbbnld";
    }

    //只要我们的接口中，返回值中存在实体，他就会扫描到swagger中
    @PostMapping("/user")
    public User user(){
        User user = new User();
        user.setUserName("123");
        user.setPassword("123");
        return user;
    }

    //ApiOperation 为controller加上注释
    @ApiOperation("说hello")
    @GetMapping("/helloUserName")
    public String helloUserName(@ApiParam("lucas") String userName){
        return "hello"+userName;
    }






}
