package com.SEB_Pre_020.demo.comment.mapper;

import com.SEB_Pre_020.demo.comment.dto.CommentDto;
import com.SEB_Pre_020.demo.comment.entity.Comment;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    default Comment commentPostToComment(CommentDto.Post requestBody) {
        Comment comment = new Comment();
        Post post = new Post();
        Member member = new Member();

        member.setId(requestBody.getMemberId());
        post.setId(requestBody.getPostId());

        comment.setCommentContent(requestBody.getCommentContent());
        comment.setPost(post);
        comment.setMember(member);

        return comment;
    }

    default Comment commentPatchToComment(CommentDto.Patch requestBody) {
        Comment comment = new Comment();
        Post post = new Post();
        Member member = new Member();

        member.setId(requestBody.getMemberId());
        post.setId(requestBody.getPostId());

        comment.setId(requestBody.getId());
        comment.setCommentContent(requestBody.getCommentContent());
        comment.setPost(post);
        comment.setMember(member);

        return comment;
    }

    default CommentDto.Response commentToCommentResponse(Comment comment) {
        CommentDto.Response response = new CommentDto.Response();

        response.setId(comment.getId());
        response.setCommentContent(comment.getCommentContent());
        response.setPostId(comment.getPost().getId());
        response.setMemberId(comment.getMember().getId());

        return response;
    }

    List<CommentDto.Response> commentsToCommentResponses(List<Comment> comments);
}