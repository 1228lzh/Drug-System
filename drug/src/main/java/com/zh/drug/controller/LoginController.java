package com.zh.drug.controller;

import com.zh.drug.common.result.CommonResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ZH
 * @Date: 2022-12-30 16:29
 */
@RestController
@RequestMapping("drug/user")
@CrossOrigin
public class LoginController {

    @PostMapping("/login")
    public CommonResponse login(){
        return CommonResponse.ok().data("token","admin");
    }

    @GetMapping("/info")
    public CommonResponse info(){
        return CommonResponse.ok()
                .data("roles","[admin]")
                .data("name","LZH")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}


