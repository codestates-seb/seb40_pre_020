package com.SEB_Pre_020.demo.Post.mapper;

import com.SEB_Pre_020.demo.Post.dto.PostDto;
import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    default Post postPostToPost(PostDto.Post requestBody) {
        Post post = new Post();
        Member member = new Member();

        member.setId(requestBody.getMemberId());

        post.setParentId(requestBody.getParentId());
        post.setPostTitle(requestBody.getPostTitle());
        post.setPostContent(requestBody.getPostContent());
        post.setPostView(0);
        post.setPostVoteCount(0);
        post.setPostCommentCount(0);
        post.setMember(member);

        return post;
    }
    default Post postPatchToPost(PostDto.Patch requestBody) {
        Post post = new Post();
        Member member = new Member();

        member.setId(requestBody.getMemberId());

        post.setId(requestBody.getId());
        post.setParentId(requestBody.getParentId());
        post.setPostTitle(requestBody.getPostTitle());
        post.setPostContent(requestBody.getPostContent());
        post.setPostView(requestBody.getPostView());
        post.setPostVoteCount(requestBody.getPostVoteCount());
        post.setPostCommentCount(requestBody.getPostCommentCount());
        post.setMember(member);

        return post;
    }

    default PostDto.Response postToPostResponse(Post post) {
        PostDto.Response response = new PostDto.Response();

        if (response == null)   return response;

        response.setId(post.getId());
        response.setParentId(post.getParentId());
        response.setPostTitle(post.getPostTitle());
        response.setPostContent(post.getPostContent());
        response.setPostView(response.getPostView());
        post.setPostVoteCount(response.getPostVoteCount());
        post.setPostCommentCount(response.getPostCommentCount());
        response.setMemberId(post.getMember().getId());

        return response;
    }

    List<PostDto.Response> postsToPostResponses(List<Post> posts);
}
