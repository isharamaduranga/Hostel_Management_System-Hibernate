/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/22/2022
 * Time        : 4:52 PM
 * Year        : 2022
 */

package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
/*@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)*/
@Entity
public class User {
    @Id
    private String id;
    private String userName;
    private String password;

}
