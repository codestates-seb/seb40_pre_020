package com.SEB_Pre_020.demo.tag.service;

import com.SEB_Pre_020.demo.tag.entity.Tag;
import com.SEB_Pre_020.demo.tag.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /** 태그 생성 */
    @Transactional public Tag createTag(Tag tag) {
        if(!tagRepository.existsByTagName(tag.getTagName())) {
            Tag savedTag = tagRepository.save(tag);
            return savedTag;
        }

        return null;
    }
}
