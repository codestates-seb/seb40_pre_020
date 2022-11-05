package com.SEB_Pre_020.demo.posttag.repository;

import com.SEB_Pre_020.demo.posttag.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {
    List<PostTag> findByTagId(int tagId);
    Optional<PostTag> findByTag_IdAndPost_Id(int tagId, int postId);
}
