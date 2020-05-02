package com.jaiz.study.lesson036;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
class City {

    private Integer id;

    private String name;

    private Integer provinceId;

}
