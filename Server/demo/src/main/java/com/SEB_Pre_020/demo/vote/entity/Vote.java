package com.SEB_Pre_020.demo.vote.entity;

import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.audit.Auditable;
import com.SEB_Pre_020.demo.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vote extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="voteId", nullable = false, unique = true)
    private int id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "votePostId")
    private Post post;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "voteMemberId")
    private Member member;

    @Column(name = "voteType", nullable = false, updatable = true, unique = false)
    private int voteType;
}
