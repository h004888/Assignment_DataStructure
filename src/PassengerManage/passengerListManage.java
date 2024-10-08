/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassengerManage;

import BusManage.Bus;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 *
 * @author ADMIN
 */
public class passengerListManage {

    public final static MyList passList = new MyList();
    public final static String path = "C:/Users/ADMIN/Documents/NB_project/BBSManage/src/Data/passengers.txt";

    public static void loadDataFromFile(MyList passList) {
        if (!Validate.CheckFile.checkFilExist(path)) {

            return;
        }
        if (Validate.CheckFile.isFileEmpty(path)) {
            System.out.println("No content in File !!!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String pcode = parts[0];
                    String name = parts[1];
                    String phone = parts[2];

                    Passenger pass = new Passenger(pcode, name, phone);
                    passList.addLast(pass);

                }
            }

            System.out.println("Pass data loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public static void addToEnd() {
        System.out.print("Enter passenger code: ");
        String pcode = Validate.CheckInput.Checkpcode();
        System.out.print("Enter name: ");
        String name = Validate.CheckInput.CheckString();
        System.out.print("Enter phone: ");
        String phone = Validate.CheckInput.checkPhone();
        passList.addLast(new Passenger(pcode, name, phone));
        passList.traverse();
        System.out.println("Pass added to the end of the list.");
    }

    public static void displayData() {
        System.out.printf("%-20s%-20s%-20s\n", "Pcode", "Name", "Phone");
        for (int i = 0; i < passList.size(); i++) {
            DisplayBus(passList.get(i).info);
        }
    }

    public static void DisplayBus(Passenger bus) {
        System.out.printf("%-20s%-20s%-20s\n", bus.pcode, bus.name, bus.phone);
    }

    public static void saveToFile(String fileName, MyList ls) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            Node current = ls.head;
            while (current != null) {
                Passenger bus = current.info;
                pw.println(bus.getPcode() + "," + bus.getName() + "," + bus.getPhone());
                current = current.next;
            }
            System.out.println("Pass list saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void searchByPcode() {
        System.out.print("Enter passenger code: ");
        String pcode = Validate.CheckInput.CheckString();
        MyList list = new MyList(); 
        loadDataFromFile(list);
        list.traverse();

        if (list.searchByPcode(pcode) == null) {
            System.out.println("Not found Bcode!!!");
        } else {
            System.out.printf("%-20s%-20s%-20s\n", "Pcode", "Name", "Phone");
            DisplayBus(list.searchByPcode(pcode).info);
        }

    }

    public static Passenger searchByBcode(String bcode) {
        MyList list = new MyList();
        loadDataFromFile(list);

        if (list.searchByPcode(bcode) == null) {
            return null;
        } else {
            return list.searchByPcode(bcode).info;
        }

    }

    public static Passenger searchPassengerByName(String name) {
        MyList list = new MyList();
        loadDataFromFile(list);
        Node current = list.head;  // passengerHead là đầu của danh sách liên kết chứa các hành khách
        while (current != null) {
            if (current.info.getName().equalsIgnoreCase(name)) {  // So sánh không phân biệt hoa thường
                return current.info;
            }
            current = current.next;
        }
        return null;  // Nếu không tìm thấy hành khách
    }

    public static void deleteByBcode() {
        System.out.print("Enter passenger code: ");
        String pcode = Validate.CheckInput.CheckString();
        MyList list = new MyList();
        loadDataFromFile(list);
        if (list.searchByPcode(pcode) == null) {
            System.out.println("Not found Bcode!!!");
        } else {

            list.remove(list.searchByPcode(pcode));
            saveToFile(path, list);
            System.out.println("Delete Successfully!!!!");
        }
    }

    public static void searchByName() {
        System.out.print("Enter name: ");
        String bcode = Validate.CheckInput.CheckString();
        MyList list = new MyList();
        loadDataFromFile(list);

        if (list.searchByName(bcode) == null) {
            System.out.println("Not found Bcode!!!");
        } else {
            System.out.printf("%-20s%-20s%-20s\n", "Pcode", "Name", "Phone");
            DisplayBus(list.searchByName(bcode).info);
        }

    }

    public static void searchBusesByPcode() {
    System.out.print("Enter bcode: "); // Yêu cầu nhập mã
    try {
        String pcode = Validate.CheckInput.CheckString(); // Kiểm tra mã
        if (pcode == null || pcode.isEmpty()) { // Kiểm tra nếu pcode rỗng
            System.out.println("Pcode cannot be empty. Please try again.");
            return; // Thoát nếu pcode không hợp lệ
        }
        BookingManage.BookingListManage.searchBookedByBcode(pcode); // Tìm kiếm các chuyến xe theo mã
    } catch (Exception e) { 
        System.out.println("An error occurred while searching: " + e.getMessage());
    }
}

    public static void Menu() {
        System.out.println("----------------------------------------------------Passenger Management Menu---------------------------------------------");
        int choice;
        do {

            System.out.println("1. Load data from file");
            System.out.println("2. Input & add to the end");
            System.out.println("3. Display all passengers");
            System.out.println("4. Save passenger list to file");
            System.out.println("5. Search by pcode");
            System.out.println("6. Delete by pcode");
            System.out.println("7. Search by name");
            System.out.println("8. Search buses by pcode");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = Validate.CheckInput.checkInteger();
            switch (choice) {
                case 1:
                    loadDataFromFile(passList);
                    break;
                case 2:
                    addToEnd();
                    break;

                case 3:
                    displayData();
                    break;
                case 4:
                    saveToFile(path, passList);
                    break;

                case 5:
                    searchByPcode();

                    break;
                case 6:
                    deleteByBcode();
                    break;
                case 7:
                    searchByName();
                    break;

                case 8:
                    searchBusesByPcode();
                    break;
                default:
                    break;
            }
        } while (choice != 9);
        System.out.println("-------------------------------------------------------------------");

    }

    public static void main(String[] args) {
        Menu();
    }
}
