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
    private double key_money;
    private int qty;

    public RoomTM() {
    }

    public RoomTM(String room_id, String type, double key_money, int qty) {
        this.setRoom_id(room_id);
        this.setType(type);
        this.setKey_money(key_money);
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

    public double getKey_money() {
        return key_money;
    }

    public void setKey_money(double key_money) {
        this.key_money = key_money;
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
                ", key_money=" + key_money +
                ", qty=" + qty +
                '}';
    }
}
