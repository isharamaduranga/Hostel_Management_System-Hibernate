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

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<RoomDTO> loadAllStudent() throws Exception {
        RoomDAO roomDAO = new RoomDAOImpl();
        List<Room> all = roomDAO.findAll();
        ArrayList<RoomDTO>roomsDto= new ArrayList<>();

        for (Room room : all) {
            roomsDto.add(new RoomDTO(room.getRoom_id(),
                    room.getType(),
                    room.getMonthly_rent(),
                    room.getQty()
                    ));
        }
        return roomsDto;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        RoomDAO roomDAO = new RoomDAOImpl();
       return roomDAO.update(new Room(
                roomDTO.getRoom_id(),
                roomDTO.getType(),
                roomDTO.getMonthly_rent(),
                roomDTO.getQty()
                ));
    }

    @Override
    public boolean deleteRoom(String id) throws Exception {
        RoomDAO roomDAO = new RoomDAOImpl();
        return roomDAO.delete(id);
    }

}
