package com.SEB_Pre_020.demo.tag;

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
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
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
}
