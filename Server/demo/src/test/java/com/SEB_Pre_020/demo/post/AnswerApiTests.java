package com.SEB_Pre_020.demo.post;

import com.SEB_Pre_020.demo.Post.controller.AnswerController;
import com.SEB_Pre_020.demo.Post.dto.PostDto;
import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.Post.mapper.PostMapper;
import com.SEB_Pre_020.demo.Post.service.AnswerService;
import com.SEB_Pre_020.demo.Post.service.PostService;
import com.SEB_Pre_020.demo.util.ApiDocumentUtils;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class AnswerApiTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private PostService postService;
    @MockBean
    private AnswerService answerService;

    @MockBean
    private PostMapper postMapper;

    @Test
    public void answerPostTest() throws Exception {
        // given
        PostDto.Post postDto = new PostDto.Post(1, "Answer1", "Content1", 2, 0, 0, 0);
        PostDto.Response responseDto = new PostDto.Response(2, 1, "Answer1", "Content1", 2, 0, 0, 0);
        String content = gson.toJson(postDto);

        given(postMapper.postPostToPost(Mockito.any(PostDto.Post.class))).willReturn(new Post());
        given(postService.createPost(Mockito.any(Post.class))).willReturn(new Post());
        given(postMapper.postToPostResponse(Mockito.any(Post.class))).willReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(
                post("/answers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.postTitle").value(postDto.getPostTitle()))
                .andExpect(jsonPath("$.data.memberId").value(postDto.getMemberId()))
                .andExpect(jsonPath("$.data.postContent").value(postDto.getPostContent()))
                .andDo(document(
                        "answer-post",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName();
//                        )
                        requestFields(
                                List.of(
                                        fieldWithPath("parentId").type(JsonFieldType.NUMBER).description("부모 Id"),
                                        fieldWithPath("postTitle").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("postContent").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("작성자 Id"),
                                        fieldWithPath("postView").type(JsonFieldType.NUMBER).description("조회수"),
                                        fieldWithPath("postVoteCount").type(JsonFieldType.NUMBER).description("추천수"),
                                        fieldWithPath("postCommentCount").type(JsonFieldType.NUMBER).description("댓글수")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("게시글 Id"),
                                        fieldWithPath("data.parentId").type(JsonFieldType.NUMBER).description("부모 Id"),
                                        fieldWithPath("data.postTitle").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.postContent").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("작성자 Id"),
                                        fieldWithPath("data.postView").type(JsonFieldType.NUMBER).description("조회수"),
                                        fieldWithPath("data.postVoteCount").type(JsonFieldType.NUMBER).description("추천수"),
                                        fieldWithPath("data.postCommentCount").type(JsonFieldType.NUMBER).description("댓글수")
                                )
                        )
                ));
    }

    @Test
    public void answerPatchTest() throws Exception {
        // given
        int id = 2;
        PostDto.Patch patchDto = new PostDto.Patch(2, 1, "Answer1", "Content1", 2, 0, 0, 0);
        PostDto.Response responseDto = new PostDto.Response(2, 1, "Answer1", "Content1", 2, 0, 0, 0);
        String content = gson.toJson(patchDto);

        given(postMapper.postPatchToPost(Mockito.any(PostDto.Patch.class))).willReturn(new Post());
        given(postService.updatePost(Mockito.any(Post.class))).willReturn(new Post());
        given(postMapper.postToPostResponse(Mockito.any(Post.class))).willReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.patch("/answers/{PostId}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.postTitle").value(patchDto.getPostTitle()))
                .andExpect(jsonPath("$.data.memberId").value(patchDto.getMemberId()))
                .andExpect(jsonPath("$.data.postContent").value(patchDto.getPostContent()))
                .andDo(document(
                        "answer-patch",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("PostId").description("게시글 Id")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시글 Id").ignored(),
                                        fieldWithPath("parentId").type(JsonFieldType.NUMBER).description("부모 Id").ignored(),
                                        fieldWithPath("postTitle").type(JsonFieldType.STRING).description("제목").optional(),
                                        fieldWithPath("postContent").type(JsonFieldType.STRING).description("내용").optional(),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("작성자 Id").ignored(),
                                        fieldWithPath("postView").type(JsonFieldType.NUMBER).description("조회수").optional(),
                                        fieldWithPath("postVoteCount").type(JsonFieldType.NUMBER).description("추천수").optional(),
                                        fieldWithPath("postCommentCount").type(JsonFieldType.NUMBER).description("댓글수").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("게시글 Id"),
                                        fieldWithPath("data.parentId").type(JsonFieldType.NUMBER).description("부모 Id"),
                                        fieldWithPath("data.postTitle").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.postContent").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("작성자 Id"),
                                        fieldWithPath("data.postView").type(JsonFieldType.NUMBER).description("조회수"),
                                        fieldWithPath("data.postVoteCount").type(JsonFieldType.NUMBER).description("추천수"),
                                        fieldWithPath("data.postCommentCount").type(JsonFieldType.NUMBER).description("댓글수")
                                )
                        )
                ));
    }
}
