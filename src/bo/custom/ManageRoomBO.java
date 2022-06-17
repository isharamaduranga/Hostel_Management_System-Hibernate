package bo.custom;

import dto.RoomDTO;
import dto.StudentDTO;

public interface ManageRoomBO {
    boolean add(RoomDTO roomDTO) throws Exception;
}
