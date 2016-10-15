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


    @RequestMapping("/hello")
    public String helloWorldMessage() {
        return "hello " + name ;
    }
}
