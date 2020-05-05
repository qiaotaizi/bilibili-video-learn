package com.jaiz.study.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Student {

    private Integer id;

    private String name;

    private Integer age;

}
