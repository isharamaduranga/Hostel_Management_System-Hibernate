/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 8:44 PM
 * Year        : 2022
 */

package controller;

import animatefx.animation.Bounce;
import bo.BOFactory;
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
import javafx.scene.paint.Paint;

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
    public JFXButton btnClear;
    int roomCount;

    /**
     * Apply Dependency Injection (Property)
     */
       private final RegisterStudentBO registerStudentBO = (RegisterStudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTER_STUDENT);



    public void initialize() {
        try {
            loadStudentIds();
            loadRoomIds();
            generateNewReservationID();
            btnRegister.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cmb_StudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                try {
                    setStudentDataToFields(newValue);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        cmbRoomID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue!=null){
                try {
                    setRoomDataToFields(newValue);
                    availableRoomCheckingLogic(newValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

    private void setRoomDataToFields(String id) throws Exception {

        Room room = registerStudentBO.getRoom(id);
        txtRoomType.setText(room.getType());
        txtKeyMoney.setText(String.valueOf(room.getKey_money()));
        txtQty.setText(String.valueOf(room.getQty()));

        /**Find RoomQty  According to various room Types*/
        roomCount = Integer.parseInt(txtQty.getText());

    }
    private void availableRoomCheckingLogic(String rid) throws IOException {
        try {
            String RoomTypeCount = registerStudentBO.generateRoomAvailableStatus(rid);
            int count = Integer.parseInt(RoomTypeCount);

            if (count >= roomCount) {
                roomAvailableStatus.setTextFill(Paint.valueOf("RED"));
                roomAvailableStatus.setText("NOT AVAILABLE");
                new Alert(Alert.AlertType.WARNING, "That " + rid + " Room is Out Of Quantity..!!!").showAndWait();
                btnRegister.setDisable(true);

            } else {
                roomAvailableStatus.setTextFill(Paint.valueOf("GREEN"));
                roomAvailableStatus.setText("AVAILABLE");
                btnRegister.setDisable(false);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    private void setStudentDataToFields(String id) throws Exception {
        Student student = registerStudentBO.getStudent(id);
        txtSname.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContact.setText(student.getContact_no());
        txtDob.setText(student.getDate());
        txtGender.setText(student.getGender());
    }

    private void loadRoomIds() throws IOException {
        cmbRoomID.getItems().addAll(registerStudentBO.getRoomIds());
    }

    private void loadStudentIds() throws IOException {
        cmb_StudentID.getItems().addAll(registerStudentBO.getStudentIds());

    }

    public void rejisterOnAction(ActionEvent actionEvent) throws Exception {

        if (roomAvailableStatus.equals("NOT AVAILABLE") | cmb_StudentID.getSelectionModel().isEmpty() || cmbRoomID.getSelectionModel().isEmpty() || date.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. Pleases try again !").showAndWait();
        } else {
            if (registerStudentBO.addReservation(new RoomReservationDTO(
                    txtRejFormNum.getText(),
                    date.getValue(),
                    cmb_StudentID.getValue(),
                    cmbRoomID.getValue(),
                    roomAvailableStatus.getText()
            ))) {

                new Alert(Alert.AlertType.INFORMATION, " Saved..Registration Successfully?").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong. Pleases try again !").showAndWait();
            }
            clear();
            generateNewReservationID();
        }
    }

    public void generateNewReservationID() {
        try {
            String s = registerStudentBO.generateNewReservationID();
            txtRejFormNum.setText(s);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void btnMouseMovedOnAction(MouseEvent mouseEvent) {
        new Bounce(btnRegister).play();
    }

    public void clear() {
        
        txtSname.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDob.clear();
        txtGender.clear();
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        cmb_StudentID.setValue(null);
        cmbRoomID.setValue(null);
        roomAvailableStatus.setText("");
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clear();
    }
}
