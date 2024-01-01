package com.example.demo.model;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
public class Board {

    private Long priKey;

    @NotNull
    @Size(min=2, max=30)
    private String title;
    private String writer;
    private String content;

}
