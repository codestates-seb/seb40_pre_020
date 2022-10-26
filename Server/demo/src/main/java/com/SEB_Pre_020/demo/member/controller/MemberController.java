package com.SEB_Pre_020.demo.member.controller;

import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="v1/members")
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("")
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