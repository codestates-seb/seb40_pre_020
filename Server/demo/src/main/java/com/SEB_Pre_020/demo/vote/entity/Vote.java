package com.SEB_Pre_020.demo.vote.entity;

import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.audit.Auditable;
import com.SEB_Pre_020.demo.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vote extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="VoteId")
    private int id;

    @ManyToOne
    @JoinColumn(name = "Vote_PostId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "Vote_MemberId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(name = "VoteType", nullable = false, updatable = true, unique = false)
    private int voteType;
}
