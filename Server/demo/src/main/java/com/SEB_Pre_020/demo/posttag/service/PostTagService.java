package com.SEB_Pre_020.demo.posttag.service;

import com.SEB_Pre_020.demo.post.entity.Post;
import com.SEB_Pre_020.demo.post.repository.PostRepository;
import com.SEB_Pre_020.demo.post.service.PostService;
import com.SEB_Pre_020.demo.posttag.entity.PostTag;
import com.SEB_Pre_020.demo.posttag.repository.PostTagRepository;
import com.SEB_Pre_020.demo.tag.entity.Tag;
import com.SEB_Pre_020.demo.tag.repository.TagRepository;
import com.SEB_Pre_020.demo.tag.service.TagService;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Transactional
@Service
public class PostTagService {
    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;
    private final PostService postService;
    private final TagService tagService;

    public PostTagService(PostTagRepository postTagRepository, TagRepository tagRepository, PostService postService, TagService tagService) {
        this.postTagRepository = postTagRepository;
        this.tagRepository = tagRepository;
        this.postService = postService;
        this.tagService = tagService;
    }

    /** 포스트태그 등록 */
    public List<PostTag> createPostTags(List<PostTag> postTags) {
        if (postTags == null) return null;

        List<PostTag> savedPosTags = new ArrayList<>();

        for(PostTag postTag : postTags) {
            String tagName = postTag.getTag().getTagName().toLowerCase();

            // 없는 태그면 새 태그 생성
            if (!tagRepository.existsByTagName(tagName)) {
                tagService.createTag(postTag.getTag());
            }

            postTag.setTag(tagRepository.findByTagName(tagName));
            postTag.setPost(postService.findPost(postTag.getPost().getId()));

            // 중복 체크
            if (postTagRepository.findByTag_IdAndPost_Id(postTag.getTag().getId(), postTag.getPost().getId()).isPresent()){
                continue;
            }

            // 포스트태그 등록
            PostTag savedPostTag = postTagRepository.save(postTag);
            savedPosTags.add(savedPostTag);
        }

        return savedPosTags;
    }

    /** 포스트태그로 게시글 목록 획득 */
    @Transactional
    public Page<Post> findTagPosts(String tagName, int page, int size) {
        if (!tagRepository.existsByTagName(tagName))    return null;

        Tag tag = tagRepository.findByTagName(tagName);

        List<PostTag> postTags = postTagRepository.findByTagId(tag.getId());
        List<Post> posts = new ArrayList<>();

        for (PostTag postTag : postTags) {
            posts.add(postTag.getPost());
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        PagedListHolder pagedListHolder = new PagedListHolder(posts);
        pagedListHolder.setPageSize(size);
        pagedListHolder.setPage(page);

        return new PageImpl<>(pagedListHolder.getPageList(), pageable, posts.size());
    }
}
