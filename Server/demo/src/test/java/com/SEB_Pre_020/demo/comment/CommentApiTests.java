package com.SEB_Pre_020.demo.comment;

import com.SEB_Pre_020.demo.comment.controller.CommentController;
import com.SEB_Pre_020.demo.comment.dto.CommentDto;
import com.SEB_Pre_020.demo.comment.entity.Comment;
import com.SEB_Pre_020.demo.comment.mapper.CommentMapper;
import com.SEB_Pre_020.demo.comment.service.CommentService;
import com.SEB_Pre_020.demo.member.entity.Member;
import com.SEB_Pre_020.demo.post.dto.PostDto;
import com.SEB_Pre_020.demo.post.entity.Post;
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
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class CommentApiTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private CommentService commentService;

    @MockBean
    private CommentMapper commentMapper;

    @Test
    public void postCommentTest() throws Exception {
        // given
        CommentDto.Post comment = new CommentDto.Post(1,1, "??????1");
        CommentDto.Response response = new CommentDto.Response(1, 1, 1, "??????1");
        String content = gson.toJson(comment);

        given(commentMapper.commentPostToComment(Mockito.any(CommentDto.Post.class))).willReturn(new Comment());
        given(commentService.createComment(Mockito.any(Comment.class))).willReturn(new Comment());
        given(commentMapper.commentToCommentResponse(Mockito.any(Comment.class))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/comments")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.postId").value(response.getPostId()))
                .andDo(document(
                        "comment-post",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName();
//                        )
                        requestFields(
                                List.of(
                                        fieldWithPath("postId").type(JsonFieldType.NUMBER).description("?????????(??????) Id"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("commentContent").type(JsonFieldType.STRING).description("?????? ??????")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("?????? Id"),
                                        fieldWithPath("data.postId").type(JsonFieldType.NUMBER).description("?????????(??????) Id"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("data.commentContent").type(JsonFieldType.STRING).description("?????? ??????")
                                )
                        )
                ));
    }

    @Test
    public void patchCommentTest() throws Exception {
        int id = 1;
        // given
        CommentDto.Patch comment = new CommentDto.Patch(1, 1, 1, "??????1");
        CommentDto.Response response = new CommentDto.Response(1, 1, 1, "??????1");
        String content = gson.toJson(comment);

        given(commentMapper.commentPatchToComment(Mockito.any(CommentDto.Patch.class))).willReturn(new Comment());
        given(commentService.updateComment(Mockito.any(Comment.class))).willReturn(new Comment());
        given(commentMapper.commentToCommentResponse(Mockito.any(Comment.class))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.patch("/comments/{PostId}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.postId").value(response.getPostId()))
                .andDo(document(
                        "comment-patch",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("PostId").description("????????? Id")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("?????? Id"),
                                        fieldWithPath("postId").type(JsonFieldType.NUMBER).description("?????????(??????) Id"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("commentContent").type(JsonFieldType.STRING).description("?????? ??????")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("?????? Id"),
                                        fieldWithPath("data.postId").type(JsonFieldType.NUMBER).description("?????????(??????) Id"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("data.commentContent").type(JsonFieldType.STRING).description("?????? ??????")
                                )
                        )
                ));
    }

    @Test
    public void getCommentsTest() throws Exception {
        int id = 1;
        int page = 1;
        int size = 20;

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", Integer.toString(page));
        queryParams.add("size", Integer.toString(size));

        Member member1 = new Member();
        Member member2 = new Member();

        member1.setId(1);
        member2.setId(2);

        Post post1 = new Post();
        Post post2 = new Post();

        post1.setId(1);
        post2.setId(1);

        // given
        Comment comment1 = new Comment(1, member1, post1, "??????1");
        Comment comment2 = new Comment(1, member2, post2, "??????2");
        CommentDto.Response response1 = new CommentDto.Response(1, 1, 1, "??????1");
        CommentDto.Response response2 = new CommentDto.Response(1, 1, 1, "??????1");

        Pageable pageable = PageRequest.of(page-1, size, Sort.by("id"));
        Page<Comment> comments = new PageImpl<>(List.of(comment1, comment2), pageable, 2);
        List<CommentDto.Response> responses = List.of(response1, response2);

//        given(commentMapper.commentPatchToComment(Mockito.any(CommentDto.Patch.class))).willReturn(new Comment());
        given(commentService.findPostComments(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).willReturn(comments);
        given(commentMapper.commentsToCommentResponses(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/comments/{PostId}", id)
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].postId").value(responses.get(0).getPostId()))
                .andDo(document(
                        "comments-get",
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
                                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("?????? Id"),
                                        fieldWithPath("data[].postId").type(JsonFieldType.NUMBER).description("?????????(??????) Id"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("????????? Id"),
                                        fieldWithPath("data[].commentContent").type(JsonFieldType.STRING).description("?????? ??????"),
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
    void deleteCommentTest() throws Exception {
        // given
        int id = 1;

        doNothing().when(commentService).deleteComment(Mockito.anyInt());

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete("/comments/{CommentId}", id));

        // then
        actions.andExpect(status().isNoContent())
                .andDo(
                        document(
                                "comment-delete",
                                ApiDocumentUtils.getRequestPreProcessor(),
                                ApiDocumentUtils.getResponsePreProcessor(),
                                pathParameters(
                                        Arrays.asList(parameterWithName("CommentId").description("?????? Id"))
                                )
                        )
                );
    }
}
