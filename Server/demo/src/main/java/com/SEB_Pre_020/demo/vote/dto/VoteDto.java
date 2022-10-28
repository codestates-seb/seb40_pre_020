package com.SEB_Pre_020.demo.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

public class VoteDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        @Positive
        private int postId;

        @Positive
        private int memberId;

        private int voteType;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id;

        @Positive
        private int postId;

        @Positive
        private int memberId;

        private int voteType;
    }
}
