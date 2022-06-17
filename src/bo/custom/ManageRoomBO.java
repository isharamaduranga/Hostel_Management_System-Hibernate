package bo.custom;

import dto.RoomDTO;
import dto.StudentDTO;

import java.util.List;

public interface ManageRoomBO {
    boolean add(RoomDTO roomDTO) throws Exception;
    List<RoomDTO> loadAllStudent() throws Exception;
    boolean updateRoom(RoomDTO roomDTO) throws Exception;
    boolean deleteRoom(String id)throws Exception;
}
