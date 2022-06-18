package bo.custom;

import java.io.IOException;
import java.util.List;

public interface RegisterStudentBO {
     List getStudentIds() throws IOException;
     List getRoomIds() throws IOException;
}
