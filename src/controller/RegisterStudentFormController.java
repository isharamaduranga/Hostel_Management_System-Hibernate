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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.sql.PreparedStatement;
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
                availableRoomCheckingLogic(newValue);
                count(newValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


    private void count(String rid) throws IOException {
       /* String temp;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("select count(room_id) from roomreservation where room_id = :room_type");
        sqlQuery.setParameter("room_type",rid);
        temp = String.valueOf(sqlQuery.uniqueResult());
        transaction.commit();*/
        RegisterStudentBO registerStudentBO = new RegisterStudentBOImpl();
        try {
            String RoomTypeCount = registerStudentBO.generateRoomAvailableStatus(rid);
            int count= Integer.parseInt(RoomTypeCount);
            if (count>=roomCount){
                roomAvailableStatus.setText("NOT AVAILABLE");
            }else{
                roomAvailableStatus.setText("AVAILABLE");
            }

        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void availableRoomCheckingLogic(String newValue) throws IOException {


    }

    int roomCount;
    private void setRoomDataToFields(String id) throws Exception {
        RegisterStudentBO registerStudentBO = new RegisterStudentBOImpl();
        Room room = registerStudentBO.getRoom(id);
        txtRoomType.setText(room.getType());
        txtKeyMoney.setText(String.valueOf(room.getKey_money()));
        txtQty.setText(String.valueOf(room.getQty()));

        /**Find RoomQty  According to various room Types*/
        roomCount= Integer.parseInt(txtQty.getText());

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
        if(cmb_StudentID.getSelectionModel().isEmpty()|| cmbRoomID.getSelectionModel().isEmpty() || date.getValue()==null){
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. Pleases try again !").showAndWait();
        }else {
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
    public void clear(){

       txtSname.clear();
       txtAddress.clear();
       txtContact.clear();
       txtDob.clear();
       txtGender.clear();
       txtRoomType.clear();
       txtKeyMoney.clear();
       txtQty.clear();
    }

}
