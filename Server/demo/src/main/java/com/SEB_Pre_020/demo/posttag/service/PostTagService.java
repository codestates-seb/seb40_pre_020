package com.SEB_Pre_020.demo.posttag.service;

import com.SEB_Pre_020.demo.posttag.entity.PostTag;
import com.SEB_Pre_020.demo.posttag.repository.PostTagRepository;
import com.SEB_Pre_020.demo.tag.repository.TagRepository;
import com.SEB_Pre_020.demo.tag.service.TagService;
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
    private final TagService tagService;

    public PostTagService(PostTagRepository postTagRepository, TagRepository tagRepository, TagService tagService) {
        this.postTagRepository = postTagRepository;
        this.tagRepository = tagRepository;
        this.tagService = tagService;
    }

    /** 포스트태그 등록 */
    public List<PostTag> createPostTags(List<PostTag> postTags) {
        if (postTags == null) return null;

        List<PostTag> savedPosTags = new ArrayList<>();

        for(PostTag postTag : postTags) {
            postTag.getTag().setTagName(postTag.getTag().getTagName().toLowerCase());

            // 없는 태그면 새 태그 생성
            if (!tagRepository.existsByTagName(postTag.getTag().getTagName())) {
                tagService.createTag(postTag.getTag());
            }

            // 포스트태그 등록
            PostTag savedPostTag = postTagRepository.save(postTag);
            savedPosTags.add(savedPostTag);
        }

        return savedPosTags;
    }
}
