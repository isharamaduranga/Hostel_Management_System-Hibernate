/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 8:46 PM
 * Year        : 2022
 */

package controller;

import bo.custom.impl.ReservationDetailsBOImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.Room;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.List;

public class ReservationDetailsFormController {
    public TableView tblRoomReservation;
    public TableColumn colStudentID;
    public TableColumn colStudentName;
    public TableColumn colRejisterDate;
    public TableColumn colKeyMoney;

    public JFXComboBox<String> cmbRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtMonthRent;
    public JFXTextField txtQty;

    public void initialize(){
        loadRoomIds();

    }

    private void loadRoomIds() {
        ReservationDetailsBOImpl reservationDetailsBO = new ReservationDetailsBOImpl();
        try {
            cmbRoomId.getItems().addAll(reservationDetailsBO.getRoomIds());
        } catch (IOException e) {
            e.printStackTrace();
        }

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){

                setRoomData(newValue);

            }
        });

    }

    private void setRoomData(String rid) {
        ReservationDetailsBOImpl reservationDetailsBO = new ReservationDetailsBOImpl();
        try {
            Room roomData = reservationDetailsBO.getRoomData(rid);
            txtRoomType.setText(roomData.getType());
            txtMonthRent.setText(String.valueOf(roomData.getKey_money()));
            txtQty.setText(String.valueOf(roomData.getQty()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
