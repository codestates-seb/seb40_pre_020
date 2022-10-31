package com.SEB_Pre_020.demo.post.entity;

import com.SEB_Pre_020.demo.audit.Auditable;
import com.SEB_Pre_020.demo.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PostId")
    private int id;

    @Column(name = "ParentId", nullable = false, updatable = true, unique = false)
    private int parentId;

    @Column(name = "PostTitle", nullable = false, updatable = true, unique = false)
    private String postTitle;

    @Column(name = "PostContent", nullable = false, updatable = true, unique = false)
    private String postContent;

    @ManyToOne
    @JoinColumn(name = "MemberId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(name = "PostView", nullable = false, updatable = true, unique = false)
    private int postView;

    @Column(name = "PostVoteCount", nullable = false, updatable = true, unique = false)
    private int postVoteCount;

    @Column(name = "PostAnswerCount", nullable = false, updatable = true, unique = false)
    private int postAnswerCount;

    @Column(name = "PostCommentCount", nullable = false, updatable = true, unique = false)
    private int postCommentCount;
}
