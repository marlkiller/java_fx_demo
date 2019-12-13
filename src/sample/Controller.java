package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Controller {
    public Button btnId;

    public void btnAction(ActionEvent actionEvent) {

        System.out.println("btnAction");
    }

    public void btnClick(MouseEvent mouseEvent) {
        System.out.println("btnClick");
    }
}
