/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookingManage;

import java.util.*;
/**
 *
 * @author ADMIN
 */
public class Booking {
   private String bcode;   // Mã của xe buýt được đặt
    private String pcode;   // Mã của hành khách đặt vé
    private String odate;     // Ngày đặt vé
    private int paid;       // Trạng thái thanh toán: 0 - chưa thanh toán, 1 - đã thanh toán
    private int seat;       // Số ghế được đặt

    // Constructor
    public Booking(String bcode, String pcode, String odate, int paid, int seat) {
        this.bcode = bcode;
        this.pcode = pcode;
        this.odate = odate;
        this.paid = paid;
        this.seat = seat;
    }

    // Getters and Setters
    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    // Hiển thị thông tin của booking
    public void displayBooking() {
        System.out.printf("%-10s %-10s %-15s %-10d %-10d\n", bcode, pcode, odate, paid, seat);
    }
}
