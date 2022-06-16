/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 8:42 PM
 * Year        : 2022
 */

package controller;

import bo.ManageStudentBOImpl;
import bo.ManageStudentBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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


    public void initialize(){
        comboLoad();
    }

    private void comboLoad() {
        cmbGender.getItems().add("MALE");
        cmbGender.getItems().add("FEMALE");
    }


    public void AddStudentOnAction(ActionEvent actionEvent) throws Exception {
        try {
            ManageStudentBo manageStudentBO = new ManageStudentBOImpl();

            if (manageStudentBO.add(new StudentDTO(txtSRejNumber.getText(),
                    txtStudentName.getText(),
                    txtSAddress.getText(),
                    txtContact.getText(),
                    txtDateOfBirth.getValue().toString(),
                    cmbGender.getValue().toString()))) {
                new Alert(Alert.AlertType.CONFIRMATION, " Student Saved... Successfully").showAndWait();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. try again carefully!").showAndWait();
        }


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
