/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusManage;

import BookingManage.Booking;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 *
 * @author ADMIN
 */
public class BusListManage {

    public final static MyList busList = new MyList();
    public final static String path = "C:/Users/ADMIN/Documents/NB_project/BBSManage/src/Data/buses.txt";

    public static void loadDataFromFile(MyList busList) {

        if (!Validate.CheckFile.checkFilExist(path)) {
            return;
        }
        //Nếu file chưa tồn tại thì tạo file mới

        if (Validate.CheckFile.isFileEmpty(path)) {
            System.out.println("File Empty !!!");
            return;
        }
        //Check File rỗng

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // Tạo một đối tượng BufferedReader để đọc dữ liệu từ file ở đường dẫn 'path'. 
            // FileReader được sử dụng để đọc dữ liệu từ file và BufferedReader sẽ giúp đọc dữ liệu một cách hiệu quả hơn.

            String line;
            while ((line = br.readLine()) != null) {
                // Đọc từng dòng trong file cho đến khi không còn dòng nào nữa.

                String[] parts = line.split(",");
                // Tách dòng thành các phần tử, sử dụng dấu phẩy (",") làm ký tự phân tách. 
                // Mỗi dòng dữ liệu được chia thành mảng các chuỗi.

                if (parts.length == 8) {
                    // Kiểm tra xem mảng 'parts' có đúng 8 phần tử hay không. Nếu không đủ 8 phần tử, bỏ qua dòng.

                    String bcode = parts[0];
                    String bnum = parts[1];
                    String dstation = parts[2];
                    String astation = parts[3];
                    double dtime = Double.parseDouble(parts[4]);
                    double atime = Double.parseDouble(parts[5]);
                    int seats = Integer.parseInt(parts[6]);
                    int booked = Integer.parseInt(parts[7]);
                    // Các lệnh trên lấy dữ liệu từ mảng 'parts' và chuyển đổi sang các kiểu dữ liệu phù hợp 
                    // (chuỗi, số thực, số nguyên) để tạo đối tượng Bus.

                    Bus bus = new Bus(bcode, bnum, dstation, astation, dtime, atime, seats, booked);
                    // Tạo một đối tượng Bus mới từ các dữ liệu đã tách và chuyển đổi.

                    busList.addLast(bus);
                    // Thêm đối tượng Bus vào danh sách 'busList' (sử dụng phương thức 'addLast').
                }
            }
            System.out.println("Bus data loaded from file.");
            // In ra thông báo khi dữ liệu bus đã được tải thành công từ file.
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
            // Bắt và xử lý ngoại lệ nếu có lỗi xảy ra trong quá trình đọc file.
        }
    }

    public static void addToEnd() {
        System.out.print("Enter bus code: ");
        String bcode = Validate.CheckInput.Checkbcode();
        System.out.print("Enter bus number: ");
        String bnum = Validate.CheckInput.CheckString();
        System.out.print("Enter departure station: ");
        String dstation = Validate.CheckInput.CheckString();
        System.out.print("Enter arrival station: ");
        String astation = Validate.CheckInput.CheckString();
        System.out.print("Enter departure time: ");
        double dtime = Validate.CheckInput.checkDtime();
        System.out.print("Enter arrival time: ");
        double atime = Validate.CheckInput.checkatime(dtime);
        System.out.print("Enter number of seats: ");
        int seats = Validate.CheckInput.checkSeat();
        System.out.print("Enter number of booked seats: ");
        int booked = Validate.CheckInput.checkBooked(seats);
        Bus bus = new Bus(bcode, bnum, dstation, astation, dtime, atime, seats, booked);

        busList.addLast(bus);
        System.out.println("Bus added Successfull !! .");
    }

    public static void displayData() {
        //Format Data
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Bcode", "Bnum", "Dstation", "Astation", "Dtime", "Seat", "Booked", "Atime");
        for (int i = 0; i < busList.size(); i++) {
            DisplayBus(busList.get(i).info);
        }
    }

    public static void DisplayBus(Bus bus) {
        //Get thông tin data
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", bus.getBcode(), bus.getBnum(), bus.getDstation(), bus.getAstation(), bus.getDtime(), bus.getSeat(), bus.getBooked(), bus.getAtime());
    }

    public static void saveToFile(String fileName, MyList ls) {
        //PrintWriter Ghi dữ liệu 
        //FileWriter Mở để ghi
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            Node current = ls.head;
            while (current != null) {
                Bus bus = current.info;
                pw.println(bus.getBcode() + "," + bus.getBnum() + "," + bus.getDstation() + "," + bus.getAstation() + ","
                        + bus.getDtime() + "," + bus.getAtime() + "," + bus.getSeat() + "," + bus.getBooked());
                current = current.next;
            }
            System.out.println("Bus List Saved To File Successfull!! .");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void searchByBcode() {
        System.out.print("Enter bus code: ");
        String bcode = Validate.CheckInput.CheckString();
        MyList list = new MyList();
        loadDataFromFile(list);

        if (list.searchByBcode(bcode) == null) {
            System.out.println("Not Found Bcode!!!");
        } else {
            System.out.println("Find Successfull !!");
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Bcode", "Bnum", "Dstation", "Astation", "Dtime", "Seat", "Booked", "Atime");

            DisplayBus(list.searchByBcode(bcode).info);
        }

    }

    public static Bus searchByBcode(String bcode) {
        MyList list = new MyList();
        loadDataFromFile(list);

        if (list.searchByBcode(bcode) == null) {
            return null;
        } else {
            return list.searchByBcode(bcode).info;
        }

    }

    public static void deleteByBcode() {
        System.out.print("Enter bus code: ");
        String bcode = Validate.CheckInput.CheckString();
        MyList list = new MyList();
        loadDataFromFile(list);
        if (list.searchByBcode(bcode) == null) {
            System.out.println("Not found Bcode!!!");
        } else {

            list.remove(list.searchByBcode(bcode));
            saveToFile(path, list);
            System.out.println("Delete Successfully!!!!");
        }
    }

    public static void sortByBcode() {
        MyList list = new MyList();
        loadDataFromFile(list);
        list.sortByBcode();
        list.traverse();
        saveToFile(path, list);
    }

    public static void addToBegin() {
//         MyList list = new MyList();
//        loadDataFromFile(list);
        System.out.print("Enter bus code: ");
        String bcode = Validate.CheckInput.Checkbcode();
        System.out.print("Enter bus number: ");
        String bnum = Validate.CheckInput.CheckString();
        System.out.print("Enter departure station: ");
        String dstation = Validate.CheckInput.CheckString();
        System.out.print("Enter arrival station: ");
        String astation = Validate.CheckInput.CheckString();
        System.out.print("Enter departure time: ");
        double dtime = Validate.CheckInput.checkDtime();
        System.out.print("Enter arrival time: ");
        double atime = Validate.CheckInput.checkatime(dtime);
        System.out.print("Enter number of seats: ");
        int seats = Validate.CheckInput.checkSeat();
        System.out.print("Enter number of booked seats: ");
        int booked = Validate.CheckInput.checkBooked(seats);
        Bus bus = new Bus(bcode, bnum, dstation, astation, dtime, atime, seats, booked);
        busList.addFirst(bus);
//        saveToFile(path, list);
//        System.out.println("Bus added to the end of the list.");
    }

    public static void addAfterPk() {
        System.out.print("Enter bus code: ");
        String bcode = Validate.CheckInput.Checkbcode();
        System.out.print("Enter bus number: ");
        String bnum = Validate.CheckInput.CheckString();
        System.out.print("Enter departure station: ");
        String dstation = Validate.CheckInput.CheckString();
        System.out.print("Enter arrival station: ");
        String astation = Validate.CheckInput.CheckString();
        System.out.print("Enter departure time: ");
        double dtime = Validate.CheckInput.checkDtime();
        System.out.print("Enter arrival time: ");
        double atime = Validate.CheckInput.checkatime(dtime);
        System.out.print("Enter number of seats: ");
        int seats = Validate.CheckInput.checkSeat();
        System.out.print("Enter number of booked seats: ");
        int booked = Validate.CheckInput.checkBooked(seats);
        Bus bus = new Bus(bcode, bnum, dstation, astation, dtime, atime, seats, booked);
        System.out.println("Enter positison: ");
        int p = Validate.CheckInput.checkInteger();

        loadDataFromFile(busList);
        busList.insert(p, bus);
        busList.traverse();

    }

    public static void deletePk() {

        System.out.println("Enter positison: ");

        int p = Validate.CheckInput.checkInteger();
        MyList ls = new MyList();
        loadDataFromFile(ls);
        ls.remove(p);
        ls.traverse();
    }

    public static void searchByName() {
        System.out.println("Enter name: ");
        String name = Validate.CheckInput.CheckString();
        PassengerManage.Passenger pass = PassengerManage.passengerListManage.searchPassengerByName(name);
        if (pass == null) {
            System.out.println("Khong co ten nay trong passenger !!!");

        } else {
            if (BookingManage.BookingListManage.searchBookingByPcode(pass.getPcode()) == null) {
                System.out.println("Passenger nay khong dat xe");
            } else {
                Booking book = BookingManage.BookingListManage.searchBookingByPcode(pass.getPcode());
                DisplayBus(searchByBcode(book.getBcode()));
            }
        }
    }

    public static void searchBookedByCode() {
        System.out.println("Enter bcode: ");
        String bcode = Validate.CheckInput.CheckString();
        BookingManage.BookingListManage.searchBookedByBcode(bcode);

    }

    public static void Menu() {
        System.out.println("==============================BUS MANAGE======================================");
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Load data from file");
            System.out.println("2. Input & add to the end");
            System.out.println("3. Display data");
            System.out.println("4. Save bus list to file");
            System.out.println("5. Search by bcode");
            System.out.println("6. Delete by bcode");
            System.out.println("7. Sort by bcode");
            System.out.println("8. Input & add to beginning");
            System.out.println("9. Add after position k");
            System.out.println("10. Delete position k");
            System.out.println("11. Search by name");
            System.out.println("12. Search Booked By Bcode");
            System.out.println("13. Exit");
            System.out.println("=========================================================================");
            System.out.print("Enter Your choice: ");
            choice = Validate.CheckInput.checkInteger();
            switch (choice) {
                case 1:
                    loadDataFromFile(busList);
                    break;
                case 2:
                    addToEnd();
                    break;
                case 3:
                    displayData();
                    break;
                case 4:
                    saveToFile(path, busList);
                    break;
                case 5:
                    searchByBcode();
                    break;

                case 6:
                    deleteByBcode();
                    break;

                case 7:
                    sortByBcode();
                    break;
                case 8:
                    addToBegin();

                    break;
                case 9:
                    addAfterPk();
                    break;

                case 10:
                    deletePk();
                    break;

                case 11:
                    searchByName();
                    break;
                case 12:
                    searchBookedByCode();
                    break;
                default:
                    break;
            }
        } while (choice != 13);
        System.out.println("-------------------------------------------------------------------");

    }

    public static void main(String[] args) {
        Menu();
    }
}
