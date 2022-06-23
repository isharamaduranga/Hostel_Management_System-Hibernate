/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/19/2022
 * Time        : 10:14 AM
 * Year        : 2022
 */

package entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RoomReservation {
    @Id
    private String res_id;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id",referencedColumnName = "room_id")
    private Room room;
    private String status;

}
