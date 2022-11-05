package com.SEB_Pre_020.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MultiResponseDto<T> {
    private List<T> data;
}
