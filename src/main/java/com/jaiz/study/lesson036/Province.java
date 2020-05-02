package com.jaiz.study.lesson036;

import javafx.collections.ObservableList;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
class Province {
    private Integer id;

    private String name;

    private ObservableList<City> cities;
}
