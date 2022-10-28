package com.SEB_Pre_020.demo.vote.service;

import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.vote.entity.Vote;
import com.SEB_Pre_020.demo.vote.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    /** 추천(비추천) 생성 */
    public Vote createVote(Vote vote) {
        int memberId = vote.getMember().getId();
        int postId = vote.getPost().getId();

        if (!voteRepository.existsById(vote.getId())) {
            Vote saveVote = voteRepository.save(vote);
            return saveVote;
        }
        return  null;
    }

    /** 추천(비추천) 삭제 */
    public void deleteVote(int memberId, int postId) {
        Vote findVote = findVerifiedVote(memberId, postId);

        voteRepository.delete(findVote);
    }

    /** id로 추천(비추천) get */
    @Transactional(readOnly = true)
    public Vote findVerifiedVote(int voteId) {
        Optional<Vote> optionalPost = voteRepository.findById(voteId);
        Vote findVote = optionalPost.orElseThrow();     // Exception code 추가 필요
        return findVote;
    }

    /** memberId, postId로 추천(비추천) get */
    @Transactional(readOnly = true)
    public Vote findVerifiedVote(int memberId, int postId) {
        Optional<Vote> optionalPost = voteRepository.findByMember_IdAndPost_Id(memberId, postId);
        Vote findVote = optionalPost.orElseThrow();     // Exception code 추가 필요
        return findVote;
    }
}
