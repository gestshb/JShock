
package com.spring;



        import com.spring.config.Config;
        import com.spring.service.ReportService;
        import com.spring.utils.FXUtil;
        import javafx.application.Application;
        import javafx.application.Platform;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.annotation.AnnotationConfigApplicationContext;

        import java.io.IOException;
        import java.time.LocalDate;


public class Main extends Application {

    public static Main FXAppInstance;

    public Main() {
        FXAppInstance =this;
    }



    public  ApplicationContext applicationContext ;

    private Runnable runnable = ()->{
        ReportService reportService =
                (ReportService) Main.FXAppInstance.applicationContext.getBean("reportServiceImpl");
        LocalDate  localDate = LocalDate.now().minusDays(30);
        reportService.deleteOldRows(localDate);

    };





    public static void main(final String[] args) {
        Application.launch(Main.class, args);
    }





    @Override
    public void init() {

    }

    @Override
    public void start(Stage stage) {

        try {

            displayInitialScene();
            FXUtil.startTask(()->applicationContext =
                    new AnnotationConfigApplicationContext(Config.class),runnable);



        } catch (Exception e) {
            e.printStackTrace();

        }


    }


    @Override
    public void stop() {


    }

    private void displayInitialScene() throws IOException {


        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("برنامج كاشف الصدمات - 1.0");
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
        stage.setOnCloseRequest(event -> {
            if (FXUtil.showMsgConfirmation("هل تريد إغلاق البرنامج ؟")) {
                Platform.exit();
            }
            event.consume();
        });


    }


}

