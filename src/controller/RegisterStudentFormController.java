/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 8:44 PM
 * Year        : 2022
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class RegisterStudentFormController {
    public JFXButton btnRegister;
    public JFXTextField txtRejFormNum;
    public JFXComboBox cmb_StudentID;

    public JFXDatePicker date;
    public JFXTextField txtSname;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtGender;
    public JFXComboBox cmbRoomID;
    public JFXTextField txtRoomType;
    public JFXTextField txtMonthRent;
    public JFXTextField txtQty;
    public Label roomAvailableStatus;
    public JFXTextField txtDob;
    public JFXTextField txtKeyMoney;

    public void rejisterOnAction(ActionEvent actionEvent) {
    }

    public void btnMouseMovedOnAction(MouseEvent mouseEvent) {

    }
}
