package com.SEB_Pre_020.demo.vote.controller;

import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.Post.service.PostService;
import com.SEB_Pre_020.demo.dto.SingleResponseDto;
import com.SEB_Pre_020.demo.vote.dto.VoteDto;
import com.SEB_Pre_020.demo.vote.entity.Vote;
import com.SEB_Pre_020.demo.vote.mapper.VoteMapper;
import com.SEB_Pre_020.demo.vote.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/Votes")
@Validated
@Slf4j
public class VoteController {
    private final VoteService voteService;
    private final PostService postService;
    private final VoteMapper voteMapper;

    public VoteController(VoteService voteService, PostService postService, VoteMapper voteMapper) {
        this.voteService = voteService;
        this.postService = postService;
        this.voteMapper = voteMapper;
    }

    // voteType에 따라 해당 게시글(답글)의 추천 수 수정
    @PostMapping
    public ResponseEntity postVote(@Valid @RequestBody VoteDto.Post requestBody) {
        Vote vote = voteMapper.votePostToVote(requestBody);

        Vote createdVote = voteService.createVote(vote);

        // 추천수 수정
        Post post = postService.findPost(createdVote.getPost().getId());
        post.setPostVoteCount(post.getPostVoteCount() + createdVote.getVoteType());
        postService.updatePost(post);

        return new ResponseEntity<>(
                new SingleResponseDto<>(voteMapper.voteToVoteResponse(createdVote))
               , HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity deleteVote(@Positive @RequestParam int memberId,
                                     @Positive @RequestParam int postId) {
        // 추천수 수정
        Post post = postService.findPost(postId);
        post.setPostView(post.getPostView() - voteService.findVerifiedVote(memberId, postId).getVoteType());
        postService.updatePost(post);

        voteService.deleteVote(memberId, postId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
