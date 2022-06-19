/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/19/2022
 * Time        : 3:18 PM
 * Year        : 2022
 */

package dto;

import java.time.LocalDate;

public class RoomReservationDTO {
    private String res_id;
    private LocalDate date;
    private double key_money;
    private String student_id;
    private String room_id;

    public RoomReservationDTO() {
    }

    public RoomReservationDTO(String res_id, LocalDate date, double key_money, String student_id, String room_id) {
        this.res_id = res_id;
        this.date = date;
        this.key_money = key_money;
        this.student_id = student_id;
        this.room_id = room_id;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getKey_money() {
        return key_money;
    }

    public void setKey_money(double key_money) {
        this.key_money = key_money;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return "RoomReservationDTO{" +
                "res_id='" + res_id + '\'' +
                ", date=" + date +
                ", key_money=" + key_money +
                ", student_id='" + student_id + '\'' +
                ", room_id='" + room_id + '\'' +
                '}';
    }
}
