/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/16/2022
 * Time        : 8:52 PM
 * Year        : 2022
 */

package entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Student {
    @Id
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private String date;
    private String gender;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<RoomReservation> reservations ;

    public Student(String student_id, String name, String address, String contact_no, String date, String gender) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.contact_no = contact_no;
        this.date = date;
        this.gender = gender;
    }


}
