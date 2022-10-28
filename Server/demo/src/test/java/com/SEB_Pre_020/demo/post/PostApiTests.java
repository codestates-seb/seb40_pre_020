package com.SEB_Pre_020.demo.post;

import com.SEB_Pre_020.demo.Post.controller.PostController;
import com.SEB_Pre_020.demo.Post.dto.PostDto;
import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.Post.mapper.PostMapper;
import com.SEB_Pre_020.demo.Post.service.PostService;
import com.SEB_Pre_020.demo.member.controller.MemberController;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.util.ApiDocumentUtils;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class PostApiTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private PostService postService;

    @MockBean
    private PostMapper postMapper;

    @Test
    public void postPostTest() throws Exception {
        // given
        PostDto.Post postDto = new PostDto.Post(0, "Post1", "Content1", 1, 0, 0, 0, 0);
        PostDto.Response responseDto = new PostDto.Response(1, 0, "Post1", "Content1", 1, 0, 0,0, 0);
        String content = gson.toJson(postDto);

        given(postMapper.postPostToPost(Mockito.any(PostDto.Post.class))).willReturn(new Post());
        given(postService.createPost(Mockito.any(Post.class))).willReturn(new Post());
        given(postMapper.postToPostResponse(Mockito.any(Post.class))).willReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(
                post("/posts")
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
                        "post-post",
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
                                        fieldWithPath("postAnswerCount").type(JsonFieldType.NUMBER).description("답글수"),
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
                                        fieldWithPath("data.postAnswerCount").type(JsonFieldType.NUMBER).description("답글수"),
                                        fieldWithPath("data.postCommentCount").type(JsonFieldType.NUMBER).description("댓글수")
                                )
                        )
                ));
    }

    @Test
    public void postPatchTest() throws Exception {
        // given
        int id = 1;
        PostDto.Patch patchDto = new PostDto.Patch(1, 0, "Post1", "Content1", 1, 0, 0, 2, 0);
        PostDto.Response responseDto = new PostDto.Response(1, 0, "Post1", "Content1", 1, 0, 0, 2, 0);
        String content = gson.toJson(patchDto);

        given(postMapper.postPatchToPost(Mockito.any(PostDto.Patch.class))).willReturn(new Post());
        given(postService.updatePost(Mockito.any(Post.class))).willReturn(new Post());
        given(postMapper.postToPostResponse(Mockito.any(Post.class))).willReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.patch("/posts/{PostId}", id)
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
                        "post-patch",
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
                                        fieldWithPath("postAnswerCount").type(JsonFieldType.NUMBER).description("답글수").optional(),
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
                                        fieldWithPath("data.postAnswerCount").type(JsonFieldType.NUMBER).description("답글수"),
                                        fieldWithPath("data.postCommentCount").type(JsonFieldType.NUMBER).description("댓글수")
                                )
                        )
                ));
    }

//    @Test
    public void postGetTest() throws Exception {
        // given
        int id = 1;
//        PostDto.Patch patchDto = new PostDto.Patch(1, 0, "Post1", "Content1", 1, 0);
        PostDto.Response responseDto = new PostDto.Response(1, 0, "Post1", "Content1", 1, 0, 1, 1, 1);
//        String content = gson.toJson(patchDto);

//        given(postMapper.postPatchToPost(Mockito.any(PostDto.Patch.class))).willReturn(new Post());
        given(postService.findPost(Mockito.anyInt())).willReturn(new Post());
        given(postMapper.postToPostResponse(Mockito.any(Post.class))).willReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/posts/{PostId}", id)
                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.postTitle").value(responseDto.getPostTitle()))
                .andExpect(jsonPath("$.data.memberId").value(responseDto.getMemberId()))
                .andExpect(jsonPath("$.data.postContent").value(responseDto.getPostContent()))
                .andDo(document(
                        "post-get",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("PostId").description("게시글 Id")
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
                                        fieldWithPath("data.postAnswerCount").type(JsonFieldType.NUMBER).description("답글수"),
                                        fieldWithPath("data.postCommentCount").type(JsonFieldType.NUMBER).description("댓글수")
                                )
                        )
                ));
    }

    @Test
    public void postsGetTest() throws Exception {
        // given
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
//        PostDto.Patch patchDto = new PostDto.Patch(1, 0, "Post1", "Content1", 1, 0);
        PostDto.Response responseDto1 = new PostDto.Response(2, 0, "Post1", "Content2", 2, 6, 1, 1, 2);
        PostDto.Response responseDto2 = new PostDto.Response(3, 0, "Post2", "Content3", 2, 5, 3, 1, 2);
        PostDto.Response responseDto3 = new PostDto.Response(4, 0, "Post3", "Content4", 3, 4, 2, 1, 2);
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
                RestDocumentationRequestBuilders.get("/posts")
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
                        "posts-get",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("페이지 번호"),
                                        parameterWithName("size").description("페이지 사이즈")
                                )
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("게시글 Id"),
                                        fieldWithPath("data[].parentId").type(JsonFieldType.NUMBER).description("부모 Id"),
                                        fieldWithPath("data[].postTitle").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data[].postContent").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("작성자 Id"),
                                        fieldWithPath("data[].postView").type(JsonFieldType.NUMBER).description("조회수"),
                                        fieldWithPath("data[].postVoteCount").type(JsonFieldType.NUMBER).description("추천수"),
                                        fieldWithPath("data[].postAnswerCount").type(JsonFieldType.NUMBER).description("답글수"),
                                        fieldWithPath("data[].postCommentCount").type(JsonFieldType.NUMBER).description("댓글수"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 번호"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 사이즈"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 건 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")
                                )
                        )
                ));
    }
}
