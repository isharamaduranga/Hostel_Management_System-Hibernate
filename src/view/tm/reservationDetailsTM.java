/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/19/2022
 * Time        : 3:14 PM
 * Year        : 2022
 */

package view.tm;

import java.time.LocalDate;

public class reservationDetailsTM {
    private String student_id;
    private String name;
    private LocalDate date;
    private double keyMoney;

    public reservationDetailsTM() {
    }

    public reservationDetailsTM(String student_id, String name, LocalDate date, double keyMoney) {
        this.student_id = student_id;
        this.name = name;
        this.date = date;
        this.keyMoney = keyMoney;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(double keyMoney) {
        this.keyMoney = keyMoney;
    }

    @Override
    public String toString() {
        return "reservationDetailsTM{" +
                "student_id='" + student_id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", keyMoney=" + keyMoney +
                '}';
    }
}
