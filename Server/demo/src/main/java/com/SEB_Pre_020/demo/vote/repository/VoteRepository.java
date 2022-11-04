package com.SEB_Pre_020.demo.vote.repository;

import com.SEB_Pre_020.demo.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    List<Vote> findByPost_Id(int postId);
    List<Vote> findByMember_Id(int memberId);
    Optional<Vote> findByMember_IdAndPost_Id(int memberId, int postId);

    Boolean existsByMember_IdAndPost_Id(int memberId, int postId);
}
