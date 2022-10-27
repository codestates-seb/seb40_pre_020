package com.SEB_Pre_020.demo.member.controller;

import com.SEB_Pre_020.demo.dto.SingleResponseDto;
import com.SEB_Pre_020.demo.member.dto.MemberPostDto;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.member.mapstruct.mapper.MemberMapper;
import com.SEB_Pre_020.demo.member.repository.MemberRepository;
import com.SEB_Pre_020.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/v1")
public class MemberController {

    private MemberService memberService;

    private MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping("/signup") // 회원가입
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto){
       Member member = memberMapper.memberDtoToMember(memberPostDto);
       Member joinedMember = memberService.joinMember(member);
       return new ResponseEntity<>(
               new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(joinedMember)),
               HttpStatus.CREATED);
    }
/** jwt 토큰 적용 뒤 완성해야 할 코드
//    @GetMapping("/member") // 로그인한 회원 정보 토큰 클라이언트에게 전달
//    public ResponseEntity getMember(){
//        Member member = memberService.getLoginMember();
//
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(member)),
//                HttpStatus.OK);
//    }
//    @PutMapping("/profile/memberid/{MemberId}") // 마이페이지 프로필 수정
 **/
}