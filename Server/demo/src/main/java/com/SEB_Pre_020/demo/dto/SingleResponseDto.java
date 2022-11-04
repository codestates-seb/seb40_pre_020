package com.SEB_Pre_020.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SingleResponseDto<T> {
    private T data;
}

