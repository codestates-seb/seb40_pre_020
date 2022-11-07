package com.SEB_Pre_020.demo.posttag.controller;

import com.SEB_Pre_020.demo.dto.MultiResponseDto;
import com.SEB_Pre_020.demo.dto.PageResponseDto;
import com.SEB_Pre_020.demo.post.entity.Post;
import com.SEB_Pre_020.demo.post.mapper.PostMapper;
import com.SEB_Pre_020.demo.post.service.PostService;
import com.SEB_Pre_020.demo.posttag.dto.PostTagDto;
import com.SEB_Pre_020.demo.posttag.entity.PostTag;
import com.SEB_Pre_020.demo.posttag.mapper.PostTagMapper;
import com.SEB_Pre_020.demo.posttag.service.PostTagService;
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
@RequestMapping("/tags")
//@CrossOrigin(origins = "*", allowedHeaders = "*") // cors 설정
@Validated
@Slf4j
public class PostTagController {
    private final PostTagService postTagService;
    private final PostTagMapper postTagMapper;
    private final PostMapper postMapper;

    public PostTagController(PostTagService postTagService, PostTagMapper postTagMapper, PostMapper postMapper) {
        this.postTagService = postTagService;
        this.postTagMapper = postTagMapper;
        this.postMapper = postMapper;
    }

    @PostMapping
    public ResponseEntity postPostTags(@Valid @RequestBody PostTagDto.Post requestBody) {
        List<PostTag> postTags = postTagMapper.postTagPostToPostTag(requestBody);

        List<PostTag> createdPostTags = postTagService.createPostTags(postTags);

        return new ResponseEntity<>(
                new MultiResponseDto<>(postTagMapper.postTagsToPostTagResponses(createdPostTags)), HttpStatus.CREATED
        );
    }

    @GetMapping("/{TagName}")
    public ResponseEntity getPostsByTag(@PathVariable("TagName") String tagName,
                                        @Positive @RequestParam int page,
                                        @Positive @RequestParam int size) {
        Page<Post> postPage = postTagService.findTagPosts(tagName, page-1, size);
        List<Post> posts = postPage.getContent();

        return new ResponseEntity<>(
                new PageResponseDto<>(postMapper.postsToPostResponses(posts), postPage), HttpStatus.OK
        );
    }
}
