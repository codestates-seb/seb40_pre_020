package com.SEB_Pre_020.demo.tag;

import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.post.dto.PostDto;
import com.SEB_Pre_020.demo.post.entity.Post;
import com.SEB_Pre_020.demo.post.mapper.PostMapper;
import com.SEB_Pre_020.demo.post.service.PostService;
import com.SEB_Pre_020.demo.posttag.controller.PostTagController;
import com.SEB_Pre_020.demo.posttag.dto.PostTagDto;
import com.SEB_Pre_020.demo.posttag.entity.PostTag;
import com.SEB_Pre_020.demo.posttag.mapper.PostTagMapper;
import com.SEB_Pre_020.demo.posttag.service.PostTagService;
import com.SEB_Pre_020.demo.tag.entity.Tag;
import com.SEB_Pre_020.demo.util.ApiDocumentUtils;
import com.SEB_Pre_020.demo.vote.mapper.VoteMapper;
import com.SEB_Pre_020.demo.vote.service.VoteService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostTagController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class TagApiTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private PostService postService;

    @MockBean
    private PostTagService postTagService;

    @MockBean
    private PostMapper postMapper;

    @MockBean
    private PostTagMapper postTagMapper;

    @Test
    public void postPostTagsTest() throws Exception {
        // given
        Post post1 = new Post();
        Post post2 = new Post();
        Tag tag1 = new Tag();
        Tag tag2 = new Tag();

        post1.setId(1);
        post2.setId(1);
        tag1.setTagName("spring");
        tag2.setTagName("react");

        PostTagDto.PostEntity entity1 = new PostTagDto.PostEntity(1, "spring");
        PostTagDto.PostEntity entity2 = new PostTagDto.PostEntity(1, "react");
        PostTagDto.Post posts = new PostTagDto.Post();
        PostTagDto.Response response1 = new PostTagDto.Response(1, 1, "spring");
        PostTagDto.Response response2 = new PostTagDto.Response(2, 1, "react");

        posts.setPostEntities(List.of(entity1, entity2));
        List<PostTagDto.Response> responses = List.of(response1, response2);
        String content = gson.toJson(posts);

        given(postTagMapper.postTagPostToPostTag(Mockito.any(PostTagDto.Post.class))).willReturn(new ArrayList<>());
        given(postTagService.createPostTags(Mockito.anyList())).willReturn(new ArrayList<>());
        given(postTagMapper.postTagsToPostTagResponses(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/tags")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data[0].tagName").value(responses.get(0).getTagName()))
                .andDo(document(
                        "postTag-post",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        requestFields(
                                Arrays.asList(
                                        fieldWithPath("postEntities").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("postEntities[].postId").type(JsonFieldType.NUMBER).description("게시글 Id"),
                                        fieldWithPath("postEntities[].tagName").type(JsonFieldType.STRING).description("태그명")
                                )
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("게시글태그 Id"),
                                        fieldWithPath("data[].postId").type(JsonFieldType.NUMBER).description("게시글 Id"),
                                        fieldWithPath("data[].tagName").type(JsonFieldType.STRING).description("태그명")
                                )
                        )
                ));

    }

    @Test
    public void getPostsByTagTest() throws Exception {
        // given
        String tagName = "spring";
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
        List<PostDto.Response> responses = List.of(responseDto1, responseDto2, responseDto3);

        given(postTagService.findTagPosts(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).willReturn(posts);
        given(postMapper.postsToPostResponses(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/tags/{TagName}", tagName)
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON));
        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].postTitle").value(responses.get(0).getPostTitle()))
                .andDo(document(
                        "postTag-get",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("TagName").description("태그명")
                        ),
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
