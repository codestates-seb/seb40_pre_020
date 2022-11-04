package com.SEB_Pre_020.demo.vote;

import com.SEB_Pre_020.demo.post.service.PostService;
import com.SEB_Pre_020.demo.util.ApiDocumentUtils;
import com.SEB_Pre_020.demo.vote.controller.VoteController;
import com.SEB_Pre_020.demo.vote.dto.VoteDto;
import com.SEB_Pre_020.demo.vote.entity.Vote;
import com.SEB_Pre_020.demo.vote.mapper.VoteMapper;
import com.SEB_Pre_020.demo.vote.service.VoteService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VoteController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class VoteApiTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private PostService postService;

    @MockBean
    private VoteService voteService;

    @MockBean
    private VoteMapper voteMapper;

    @Test
    public void postVoteTest() throws Exception {
        // given
        VoteDto.Post vote = new VoteDto.Post(1, 1, 1);
        VoteDto.Response response = new VoteDto.Response(1, 1, 1, 1);
        String content = gson.toJson(vote);

        given(voteMapper.votePostToVote(Mockito.any(VoteDto.Post.class))).willReturn(new Vote());
        given(voteService.createVote(Mockito.any(Vote.class))).willReturn(new Vote());
        given(voteMapper.voteToVoteResponse(Mockito.any(Vote.class))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/votes")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.postId").value(response.getPostId()))
                .andDo(document(
                        "vote-post",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("postId").type(JsonFieldType.NUMBER).description("게시글(답글) Id"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("작성자 Id"),
                                        fieldWithPath("voteType").type(JsonFieldType.NUMBER).description("투표 타입(추천:1, 비추천:-1)")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("투표 Id"),
                                        fieldWithPath("data.postId").type(JsonFieldType.NUMBER).description("게시글(답글) Id"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("작성자 Id"),
                                        fieldWithPath("data.voteType").type(JsonFieldType.NUMBER).description("투표 타입(추천:1, 비추천:-1)")
                                )
                        )
                ));

    }
}
