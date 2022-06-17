/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/17/2022
 * Time        : 7:23 PM
 * Year        : 2022
 */

package bo.custom.impl;

import bo.custom.ManageRoomBO;
import dao.custom.RoomDAO;
import dao.custom.impl.RoomDAOImpl;
import dto.RoomDTO;
import entity.Room;

public class ManageRoomBOImpl implements ManageRoomBO {

    @Override
    public boolean add(RoomDTO roomDTO) throws Exception {
        RoomDAO roomDAO = new RoomDAOImpl();
       return roomDAO.add(new Room(
               roomDTO.getRoom_id(),
               roomDTO.getType(),
               roomDTO.getMonthly_rent(),
               roomDTO.getQty()
        ));
    }
}
