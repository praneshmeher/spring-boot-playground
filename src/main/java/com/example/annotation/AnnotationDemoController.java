package com.example.annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annotation-demo")
public class AnnotationDemoController {


    @GetMapping("/shout")
    @UppercaseResponse
    public String shout() {
        return "please make me loud";
    }

}
