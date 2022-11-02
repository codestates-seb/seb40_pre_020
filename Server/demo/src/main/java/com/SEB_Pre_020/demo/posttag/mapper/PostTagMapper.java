package com.SEB_Pre_020.demo.posttag.mapper;

import com.SEB_Pre_020.demo.post.entity.Post;
import com.SEB_Pre_020.demo.posttag.dto.PostTagDto;
import com.SEB_Pre_020.demo.posttag.entity.PostTag;
import com.SEB_Pre_020.demo.tag.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostTagMapper {
    default PostTag postTagPostToPostTag(PostTagDto.Post requestBody) {
        PostTag postTag = new PostTag();
        Post post = new Post();
        Tag tag = new Tag();

        post.setId(requestBody.getPostId());
        tag.setId(requestBody.getTagId());

        postTag.setPost(post);
        postTag.setTag(tag);

        return postTag;
    }

    List<PostTag> postTagPostsToPostTags(List<PostTagDto.Post> requestBody);

    default PostTag postTagPatchToPostTag(PostTagDto.Patch requestBody) {
        PostTag postTag = new PostTag();
        Post post = new Post();
        Tag tag = new Tag();

        post.setId(requestBody.getPostId());
        tag.setId(requestBody.getTagId());

        postTag.setId(requestBody.getId());
        postTag.setPost(post);
        postTag.setTag(tag);

        return postTag;
    }

    default PostTagDto.Response postTagToPostTagResponse(PostTag postTag) {
        PostTagDto.Response response = new PostTagDto.Response();

        response.setId(postTag.getId());
        response.setPostId(postTag.getPost().getId());
        response.setTagId(postTag.getTag().getId());

        return response;
    }

    List<PostTagDto.Response> postTagsToPostTagResponses(List<PostTag> postTags);
}
