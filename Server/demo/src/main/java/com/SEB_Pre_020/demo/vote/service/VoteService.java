package com.SEB_Pre_020.demo.vote.service;

import com.SEB_Pre_020.demo.post.entity.Post;
import com.SEB_Pre_020.demo.post.service.PostService;
import com.SEB_Pre_020.demo.vote.entity.Vote;
import com.SEB_Pre_020.demo.vote.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostService postService;

    public VoteService(VoteRepository voteRepository, PostService postService) {
        this.voteRepository = voteRepository;
        this.postService = postService;
    }

    /** 추천(비추천) 생성 */
    public Vote createVote(Vote vote) {
        int memberId = vote.getMember().getId();
        int postId = vote.getPost().getId();

        if(voteRepository.existsByMember_IdAndPost_Id(memberId, postId)) {
            Vote findVote = findVerifiedVote(memberId, postId);
            // 이미 추천했으면 무시
            if (findVote.getVoteType() == vote.getVoteType())   return vote;
            // 반대된 추천을 했다면 추천수 수정 & 이전 추천 삭제
            Post post = postService.findPost(vote.getPost().getId());
            post.setPostVoteCount(post.getPostVoteCount() + vote.getVoteType());
            postService.updatePost(post);

            deleteVote(memberId, postId);
            return vote;
        }

        else if (!voteRepository.existsById(vote.getId())) {
            // 추천수 수정
            Post post = postService.findPost(vote.getPost().getId());
            post.setPostVoteCount(post.getPostVoteCount() + vote.getVoteType());
            postService.updatePost(post);
            Vote saveVote = voteRepository.save(vote);
            return saveVote;
        }
        return  findVerifiedVote(memberId, postId);
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
