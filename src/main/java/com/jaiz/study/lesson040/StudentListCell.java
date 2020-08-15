package com.jaiz.study.lesson040;

import com.jaiz.study.beans.Student;
import javafx.scene.control.ListCell;
import lombok.extern.slf4j.Slf4j;

/**
 * Student泛型的ListCell快速实现
 */
@Slf4j
public class StudentListCell extends ListCell<Student> {
    @Override
    protected void updateItem(Student item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            this.setText(item.getName());
            log.info("StudentListCell.updateItem" + item);
        }
    }
}
