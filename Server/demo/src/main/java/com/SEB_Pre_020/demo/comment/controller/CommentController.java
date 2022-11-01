package com.SEB_Pre_020.demo.comment.controller;

import com.SEB_Pre_020.demo.comment.dto.CommentDto;
import com.SEB_Pre_020.demo.comment.entity.Comment;
import com.SEB_Pre_020.demo.comment.mapper.CommentMapper;
import com.SEB_Pre_020.demo.comment.service.CommentService;
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
@RequestMapping("/comments")
@Validated
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.Post requestBody) {
        Comment comment = commentMapper.commentPostToComment(requestBody);

        Comment createdComment = commentService.createComment(comment);

        return new ResponseEntity<>(
                new SingleResponseDto<>(commentMapper.commentToCommentResponse(createdComment)),
                HttpStatus.CREATED);

    }

    @PatchMapping("/{CommentId}")
    public ResponseEntity patchComment(@PathVariable("CommentId") @Positive int commentId,
                                       @Valid @RequestBody CommentDto.Patch requestBody) {
        requestBody.setId(commentId);

        Comment comment = commentService.updateComment(commentMapper.commentPatchToComment(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(commentMapper.commentToCommentResponse(comment)),
                HttpStatus.OK);
    }

    /** 한 게시글(답글)의 댓글 목록 get */
    @GetMapping("/{PostId}")
    public ResponseEntity getComments(@PathVariable("PostId") @Positive int postId,
                                      @Positive @RequestParam int page,
                                      @Positive @RequestParam int size) {
        Page<Comment> commentPage = commentService.findPostComments(postId, page-1, size);
        List<Comment> comments = commentPage.getContent();

        return new ResponseEntity<>(
                new PageResponseDto<>(commentMapper.commentsToCommentResponses(comments), commentPage), HttpStatus.OK
        );
    }

    @DeleteMapping("/{CommentId}")
    public ResponseEntity deleteComment(@PathVariable("CommentId") @Positive int commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
