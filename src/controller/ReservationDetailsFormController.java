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
import entity.CustomEntity;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.reservationDetailsTM;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

public class ReservationDetailsFormController {
    public TableView tblRoomReservation;
    public TableColumn colStudentID;
    public TableColumn colStudentName;
    public TableColumn colDateOfRegister;


    public JFXComboBox<String> cmbRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtMonthRent;
    public JFXTextField txtQty;

    /**
     * Apply Dependency Injection (Property)
     */
    ReservationDetailsBOImpl reservationDetailsBO = new ReservationDetailsBOImpl();


    public void initialize(){
        loadRoomIds();

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){

                setRoomData(newValue);
                try {

                    setProgrammeDetailsToTable (newValue);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        colStudentID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDateOfRegister.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));


    }

    private void setProgrammeDetailsToTable(String roomId) throws Exception {

        ObservableList<CustomEntity> tmList= FXCollections.observableArrayList();
        List<CustomEntity> customEntities = reservationDetailsBO.loadAllStudentDetails(roomId);
        for (CustomEntity customEntity : customEntities) {
            reservationDetailsTM reservationDetailsTM = new reservationDetailsTM(
                    customEntity.getStudent_id(),
                    customEntity.getName(),
                    customEntity.getReservationDate()
            );
            tmList.add(customEntity);
            tblRoomReservation.setItems(tmList);
        }
        System.out.println(tmList);
    }

    private void loadRoomIds() {
        try {
            cmbRoomId.getItems().addAll(reservationDetailsBO.getRoomIds());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setRoomData(String rid) {
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
