package com.SEB_Pre_020.demo.controller;

import com.SEB_Pre_020.demo.member.Member;
import com.SEB_Pre_020.demo.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("수정")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("수정")
    public String addForm(@ModelAttribute("member") Member member){
        return "수정";
    }

    @PostMapping("수정")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "수정";
        }

        memberRepository.save(member);
        return "redirect:/";
    }

}