package com.SEB_Pre_020.demo.member.entity;

import com.SEB_Pre_020.demo.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MemberId", updatable = false, nullable = false, unique = true)
    private Integer id;

    @Column(name="MemberNickname", nullable = false, unique = true)
    private String name; //사용자 이름

    @Column(name="MemberEmail", nullable = false, unique = true)
    private String email; //로그인 아이디

    @Column(name="MemberPassword", nullable = false)
    private String password;
}
