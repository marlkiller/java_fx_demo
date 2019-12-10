package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        GridPane grid = new GridPane();//网格式布局，由行列网格控制
        grid.setAlignment(Pos.CENTER);//对齐方式，默认靠左对齐，当前设置居中对齐

        grid.setHgap(10);//设置水平间隔
        grid.setVgap(10);//设置垂直间隔
        grid.setPadding(new Insets(25, 25, 25, 25));//设置Padding，顺序是：上、右、下、左


        Scene scene = new Scene(grid, 300, 300);//新建Scene，并将网格式Panel置于其中
        primaryStage.setScene(scene);//设置场景
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                // 主窗口关闭时 , 子窗口也关闭
                Platform.exit();
            }
        });
        primaryStage.getIcons().add(new Image("file:C:\\Users\\void\\Pictures\\Camera Roll\\ma.jpg"));
        primaryStage.show();

        loginWin(grid);
        // girdWin(grid);
        // dialog();

    }

    private void dialog() {

        // 1. 简单信息提示框
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("信息提示对话框");
        alert.setHeaderText(null);
        alert.setContentText("只显示文本内容");
        alert.showAndWait();


        // 2. 信息提示框
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("信息提示对话框");
        alert2.setHeaderText("头部内容");
        alert2.setContentText("文本内容");
        alert2.showAndWait();

        // 3. 警告对话框
        Alert alert3 = new Alert(Alert.AlertType.WARNING);
        alert3.setTitle("警告对话框");
        alert3.setHeaderText("头部内容");
        alert3.setContentText("文本内容");
        alert3.showAndWait();

        // 4. 警告对话框
        Alert alert4 = new Alert(Alert.AlertType.ERROR);
        alert4.setTitle("错误提示对话框");
        alert4.setHeaderText("头部内容");
        alert4.setContentText("文本内容");
        alert4.showAndWait();

        // 5. 异常信息框
        Alert alert5 = new Alert(Alert.AlertType.ERROR);
        alert5.setTitle("异常堆栈对话框");
        alert5.setHeaderText("头部内容");
        alert5.setContentText("文本内容");

        Exception ex = new ClassNotFoundException("找不到类");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("异常堆栈信息为:");

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

        alert5.getDialogPane().setExpandableContent(expContent);
        alert5.showAndWait();

        // 6. 确认对话框
        Alert alert6 = new Alert(Alert.AlertType.CONFIRMATION);
        alert6.setTitle("确认信息对话框");
        alert6.setHeaderText("头部内容");
        alert6.setContentText("文本内容");

        Optional result = alert6.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("点击了确定");
        } else {
            System.out.println("点击了取消");
        }

        // 7. 自定义按钮
        Alert alert7 = new Alert(Alert.AlertType.CONFIRMATION);
        alert7.setTitle("自定义按钮的确认信息对话框");
        alert7.setHeaderText("头部内容");
        alert7.setContentText("文本内容");

        ButtonType btnType1 = new ButtonType("1");
        ButtonType btnType2 = new ButtonType("2");
        ButtonType btnType3 = new ButtonType("3");
        ButtonType btnType4 = new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert7.getButtonTypes().setAll(btnType1, btnType2, btnType3, btnType4);

        Optional result2 = alert7.showAndWait();
        if (result2.get() == btnType1) {
            System.out.println("btn7 点击了 1");
        } else if (result2.get() == btnType2) {
            System.out.println("btn7 点击了 2");
        } else if (result2.get() == btnType3) {
            System.out.println("btn7 点击了 3");
        } else {
            System.out.println("btn7 点击了取消");
        }

        // 8. 文本框输入
        TextInputDialog dialog = new TextInputDialog("默认值");
        dialog.setTitle("文本输入对话框");
        dialog.setHeaderText("头部内容");
        dialog.setContentText("文本内容");

        // 传统的获取输入值的方法
        Optional result3 = dialog.showAndWait();
        if (result3.isPresent()) {
            System.out.println("btn8 输入了" + result3.get());
        }

        // 9. 选择对话框
        List choices = new ArrayList<>();
        choices.add("Java");
        choices.add("Python");
        choices.add("JavaScript");

        ChoiceDialog dialog2 = new ChoiceDialog<>("Python", choices);
        dialog2.setTitle("选择对话框");
        dialog2.setHeaderText("头部内容");
        dialog2.setContentText("文本内容");

        // 传统的获取输入值的方法
        Optional result4 = dialog2.showAndWait();
        if (result4.isPresent()) {
            System.out.println("btn9 选择了" + result4.get());
        }


    }

    private void girdWin(GridPane grid) {

        int count = 10;
        for (int i = 0; i < count; i++) {

            for (int j = 0; j < count; j++) {

                Text tmp = new Text(i + ":" + j);
                grid.add(tmp, i, j);
            }
        }
    }

    private void loginWin(GridPane grid) {
        Text scenetitle = new Text("欢迎标题");
        //scenetitle.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        //创建Label对象，放到第0列，第1行
        Label userName = new Label("用户名：");
        grid.add(userName, 0, 1);

        //创建文本输入框，放到第1列，第1行
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("密 码：");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("登录");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);//将按钮控件作为子节点
        grid.add(hbBtn, 1, 4);//将HBox pane放到grid中的第1列，第4行

        final Text actiontarget = new Text();//增加用于显示信息的文本
        grid.add(actiontarget, 1, 6);


        //注册事件handler
        btn.setOnAction(e -> {
            actiontarget.setFill(Color.FIREBRICK);//将文字颜色变成 firebrick red
            actiontarget.setText("登录中...");


            // 创建新的stage
            Stage secondStage = new Stage();
            Label label = new Label("子窗口"); // 放一个标签
            StackPane secondPane = new StackPane(label);
            Scene secondScene = new Scene(secondPane, 300, 200);
            secondStage.setScene(secondScene);
            secondStage.show();

        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
