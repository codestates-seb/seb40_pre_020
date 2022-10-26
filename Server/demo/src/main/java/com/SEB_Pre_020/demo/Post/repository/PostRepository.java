package com.SEB_Pre_020.demo.Post.repository;

import com.SEB_Pre_020.demo.Post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
