package com.spring.utils;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.StageStyle;
import org.springframework.util.StringUtils;


import javax.crypto.spec.SecretKeySpec;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.time.LocalDate;
import java.util.*;

public class FXUtil {

    public static LocalDate getDate(LocalDate exp) {
        return exp == null ? LocalDate.of(9999, 12, 31) : exp;
    }


    public static void clearField(Node node) {

        if (node instanceof Pane) {
            Pane p = (Pane) node;
            final int childrenCount = p.getChildren().size();

            for (int i = 0; i < childrenCount; i++) {
                Node n = p.getChildren().get(i);
                if (n != null) clearField(n);
            }
        } else if (node instanceof TextField) {
            TextField t = (TextField) node;
            t.clear();
        } else if (node instanceof ComboBox) {
            ComboBox t = (ComboBox) node;
            t.getSelectionModel().clearSelection();
        } else if (node instanceof CheckBox) {
            CheckBox t = (CheckBox) node;
            t.setSelected(false);
        } else
            return;

    }

    /**
     * alert
     *
     * @param key
     */
    public static void showError(String key) {
        showMsg(key, Alert.AlertType.ERROR);

    }

    public static void showInfo(String key) {
        showMsg(key, Alert.AlertType.INFORMATION);

    }

    public static void showMsg(String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.initStyle(StageStyle.DECORATED);
        alert.setHeaderText(" ");
        setOrientation(alert);
        alert.setHeaderText(null);
        alert.setContentText(getTranslated(content));
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();

    }

    public static boolean showMsgConfirmation(String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("رسالة تأكيد");
        setOrientation(alert);
        alert.setHeaderText(" ");
        alert.setContentText(content);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        ButtonType okButton = new ButtonType("نعم", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("لا", ButtonBar.ButtonData.NO);


        alert.getButtonTypes().setAll(okButton, noButton);

        Button yesButton = (Button) alert.getDialogPane().lookupButton(okButton);
        yesButton.setDefaultButton(false);
        EventHandler<KeyEvent> fireOnEnter = event -> {
            if (KeyCode.ENTER.equals(event.getCode())
                    && event.getTarget() instanceof Button) {
                ((Button) event.getTarget()).fire();
            }
        };

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getButtonTypes().stream()
                .map(dialogPane::lookupButton)
                .forEach(button ->
                        button.addEventHandler(
                                KeyEvent.KEY_PRESSED,
                                fireOnEnter
                        )
                );


        Optional<ButtonType> optional = alert.showAndWait();
        return optional.get() == okButton;
    }

    public static boolean showWarningWithConfirmation(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("رسالة تحذير");
        setOrientation(alert);
        alert.setHeaderText(" تحذير!");
        alert.setContentText(content);
        ButtonType okButton = new ButtonType("متابعة", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("تراجع", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);

        Button yesButton = (Button) alert.getDialogPane().lookupButton(okButton);
        yesButton.setDefaultButton(false);
        EventHandler<KeyEvent> fireOnEnter = event -> {
            if (KeyCode.ENTER.equals(event.getCode())
                    && event.getTarget() instanceof Button) {
                ((Button) event.getTarget()).fire();
            }
        };

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getButtonTypes().stream()
                .map(dialogPane::lookupButton)
                .forEach(button ->
                        button.addEventHandler(
                                KeyEvent.KEY_PRESSED,
                                fireOnEnter
                        )
                );


        Optional<ButtonType> optional = alert.showAndWait();
        return optional.get() == okButton;
    }

    private static void shwMultiErrorMsg(Collection<String> s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.DECORATED);
        setOrientation(alert);
        alert.setHeaderText(getTranslated("ErrorMsg"));
        StringBuilder stringBuilder = new StringBuilder();
        s.forEach(s1 -> {
            stringBuilder.append(getTranslated(s1));
            stringBuilder.append("\n");
        });
        alert.setContentText(stringBuilder.toString());
        alert.showAndWait();

    }

   /* public static void shwErrorMsgWithStack(String msg, Exception ex) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        setOrientation(alert);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText(ResourceBundle.getBundle("Bundle").getString("ErrorMsg"));
        alert.setContentText(msg);
        alert.getDialogPane().setPrefWidth(600);
        alert.getDialogPane().setPrefHeight(200);


// Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
*/

    public static String getTranslated(String key) {
        String result;
        try {
            result = ResourceBundle.getBundle("Bundle").getString(key);
        } catch (MissingResourceException mre) {
            return key;
        }
        return result;
    }


    private static void setOrientation(Alert alert) {
        Locale locale = Locale.getDefault();
        if (locale.getLanguage().equals("ar"))
            alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        else
            alert.getDialogPane().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

    }



    public static void writeProperty(String property, String value, String fileProperties) {
        Properties pro = new Properties();
        Path path = Paths.get(System.getProperty("user.home"), ".setting", fileProperties);
        if (!Files.exists(path)) {
            try (Writer ignored = new FileWriter(path.toFile())) {

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        try {
            Reader reader = new FileReader(path.toFile());
            pro.load(reader);
            reader.close();


            Writer writer = new FileWriter(path.toFile());
            pro.setProperty(property, value);
            pro.store(writer, null);
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static Task<Void> startTaskUI(Runnable runnable, Runnable OnSucceeded) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {

                Platform.runLater(runnable);
                return null;
            }
        };
        task.setOnFailed(evt -> task.getException().printStackTrace());
        task.setOnSucceeded(evt -> OnSucceeded.run());
        new Thread(task).start();
        return task;

    }

    public static Task<Void> startTaskUI(Runnable runnable) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {

                Platform.runLater(runnable);
                return null;
            }
        };
        task.setOnFailed(evt -> task.getException().printStackTrace());
        new Thread(task).start();
        return task;

    }

    public static Task<Void> startTask(Runnable runnable, Runnable OnSucceeded) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {

                runnable.run();
                return null;
            }
        };

