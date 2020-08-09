package com.example.springStudy2.controller;

import com.example.springStudy2.domain.Member;
import com.example.springStudy2.repository.MemoryRepository;
import com.example.springStudy2.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller //controller 객체임을 명시해서 얘를 이용해 controller에 bean객체를 등록한다

public class Controller {
    private final Service service;

    @Autowired

    public Controller(Service service) { //controller 생성자, 이 생성자는 bean객체만을 인수로 받음 따라서 service를 bean 객체로 등록해야함
        this.service = service;
    }

    @GetMapping("/join")
    public String join(@RequestParam(value = "name", required = true) String name,
                       Model model) {
        Member member = new Member();
        member.setName(name);
        service.join(member);
        model.addAttribute("name", name);
        return "join";
    }

    @GetMapping("/find")
    public String find(@RequestParam(value = "name", required = true) String name,
                       Model model) {
        service.findMemberByname(name);
        model.addAttribute("name", service.findMemberByname(name).get().getName());
        // findMemberByname(name).get은 member 객체를 뽑는것이고 거기에 .getName()을 하면 member변수의 name을 뽑는 것이다.
        return "find";
    }

    @GetMapping("/member/new")
    public String newForm(){
        return "member/createMemberForm"; //new에 접근하면 createMemberForm이라는 페이지를 리턴
    }

    @PostMapping("/member/new")
    public String join(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        service.join(member);

        return "redirect:/"; // 루트페이지로 다시 이동시켜줌 즉, static에서 생성한 index 페이지를 보여줌
    }
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("members",service.findMembers());
        return "member/memberList";
    }
}
