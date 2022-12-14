package com.SEB_Pre_020.demo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberPostDto {
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String name;

    @NotBlank(message = "패스워드를 입력해주세요.")
    private String password;

    public String getEmail() { return email; }

    public String getName() { return name; }

    public String getPassword() {return password; }
}
