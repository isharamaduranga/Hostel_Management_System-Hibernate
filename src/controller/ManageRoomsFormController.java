/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 8:43 PM
 * Year        : 2022
 */

package controller;

import animatefx.animation.Pulse;
import animatefx.animation.ZoomIn;
import bo.custom.ManageRoomBO;
import bo.custom.impl.ManageRoomBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ManageRoomsFormController {
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public JFXButton btnClear;
    public TableView tblRooms;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colMonthRent;
    public TableColumn colRoomQty;
    public JFXTextField txtRoomId;
    public JFXTextField txtMonthlyRental;
    public JFXTextField txtRoomQty;
    public JFXTextField txtSearchRoomId;
    public JFXComboBox cmbRoomType;

    public void initialize(){

        /** tabel zoom in feature */
        new ZoomIn(tblRooms).play();

        comboLoad();

    }

    private void comboLoad() {
        cmbRoomType.getItems().add("AC");
        cmbRoomType.getItems().add("NON-AC");
        cmbRoomType.getItems().add("AC / FOOD");
        cmbRoomType.getItems().add("NON-AC / FOOD");
    }


    public void AddRoomOnAction(ActionEvent actionEvent) {
        ManageRoomBO manageRoomBO = new ManageRoomBOImpl();
        try {
            if (manageRoomBO.add(new RoomDTO(txtRoomId.getText(),
                    cmbRoomType.getValue().toString(),
                    txtMonthlyRental.getText(),
                    txtRoomQty.getText()
                    ))) {
                new Alert(Alert.AlertType.CONFIRMATION, " Room Saved... Successfully").showAndWait();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. try again carefully!").showAndWait();
        }
        clear();
    }

    public void clear(){
        txtRoomId.clear();
        cmbRoomType.getSelectionModel().clearSelection();
        txtMonthlyRental.clear();
        txtRoomQty.clear();
        txtSearchRoomId.clear();
    }

    public void btnMouseMovedOnAction(MouseEvent mouseEvent) {
        if(((JFXButton) mouseEvent.getSource()).getText().equals("ADD")){
            new Pulse(btnAdd).play();

        }else if(((JFXButton) mouseEvent.getSource()).getText().equals("UPDATE")){
            new Pulse(btnUpdate).play();

        }else if(((JFXButton) mouseEvent.getSource()).getText().equals("DELETE")){
            new Pulse(btnDelete).play();

        }else{
            new Pulse(btnClear).play();
        }
    }

    public void DeleteRoomOnAction(ActionEvent actionEvent) {
    }

    public void UpdateRoomOnAction(ActionEvent actionEvent) {
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
    }
}
