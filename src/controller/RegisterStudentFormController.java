/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 8:44 PM
 * Year        : 2022
 */

package controller;

import animatefx.animation.Bounce;
import bo.custom.RegisterStudentBO;
import bo.custom.impl.RegisterStudentBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.RoomReservationDTO;
import entity.Room;
import entity.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterStudentFormController {
    public JFXButton btnRegister;
    public JFXTextField txtRejFormNum;
    public JFXComboBox<String> cmb_StudentID;

    public JFXDatePicker date;
    public JFXTextField txtSname;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtGender;
    public JFXComboBox<String> cmbRoomID;
    public JFXTextField txtRoomType;
    public JFXTextField txtQty;
    public Label roomAvailableStatus;
    public JFXTextField txtDob;
    public JFXTextField txtKeyMoney;

    public void initialize(){
        try {
            loadStudentIds();
            loadRoomIds();
            generateNewReservationID();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cmb_StudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setStudentDataToFields(newValue);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        cmbRoomID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setRoomDataToFields(newValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void setRoomDataToFields(String id) throws Exception {
        RegisterStudentBO registerStudentBO = new RegisterStudentBOImpl();
        Room room = registerStudentBO.getRoom(id);
        txtRoomType.setText(room.getType());
        txtKeyMoney.setText(String.valueOf(room.getKey_money()));
        txtQty.setText(String.valueOf(room.getQty()));

    }

    private void setStudentDataToFields(String id) throws Exception {
        RegisterStudentBO registerStudentBO = new RegisterStudentBOImpl();
        Student student = registerStudentBO.getStudent(id);
        txtSname.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContact.setText(student.getContact_no());
        txtDob.setText(student.getDate());
        txtGender.setText(student.getGender());

    }


    private void loadRoomIds() throws IOException {
        RegisterStudentBO registerStudentBO = new RegisterStudentBOImpl();
        cmbRoomID.getItems().addAll(registerStudentBO.getRoomIds());

    }

    private void loadStudentIds() throws IOException {
        RegisterStudentBO registerStudentBO = new RegisterStudentBOImpl();
        cmb_StudentID.getItems().addAll(registerStudentBO.getStudentIds());

    }

    public void rejisterOnAction(ActionEvent actionEvent) throws Exception {
        RegisterStudentBO registerStudentBO = new RegisterStudentBOImpl();
        if (registerStudentBO.addReservation(new RoomReservationDTO(
                txtRejFormNum.getText(),
                date.getValue(),
                cmb_StudentID.getValue(),
                cmbRoomID.getValue(),
                roomAvailableStatus.getText()
        ))) {
            generateNewReservationID();
            new Alert(Alert.AlertType.CONFIRMATION, " Saved..Registration Successfully?").showAndWait();
        }else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. Pleases try again !").showAndWait();
        }



    }
    public void generateNewReservationID(){
        RegisterStudentBO registerStudentBO = new RegisterStudentBOImpl();
        try {
            String s = registerStudentBO.generateNewReservationID();
            txtRejFormNum.setText(s);
        } catch (SQLException| ClassNotFoundException |IOException e) {
            e.printStackTrace();
        }
    }

    public void btnMouseMovedOnAction(MouseEvent mouseEvent) {
        new Bounce(btnRegister).play();
    }

}
