package com.SEB_Pre_020.demo.member.entity;

import com.SEB_Pre_020.demo.audit.Auditable;
import com.SEB_Pre_020.demo.member.repository.MemberRepository;
import com.SEB_Pre_020.demo.member.service.MemberService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.transaction.Transactional;

@NoArgsConstructor
@Getter
@Setter
@Entity
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

    @Column(nullable = true)
    private String role; // Member


    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
