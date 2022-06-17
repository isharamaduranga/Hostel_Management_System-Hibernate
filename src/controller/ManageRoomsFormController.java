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
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import util.ValidationUtil;
import view.tm.RoomTM;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ManageRoomsFormController {
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public JFXButton btnClear;
    public TableView<RoomDTO> tblRooms;
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

        try {
            comboLoad();
            setRoomDataLoad();
            tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue!=null){
                    setDataFields(newValue);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** create validation pattern*/
        //Create a pattern and compile it to use
        Pattern idPattern = Pattern.compile("^(RM-)[0-9]{3,5}$");
        Pattern rentPattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");
        Pattern qtyPattern = Pattern.compile("^[0-9]{1,}$");

        //add pattern and text to the map
        map.put(txtRoomId,idPattern);
        map.put(txtMonthlyRental,rentPattern);
        map.put(txtRoomQty,qtyPattern);

        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colMonthRent.setCellValueFactory(new PropertyValueFactory<>("monthly_rent"));
        colRoomQty.setCellValueFactory(new PropertyValueFactory<>("qty"));


    }

    private void setDataFields(RoomDTO newValue) {
        txtRoomId.setText(newValue.getRoom_id());
        cmbRoomType.setValue(newValue.getType());
        txtMonthlyRental.setText(newValue.getMonthly_rent());
        txtRoomQty.setText(newValue.getQty());

        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        btnAdd.setDisable(true);
    }

    private void setRoomDataLoad() throws Exception {
        ManageRoomBO manageRoomBO = new ManageRoomBOImpl();
        List<RoomDTO> roomDTOS = manageRoomBO.loadAllStudent();
        ObservableList<RoomDTO>observableList = FXCollections.observableArrayList();
        for (RoomDTO roomDTO : roomDTOS) {
            observableList.add(new RoomTM(
                    roomDTO.getRoom_id(),
                    roomDTO.getType(),
                    roomDTO.getMonthly_rent(),
                    roomDTO.getQty()
                    ));
        }
        tblRooms.setItems(observableList);
        FilteredList<RoomDTO> filterData = new FilteredList(observableList, b -> true);

        txtSearchRoomId.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(RoomDTO -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (RoomDTO.getRoom_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else
                    return false;
            });
        });

        SortedList<RoomDTO> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tblRooms.comparatorProperty());
        tblRooms.setItems(sortedData);
    }

    private void comboLoad() {
        cmbRoomType.getItems().add("AC");
        cmbRoomType.getItems().add("NON-AC");
        cmbRoomType.getItems().add("AC / FOOD");
        cmbRoomType.getItems().add("NON-AC / FOOD");
    }


    public void AddRoomOnAction(ActionEvent actionEvent) throws Exception {
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
        setRoomDataLoad();
    }



    public void DeleteRoomOnAction(ActionEvent actionEvent) throws Exception {

    }

    public void UpdateRoomOnAction(ActionEvent actionEvent) throws Exception {
        ManageRoomBOImpl manageRoomBO = new ManageRoomBOImpl();
        try {
            if (manageRoomBO.updateRoom(new RoomDTO(
                    txtRoomId.getText(),
                    cmbRoomType.getValue().toString(),
                    txtMonthlyRental.getText(),
                    txtRoomQty.getText()
            ))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update it?").showAndWait();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. try again carefully!").showAndWait();
        }
        clear();
        setRoomDataLoad();
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
        ValidationUtil.validate(map,btnAdd);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        tblRooms.refresh();
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
