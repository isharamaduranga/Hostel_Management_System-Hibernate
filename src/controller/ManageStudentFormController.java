/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 8:42 PM
 * Year        : 2022
 */

package controller;

import animatefx.animation.Pulse;
import animatefx.animation.ZoomIn;
import bo.custom.impl.ManageStudentBOImpl;
import bo.custom.ManageStudentBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.AnchorPane;
import util.ValidationUtil;
import view.tm.StudentTM;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ManageStudentFormController {
    public AnchorPane studentManageContext;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public JFXButton btnClear;

    public TableView<StudentDTO> tblStudent;
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

    /** Define Linked-HashMap for the validation  */
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {

        colSRegID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("date"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        /** tabel zoom in feature */
        new ZoomIn(tblStudent).play();
        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        try {
            setStudentData();
            comboLoad();
            tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue!=null){
                    setDataFields(newValue);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        /** create validation pattern*/
        //Create a pattern and compile it to use
        Pattern idPattern = Pattern.compile("^(SI)[0-9]{3,5}$");
        Pattern namePattern = Pattern.compile("^[A-z]{3,20}$");
        Pattern AddressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern TPNumberPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9}$");

        //add pattern and text to the map
        map.put(txtSRejNumber,idPattern);
        map.put(txtStudentName,namePattern);
        map.put(txtSAddress,AddressPattern);
        map.put(txtContact,TPNumberPattern);


    }

    private void setDataFields(StudentDTO s) {
        txtSRejNumber.setText(s.getStudent_id());
        txtStudentName.setText(s.getName());
        txtSAddress.setText(s.getAddress());
        txtContact.setText(s.getContact_no());
        txtDateOfBirth.setValue(LocalDate.parse(s.getDate()));
        cmbGender.setValue(s.getGender());
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        btnAdd.setDisable(true);
    }

    private void setStudentData() throws Exception {
        ManageStudentBo manageStudentBO = new ManageStudentBOImpl();
        ObservableList<StudentDTO>tmList= FXCollections.observableArrayList();
        List<StudentDTO> studentDTOS = manageStudentBO.loadAllStudent();

        for (StudentDTO dto : studentDTOS) {


            tmList.add(new StudentTM(
                    dto.getStudent_id(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getContact_no(),
                    dto.getDate(),
                    dto.getGender()));
        }
        tblStudent.setItems(tmList);
        FilteredList<StudentDTO> filterData = new FilteredList(tmList, b -> true);

        txtSearchRegisterId.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(StudentDTO -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (StudentDTO.getStudent_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else
                    return false;
            });
        });

        SortedList<StudentDTO> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tblStudent.comparatorProperty());
        tblStudent.setItems(sortedData);
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

    public void DeleteStudentOnAction(ActionEvent actionEvent) throws Exception {
        ManageStudentBo manageStudentBO = new ManageStudentBOImpl();
        try {
            if (manageStudentBO.deleteStudent(txtSRejNumber.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Delete this record?").showAndWait();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. Please Try again!").showAndWait();
        }
        clear();
        setStudentData();

    }

    public void UpdateStudentOnAction(ActionEvent actionEvent) throws Exception {
        ManageStudentBo manageStudentBO = new ManageStudentBOImpl();
        try {
            if (manageStudentBO.updateStudent(new StudentDTO(
                    txtSRejNumber.getText(),
                    txtStudentName.getText(),
                    txtSAddress.getText(),
                    txtContact.getText(),
                    txtDateOfBirth.getValue().toString(),
                    cmbGender.getValue().toString()
                    ))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update it?").showAndWait();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong. try again carefully!").showAndWait();
        }
        clear();
        setStudentData();
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void clear() {
        txtSRejNumber.clear();
        txtStudentName.clear();
        txtSAddress.clear();
        txtContact.clear();
        txtDateOfBirth.setValue(null);
        cmbGender.getSelectionModel().clearSelection();
        txtSearchRegisterId.clear();
        ValidationUtil.validate(map,btnAdd);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        tblStudent.refresh();
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
