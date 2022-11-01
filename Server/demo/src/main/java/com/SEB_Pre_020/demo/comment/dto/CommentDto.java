package com.SEB_Pre_020.demo.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

public class CommentDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        @Positive
        private int memberId;

        @Positive
        private int postId;

        private String commentContent;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private int id;

        @Positive
        private int memberId;

        @Positive
        private int postId;

        private String commentContent;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id;

        @Positive
        private int memberId;

        @Positive
        private int postId;

        private String commentContent;
    }
}
