package com.SEB_Pre_020.demo.comment.repository;

import com.SEB_Pre_020.demo.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost_Id(int postId);
}