package com.jaiz.study.lesson036;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.beans.City;
import com.jaiz.study.beans.Province;
import com.jaiz.study.beans.Student;
import com.jaiz.study.utils.AppUtils;
import com.jaiz.study.utils.Repo;
import com.jaiz.study.utils.SimplifiedStringConverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * ChoiceBox
 * 附加一些ObservableList的使用
 */
@Slf4j
@StartableMeta(title = "lesson036",category = CategoryType.LESSON,
subtitle = "ChoiceBox")
public class App extends Startable {


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        ChoiceBox<Student> cb = new ChoiceBox<>();

        cb.getItems().addAll(Repo.STUDENTS);

        //将cb所含的对象转化为String以进行显示，
        //条件允许的话也可以通过重写对象的toString方法实现
        cb.setConverter(new SimplifiedStringConverter<>() {
            @Override
            public String toString(Student object) {
                return object.getName();
            }
        });

        //控制选择模式
        cb.getSelectionModel().selectFirst();

        cb.setOnAction(event -> {
            //获取被选中的对象
            Student stu = cb.getSelectionModel().getSelectedItem();
            log.info("selected = {}",stu);
        });

        root.getChildren().add(cb);

        //实现一个二级联动
        ObservableList<Province> provinces = dataInit();

        HBox hBox = new HBox();
        ChoiceBox<Province> provinceChoiceBox = new ChoiceBox<>();
        provinceChoiceBox.setItems(provinces);
        provinceChoiceBox.setConverter(new SimplifiedStringConverter<>() {
            @Override
            public String toString(Province province) {
                return province.getName();
            }
        });
        ChoiceBox<City> cityChoiceBox = new ChoiceBox<>();
        cityChoiceBox.setConverter(
            new SimplifiedStringConverter<>() {
                @Override
                public String toString(City city) {
                    return city.getName();
                }
            }
        );
        provinceChoiceBox.setOnAction(event -> {
            Province province = provinceChoiceBox.getSelectionModel().getSelectedItem();
            ObservableList<City> cities = province.getCities();
            cityChoiceBox.setItems(cities);
            cityChoiceBox.getSelectionModel().selectFirst();
        });

        provinceChoiceBox.getSelectionModel().selectFirst();


        hBox.getChildren().addAll(provinceChoiceBox, cityChoiceBox);

        hBox.setLayoutY(50);


        root.getChildren().add(hBox);


        AppUtils.quickInit(primaryStage, "lesson036", root);
        primaryStage.show();
    }

    /**
     * 数据源初始化
     *
     * @return
     */
    private ObservableList<Province> dataInit() {
        //ObservableList实例化方式
        ObservableList<Province> result = FXCollections.observableArrayList();

        Integer beijingId = 1;
        Integer henanId = 2;
        Integer shandongId = 3;

        Province beijing = Province.builder().id(beijingId).name("北京").build();
        Province henan = Province.builder().id(henanId).name("河南").build();
        Province shandong = Province.builder().id(shandongId).name("山东").build();

        ObservableList<City> beijingCities = FXCollections.observableArrayList(
                City.builder().id(1).name("朝阳").provinceId(beijingId).build(),
                City.builder().id(2).name("海淀").provinceId(beijingId).build()
        );
        beijing.setCities(beijingCities);

        ObservableList<City> henanCities = FXCollections.observableArrayList(
                City.builder().id(1).name("郑州").provinceId(henanId).build(),
                City.builder().id(2).name("开封").provinceId(henanId).build(),
                City.builder().id(3).name("洛阳").provinceId(henanId).build()
        );
        henan.setCities(henanCities);

        ObservableList<City> shandongCities = FXCollections.observableArrayList(
                City.builder().id(4).name("济南").provinceId(shandongId).build(),
                City.builder().id(5).name("青岛").provinceId(shandongId).build(),
                City.builder().id(6).name("烟台").provinceId(shandongId).build()
        );
        shandong.setCities(shandongCities);


        result.addAll(
                beijing, henan, shandong
        );

        return result;
    }
}
