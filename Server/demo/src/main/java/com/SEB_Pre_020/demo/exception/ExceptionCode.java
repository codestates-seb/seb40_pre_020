package com.SEB_Pre_020.demo.exception;

import lombok.Getter;

public enum ExceptionCode {
    USER_NOT_FOUND(404, "해당 정보로 등록된 회원이 존재하지 않습니다"),
    USER_EXISTS(409, "등록된 회원입니다"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    QUESTION_EXISTS(409, "Question exists"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    ANSWER_EXISTS(409, "Answer exists"),
    ACCESS_DENIED_USER(403,"Access Denied User"),
    CANNOT_CHANGE_ORDER(403, "Order can not change"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_MEMBER_STATUS(400, "Invalid member status");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
