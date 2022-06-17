/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 8:43 PM
 * Year        : 2022
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
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

    }


    public void AddStudentOnAction(ActionEvent actionEvent) {
    }


    public void btnMouseMovedOnAction(MouseEvent mouseEvent) {
    }

    public void DeleteStudentOnAction(ActionEvent actionEvent) {
    }

    public void UpdateStudentOnAction(ActionEvent actionEvent) {
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
    }
}
