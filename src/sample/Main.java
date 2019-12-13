package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// https://zhuanlan.zhihu.com/p/30386925
public class Main extends Application {

    private final TableView<Person> table = new TableView<>();
    final HBox hb = new HBox();


    List<Person> list = new ArrayList<>(Arrays.asList(new Person("https://img2018.cnblogs.com/blog/1309478/201905/1309478-20190503124346847-433585445.png", "Jacob", "Smith", "jacob.smith@example.com"),
            new Person("https://dn-qiniu-avatar.qbox.me/avatar/8d693b2028982e73644044bd01a01b27?qiniu-avatar&t=190716", "Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("https://dn-qiniu-avatar.qbox.me/avatar/8d693b2028982e73644044bd01a01b27?qiniu-avatar&t=190716", "Ethan", "Williams", "ethan.williams@example.com"),
            new Person("https://dn-qiniu-avatar.qbox.me/avatar/8d693b2028982e73644044bd01a01b27?qiniu-avatar&t=190716", "void", "Jones", "emma.jones@example.com"),
            new Person("https://fanyi.bdstatic.com/static/translation/img/header/logo_cbfea26.png", "Michael", "Brown", "michael.brown@example.com")));

    private final ObservableList<Person> data = FXCollections.observableList(list);

    // 数据源
    // private final ObservableList<Person> data =
    //         FXCollections.observableArrayList(
    //                 new Person("https://img2018.cnblogs.com/blog/1309478/201905/1309478-20190503124346847-433585445.png", "Jacob", "Smith", "jacob.smith@example.com"),
    //                 new Person("https://dn-qiniu-avatar.qbox.me/avatar/8d693b2028982e73644044bd01a01b27?qiniu-avatar&t=190716", "Isabella", "Johnson", "isabella.johnson@example.com"),
    //                 new Person("https://dn-qiniu-avatar.qbox.me/avatar/8d693b2028982e73644044bd01a01b27?qiniu-avatar&t=190716", "Ethan", "Williams", "ethan.williams@example.com"),
    //                 new Person("https://dn-qiniu-avatar.qbox.me/avatar/8d693b2028982e73644044bd01a01b27?qiniu-avatar&t=190716", "void", "Jones", "emma.jones@example.com"),
    //                 new Person("https://fanyi.bdstatic.com/static/translation/img/header/logo_cbfea26.png", "Michael", "Brown", "michael.brown@example.com")
    //         );

    @Override
    public void start(Stage primaryStage) throws Exception {


        GridPane grid = new GridPane();//网格式布局，由行列网格控制
        grid.setAlignment(Pos.CENTER);//对齐方式，默认靠左对齐，当前设置居中对齐

        grid.setHgap(10);//设置水平间隔
        grid.setVgap(10);//设置垂直间隔
        grid.setPadding(new Insets(25, 25, 25, 25));//设置Padding，顺序是：上、右、下、左


        Scene scene = new Scene(grid, 750, 340);//新建Scene，并将网格式Panel置于其中
        scene.getStylesheets().add(Main.class.getClassLoader().getResource("Login.css").toExternalForm());
        primaryStage.setScene(scene);//设置场景
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                // 主窗口关闭时 , 子窗口也关闭
                Platform.exit();
            }
        });

        // 设置程序图标
        primaryStage.getIcons().add(new Image(Main.class.getClassLoader().getResource("icon.jpg").toString()));

        tableWin(grid, primaryStage);
        // loginWin(grid);
        // girdWin(grid);
        // dialog();


        primaryStage.show();



        new Thread(new Runnable() {
            @Override
            public void run () {
                try {
                    Thread.sleep(3000);
                    list.add(new Person("https://img2018.cnblogs.com/blog/1309478/201905/1309478-20190503124346847-433585445.png", "fuckList", "Smith", "jacob.smith@example.com"));
                    data.add(new Person("https://img2018.cnblogs.com/blog/1309478/201905/1309478-20190503124346847-433585445.png", "fuckData", "Smith", "jacob.smith@example.com"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void tableWin(GridPane grid, Stage primaryStage) {

        // 设置 table 可修改
        table.setEditable(true);
        table.setId("tableView");

        // 每个Table的列
        TableColumn<Person, String> iconCol = new TableColumn<>("Icon");
        iconCol.setMinWidth(100);
        iconCol.setCellFactory((col) -> {
            TableCell<Person, String> cell = new TableCell<Person, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        ImageView imgIcon = null;
                        try {
                            String url = this.getTableView().getItems().get(this.getIndex()).getIcon();
                            imgIcon = new ImageView(new Image(Utils.getImageStream(url)));
                            imgIcon.setFitHeight(100);
                            imgIcon.setFitWidth(100);
                            // 保留 width：height 比率
                            imgIcon.setPreserveRatio(false);
                            // Button delBtn = new Button("删除", imgIcon);
                            // imgIcon.setPadding(new Insets(25, 25, 25, 25));//设置Padding，顺序是：上、右、下、左
                            imgIcon.setSmooth(true);
                            imgIcon.setCache(true);
                            this.setStyle("-fx-alignment: center");
                            this.setGraphic(imgIcon);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }

            };
            return cell;
        });

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        // 设置宽度
        firstNameCol.setMinWidth(100);
        // 设置分箱的类下面的属性名
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        // 设置为可编辑的
        firstNameCol.setCellFactory((col) -> {
            TableCell<Person, String> cell = new TableCell<Person, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        String fName = this.getTableView().getItems().get(this.getIndex()).getFirstName();
                        this.setText(fName);
                        if (fName.equals("void")) {
                            // this.setStyle("-fx-background-color: red");
                            this.setTextFill(Color.RED);
                        }
                    }
                }

            };
            return cell;
        });
        firstNameCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Person, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setFirstName(t.getNewValue()));

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        lastNameCol.setOnEditCommit((TableColumn.CellEditEvent<Person, String> t) -> {
            ((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
        });

        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        emailCol.setOnEditCommit((TableColumn.CellEditEvent<Person, String> t) -> {
            ((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
        });

        // 设置数据源
        table.setItems(data);
        // 一次添加列进TableView
        table.getColumns().addAll(iconCol, firstNameCol, lastNameCol, emailCol);

        // grid.add(table, 0, 0);
        grid.add(table, 0, 1, 5, 1);


        // 创建3个文本编辑框
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");

        // 设置宽度
        addFirstName.setPrefWidth(100);

        final TextField addLastName = new TextField();
        addLastName.setPrefWidth(100);
        addLastName.setPromptText("Last Name");

        final TextField addEmail = new TextField();
        addEmail.setPrefWidth(195);
        addEmail.setPromptText("Email");


        // 创建一个添加按钮
        final Button addButton = new Button("添加");
        // 设置按钮的事件响应
        addButton.setOnAction((ActionEvent e) -> {
            // 数据源添加一行
            data.add(new Person("https://fanyi.bdstatic.com/static/translation/img/header/logo_cbfea26.png",
                    addFirstName.getText(),
                    addLastName.getText(),
                    addEmail.getText()
            ));
            // 清空文本
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
        });

        grid.add(addFirstName, 0, 2);
        grid.add(addLastName, 1, 2);
        grid.add(addEmail, 2, 2);
        grid.add(addButton, 3, 2);

        // 创建右键菜单
        MenuBar menuBar = new MenuBar();

        ContextMenu contextMenu = new ContextMenu();
        MenuItem menu1 = new MenuItem("MenuItem");
        menu1.setOnAction(event -> {
            ObservableList<Person> selectedItems = table.getSelectionModel().getSelectedItems();
            for (Person selectedItem : selectedItems) {
                System.out.println(selectedItem);
            }
        });

        // 普通菜单
        Menu menu2 = new Menu("Menu - MenuItem");
        menu2.getItems().add(new MenuItem("New"));
        menu2.getItems().add(new MenuItem("Save"));
        menu2.getItems().add(new SeparatorMenuItem());
        menu2.getItems().add(new MenuItem("Exit"));

        // 多选
        Menu menu3 = new Menu("Menu - CheckMenuItem");
        CheckMenuItem item1 = new CheckMenuItem();
        item1.setText("3");
        item1.setSelected(true);
        menu3.getItems().add(item1);

        CheckMenuItem item2 = new CheckMenuItem();
        item2.setText("2");
        item2.setSelected(false);
        menu3.getItems().add(item2);


        // 单选
        Menu menu4 = new Menu("Menu - ToggleGroup - RadioMenuItem");
        ToggleGroup tGroup = new ToggleGroup();

        RadioMenuItem soundAlarmItem = new RadioMenuItem();
        soundAlarmItem.setToggleGroup(tGroup);
        soundAlarmItem.setText("1");

        RadioMenuItem stopAlarmItem = new RadioMenuItem();
        stopAlarmItem.setToggleGroup(tGroup);
        stopAlarmItem.setText("2");
        stopAlarmItem.setSelected(true);

        menu4.getItems().add(soundAlarmItem);
        menu4.getItems().add(stopAlarmItem);

        // 多选 - 子菜单
        Menu menu5 = new Menu("Menu - CheckMenuItem");
        menu5.getItems().add(
                new CheckMenuItem("A"));
        menu5.getItems().add(
                new CheckMenuItem("B"));
        menu5.getItems().add(new CheckMenuItem("C"));
        menu4.getItems().add(menu5);

        contextMenu.getItems().addAll(menu1, menu2, menu3, menu4);

        menuBar.getMenus().addAll(menu2, menu3, menu4);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        // 菜单按钮
        grid.add(menuBar, 0, 0, 4, 1);

        // 列表框添加右键菜单
        table.setOnContextMenuRequested(event -> contextMenu.show(table, event.getScreenX(), event.getScreenY()));


        // 选择列表框
        String[] pros = Arrays.stream(Pos.values()).map(Enum::toString).collect(Collectors.toList()).toArray(new String[]{});

        final ChoiceBox<String> choiceBox = new ChoiceBox<String>(
                FXCollections.observableArrayList(pros));

        choiceBox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        String pro = pros[new_value.intValue()];
                        System.out.println(pro);
                        grid.setAlignment(Pos.valueOf(pro));
                    }
                });

        choiceBox.setTooltip(new Tooltip("Select a Pro"));

        // choiceBox bind object

        // ObservableList<Person> items = FXCollections.observableArrayList(
        //         e -> new Observable[] {e.choiceValueProperty()} );
        // items.addAll(data.toArray(new Person[] {}));
        // ChoiceBox<Person> choiceBox = new ChoiceBox<Person>(items);
        // StringConverter<Person> converter = new StringConverter<Person>() {
        //     @Override
        //     public String toString(Person album) {
        //         return album != null ? album.getChoiceValue() : null;
        //     }
        //     @Override
        //     public Person fromString(String string) {
        //         return null;
        //     }
        //
        // };
        // choiceBox.setConverter(converter);
        grid.add(choiceBox, 0, 3);


        // 刷新 Style
        final Button tmp = new Button("刷新 Style");
        grid.add(tmp, 1, 3);
        tmp.setOnAction(event -> {

        });
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
        scenetitle.setId("welcome-text");

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
        actiontarget.setId("actiontarget");
        grid.add(actiontarget, 1, 6);


        //注册事件handler
        btn.setOnAction(e -> {
            actiontarget.setFill(Color.FIREBRICK);//将文字颜色变成 firebrick red
            actiontarget.setText("登录中...");

            System.out.println(userTextField.getText() + ":" + pwBox.getText());

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
