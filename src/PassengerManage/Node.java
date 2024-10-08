package PassengerManage;

import BusManage.*;

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
        Passenger info;
        Node next;
        
        //contructor

        Node() {}
        Node(Passenger x , Node p){
            info = x;
            next = p;
        }
        
        Node(Passenger x){
            this(x,null);
        }
}
