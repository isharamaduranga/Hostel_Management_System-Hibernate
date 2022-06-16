/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/16/2022
 * Time        : 8:52 PM
 * Year        : 2022
 */

package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Student  {
    @Id
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private String date;
    private String gender;


}
