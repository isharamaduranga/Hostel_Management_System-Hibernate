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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.tm.StudentTM;

import java.util.List;

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


    public void initialize() {

        colSRegID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("date"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));



        try {
            setStudentData();

        } catch (Exception e) {
            e.printStackTrace();
        }

        comboLoad();
    }

    private void setStudentData() throws Exception {
        ManageStudentBo manageStudentBO = new ManageStudentBOImpl();
        ObservableList<StudentDTO>tmList= FXCollections.observableArrayList();
        List<StudentDTO> studentDTOS = manageStudentBO.loadAllStudent();

        for (StudentDTO dto : studentDTOS) {


            tmList.add(new StudentTM(dto.getStudent_id(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getContact_no(),
                    dto.getDate(),
                    dto.getGender()));
        }
        tblStudent.setItems(tmList);
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
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. try again carefully!").showAndWait();
        }
            clear();
        setStudentData();
    }

    public void DeleteStudentOnAction(ActionEvent actionEvent) {



    }

    public void UpdateStudentOnAction(ActionEvent actionEvent) {


    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void clear() {
        txtSRejNumber.clear();
        txtStudentName.clear();
        txtSAddress.clear();
        txtContact.clear();
        txtDateOfBirth.getEditor().clear();
        cmbGender.getSelectionModel().clearSelection();
    }

    public void btnMouseMovedOnAction(MouseEvent mouseEvent) {

    }
}
