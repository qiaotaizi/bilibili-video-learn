package com.jaiz.study.beans;

import javafx.collections.ObservableList;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Province {
    private Integer id;

    private String name;

    private ObservableList<City> cities;
}