        task.setOnFailed(evt -> task.getException().printStackTrace());
        task.setOnSucceeded(evt -> OnSucceeded.run());

        new Thread(task).start();
        return task;

    }


    public static Task<Void> startTask(Runnable runnable) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {

                runnable.run();
                return null;
            }
        };

        task.setOnFailed(evt -> task.getException().printStackTrace());

        new Thread(task).start();
        return task;

    }

    /**
     * هذه الدالة من أجل تحريك المؤشر إلى الخلية الأسفل
     * بعد الضغط على أنتر
     *
     * @param tableView
     * @param addItem
     * @param <T>
     */
    public static <T> void onKeyPressesInTableView(TableView<T> tableView, Runnable addItem) {
        tableView.setEditable(true);

        tableView.getSelectionModel().setCellSelectionEnabled(true);
       /* //add item
        if (addItem != null)
            addItem.run();*/
        //set event
        tableView.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                return;
            } else if (event.getCode() == KeyCode.DOWN && addItem != null) {
                addItem.run();
            }
            if (tableView.getEditingCell() == null) {
                if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
                    TablePosition focusedCellPosition = tableView.getFocusModel().getFocusedCell();
                    tableView.edit(focusedCellPosition.getRow(), focusedCellPosition.getTableColumn());

                }
            }

        });

        tableView.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (KeyCode.ENTER == event.getCode()) {
                TablePosition<? extends Object, ? extends Object> pos = tableView.getSelectionModel()
                        .getSelectedCells()
                        .get(0);

                Platform.runLater(() -> {
                    tableView.requestFocus();
                    if (addItem != null)
                        addItem.run();
                    tableView.getFocusModel().focus(pos.getRow() + 1, tableView.getColumns().get(pos.getColumn()));
                    tableView.getSelectionModel().select(pos.getRow() + 1, tableView.getColumns().get(pos.getColumn()));
                });


            }
        });
    }




    public static boolean isTargetBetween(LocalDate start, LocalDate end, LocalDate target) {
        if (start == null || end == null) return false;
        return !target.isBefore(start) && !target.isAfter(end);
    }




    public static long round(double vDouble) {
        return Math.round(vDouble * 100) / 100;
    }

    public static void selectFocusedTextField(Class<?> c, javafx.fxml.Initializable obj) {
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(TextField.class)) {
                try {
                    String getterName = "get" + StringUtils.capitalize(field.getName());
                    Method m = obj.getClass().getDeclaredMethod(getterName);
                    TextField text = (TextField) m.invoke(obj);
                    if (text != null)
                        text.focusedProperty().addListener((observable, oldValue, newValue) ->
                                Platform.runLater(() -> {
                                    if (newValue) {
                                        text.selectAll();
                                    }
                                }));
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void settingTextFieldOrder(Class<?> c, javafx.fxml.Initializable obj, Runnable save) {
        List<Control> controls = new ArrayList<>();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {


            if (Control.class.isAssignableFrom(field.getType())) {

                String getterName = "get" + StringUtils.capitalize(field.getName());
                Method m = null;
                try {
                    m = obj.getClass().getDeclaredMethod(getterName);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {


                    Control control = (Control) m.invoke(obj);

                    //add to list
                    controls.add(control);
                    //Enter event
                    control.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

                        if (event.getCode().equals(KeyCode.ENTER)) {

                            if (control.getId().equals("saveButton")) {
                                if (FXUtil.showMsgConfirmation("ds_846"))
                                    save.run();
                                return;
                            }
                            int index = controls.indexOf(event.getSource());
                            if (controls.size() - 1 > index) {
                                Control control1 = controls.get(index + 1);
                                Platform.runLater(control1::requestFocus);

                            }
                        } else if (event.getCode().equals(KeyCode.DOWN) && control instanceof ComboBox) {
                            ComboBox comboBox = (ComboBox) control;
                            comboBox.show();
                        }

                    });

                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class PasswordDialog extends Dialog<String> {
        private PasswordField passwordField;

        PasswordDialog() {
            setTitle("Password");
            setHeaderText("Please enter your password.");

            ButtonType passwordButtonType = new ButtonType("open", ButtonBar.ButtonData.OK_DONE);
            getDialogPane().getButtonTypes().addAll(passwordButtonType, ButtonType.CANCEL);

            passwordField = new PasswordField();
            passwordField.setPromptText("Password");

            HBox hBox = new HBox();
            hBox.getChildren().add(passwordField);
            hBox.setPadding(new Insets(20));

            HBox.setHgrow(passwordField, Priority.ALWAYS);

            getDialogPane().setContent(hBox);

            Platform.runLater(() -> passwordField.requestFocus());

            setResultConverter(dialogButton -> {
                if (dialogButton == passwordButtonType) {
                    return passwordField.getText();
                }
                return null;
            });
        }

    }

    public static String getInputMsgDialog(){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("بحث");
        dialog.setHeaderText("");
        dialog.setContentText("أدخل رقم المعاملة أو رقم السيارة");
        dialog.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        Optional<String> result = dialog.showAndWait();

        return result.orElse(null);
    }


}





/*
من أجل تغيير لو السطر في الجدول أو أي تغيير آخر في المظهر
        tableView.setRowFactory(row -> new TableRow<TransItem>() {

@Override
public void updateItem(TransItem item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
        setStyle("");
        } else {
        //Now 'item' has all the info of the Person in this row
        if (item.getTrans().getId().equals(1)) {
        //We apply now the changes in all the cells of the row
        for (int i = 0; i < getChildren().size(); i++) {
        ((Labeled) getChildren().get(i)).setStyle("-fx-background-color: yellow");
        }
        }
        }
        }
        });

*/




