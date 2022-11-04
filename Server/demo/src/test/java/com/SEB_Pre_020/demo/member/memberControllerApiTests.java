package com.SEB_Pre_020.demo.member;

import com.SEB_Pre_020.demo.member.controller.MemberController;
import com.SEB_Pre_020.demo.member.dto.MemberPostDto;
import com.SEB_Pre_020.demo.member.dto.MemberResponseDto;
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
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class memberControllerApiTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper memberMapper;

    @Test
    public void memberPostTest() throws Exception {
        // given
        MemberPostDto memberPostDto = new MemberPostDto("test1234@gmail.com", "MemberPosttest1", "121720310");
        String content = gson.toJson(memberPostDto);

        given(memberMapper.memberPostDtoToMember(Mockito.any(MemberPostDto.class))).willReturn(new Member());
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());
        given(memberMapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(new MemberResponseDto());

        //when
        ResultActions actions = mockMvc.perform(
                post("/v1/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(memberPostDto.getName()))
                .andExpect(jsonPath("$.data.email").value(memberPostDto.getEmail()))
                .andExpect(jsonPath("$.data.postContent").value(memberPostDto.getPassword()))
                .andDo(document(
                        "signup",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),

                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일(로그인ID로 사용)"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.name").type(JsonFieldType.NUMBER).description("닉네임"),
                                        fieldWithPath("data.email").type(JsonFieldType.NUMBER).description("이메일"),
                                        fieldWithPath("data.password").type(JsonFieldType.STRING).description("비밀번호")
                                )
                        )
                )
        );
    }
}
