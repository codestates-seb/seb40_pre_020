package com.SEB_Pre_020.demo.member.entity;

import com.SEB_Pre_020.demo.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name="member")
@Table(name="member")
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 설정을 위한 애너테이션.
    // IDENTITY 전략(=기본키 설정을 데이터베이스에 위임)사용
    @Column(name="memberId", nullable = false)
    private int id;

    @Column(name="memberNickname", nullable = false, unique = true)
    private String name; //사용자 이름

    @Column(name="memberEmail", nullable = false, unique = true)
    private String email; //로그인 아이디

    @Column(name="memberPassword", nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Member(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
