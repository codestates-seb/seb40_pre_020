package com.SEB_Pre_020.demo.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

public class TagDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private String tagName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        @Positive
        private int id;

        private String tagName;
    }
}
