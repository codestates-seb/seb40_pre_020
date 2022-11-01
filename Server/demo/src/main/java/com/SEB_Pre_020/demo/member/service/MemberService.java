package com.SEB_Pre_020.demo.member.service;

import com.SEB_Pre_020.demo.config.auth.PrincipalDetails;
import com.SEB_Pre_020.demo.exception.ExceptionCode;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.member.repository.MemberRepository;
import com.SEB_Pre_020.demo.exception.BusinessLogicException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository,
                         PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        List<GrantedAuthority> authorities = createAuthorities(Member.MemberRole.ROLE_USER.name());
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        UserDetails userDetails = new User(member.getEmail(), encryptedPassword, authorities);

        Member savedMember = memberRepository.save(member);

        System.out.println("회원정보가 DB에 등록되었습니다");
        return savedMember;
    } // 회원등록 메서드

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
    }

    public Member findVerifiedMember(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        Member findMember=optionalMember.orElseThrow(()-> //만일 db에 저장된 유저 정보 없으면 예외발생
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return findMember;
    }

    public Member getUserByToken(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetails principalDetails = (PrincipalDetails)principal;

        return principalDetails.getMember();
    }
}
