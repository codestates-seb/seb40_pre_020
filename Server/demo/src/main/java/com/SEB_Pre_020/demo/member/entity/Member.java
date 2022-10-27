package com.SEB_Pre_020.demo.member.entity;

import com.SEB_Pre_020.demo.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="member")
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 설정을 위한 애너테이션.
    // IDENTITY 전략(=기본키 설정을 데이터베이스에 위임)사용
    @Column(name="MemberId")
    private int id;

    @Column(name="MemberNickname", nullable = false, unique = true)
    private String name; //사용자 이름

    @Column(name="MemberEmail", nullable = false, unique = true)
    private String email; //로그인 아이디

    @Column(name="MemberPassword", nullable = false)
    private String password;

    //방문수(VisitCount) 추가해야 함


    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
