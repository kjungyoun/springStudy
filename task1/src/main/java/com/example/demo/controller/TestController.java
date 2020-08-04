package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class TestController {
    @GetMapping("/timeprint")
    public String timeprint(Model model) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분ss초");
        Date currentTime = new Date();
        String mTime = format1.format(currentTime);
        model.addAttribute("mtime", mTime);
        return "timeprint";
    }
}
