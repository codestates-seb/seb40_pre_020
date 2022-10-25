package com.SEB_Pre_020.demo.Post.repository;

import com.SEB_Pre_020.demo.Post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
