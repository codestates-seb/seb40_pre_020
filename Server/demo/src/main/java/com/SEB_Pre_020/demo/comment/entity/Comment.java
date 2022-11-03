package com.SEB_Pre_020.demo.comment.entity;

import com.SEB_Pre_020.demo.audit.Auditable;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.swing.text.StringContent;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="comment")
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="commentId")
    private int id;

    @ManyToOne
    @JoinColumn(name = "commentMemberId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "commentPostId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @Column(name = "commentContent", nullable = false, updatable = true, unique = false)
    private String commentContent;
}
