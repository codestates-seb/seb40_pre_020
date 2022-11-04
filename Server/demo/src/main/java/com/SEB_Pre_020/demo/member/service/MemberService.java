package com.SEB_Pre_020.demo.member.service;

import com.SEB_Pre_020.demo.config.auth.utils.CustomAuthorityUtils;
import com.SEB_Pre_020.demo.exception.ExceptionCode;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.member.repository.MemberRepository;
import com.SEB_Pre_020.demo.exception.BusinessLogicException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    public MemberService(MemberRepository memberRepository,
                         ApplicationEventPublisher publisher,
                         PasswordEncoder passwordEncoder,
                         CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

    private Member findMember(Member member) { return findVerifiedMember(member.getEmail());}

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles); // DB에 MEMBER ROLE 저장

        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    private List<GrantedAuthority> createAuthorities(String... roles) {
        // (3-1) Java의 Stream API를 이용해 생성자 파라미터로 해당 User의 Role을 전달하면서
        // SimpleGrantedAuthority 객체를 생성한 후, List<SimpleGrantedAuthority> 형태로 리턴
        return Arrays.stream(roles)
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    } //SimpleGrantedAuthority 를 사용해 Role 베이스 형태의 권한을 지정할 때
    // ‘Roll_’ + 권한명 형태로 지정해 주어야

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
    }

    @Transactional(readOnly = true)
    public Member findMember(String memberEmail) { return findVerifiedMember(memberEmail); }

    @Transactional
    public Member findVerifiedMember(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        Member findMember=optionalMember.orElseThrow(()-> //만일 db에 저장된 유저 정보 없으면 예외발생
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return findMember;
    }

//    public Member getUserByToken(){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        PrincipalDetails principalDetails = (PrincipalDetails)principal;
//
//
//        return .getMember();
//    }
}
