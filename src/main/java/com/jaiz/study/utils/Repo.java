package com.jaiz.study.utils;

import com.jaiz.study.beans.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Repo {

    public static ObservableList<Student> STUDENTS;

    static {
        STUDENTS = FXCollections.observableArrayList(
                Student.builder().id(1).name("小明").age(18).build(),
                Student.builder().id(2).name("小强").age(19).build(),
                Student.builder().id(3).name("小华").age(17).build()
        );
    }

}
