package com.SEB_Pre_020.demo.vote.repository;

import com.SEB_Pre_020.demo.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    List<Vote> findByPostId(int postId);
    List<Vote> findByMemberId(int memberId);
}
