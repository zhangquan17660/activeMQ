package com.zq.mvc.controller;

import com.zq.mvc.pojo.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class HelloController {
    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String hello(){
        System.out.println("hello");
        return "success";
    }
    //表单属性和bean对应
    @RequestMapping(value = "/hello/account" ,method = RequestMethod.POST)
    public String helloAccount(Account account){
        System.out.println("hello");
        System.out.println(account.getPassword());
        return "success";
    }
    //@RequestParam和传递参数对应，后端参数可以随便定义
    @RequestMapping(value = "/hello/param" ,method = RequestMethod.GET)
    public String helloAccount(@RequestParam("para") String par){
        System.out.println("hello");
        System.out.println(par);
        return "success";
    }
    //@RequestBody表示想获取表单内容，提交方式只能是POST获取格式id=111&username=11&password=11
    @RequestMapping(value = "/hello/body" ,method = RequestMethod.POST)
    public String helloBody(@RequestBody String body){
        System.out.println(body);
        return "success";
    }
    //使用@PathVariable获取url上的参数，适用restful风格的请求，即路径前缀相同，根据请求方式和参数形式进行路径匹配
    @RequestMapping(value = "/hello/param/{id}" ,method = RequestMethod.GET)
    public String helloVariable(@PathVariable("id") String id){
        System.out.println(id);
        return "success";
    }
}