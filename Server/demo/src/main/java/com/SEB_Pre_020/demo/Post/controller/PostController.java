package com.SEB_Pre_020.demo.Post.controller;

import com.SEB_Pre_020.demo.Post.dto.PostDto;
import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.Post.mapper.PostMapper;
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
@RequestMapping("/posts")
@Validated
@Slf4j
public class PostController {
    private final PostService postService;
    private final PostMapper mapper;

    public PostController(PostService postService, PostMapper mapper) {
        this.postService = postService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postPost(@Valid @RequestBody PostDto.Post requestBody) {
        Post post = mapper.postPostToPost(requestBody);

        Post createdPost = postService.createPost(post);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.postToPostResponse(createdPost)), HttpStatus.CREATED
        );
    }

    @PatchMapping("/{PostId}")
    public ResponseEntity patchPost(
            @PathVariable("PostId") @Positive int postId,
            @Valid @RequestBody PostDto.Patch requestBody) {
        requestBody.setId(postId);
        Post post = postService.updatePost(mapper.postPatchToPost(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.postToPostResponse(post)),
                HttpStatus.OK);
    }

    /** 단일 게시글(답글) get */
    @GetMapping("/{PostId}")
    public ResponseEntity getPost(@PathVariable("PostId") @Positive int postId) {
        Post post = postService.findPost(postId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.postToPostResponse(post)), HttpStatus.OK
        );
    }

}