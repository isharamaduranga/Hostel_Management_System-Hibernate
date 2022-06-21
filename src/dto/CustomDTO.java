/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/22/2022
 * Time        : 12:43 AM
 * Year        : 2022
 */

package dto;

import java.time.LocalDate;

public class CustomDTO {
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private String date;
    private String gender;
    private String room_id;
    private String type;
    private double key_money;
    private int qty;
    private String res_id;
    private LocalDate reservationDate;
    private String status;

    public CustomDTO() {
    }

    public CustomDTO(String student_id, String name, String address, String contact_no, String date, String gender, String room_id, String type, double key_money, int qty, String res_id, LocalDate reservationDate, String status) {
        this.setStudent_id(student_id);
        this.setName(name);
        this.setAddress(address);
        this.setContact_no(contact_no);
        this.setDate(date);
        this.setGender(gender);
        this.setRoom_id(room_id);
        this.setType(type);
        this.setKey_money(key_money);
        this.setQty(qty);
        this.setRes_id(res_id);
        this.setReservationDate(reservationDate);
        this.setStatus(status);
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getKey_money() {
        return key_money;
    }

    public void setKey_money(double key_money) {
        this.key_money = key_money;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "student_id='" + student_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", date='" + date + '\'' +
                ", gender='" + gender + '\'' +
                ", room_id='" + room_id + '\'' +
                ", type='" + type + '\'' +
                ", key_money=" + key_money +
                ", qty=" + qty +
                ", res_id='" + res_id + '\'' +
                ", reservationDate=" + reservationDate +
                ", status='" + status + '\'' +
                '}';
    }
}
