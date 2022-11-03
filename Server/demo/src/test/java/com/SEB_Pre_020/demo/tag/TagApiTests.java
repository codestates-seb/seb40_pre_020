package com.SEB_Pre_020.demo.tag;

import com.SEB_Pre_020.demo.post.mapper.PostMapper;
import com.SEB_Pre_020.demo.post.service.PostService;
import com.SEB_Pre_020.demo.posttag.controller.PostTagController;
import com.SEB_Pre_020.demo.posttag.dto.PostTagDto;
import com.SEB_Pre_020.demo.posttag.mapper.PostTagMapper;
import com.SEB_Pre_020.demo.posttag.service.PostTagService;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;

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

//    @Test
    public void postPostTagsTest() throws Exception {
        // given
        PostTagDto.Response response1 = new PostTagDto.Response(1, 1, "spring");
        PostTagDto.Response response2 = new PostTagDto.Response(1, 1, "react");

        given(postTagMapper.postTagPostToPostTag(Mockito.any(PostTagDto.Post.class))).willReturn(new ArrayList<>());
//        given(postTagService.createPostTags(Mockito.anyList())).willReturn()

        // when

        // then
    }
}
