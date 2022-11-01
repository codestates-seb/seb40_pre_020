package com.SEB_Pre_020.demo.config.auth;

import com.SEB_Pre_020.demo.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 시큐리티가 로그인 주소 요청 오면 낚아채서 로그인 진행
 * 로그인 진행이 완료가 되면 시큐리티 세션을 만들어줍니다.
 * Security ContextHolder 라는 곳에 키값 저장
 * 세션에 들어갈 수 있는 정보는 Authentication 타입 객체로 정해짐
 * Authentication 안에 User 정보(UserDetails) 있어야 함
 *
 * **/

public class PrincipalDetails implements UserDetails {

    private Member member;

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> member.getRole());
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}