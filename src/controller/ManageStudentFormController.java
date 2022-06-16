/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 8:42 PM
 * Year        : 2022
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ManageStudentFormController {
    public AnchorPane studentManageContext;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public JFXButton btnClear;
    
    public TableView tblStudent;
    public TableColumn colSRegID;
    public TableColumn colStudentName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;

    
    public JFXTextField txtSRejNumber;
    public JFXTextField txtStudentName;
    public JFXTextField txtSAddress;
    public JFXTextField txtContact;
    public JFXDatePicker txtDateOfBirth;
    public JFXComboBox cmbGender;
    public JFXTextField txtSearchRegisterId;



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
