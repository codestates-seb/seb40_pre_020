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
@RequestMapping(value="v1/members", produces = {MediaType.APPLICATION_JSON_VALUE})
//JSON 형식의 데이터를 응답 데이터로 전송합니다
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