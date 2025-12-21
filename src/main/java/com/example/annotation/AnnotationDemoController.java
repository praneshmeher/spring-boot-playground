package com.example.annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/prefix")
    @PrefixResponse(prefix = "Hi: ", apply = true)
    public String prefix() {
        return "this is a prefixed response";
    }

}
