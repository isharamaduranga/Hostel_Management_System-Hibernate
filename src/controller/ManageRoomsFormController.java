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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import util.ValidationUtil;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

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

    /** Define Linked-HashMap for the validation  */
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    public void initialize(){

        /** tabel zoom in feature */
        new ZoomIn(tblRooms).play();
        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);


        comboLoad();

        /** create validation pattern*/
        //Create a pattern and compile it to use
        Pattern idPattern = Pattern.compile("^(RM-)[0-9]{3,5}$");
        Pattern rentPattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");
        Pattern qtyPattern = Pattern.compile("^[0-9]{1,}$");


        //add pattern and text to the map
        map.put(txtRoomId,idPattern);
        map.put(txtMonthlyRental,rentPattern);
        map.put(txtRoomQty,qtyPattern);


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





    public void DeleteRoomOnAction(ActionEvent actionEvent) {
    }

    public void UpdateRoomOnAction(ActionEvent actionEvent) {
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
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

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAdd);

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = ValidationUtil.validate(map,btnAdd);
            if (response instanceof JFXTextField){
                JFXTextField textField = (JFXTextField) response;
                textField.requestFocus();
            }else if (response instanceof Boolean){

            }
        }
    }
}
