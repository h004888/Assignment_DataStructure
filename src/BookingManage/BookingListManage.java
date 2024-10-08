/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookingManage;

import BusManage.*;
import PassengerManage.*;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ADMIN
 */
public class BookingListManage {

    public final static MyList bookList = new MyList();
    public final static String path = "C:/Users/ADMIN/Documents/NB_project/BBSManage/src/Data/booking.txt";
    public final static String path1 = "C:/Users/ADMIN/Documents/NB_project/BBSManage/src/Data/buses.txt";
    public final static String path2 = "C:/Users/ADMIN/Documents/NB_project/BBSManage/src/Data/passengers.txt";

    // Doc va tai danh sach
    public static void loadBookingsFromFile(MyList bookingList, String path) {
        if (!Validate.CheckFile.checkFilExist(path)) {
            return;
        }
        if (Validate.CheckFile.isFileEmpty(path)) {
            System.out.println("No content in File !!!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line; // doc va luu tru tung dong van ban
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String bcode = parts[0];
                    String pcode = parts[1];
                    String odate = parts[2];
                    int paid = Integer.parseInt(parts[3]);
                    int seat = Integer.parseInt(parts[4]);

                    Booking booking = new Booking(bcode, pcode, odate, paid, seat);
                    bookingList.addLast(booking);
                }
            }
            System.out.println("Booking data loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading booking data: " + e.getMessage());
        }
    }

    public static void bookBus() {
        // kiem tra bus code co ton tai khong ?
        System.out.print("Enter bus code: ");
        String bcode = Validate.CheckInput.CheckString();

        Bus bus = BusManage.BusListManage.searchByBcode(bcode);

        if (bus == null) {
            System.out.println("Bus not found!");
            return;
        }

        // Kiem tra passenger code co ton tai khong ?
        System.out.print("Enter passenger code: ");
        String pcode = Validate.CheckInput.CheckString();
        Passenger passenger = PassengerManage.passengerListManage.searchByBcode(pcode);

        if (passenger == null) {
            System.out.println("Passenger not found!");
            return;
        }

        // check xem so cho can book co nho hon so cho con lai khong ?
        System.out.print("Enter number of seats to book: ");
        int seats = Validate.CheckInput.checkInteger();

        if (seats > (bus.getSeat() - bus.getBooked())) {
            System.out.println("Not enough seats available!");
            return;
        }

        // check ngay thang toi hien tai va thiet lap trang thai thanh toan bang 0
        String odate = getCurrentDate();
        int paid = 0;

        // Them booking vao cuoi danh sach
        Booking newBooking = new Booking(bcode, pcode, odate, paid, seats);
        bookList.addLast(newBooking);

        // Cap nhat so ghe da dat va so ghe con lai 
        bus.setBooked(bus.getBooked() + seats);

        System.out.println("Seats Booked: " + bus.getBooked());
        System.out.println("Seats Remaining: " + (bus.getSeat() - bus.getBooked()));
        saveToFile(path, bookList);
        System.out.println("Booking successful.");
    }

    public static void saveToFile(String fileName, MyList ls) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            Node current = ls.head;
            while (current != null) {
                Booking bus = current.info;
                pw.println(bus.getBcode() + "," + bus.getPcode() + "," + bus.getOdate() + "," + bus.getPaid() + "," + bus.getSeat());
                current = current.next;
            }
            System.out.println("Bus list saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void displayData(MyList booList) {
        System.out.printf("%-20s%-20s%-20s\n", "Bcode", "Pcode", "Date", "Paid", "Seat");
        for (int i = 0; i < bookList.size(); i++) {
            DisplayBus(bookList.get(i).info);
        }
    }

    public static void DisplayBus(Booking bus) {
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", bus.getBcode(), bus.getPcode(), bus.getOdate(), bus.getPaid(), bus.getSeat());
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    public static void searchBookedByBcode(String bcode) {
        Bus bus = BusListManage.searchByBcode(bcode); // Tìm xe buýt dựa trên mã bcode
        if (bus != null) {
            System.out.println("Bus found:");
            BusManage.BusListManage.DisplayBus(bus); // Hiển thị thông tin xe buýt
            MyList ls = new MyList();
            BookingListManage.loadBookingsFromFile(ls, path);
            System.out.println("Passengers who booked this bus:");

            Node currentBooking = ls.head; // Duyệt qua danh sách booking
            boolean foundPassenger = false;

            while (currentBooking != null) {
                Booking booking = currentBooking.info;
                if (booking.getBcode().equalsIgnoreCase(bcode)) {
                    PassengerManage.Passenger passenger = PassengerManage.passengerListManage.searchByBcode(booking.getPcode()); // Tìm hành khách dựa trên pcode từ booking
                    if (passenger != null) {
                        foundPassenger = true;
                        System.out.println("Passenger: " + passenger.name + ", Phone: " + passenger.phone + ", Seats booked: " + booking.getSeat());
                    }
                }
                currentBooking = currentBooking.next;
            }

            if (!foundPassenger) {
                System.out.println("No passengers have booked this bus.");
            }
        } else {
            System.out.println("Bus not found with bcode: " + bcode);
        }
    }

    public static void searchBusesByPcode(String bcode) {
        Passenger pass = passengerListManage.searchByBcode(bcode); // Tìm xe buýt dựa trên mã bcode
        if (pass != null) {
            System.out.println("Pass found:");
            passengerListManage.DisplayBus(pass); // Hiển thị thông tin xe buýt
            MyList ls = new MyList();
            BookingListManage.loadBookingsFromFile(ls, path);
            System.out.println("Passengers who booked this bus:");

            Node currentBooking = ls.head; // Duyệt qua danh sách booking
            boolean foundPassenger = false;

            while (currentBooking != null) {
                Booking booking = currentBooking.info;
                if (booking.getPcode().equalsIgnoreCase(bcode)) {
                    BusManage.Bus bus = BusManage.BusListManage.searchByBcode(booking.getBcode()); // Tìm hành khách dựa trên pcode từ booking
                    if (bus != null) {
                        foundPassenger = true;
                        BusListManage.DisplayBus(bus);
                    }
                }
                currentBooking = currentBooking.next;
            }

            if (!foundPassenger) {
                System.out.println("passenger have not booked this bus.");
            }
        } else {
            System.out.println("Passenger not found with bcode: " + bcode);
        }
    }

    public static void sortByBcodeAndPcode() {
        MyList ls = new MyList();
        loadBookingsFromFile(ls, path);
        ls.sortByBcodeAndPcode();
        displayData(ls);

    }

    public static String searchByPcode(String pcode) {
        MyList list = new MyList();
        loadBookingsFromFile(list, path);

        if (list.searchByBcode(pcode) == null) {
            return null;
        } else {
            return list.searchByBcode(pcode).info.getBcode();
        }

    }

    public static Booking searchBookingByPcode(String name) {
        MyList list = new MyList();
        loadBookingsFromFile(list, path);
        Node current = list.head;  // passengerHead là đầu của danh sách liên kết chứa các hành khách
        while (current != null) {
            if (current.info.getPcode().equalsIgnoreCase(name)) {  // So sánh không phân biệt hoa thường
                return current.info;
            }
            current = current.next;
        }
        return null;  // Nếu không tìm thấy hành khách
    }

    public static void payBookingByBcodePcode() {
        System.out.print("Enter bus code: ");
        String bcode = Validate.CheckInput.CheckString();
        System.out.print("Enter passenger code: ");
        String pcode = Validate.CheckInput.CheckString();

        Node current = bookList.getFirst();
        while (current != null) {
            Booking booking = current.info;
            if (booking.getBcode().equals(bcode) && booking.getPcode().equals(pcode)) {
                if (booking.getPaid() == 0) {
                    booking.setPaid(1);
                    System.out.println("Booking paid successfully.");
                } else {
                    System.out.println("Booking is already paid.");
                }
                return;
            }
            current = current.next;
        }
        System.out.println("Booking not found.");
    }

    public static void Menu() {
        int choice;
        do {
            System.out.println("1. Load data from file");
            System.out.println("2. Book bus ");
            System.out.println("3. Display data");
            System.out.println("4. Save Booking list to file");
            System.out.println("5. Sort  by bcode + pcode");
            System.out.println("6. Pay booking by bcode + pcode");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = Validate.CheckInput.checkInteger();

            switch (choice) {
                case 1:
                    loadBookingsFromFile(bookList, path);

                    break;
                case 2:
                    bookBus();
                    break;
                case 3:
                    displayData(bookList);
                    break;
                case 4:
                    saveToFile(path, bookList);

                    break;
                case 5:
                    sortByBcodeAndPcode();
                    break;
                case 6:
                    payBookingByBcodePcode();
                    break;
                default:
                    break;
            }
        } while (choice != 7);
        System.out.println("-------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Menu();
    }

}
