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
    RoomDAO roomDAO = new RoomDAOImpl();

    @Override
    public boolean add(RoomDTO roomDTO) throws Exception {

       return roomDAO.add(new Room(
               roomDTO.getRoom_id(),
               roomDTO.getType(),
               roomDTO.getKey_money(),
               roomDTO.getQty()
        ));
    }

    @Override
    public List<RoomDTO> loadAllStudent() throws Exception {
        List<Room> all = roomDAO.findAll();
        ArrayList<RoomDTO>roomsDto= new ArrayList<>();

        for (Room room : all) {
            roomsDto.add(new RoomDTO(room.getRoom_id(),
                    room.getType(),
                    room.getKey_money(),
                    room.getQty()
                    ));
        }
        return roomsDto;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
       return roomDAO.update(new Room(
                roomDTO.getRoom_id(),
                roomDTO.getType(),
                roomDTO.getKey_money(),
                roomDTO.getQty()
                ));
    }

    @Override
    public boolean deleteRoom(String id) throws Exception {
        return roomDAO.delete(id);
    }

}
