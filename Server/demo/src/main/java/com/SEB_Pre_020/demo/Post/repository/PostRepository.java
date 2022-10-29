package com.SEB_Pre_020.demo.Post.repository;

import com.SEB_Pre_020.demo.Post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByParentId(int parentId);
    List<Post> findByMember_IdAndParentId(int memberId, int parentId);
    List<Post> findByMember_IdAndParentIdNot(int memberId, int parentId);
}
