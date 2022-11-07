package com.SEB_Pre_020.demo.member.controller;

import com.SEB_Pre_020.demo.post.entity.Post;
import com.SEB_Pre_020.demo.post.mapper.PostMapper;
import com.SEB_Pre_020.demo.post.service.PostService;
import com.SEB_Pre_020.demo.dto.PageResponseDto;
import com.SEB_Pre_020.demo.member.mapstruct.mapper.MemberMapper;
import com.SEB_Pre_020.demo.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/profiles")
//@CrossOrigin(origins = {"http://localhost:3000","http://seb40pre020.s3-website.ap-northeast-2.amazonaws.com:80"}, allowedHeaders = "*") // cors 설정
@Validated
@Slf4j
public class ProfileController {
    private final PostService postService;
    private final PostMapper postMapper;
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public ProfileController(PostService postService, PostMapper postMapper, MemberService memberService, MemberMapper memberMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    /** 사용자 게시글 조회 */
    @GetMapping("/{MemberId}/posts")
    public ResponseEntity getMemberPosts(@Positive @RequestParam int page,
                                         @Positive @RequestParam int size,
                                         @PathVariable("MemberId") @Positive int memberId) {
        Page<Post> postPage = postService.findMemberPosts(memberId, page-1, size);
        List<Post> posts = postPage.getContent();

        return new ResponseEntity<>(
                new PageResponseDto<>(postMapper.postsToPostResponses(posts), postPage), HttpStatus.OK
        );
    }

    /** 사용자 답글 조회 */
    @GetMapping("/{MemberId}/answers")
    public ResponseEntity getMemberAnswers(@Positive @RequestParam int page,
                                         @Positive @RequestParam int size,
                                         @PathVariable("MemberId") @Positive int memberId) {
        Page<Post> postPage = postService.findMemberAnswers(memberId, page-1, size);
        List<Post> posts = postPage.getContent();

        return new ResponseEntity<>(
                new PageResponseDto<>(postMapper.postsToPostResponses(posts), postPage), HttpStatus.OK
        );
    }
}
