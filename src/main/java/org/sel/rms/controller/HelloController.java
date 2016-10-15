package org.sel.rms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xubowei on 16/10/15.
 */
@RestController
public class HelloController {

    @Value("${config.name}")
    String name = "World";


    /**
     *   @api {get} /hello 基础测试
     *   @apiName 返回测试字符串
     *   @apiGroup Test
     *   @apiVersion 0.1.1
     *   @apiSuccess {String} null hello + ${config.name}
     */
    /**
     *   @api {get} /hello 基础测试
     *   @apiName 返回测试字符串
     *   @apiGroup Test
     *   @apiVersion 0.1.0
     *   @apiSuccess {String} null hello + xxx
     */
    @RequestMapping("/hello")
    public String helloWorldMessage() {
        return "hello " + name ;
    }
}
