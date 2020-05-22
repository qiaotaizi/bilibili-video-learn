package com.jaiz.study.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class City {

    private Integer id;

    private String name;

    private Integer provinceId;

}
