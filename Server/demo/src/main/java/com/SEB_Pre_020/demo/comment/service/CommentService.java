package com.SEB_Pre_020.demo.comment.service;

import com.SEB_Pre_020.demo.comment.entity.Comment;
import com.SEB_Pre_020.demo.comment.repository.CommentRepository;
import com.SEB_Pre_020.demo.post.entity.Post;
import com.SEB_Pre_020.demo.post.service.PostService;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    /** 댓글 생성 */
    @Transactional
    public Comment createComment(Comment comment) {
        int memberId = comment.getMember().getId();
        int postId = comment.getPost().getId();

        if (!commentRepository.existsById(comment.getId())) {
            Comment savedComment = commentRepository.save(comment);

            // 게시글(답글)의 commentCount 증가
            Post post = postService.findPost(comment.getPost().getId());
            post.setPostCommentCount(post.getPostCommentCount() + 1);
            postService.updatePost(post);


            return savedComment;
        }

        return null;
    }

    /**  댓글 수정 */
    public Comment updateComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getId());

        // 댓글 내용만 수정 가능
        findComment.setCommentContent(comment.getCommentContent());

        return commentRepository.save(findComment);
    }

    /** 댓글 하나만 get */
    @Transactional(readOnly = true)
    public Comment findComment(int commentId) {
        return findVerifiedComment(commentId);
    }

    /** 게시글(답글)의 모든 댓글 get */
    @Transactional
    public Page<Comment> findPostComments(int postId, int page, int size) {
        List<Comment> commentList = commentRepository.findByPost_Id(postId);

        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        PagedListHolder pagedListHolder = new PagedListHolder(commentList);
        pagedListHolder.setPageSize(size);
        pagedListHolder.setPage(page);

        return new PageImpl<>(pagedListHolder.getPageList(), pageable, commentList.size());
    }

    /** id로 댓글 get */
    @Transactional(readOnly = true)
    public Comment findVerifiedComment(int commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment findComment = optionalComment.orElseThrow();
        return findComment;
    }

    /** 댓글 삭제 */
    @Transactional
    public void deleteComment(int commentId) {
        Comment findComment = findVerifiedComment(commentId);

        // 게시글(답글)의 commentCount 감소
        Post post = postService.findPost(findComment.getPost().getId());
        post.setPostCommentCount(post.getPostCommentCount() - 1);
        postService.updatePost(post);

        commentRepository.delete(findComment);
    }
}