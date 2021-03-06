/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/2/2022
 * Time        : 3:22 PM
 * Year        : 2022
 */

package controller;

import bo.BOFactory;
import bo.custom.LoginBO;
import bo.custom.ManageRoomBO;
import bo.custom.RegisterStudentBO;
import bo.custom.ReservationDetailsBO;
import bo.custom.impl.ReservationDetailsBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import dto.UserDTO;
import entity.Room;
import entity.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.NavigationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class DashBoardFormController {
    /**
     * Apply Dependency Injection (Property)
     */
    private final LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN_USER);
    private final ReservationDetailsBO reservationDetailsBO = (ReservationDetailsBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION_DETAILS);
    private final RegisterStudentBO registerStudentBO = (RegisterStudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTER_STUDENT);
    private final ManageRoomBO manageRoomBO = (ManageRoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_ROOM);

    public Label lblDate;
    public Label lblDay;
    public Label lblTime;
    public AnchorPane dashBoardContext;
    public Pane UserPane;
    public BorderPane FullBoardContext;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public Text hintNewUserName;
    public JFXButton hintBtnUpdate;
    public Text hintNewPassword;
    public TextField txtUserId;
    public JFXComboBox<String> cmbRoomId;
    public Label lblRoomQty;
    public Label lblAvailableRoom;
    public PieChart availableQtyPieChart;


    public void initialize() {
        UserPane.setVisible(false);
        try {
            loadUserDataLogin();
            initPieChart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadTimeDate();
        loadRoomIds();

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){

                setRoomData(newValue);
                try {

                    availableRoomCheckingLogic(newValue);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initPieChart() throws Exception {

        ObservableList<PieChart.Data> productData = FXCollections.observableArrayList();

        try {

            List<RoomDTO> roomDTOS = manageRoomBO.loadAllStudent();

            for (RoomDTO roomDTO : roomDTOS) {
                String rmTypeId=roomDTO.getRoom_id();
                int qty= roomDTO.getQty();
                productData.add(new PieChart.Data(rmTypeId, qty));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        productData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(data.getName(), " ", data.pieValueProperty())
                )
        );
        availableQtyPieChart.setData(productData);
        availableQtyPieChart.setTitle(" R O O M  T Y P E  W I S E  Q U A N T I T Y  P O G R E S S");
    }


    private void availableRoomCheckingLogic(String rid) throws SQLException, IOException, ClassNotFoundException {
        String RoomTypeCount = registerStudentBO.generateRoomAvailableStatus(rid);
        int count = Integer.parseInt(RoomTypeCount);

       int roomQty= Integer.parseInt(lblRoomQty.getText());

       int availableRM= roomQty-count;

       if(count >= roomQty){
           lblAvailableRoom.setTextFill(Paint.valueOf("RED"));
           lblAvailableRoom.setText("   OUT OF ROOM");
       }else{
           lblAvailableRoom.setTextFill(Paint.valueOf("WHITE"));
           lblAvailableRoom.setText(availableRM+"  ROOMS");
       }

    }

    private void setRoomData(String rid) {
        try {
            Room roomData = reservationDetailsBO.getRoomData(rid);

            lblRoomQty.setText(String.valueOf(roomData.getQty()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRoomIds() {
        try {
            cmbRoomId.getItems().addAll(reservationDetailsBO.getRoomIds());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void loadUserDataLogin() throws Exception {
        List<User> users = loginBO.loadAllUser();
        for (User user : users) {
            txtUserName.setText(user.getUserName());
            txtPassword.setText(user.getPassword());
        }


    }

    private void loadTimeDate() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {

            LocalTime currentTime = LocalTime.now();

            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());

            lblDay.setText(LocalDate.now().getDayOfWeek().toString());
        }),
                new KeyFrame(Duration.seconds(1))

        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
    }

    public void manageStudentOnAction(ActionEvent actionEvent) throws IOException {
        NavigationUtil.setUiChildren(dashBoardContext, "ManageStudentForm");
    }

    public void manageProgramsOnAction(ActionEvent actionEvent) throws IOException {
        NavigationUtil.setUiChildren(dashBoardContext, "ManageRoomsForm");
    }

    public void registerStudentOnAction(ActionEvent actionEvent) throws IOException {
        NavigationUtil.setUiChildren(dashBoardContext, "RegisterStudentForm");
    }

    public void programDetailsOnAction(ActionEvent actionEvent) throws IOException {
        NavigationUtil.setUiChildren(dashBoardContext, "ReservationDetailsForm");
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        NavigationUtil.close(dashBoardContext);
        NavigationUtil.setUiNew("LoginForm", "Log In Form");
    }

    public void shutDownOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnUserDetailsMouseClickOnaction(MouseEvent mouseEvent) {
        UserPane.setVisible(true);
        UserPane.setEffect(null);
        FullBoardContext.setEffect(new BoxBlur(5, 5, 5));

    }

    public void uiCloseOnAction(MouseEvent mouseEvent) {
        UserPane.setVisible(false);
        FullBoardContext.setEffect(null);
    }


    public void userRequestYES(ActionEvent actionEvent) {
        txtUserName.clear();
        txtPassword.clear();
        hintNewUserName.setVisible(true);
        hintNewPassword.setVisible(true);
        hintBtnUpdate.setVisible(true);
        txtUserName.setEditable(true);
        txtPassword.setEditable(true);

    }

    public void btnUpdateData(ActionEvent actionEvent) throws Exception {
        if (loginBO.updateUser(new UserDTO(txtUserId.getText(), txtUserName.getText(), txtPassword.getText()))) {
            new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update it?").showAndWait();
        }
    }

    public void userRequestNO(ActionEvent actionEvent) throws Exception {
        loadUserDataLogin();
        hintNewUserName.setVisible(false);
        hintNewPassword.setVisible(false);
        hintBtnUpdate.setVisible(false);
    }
}
