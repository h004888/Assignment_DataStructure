/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusManage;

/**
 *
 * @author ADMIN
 */
public class Bus {

    String bcode;
    String bnum;
    String dstation;
    String astation;
    double dtime;
    double atime;
    int seat;
    int booked;

    public Bus(String bcode, String bnum, String dstation, String astation, double dtime, double atime, int seat, int booked) {
        this.bcode = bcode;
        this.bnum = bnum;
        this.dstation = dstation;
        this.astation = astation;
        this.dtime = dtime;
        this.atime = atime;
        this.seat = seat;
        this.booked = booked;
    }

    public Bus() {
    }

    @Override
    public String toString() {
        return bcode + " | " + bnum + " | " + dstation + " | " + astation + " | " + dtime + " | " + atime + " | " + seat + " | " + booked+"\n";
    }

    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public String getBnum() {
        return bnum;
    }

    public void setBnum(String bnum) {
        this.bnum = bnum;
    }

    public String getDstation() {
        return dstation;
    }

    public void setDstation(String dstation) {
        this.dstation = dstation;
    }

    public String getAstation() {
        return astation;
    }

    public void setAstation(String astation) {
        this.astation = astation;
    }

    public double getDtime() {
        return dtime;
    }

    public void setDtime(double dtime) {
        this.dtime = dtime;
    }

    public double getAtime() {
        return atime;
    }

    public void setAtime(double atime) {
        this.atime = atime;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }
    
    
}
