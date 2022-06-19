/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/17/2022
 * Time        : 5:31 PM
 * Year        : 2022
 */

package view.tm;

import dto.RoomDTO;

public class RoomTM extends RoomDTO {
    private String room_id;
    private String type;
    private double monthly_rent;
    private int qty;

    public RoomTM() {
    }

    public RoomTM(String room_id, String type, double monthly_rent, int qty) {
        this.setRoom_id(room_id);
        this.setType(type);
        this.setMonthly_rent(monthly_rent);
        this.setQty(qty);
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

    public double getMonthly_rent() {
        return monthly_rent;
    }

    public void setMonthly_rent(double monthly_rent) {
        this.monthly_rent = monthly_rent;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "RoomTM{" +
                "room_id='" + room_id + '\'' +
                ", type='" + type + '\'' +
                ", monthly_rent=" + monthly_rent +
                ", qty=" + qty +
                '}';
    }
}
