package com.SEB_Pre_020.demo.post;

import com.SEB_Pre_020.demo.post.controller.AnswerController;
import com.SEB_Pre_020.demo.post.dto.PostDto;
import com.SEB_Pre_020.demo.post.entity.Post;
import com.SEB_Pre_020.demo.post.mapper.PostMapper;
import com.SEB_Pre_020.demo.post.service.PostService;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.util.ApiDocumentUtils;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
    private PostMapper postMapper;

    @Test
    public void answerPostTest() throws Exception {
        // given
        PostDto.Post postDto = new PostDto.Post(1, "Answer1", "Content1", 2, 0, 0, 0, 0);
        PostDto.Response responseDto = new PostDto.Response(2, 1, "Answer1", "Content1", 2, 0, 0, 0, 0);
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
                                        fieldWithPath("parentId").type(JsonFieldType.NUMBER).description("?????? Id"),
                                        fieldWithPath("postTitle").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("postContent").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("postView").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("postVoteCount").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("postAnswerCount").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("postCommentCount").type(JsonFieldType.NUMBER).description("?????????")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("data.parentId").type(JsonFieldType.NUMBER).description("?????? Id"),
                                        fieldWithPath("data.postTitle").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("data.postContent").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("data.postView").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("data.postVoteCount").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("data.postAnswerCount").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("data.postCommentCount").type(JsonFieldType.NUMBER).description("?????????")
                                )
                        )
                ));
    }

    @Test
    public void answerPatchTest() throws Exception {
        // given
        int id = 2;
        PostDto.Patch patchDto = new PostDto.Patch(2, 1, "Answer1", "Content1", 2, 0, 0, 0, 0);
        PostDto.Response responseDto = new PostDto.Response(2, 1, "Answer1", "Content1", 2, 0, 0, 0, 0);
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
                                parameterWithName("PostId").description("????????? Id")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("????????? Id").ignored(),
                                        fieldWithPath("parentId").type(JsonFieldType.NUMBER).description("?????? Id").ignored(),
                                        fieldWithPath("postTitle").type(JsonFieldType.STRING).description("??????").optional(),
                                        fieldWithPath("postContent").type(JsonFieldType.STRING).description("??????").optional(),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("????????? Id").ignored(),
                                        fieldWithPath("postView").type(JsonFieldType.NUMBER).description("?????????").optional(),
                                        fieldWithPath("postVoteCount").type(JsonFieldType.NUMBER).description("?????????").optional(),
                                        fieldWithPath("postAnswerCount").type(JsonFieldType.NUMBER).description("?????????").optional(),
                                        fieldWithPath("postCommentCount").type(JsonFieldType.NUMBER).description("?????????").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("data.parentId").type(JsonFieldType.NUMBER).description("?????? Id"),
                                        fieldWithPath("data.postTitle").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("data.postContent").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("data.postView").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("data.postVoteCount").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("data.postAnswerCount").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("data.postCommentCount").type(JsonFieldType.NUMBER).description("?????????")
                                )
                        )
                ));
    }

    @Test
    public void answersGetTest() throws Exception {
        // given
        int id = 1;
        int page = 1;
        int size = 20;

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", Integer.toString(page));
        queryParams.add("size", Integer.toString(size));

        Member member1 = new Member();
        Member member2 = new Member();
        Member member3 = new Member();

        member1.setId(2);
        member2.setId(2);
        member3.setId(3);


        Post post1 = new Post(2, 1, "Answer1", "Content2", member1, 6, 1, 0, 2);
        Post post2 = new Post(3, 1, "Answer2", "Content3", member2, 5, 3, 0, 2);
        Post post3 = new Post(4, 1, "Answer3", "Content4", member3, 4, 2, 0, 2);
//        PostDto.Patch patchDto = new PostDto.Patch(1, 0, "Post1", "Content1", 1, 0);
        PostDto.Response responseDto1 = new PostDto.Response(2, 1, "Answer1", "Content2", 2, 6, 1, 0, 2);
        PostDto.Response responseDto2 = new PostDto.Response(3, 1, "Answer2", "Content3", 2, 5, 3, 0, 2);
        PostDto.Response responseDto3 = new PostDto.Response(4, 1, "Answer3", "Content4", 3, 4, 2, 0, 2);
//        String content = gson.toJson(patchDto);

        Pageable pageable = PageRequest.of(page-1, size, Sort.by("postVoteCount").descending());
        Page<Post> posts = new PageImpl<>(List.of(post1, post2, post3), pageable, 3);
        List<PostDto.Response> responses = List.of(responseDto1, responseDto2, responseDto3);

//        given(postMapper.postPatchToPost(Mockito.any(PostDto.Patch.class))).willReturn(new Post());

        given(postService.findPostPosts(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).willReturn(posts);
        given(postMapper.postsToPostResponses(Mockito.anyList()))
                .willReturn(responses);

        //when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/answers/{PostId}", id)
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].postTitle").value(responses.get(0).getPostTitle()))
                .andExpect(jsonPath("$.data[0].memberId").value(responses.get(0).getMemberId()))
                .andExpect(jsonPath("$.data[0].postContent").value(responses.get(0).getPostContent()))
                .andDo(document(
                        "answer-get",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        pathParameters(
                                        parameterWithName("PostId").description("????????? Id")
                        ),
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("????????? ??????"),
                                        parameterWithName("size").description("????????? ?????????")
                                )
                        ),
                        responseFields(
                               Arrays.asList(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("data[].parentId").type(JsonFieldType.NUMBER).description("?????? Id"),
                                        fieldWithPath("data[].postTitle").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("data[].postContent").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("data[].postView").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("data[].postVoteCount").type(JsonFieldType.NUMBER).description("?????????"),
                                       fieldWithPath("data[].postAnswerCount").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("data[].postCommentCount").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("????????? ??????"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("????????? ?????????"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("?????? ??? ???"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("?????? ????????? ???")
                                )
                        )
                ));
    }

    @Test
    void deleteAnswerTest() throws Exception {
        // given
        int id = 1;

        doNothing().when(postService).deletePost(Mockito.anyInt());

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete("/answers/{PostId}", id));

        // then
        actions.andExpect(status().isNoContent())
                .andDo(
                        document(
                                "answer-delete",
                                ApiDocumentUtils.getRequestPreProcessor(),
                                ApiDocumentUtils.getResponsePreProcessor(),
                                pathParameters(
                                        Arrays.asList(parameterWithName("PostId").description("????????? Id"))
                                )
                        )
                );
    }
}
