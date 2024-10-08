package BusManage;

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
        Bus info;
        Node next;
        
        //contructor

        Node() {}
        Node(Bus x , Node p){
            info = x;
            next = p;
        }
        
        Node(Bus x){
            this(x,null);
        }
}
