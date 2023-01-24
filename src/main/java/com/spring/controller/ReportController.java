package com.spring.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import com.spring.Main;
import com.spring.bean.Report;
import com.spring.service.ReportService;
import com.spring.service.impl.ReportServiceImpl;
import com.spring.utils.FXUtil;
import com.spring.utils.JasperViewerFX;
import javafx.application.Platform;
import javafx.beans.property.adapter.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.converter.LongStringConverter;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JREmptyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


@Getter
@Setter
public class ReportController implements Initializable {
    @FXML
    private DatePicker date_;

    @FXML
    private TextField id;

    @FXML
    private TextField carType;

    @FXML
    private TextField carId;

    @FXML
    private TextField bodyCarId;

    @FXML
    private TextField note_1;

    @FXML
    private TextField note_2;

    @FXML
    private TextField note_3;

    @FXML
    private TextField note_4;

    @FXML
    private TextField note_5;

    @FXML
    private TextField note_6;


    @FXML
    private ImageView image_1;

    @FXML
    private JFXButton upImage_1;

    @FXML
    private JFXButton deleteImage_1;

    @FXML
    private JFXToggleButton shock_1;

    @FXML
    private ImageView image_3;

    @FXML
    private JFXButton upImage_3;

    @FXML
    private JFXButton deleteImage_3;

    @FXML
    private JFXToggleButton shock_3;

    @FXML
    private ImageView image_5;

    @FXML
    private JFXButton upImage_5;

    @FXML
    private JFXButton deleteImage_5;

    @FXML
    private JFXToggleButton shock_5;

    @FXML
    private ImageView image_2;

    @FXML
    private JFXButton upImage_2;

    @FXML
    private JFXButton deleteImage_2;

    @FXML
    private JFXToggleButton shock_2;

    @FXML
    private ImageView image_4;

    @FXML
    private JFXButton upImage_4;

    @FXML
    private JFXButton deleteImage_4;

    @FXML
    private JFXToggleButton shock_4;


    private JavaBeanObjectProperty<Long> idProperty;
    private JavaBeanObjectProperty<java.time.LocalDate> date_Property;
    private JavaBeanStringProperty carTypeProperty;
    private JavaBeanStringProperty carIdProperty;
    private JavaBeanStringProperty bodyCarIdProperty;
    private JavaBeanStringProperty note_1Property;
    private JavaBeanStringProperty note_2Property;
    private JavaBeanStringProperty note_3Property;
    private JavaBeanStringProperty note_4Property;
    private JavaBeanStringProperty note_5Property;
    private JavaBeanStringProperty note_6Property;
    private JavaBeanObjectProperty<byte[]> image_1Property;
    private JavaBeanBooleanProperty shock_1Property;
    private JavaBeanObjectProperty<byte[]> image_2Property;
    private JavaBeanBooleanProperty shock_2Property;
    private JavaBeanObjectProperty<byte[]> image_3Property;
    private JavaBeanBooleanProperty shock_3Property;
    private JavaBeanObjectProperty<byte[]> image_4Property;
    private JavaBeanBooleanProperty shock_4Property;
    private JavaBeanObjectProperty<byte[]> image_5Property;
    private JavaBeanBooleanProperty shock_5Property;

    private Report currentReport;


