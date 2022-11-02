package com.SEB_Pre_020.demo.tag.repository;

import com.SEB_Pre_020.demo.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    boolean existsByTagName(String tagName);
}
