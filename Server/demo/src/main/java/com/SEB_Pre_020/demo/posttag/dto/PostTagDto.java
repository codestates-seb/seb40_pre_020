package com.SEB_Pre_020.demo.posttag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

public class PostTagDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        @Positive
        private int postId;

        @Positive
        private int tagId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private int id;

        @Positive
        private int postId;

        @Positive
        private int tagId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id;

        @Positive
        private int tagId;

        @Positive
        private int postId;
    }
}
