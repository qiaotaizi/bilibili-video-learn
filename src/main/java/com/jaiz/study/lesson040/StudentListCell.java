package com.jaiz.study.lesson040;

import com.jaiz.study.beans.Student;
import javafx.scene.control.ListCell;

public class StudentListCell extends ListCell<Student> {
    @Override
    protected void updateItem(Student item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty){
            this.setText(item.getName());
            System.out.println("StudentListCell.updateItem"+item);
        }
    }
}
