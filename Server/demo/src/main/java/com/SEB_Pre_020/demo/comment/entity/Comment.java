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
@Entity
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CommentId")
    private int id;

    @ManyToOne
    @JoinColumn(name = "MemberId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PostId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @Column(name = "CommentContent", nullable = false, updatable = true, unique = false)
    private String commentContent;
}
