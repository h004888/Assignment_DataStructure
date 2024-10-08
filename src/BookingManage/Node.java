package BookingManage;

import BusManage.*;
import java.awt.print.Book;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class Node {
        //data member
        Booking info;
        Node next;
        
        //contructor

        Node() {}
        Node(Booking x , Node p){
            info = x;
            next = p;
        }
        
        Node(Booking x){
            this(x,null);
        }
}