    @SuppressWarnings("unchecked")
    public void binding() {
        try {
            //  if (!currentReport.isNew()) {
            idProperty = JavaBeanObjectPropertyBuilder.create().bean(currentReport).name("id").build();
            id.textProperty().bindBidirectional(idProperty, new LongStringConverter());
            //  }
            date_Property = JavaBeanObjectPropertyBuilder.create().bean(currentReport).name("date_").build();
            date_.valueProperty().bindBidirectional(date_Property);
            carTypeProperty = JavaBeanStringPropertyBuilder.create().bean(currentReport).name("carType").build();
            carType.textProperty().bindBidirectional(carTypeProperty);
            carIdProperty = JavaBeanStringPropertyBuilder.create().bean(currentReport).name("carId").build();
            carId.textProperty().bindBidirectional(carIdProperty);
            bodyCarIdProperty = JavaBeanStringPropertyBuilder.create().bean(currentReport).name("bodyCarId").build();
            bodyCarId.textProperty().bindBidirectional(bodyCarIdProperty);
            note_1Property = JavaBeanStringPropertyBuilder.create().bean(currentReport).name("note_1").build();
            note_1.textProperty().bindBidirectional(note_1Property);
            note_2Property = JavaBeanStringPropertyBuilder.create().bean(currentReport).name("note_2").build();
            note_2.textProperty().bindBidirectional(note_2Property);

            note_3Property = JavaBeanStringPropertyBuilder.create().bean(currentReport).name("note_3").build();
            note_3.textProperty().bindBidirectional(note_3Property);

            note_4Property = JavaBeanStringPropertyBuilder.create().bean(currentReport).name("note_4").build();
            note_4.textProperty().bindBidirectional(note_4Property);

            note_5Property = JavaBeanStringPropertyBuilder.create().bean(currentReport).name("note_5").build();
            note_5.textProperty().bindBidirectional(note_5Property);

            note_6Property = JavaBeanStringPropertyBuilder.create().bean(currentReport).name("note_6").build();
            note_6.textProperty().bindBidirectional(note_6Property);
            shock_1Property = JavaBeanBooleanPropertyBuilder.create().bean(currentReport).name("shock_1").build();
            shock_1.selectedProperty().bindBidirectional(shock_1Property);
            shock_2Property = JavaBeanBooleanPropertyBuilder.create().bean(currentReport).name("shock_2").build();
            shock_2.selectedProperty().bindBidirectional(shock_2Property);

            shock_3Property = JavaBeanBooleanPropertyBuilder.create().bean(currentReport).name("shock_3").build();
            shock_3.selectedProperty().bindBidirectional(shock_3Property);

            shock_4Property = JavaBeanBooleanPropertyBuilder.create().bean(currentReport).name("shock_4").build();
            shock_4.selectedProperty().bindBidirectional(shock_4Property);

            shock_5Property = JavaBeanBooleanPropertyBuilder.create().bean(currentReport).name("shock_5").build();
            shock_5.selectedProperty().bindBidirectional(shock_5Property);


            image_1.imageProperty().addListener((observable, oldValue, newValue) -> {
                addListener(newValue, image_1.getId());
            });

            image_2.imageProperty().addListener((observable, oldValue, newValue) -> {
                addListener(newValue, image_2.getId());
            });

            image_3.imageProperty().addListener((observable, oldValue, newValue) -> {
                addListener(newValue, image_3.getId());
            });

            image_4.imageProperty().addListener((observable, oldValue, newValue) -> {
                addListener(newValue, image_4.getId());
            });

            image_5.imageProperty().addListener((observable, oldValue, newValue) -> {
                addListener(newValue, image_5.getId());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addListener(javafx.scene.image.Image newValue, String imageName) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            if (newValue != null) {
                ImageIO.write(SwingFXUtils.fromFXImage(newValue, null), "png", byteArrayOutputStream);
                String getterName = "set" + StringUtils.capitalize(imageName);
                Method m = currentReport.getClass().getDeclaredMethod(getterName, byte[].class);
                m.invoke(currentReport, byteArrayOutputStream.toByteArray());
            } else {
                String getterName = "set" + StringUtils.capitalize(imageName);
                Method m = currentReport.getClass().getDeclaredMethod(getterName, byte[].class);
                m.invoke(currentReport, new Byte[]{});
            }

        } catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void uploadToUI(ActionEvent actionEvent) {
        switch (((Node) actionEvent.getSource()).getId()) {
            case "upImage_1":
                upLoadImage(image_1);
                break;
            case "upImage_2":
                upLoadImage(image_2);
                break;
            case "upImage_3":
                upLoadImage(image_3);
                break;
            case "upImage_4":
                upLoadImage(image_4);
                break;
            case "upImage_5":
                upLoadImage(image_5);
                break;
        }
    }

    @FXML
    public void deleteImage(ActionEvent actionEvent) {
        switch (((Node) actionEvent.getSource()).getId()) {
            case "deleteImage_1":
                image_1.setImage(null);
                break;
            case "deleteImage_2":
                image_2.setImage(null);
                break;
            case "deleteImage_3":
                image_3.setImage(null);
                break;
            case "deleteImage_4":
                image_4.setImage(null);
                break;
            case "deleteImage_5":
                image_5.setImage(null);
                break;
        }


    }

    public void upLoadImage(ImageView image_) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);
        File file = fileChooser.showOpenDialog(null);
        if (file != null)
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image localLmage = SwingFXUtils.toFXImage(bufferedImage, null);
                image_.setImage(localLmage);
            } catch (IOException ex) {
                ex.printStackTrace();

            }

    }

