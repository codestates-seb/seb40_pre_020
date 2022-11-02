package com.SEB_Pre_020.demo.config.auth.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class CustomAuthorityUtils {
    private final List<GrantedAuthority> MEMBER_ROLES = AuthorityUtils.createAuthorityList("ROLE_MEMBER");
    private final List<String> MEMBER_ROLES_STRING = List.of("MEMBER");

    // 메모리 상의 Role을 기반으로 권한 정보 생성.
    public List<GrantedAuthority> createAuthorities(String email) {
        return MEMBER_ROLES;
    }

    public List<GrantedAuthority> createAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        return authorities;
    }

    // DB 저장 용
    public List<String> createRoles(String email) {
        return MEMBER_ROLES_STRING;
    }
}
