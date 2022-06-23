/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 2:27 PM
 * Year        : 2022
 */

package controller;

import bo.BOFactory;
import bo.custom.LoginBO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.net.URL;

public class LoginFormController {
    /**
     * Apply Dependency Injection (Property)
     */
    private final LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN_USER);


    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public AnchorPane loginFormContext;
    public ImageView dontShowIcon;
    public ImageView showPasswordIcon;
    public JFXTextField txtShowPassword;
    int attemptsLogin = 0;

    public void LogInOnAction(ActionEvent actionEvent) throws Exception {
        attemptsLogin++;
        if (attemptsLogin < 5) {  // attempts calculate


            User userData = loginBO.getUserData("UID-001");

            try {
                if (txtUserName.getText().equals(userData.getUserName()) & pwdPassword.getText().equals(userData.getPassword())) {

                    String title = "Hello...!! Dear, Sign in Successfully";
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle(title);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));

                    Stage stage = (Stage) loginFormContext.getScene().getWindow();
                    stage.close();
                    URL resource = getClass().getResource("../view/DashBoardForm.fxml");

                    Parent load = FXMLLoader.load(resource);
                    Scene scene = new Scene(load);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene);
                    stage1.centerOnScreen();
                    stage1.show();
                }else{
                    String tilte = "Sign In ";
                    String message = "Something Went Wrong  Check fields";
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.WARNING);
                    tray.showAndDismiss(Duration.millis(3000));
                }

            } catch (Exception e) {
                String tilte = "Sign In ";
                String message = "Something Went Wrong  Check fields";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.WARNING);
                tray.showAndDismiss(Duration.millis(3000));
            }


        } else {
            //  number of wrong input visible false option
            txtUserName.setVisible(false);
            pwdPassword.setVisible(false);
        }
    }

    public void donotShowPasswordIconClick(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(true);
        pwdPassword.setVisible(false);
        showPasswordIcon.setVisible(true);
        dontShowIcon.setVisible(false);
        txtShowPassword.setText(pwdPassword.getText());
    }

    public void ShowPasswordIconClick(MouseEvent mouseEvent) {
        txtShowPassword.setText(pwdPassword.getText());
        txtShowPassword.setVisible(false);
        pwdPassword.setVisible(true);
        showPasswordIcon.setVisible(false);
        dontShowIcon.setVisible(true);
    }
}