    public void readImageFromObject(ImageView image_, String imageName) {
        try {
            String getterName = "get" + StringUtils.capitalize(imageName);
            Method m = currentReport.getClass().getDeclaredMethod(getterName);
            byte[] b = (byte[]) m.invoke(currentReport);

            if (b != null) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
                BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
                Image localLmage = SwingFXUtils.toFXImage(bufferedImage, null);
                image_.setImage(localLmage);
            }
        } catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        new_();

        FXUtil.selectFocusedTextField(ReportController.class, this);
        FXUtil.settingTextFieldOrder(ReportController.class, this, this::save);
        Platform.runLater(carType::requestFocus);


    }

    ReportService reportService;

    @FXML
    public void save() {
        try {
            if (reportService == null)
                reportService = (ReportService) Main.FXAppInstance.applicationContext.getBean("reportServiceImpl");
            currentReport = reportService.save(currentReport);
            FXUtil.showInfo("تمت عملية الحفظ بنجاح \n تنبيه: ستحذف هذه المعاملة تلقائيا بعد ٣٠ يوما ");
            binding();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void remove() {
        if (!FXUtil.showWarningWithConfirmation("هل أنت متأكد من الحذف؟")) return;
        try {
            if (reportService == null)
                reportService = (ReportService) Main.FXAppInstance.applicationContext.getBean("reportServiceImpl");
            reportService.delete(currentReport.getId());

            FXUtil.showInfo("تمت عملية الحذف بنجاح");
            new_();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void search() {

        var word = FXUtil.getInputMsgDialog();
        try {
            var wordNum = Long.parseLong(word);
            open(wordNum, null);
        } catch (Exception e) {
            open(null, word);
        }


    }

    void open(Long id, String idCar) {
        if (reportService == null)
            reportService = (ReportService) Main.FXAppInstance.applicationContext.getBean("reportServiceImpl");
        image_1.setImage(null);
        image_2.setImage(null);
        image_3.setImage(null);
        image_4.setImage(null);
        image_5.setImage(null);

        currentReport = reportService.findByIdOrCarId(id, idCar);
        if (currentReport != null) {
            binding();
            readImageFromObject(image_1, image_1.getId());
            readImageFromObject(image_2, image_2.getId());
            readImageFromObject(image_3, image_3.getId());
            readImageFromObject(image_4, image_4.getId());
            readImageFromObject(image_5, image_5.getId());


        }

    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }


    public void new_() {
        currentReport = new Report();
        image_1.setImage(null);
        image_2.setImage(null);
        image_3.setImage(null);
        image_4.setImage(null);
        image_5.setImage(null);
        binding();
        date_.setValue(LocalDate.now());
    }

    JasperViewerFX jasperViewerFX;

    @FXML
    public void print(ActionEvent actionEvent) {
        try {
            if (reportService == null)
                reportService = (ReportService) Main.FXAppInstance.applicationContext.getBean("reportServiceImpl");
            currentReport = reportService.save(currentReport);
            binding();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jasperViewerFX == null)
            jasperViewerFX = new JasperViewerFX();
        Map<String, Object> params = new HashMap<>();

        params.put("REPORT_OBJECT", currentReport);
        String jasper = "/report/report.jasper";
        jasperViewerFX
                .init(jasper, params, new JREmptyDataSource())
                .show();
    }


}
