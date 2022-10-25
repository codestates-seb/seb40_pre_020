package com.SEB_Pre_020.demo.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String name; //사용자 이름
    @NotEmpty
    private Long email; //로그인 아이디
    @NotEmpty
    private String password;

}
