/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/23/2022
 * Time        : 1:21 AM
 * Year        : 2022
 */

package bo;

import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes {
        LOGIN_USER,MANAGE_ROOM,MANAGE_STUDENT,REGISTER_STUDENT,RESERVATION_DETAILS,
    }
    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case LOGIN_USER:
                return new LoginBOImpl();
            case MANAGE_ROOM:
                return new ManageRoomBOImpl();
            case MANAGE_STUDENT:
                return new ManageStudentBOImpl();
            case REGISTER_STUDENT:
                return new RegisterStudentBOImpl();
            case RESERVATION_DETAILS:
                return new ReservationDetailsBOImpl();
            default:
                return null;
        }
    }

}
