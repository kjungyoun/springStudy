package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //에너테이션 = Controller 구성 시 자동으로 필요한 것들을 import해준다.
public class DemoController {

    @GetMapping("/greeting")
    //localhost:8080 뒤에 주소를 어느곳으로 연결할때 어떤 메소드를 실행할지 mapping한다. 즉, 여기서는 localhost:8080/greeting으로 매핑한다.
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                           Model model) { //required =ture는 주소 뒤에 name이 꼭 와야한다는 의미, default는 name이 없을 경우 world가 출력되게 함
        model.addAttribute("name", name); //name을 greeting.html에 넘겨준다.
        return "greeting"; // greeting.html을 return을 하겠다는 의미, 여기서 html파일 이름과 같아야한다.
    }
}
