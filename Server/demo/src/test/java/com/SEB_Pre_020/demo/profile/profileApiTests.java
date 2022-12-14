package com.SEB_Pre_020.demo.profile;

import com.SEB_Pre_020.demo.post.dto.PostDto;
import com.SEB_Pre_020.demo.post.entity.Post;
import com.SEB_Pre_020.demo.post.mapper.PostMapper;
import com.SEB_Pre_020.demo.post.service.PostService;
import com.SEB_Pre_020.demo.member.controller.ProfileController;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.member.mapstruct.mapper.MemberMapper;
import com.SEB_Pre_020.demo.member.service.MemberService;
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
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class profileApiTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private PostService postService;

    @MockBean
    private PostMapper postMapper;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper memberMapper;

    @Test
    public void getMemberPostsTest() throws Exception {
        // given
        int memberId = 2;
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

        Post post1 = new Post(2, 0, "Post1", "Content2", member1, 6, 1, 1, 2);
        Post post2 = new Post(3, 0, "Post2", "Content3", member2, 5, 3, 1, 2);
        Post post3 = new Post(4, 0, "Post3", "Content4", member3, 4, 2, 1, 2);

        PostDto.Response responseDto1 = new PostDto.Response(2, 0, "Post1", "Content2", 2, 6, 1, 1, 2);
        PostDto.Response responseDto2 = new PostDto.Response(3, 0, "Post2", "Content3", 2, 5, 3, 1, 2);
        PostDto.Response responseDto3 = new PostDto.Response(4, 0, "Post3", "Content4", 3, 4, 2, 1, 2);


        Pageable pageable = PageRequest.of(page-1, size, Sort.by("postVoteCount").descending());
        Page<Post> posts = new PageImpl<>(List.of(post1, post2, post3), pageable, 3);
        List<PostDto.Response> responses = List.of(responseDto1, responseDto2);


        given(postService.findMemberPosts(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).willReturn(posts);
        given(postMapper.postsToPostResponses(Mockito.anyList()))
                .willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/profiles/{MemberId}/posts", memberId)
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].postTitle").value(responses.get(0).getPostTitle()))
                .andExpect(jsonPath("$.data[0].memberId").value(responses.get(0).getMemberId()))
                .andExpect(jsonPath("$.data[0].postContent").value(responses.get(0).getPostContent()))
                .andDo(document(
                        "profile-getPosts",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("MemberId").description("????????? Id")
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
    public void getMemberAnswersTest() throws Exception {
        // given
        int memberId = 2;
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

        Post post1 = new Post(2, 1, "Post1", "Content2", member1, 6, 1, 1, 2);
        Post post2 = new Post(3, 3, "Post2", "Content3", member2, 5, 3, 1, 2);
        Post post3 = new Post(4, 1, "Post3", "Content4", member3, 4, 2, 1, 2);

        PostDto.Response responseDto1 = new PostDto.Response(2, 1, "Post1", "Content2", 2, 6, 1, 1, 2);
        PostDto.Response responseDto2 = new PostDto.Response(3, 3, "Post2", "Content3", 2, 5, 3, 1, 2);
        PostDto.Response responseDto3 = new PostDto.Response(4, 1, "Post3", "Content4", 3, 4, 2, 1, 2);


        Pageable pageable = PageRequest.of(page-1, size, Sort.by("postVoteCount").descending());
        Page<Post> posts = new PageImpl<>(List.of(post1, post2, post3), pageable, 3);
        List<PostDto.Response> responses = List.of(responseDto1, responseDto2);


        given(postService.findMemberAnswers(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).willReturn(posts);
        given(postMapper.postsToPostResponses(Mockito.anyList()))
                .willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/profiles/{MemberId}/answers", memberId)
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].postTitle").value(responses.get(0).getPostTitle()))
                .andExpect(jsonPath("$.data[0].memberId").value(responses.get(0).getMemberId()))
                .andExpect(jsonPath("$.data[0].postContent").value(responses.get(0).getPostContent()))
                .andDo(document(
                        "profile-getAnswers",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("MemberId").description("????????? Id")
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
}
