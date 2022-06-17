/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/17/2022
 * Time        : 10:44 AM
 * Year        : 2022
 */

package view.tm;

import dto.StudentDTO;

public class StudentTM extends StudentDTO {
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private String date;
    private String gender;

    public StudentTM() {
    }

    public StudentTM(String student_id, String name, String address, String contact_no, String date, String gender) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.contact_no = contact_no;
        this.date = date;
        this.gender = gender;
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

    @Override
    public String toString() {
        return "StudentTM{" +
                "student_id='" + student_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", date='" + date + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
