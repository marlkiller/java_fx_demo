package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


// 方式一 :
// javapackager -no-jre-restrict-search -deploy -native dmg -srcfiles china.jar -appclass sample.Main -outdir exe -name name -outfile outfile -v
//
// 方式二 :
// javapackager -deploy -native dmg -appclass sample.Main -srcdir jar -outdir exe -outfile chinaApp -name china
public class MainWithSceneBuilder extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 750, 340));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
