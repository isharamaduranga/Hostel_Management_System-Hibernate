/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/17/2022
 * Time        : 5:31 PM
 * Year        : 2022
 */

package view.tm;

public class RoomTM {
    private String room_id;
    private String type;
    private String monthly_rent;
    private String qty;

    public RoomTM() {
    }

    public RoomTM(String room_id, String type, String monthly_rent, String qty) {
        this.room_id = room_id;
        this.type = type;
        this.monthly_rent = monthly_rent;
        this.qty = qty;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMonthly_rent() {
        return monthly_rent;
    }

    public void setMonthly_rent(String monthly_rent) {
        this.monthly_rent = monthly_rent;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "RoomTM{" +
                "room_id='" + room_id + '\'' +
                ", type='" + type + '\'' +
                ", monthly_rent='" + monthly_rent + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
