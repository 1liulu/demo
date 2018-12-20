package com.example.demo1.Contorller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/index")
public class IndexController {


    @RequestMapping(value = "/show")
    public Map show(String page,String size) {
        return null;
    }


}
