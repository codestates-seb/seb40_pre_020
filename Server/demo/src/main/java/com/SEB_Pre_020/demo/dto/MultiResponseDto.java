package com.SEB_Pre_020.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultiResponseDto<T> {
    private List<T> data;
}
