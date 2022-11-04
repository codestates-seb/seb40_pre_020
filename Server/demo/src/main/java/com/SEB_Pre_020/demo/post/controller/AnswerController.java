package com.SEB_Pre_020.demo.post.controller;

import com.SEB_Pre_020.demo.post.dto.PostDto;
import com.SEB_Pre_020.demo.post.entity.Post;
import com.SEB_Pre_020.demo.post.mapper.PostMapper;
import com.SEB_Pre_020.demo.post.service.PostService;
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
    private final PostMapper mapper;

    public AnswerController(PostService postService, PostMapper mapper) {
        this.postService = postService;
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
        Page<Post> postPage = postService.findPostPosts(postId, page-1, size);
        List<Post> posts = postPage.getContent();

        return new ResponseEntity<>(
                new PageResponseDto<>(mapper.postsToPostResponses(posts), postPage), HttpStatus.OK
        );
    }

    @DeleteMapping("/{PostId}")
    public ResponseEntity deletePost(@PathVariable("PostId") @Positive int postId) {
        postService.deletePost(postId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
