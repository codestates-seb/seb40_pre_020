//package com.SEB_Pre_020.demo.config.auth;
//
//import com.SEB_Pre_020.demo.member.entity.Member;
//import com.SEB_Pre_020.demo.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class PrincipalDetailService implements UserDetailsService {
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        System.out.println("PrincipalDetailsService : 진입");
//        Optional<Member> memberEntity  = memberRepository.findByEmail(email);
//
//        return new PrincipalDetails(memberEntity.get());
//    }
//}
