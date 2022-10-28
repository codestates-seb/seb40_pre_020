package com.SEB_Pre_020.demo.vote.mapper;

import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.vote.dto.VoteDto;
import com.SEB_Pre_020.demo.vote.entity.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoteMapper {
    default Vote votePostToVote(VoteDto.Post requestBody) {
        Vote vote = new Vote();
        Member member = new Member();
        Post post = new Post();

        member.setId(requestBody.getMemberId());
        post.setId(requestBody.getPostId());

        vote.setVoteType(requestBody.getVoteType());
        vote.setPost(post);
        vote.setMember(member);

        return vote;
    }

    default VoteDto.Response voteToVoteResponse(Vote vote) {
        VoteDto.Response response = new VoteDto.Response();

        response.setId(vote.getId());
        response.setPostId(vote.getPost().getId());
        response.setMemberId(vote.getMember().getId());
        response.setVoteType(vote.getVoteType());

        return response;
    }

    List<VoteDto.Response> votesToVoteResponses(List<Vote> votes);
}
