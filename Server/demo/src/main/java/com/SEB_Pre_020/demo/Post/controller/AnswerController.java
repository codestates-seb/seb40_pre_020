package com.SEB_Pre_020.demo.Post.controller;

import com.SEB_Pre_020.demo.Post.dto.PostDto;
import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.Post.mapper.PostMapper;
import com.SEB_Pre_020.demo.Post.service.AnswerService;
import com.SEB_Pre_020.demo.Post.service.PostService;
import com.SEB_Pre_020.demo.dto.PageResponseDto;
import com.SEB_Pre_020.demo.dto.SingleResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/answers")
@Validated
@Slf4j
public class AnswerController {
    private final PostService postService;

    private final AnswerService answerService;
    private final PostMapper mapper;

    public AnswerController(PostService postService, AnswerService answerService, PostMapper mapper) {
        this.postService = postService;
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody PostDto.Post requestBody) {
        Post post = mapper.postPostToPost(requestBody);

        Post createdPost = postService.createPost(post);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.postToPostResponse(createdPost)), HttpStatus.CREATED
        );
    }

    @PatchMapping("/{PostId}")
    public ResponseEntity patchAnswer(
            @PathVariable("PostId") @Positive int postId,
            @Valid @RequestBody PostDto.Patch requestBody) {
        requestBody.setId(postId);
        Post post = postService.updatePost(mapper.postPatchToPost(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.postToPostResponse(post)),
                HttpStatus.OK);
    }

    /** 특정 게시글의 모든 답글 get */
    @GetMapping("/{PostId}")
    public ResponseEntity getAnswers(@Positive @RequestParam int page,
                                  @Positive @RequestParam int size,
                                  @PathVariable("PostId") @Positive int postId) {
        Page<Post> postPage = answerService.findPostAnswers(postId, page-1, size);
        List<Post> posts = postPage.getContent();

        return new ResponseEntity<>(
                new PageResponseDto<>(mapper.postsToPostResponses(posts), postPage), HttpStatus.OK
        );
    }
}
