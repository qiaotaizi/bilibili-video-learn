package com.jaiz.study.lesson036;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
class Student {

    private Integer id;

    private String name;

    private Integer age;

}
