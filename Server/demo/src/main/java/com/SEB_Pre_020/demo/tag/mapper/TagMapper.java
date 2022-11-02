package com.SEB_Pre_020.demo.tag.mapper;

import com.SEB_Pre_020.demo.tag.dto.TagDto;
import com.SEB_Pre_020.demo.tag.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface TagMapper {
    Tag tagPostToTag(TagDto.Post requestBody);
    TagDto.Response tagToTagResponse(Tag tag);
    List<TagDto.Response> tagsToTagResponses(List<Tag> tags);
}
