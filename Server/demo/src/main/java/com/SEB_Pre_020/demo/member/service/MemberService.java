package com.SEB_Pre_020.demo.member.service;

import com.SEB_Pre_020.demo.exception.ExceptionCode;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.member.repository.MemberRepository;
import com.SEB_Pre_020.demo.exception.BusinessLogicException;

import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member joinMember(Member member) {
        verifyExistsEmail(member.getEmail());
        return memberRepository.save(member);
    } // 회원등록 메서드

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
    }
}

/** jwt 토큰 적용 뒤에 수정할 코드
//    public Member getLoginMember() {
//        return findMember(getUserByToken());
//    }

//    public User getUserByToken(){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        PrincipalDetails principalDetails = (PrincipalDetails)principal;
//
//        return principalDetails.getUser();
//    }
} **/
