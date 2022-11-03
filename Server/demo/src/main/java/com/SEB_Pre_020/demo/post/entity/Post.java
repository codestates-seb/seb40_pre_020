package com.SEB_Pre_020.demo.post.entity;

import com.SEB_Pre_020.demo.audit.Auditable;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.posttag.entity.PostTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="postId")
    private int id;

    @Column(name = "parentId", nullable = false, updatable = true, unique = false)
    private int parentId;

    @Column(name = "postTitle", nullable = false, updatable = true, unique = false)
    private String postTitle;

    @Column(name = "postContent", nullable = false, updatable = true, unique = false)
    private String postContent;

    @ManyToOne
    @JoinColumn(name = "memberId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(name = "postView", nullable = false, updatable = true, unique = false)
    private int postView;

    @Column(name = "postVoteCount", nullable = false, updatable = true, unique = false)
    private int postVoteCount;

    @Column(name = "postAnswerCount", nullable = false, updatable = true, unique = false)
    private int postAnswerCount;

    @Column(name = "postCommentCount", nullable = false, updatable = true, unique = false)
    private int postCommentCount;

//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    private List<PostTag> postTags;
}
