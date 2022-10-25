package com.SEB_Pre_020.demo.Post.entity;

import com.SEB_Pre_020.demo.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

//    @ManyToOne
//    @JoinColumn(name = "MemberId")
//    private Member member;

    @Column(name = "PostView", nullable = false, updatable = true, unique = false)
    private int postView;
}