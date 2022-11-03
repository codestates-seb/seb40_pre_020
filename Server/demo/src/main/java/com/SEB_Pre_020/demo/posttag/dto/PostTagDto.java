package com.SEB_Pre_020.demo.posttag.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public class PostTagDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostEntity {
        @Positive
        private int postId;

        @NotBlank
        private String tagName;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        List<PostEntity> postEntities;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private int id;

        @Positive
        private int postId;

        @NotBlank
        private String tagName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id;

        @Positive
        private int postId;

        @NotBlank
        private String tagName;
    }
}
