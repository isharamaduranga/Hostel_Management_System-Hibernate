/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 3:22 PM
 * Year        : 2022
 */

package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.NavigationUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardFormController {
    public Label lblDate;
    public Label lblDay;
    public Label lblTime;
    public AnchorPane dashBoardContext;
    public Pane UserPane;
    public BorderPane FullBoardContext;



    public void initialize(){
        UserPane.setVisible(false);
        loadTimeDate();
    }

    private void loadTimeDate() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {

            LocalTime currentTime = LocalTime.now();

            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());

            lblDay.setText(LocalDate.now().getDayOfWeek().toString());
        }),
                new KeyFrame(Duration.seconds(1))

        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }



    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
    }

    public void manageStudentOnAction(ActionEvent actionEvent) throws IOException {
        NavigationUtil.setUiChildren(dashBoardContext, "ManageStudentForm");
    }

    public void manageProgramsOnAction(ActionEvent actionEvent) throws IOException {
        NavigationUtil.setUiChildren(dashBoardContext, "ManageRoomsForm");
    }

    public void registerStudentOnAction(ActionEvent actionEvent) throws IOException {
        NavigationUtil.setUiChildren(dashBoardContext, "RegisterStudentForm");
    }

    public void programDetailsOnAction(ActionEvent actionEvent) throws IOException {
        NavigationUtil.setUiChildren(dashBoardContext, "ReservationDetailsForm");
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        NavigationUtil.close(dashBoardContext);
        NavigationUtil.setUiNew("LoginForm", "Log In Form");
    }

    public void shutDownOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnUserDetailsMouseClickOnaction(MouseEvent mouseEvent) {
        UserPane.setVisible(true);
        UserPane.setEffect(null);
        FullBoardContext.setEffect(new BoxBlur(3,3,3));

    }

    public void uiCloseOnAction(MouseEvent mouseEvent) {
        UserPane.setVisible(false);
        FullBoardContext.setEffect(null);
    }

    public void btnChangeUserDetails(ActionEvent actionEvent) {

    }
}
