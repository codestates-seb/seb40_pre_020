package com.SEB_Pre_020.demo.comment;

import com.SEB_Pre_020.demo.comment.controller.CommentController;
import com.SEB_Pre_020.demo.comment.mapper.CommentMapper;
import com.SEB_Pre_020.demo.comment.service.CommentService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

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


}
