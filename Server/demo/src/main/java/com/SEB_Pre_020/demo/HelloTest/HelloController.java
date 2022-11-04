package com.SEB_Pre_020.demo.HelloTest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public List<String> Hello(){
        return Arrays.asList("서버", "연결 성공");
    }
}