package com.SEB_Pre_020.demo.Post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class PostDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private int parentId;

        @NotBlank
        private String postTitle;

        @NotBlank
        private String postContent;

        @Positive
        private int memberId;

        private int postView;

        private int postVoteCount;

        private int postAnswerCount;

        private int postCommentCount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private int id;

        private int parentId;

        @NotBlank
        private String postTitle;

        @NotBlank
        private String postContent;

        @Positive
        private int memberId;

        private int postView;

        private int postVoteCount;

        private int postAnswerCount;

        private int postCommentCount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id;

        private int parentId;

        @NotBlank
        private String postTitle;

        @NotBlank
        private String postContent;

        @Positive
        private int memberId;

        private int postView;

        private int postVoteCount;

        private int postAnswerCount;

        private int postCommentCount;
    }
}
