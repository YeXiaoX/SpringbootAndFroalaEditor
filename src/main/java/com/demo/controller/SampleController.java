package com.demo.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class SampleController {

    @RequestMapping("/index")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


}