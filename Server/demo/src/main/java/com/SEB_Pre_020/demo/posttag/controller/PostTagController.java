package com.SEB_Pre_020.demo.posttag.controller;

import com.SEB_Pre_020.demo.dto.MultiResponseDto;
import com.SEB_Pre_020.demo.post.service.PostService;
import com.SEB_Pre_020.demo.posttag.dto.PostTagDto;
import com.SEB_Pre_020.demo.posttag.entity.PostTag;
import com.SEB_Pre_020.demo.posttag.mapper.PostTagMapper;
import com.SEB_Pre_020.demo.posttag.service.PostTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tags")
@Validated
@Slf4j
public class PostTagController {
    private final PostTagService postTagService;
    private final PostTagMapper postTagMapper;

    public PostTagController(PostTagService postTagService, PostTagMapper postTagMapper) {
        this.postTagService = postTagService;
        this.postTagMapper = postTagMapper;
    }

    @PostMapping
    public ResponseEntity postPostTags(@Valid @RequestBody PostTagDto.Post requestBody) {
        List<PostTag> postTags = postTagMapper.postTagPostToPostTag(requestBody);

        List<PostTag> createdPostTags = postTagService.createPostTags(postTags);

        return new ResponseEntity<>(
                new MultiResponseDto<>(postTagMapper.postTagsToPostTagResponses(createdPostTags)), HttpStatus.CREATED
        );
    }
}
