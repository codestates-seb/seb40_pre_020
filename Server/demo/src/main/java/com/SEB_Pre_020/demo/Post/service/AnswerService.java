package com.SEB_Pre_020.demo.Post.service;

import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.Post.repository.PostRepository;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AnswerService {
    private final PostRepository postRepository;

    public AnswerService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    /** 게시글의 모든 답변 get */
    @Transactional
    public Page<Post> findPostAnswers(int postId, int page, int size) {
        List<Post> postList = postRepository.findByParentId(postId);

        Pageable pageable = PageRequest.of(page, size);
        PagedListHolder pagedListHolder = new PagedListHolder(postList);
        pagedListHolder.setPageSize(size);
        pagedListHolder.setPage(page);

        return new PageImpl<>(pagedListHolder.getPageList(), pageable, postList.size());
    }
}