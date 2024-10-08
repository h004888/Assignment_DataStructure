/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author ADMIN
 */
public class Main {
    public static void main(String[] args) {
        int choice ;
        System.out.println("------------------------   Bus Booking System   ------------------------");
        do {        
            System.out.println("1.Bus Management");
            System.out.println("2.Passenger Management");
            System.out.println("3.Booking Management");
            System.out.println("4.Exit");
            System.out.print("Enter choose : ");
            choice=Validate.CheckInput.checkInteger();
            switch (choice) {
                case 1:
                    BusManage.BusListManage.main(args);
                    break;
                case 2:
                    PassengerManage.passengerListManage.main(args);
                    break;
                case 3:
                    BookingManage.BookingListManage.main(args);
                    break;
                default:
                    break;
            }
        } while (choice!=4);
    }
}
