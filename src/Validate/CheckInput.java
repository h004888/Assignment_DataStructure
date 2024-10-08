/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validate;

import java.util.*;
import java.io.*;
import java.lang.*;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class CheckInput {

    public final static String path = "C:/Users/ADMIN/Documents/NB_project/BBSManage/src/Data/buses.txt";
    public final static String pathpass = "C:/Users/ADMIN/Documents/NB_project/BBSManage/src/Data/passengers.txt";
    private final static Scanner sc = new Scanner(System.in);

    public static int checkInteger() {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());

                return result;

            } catch (NumberFormatException e) {
                System.out.print("Enter again: ");
            }
        }
    }

    public static double checkDtime() {
        while (true) {
            try {
                double dtime = Double.parseDouble(sc.nextLine().trim());
                if (dtime >= 0 && dtime <= 24) {
                    return dtime;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.print("Enter dtime 0 ≤ dtime ≤ 24 : ");
            }
        }
    }

    public static double checkatime(double dtime) {
        while (true) {
            try {
                double atime = Double.parseDouble(sc.nextLine().trim());
                if (atime >= dtime && atime <= 24) {
                    return atime;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.print("Enter dtime 0 ≤ dtime ≤ 24 : ");
            }
        }
    }

    public static int CheckPositiveInt() {
        Scanner scanner = new Scanner(System.in);
        int number = -1;
        do {
            System.out.print("Enter a positive integer: ");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number!");
                scanner.next(); // discard invalid input
            }
            number = scanner.nextInt();
            if (number <= 0) {
                System.out.println("Please enter a positive integer.");
            }
        } while (number <= 0);
        return number;
    }

    public static int checkSeat() {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result > 0) {
                    return result;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.print("Enter again (Seat > 0): ");
            }
        }
    }

    public static int checkBooked(int seat) {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < 0 && result > seat) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.print("Enter again booked  > 0 : ");
            }
        }
    }

    public static String Checkbcode() {
        while (true) {
            String bcode = sc.nextLine().trim();

            // Kiểm tra xem bcode có rỗng hay không
            if (bcode.isEmpty()) {
                System.out.print("Enter bcode again: ");
            } // Kiểm tra định dạng bcode (phải là bxxxx, với x là các chữ số)
            else if (!bcode.matches("b\\d{4}")) {
                System.out.print("Bcode must be in the format 'bxxxx' (x is a digit). Enter bcode again: ");
            } // Kiểm tra xem bcode đã tồn tại hay chưa
            else if (checkBcodeExists(bcode, path)) {
                System.out.print("Bcode already exists. Enter bcode again: ");
            } // Nếu không có vấn đề gì, trả về bcode
            else {
                return bcode;
            }
        }
    }

    public static String Checkpcode() {
        while (true) {
            String bcode = sc.nextLine().trim();

            // Kiểm tra xem bcode có rỗng hay không
            if (bcode.isEmpty()) {
                System.out.print("Enter pcode again: ");
            } // Kiểm tra định dạng bcode (phải là bxxxx, với x là các chữ số)
            else if (!bcode.matches("p\\d{4}")) {
                System.out.print("Bcode must be in the format 'pxxxx' (x is a digit). Enter bcode again: ");
            } // Kiểm tra xem bcode đã tồn tại hay chưa
            else if (checkBcodeExists(bcode, path)) {
                System.out.print("Bcode already exists. Enter bcode again: ");
            } // Nếu không có vấn đề gì, trả về bcode
            else {
                return bcode;
            }
        }
    }

    public static String checkPhone() {
        while (true) {
            String phone = sc.nextLine().trim();

            // Kiểm tra xem số điện thoại có chỉ chứa ký tự số
            if (!phone.matches("\\d+")) {
                System.out.print("Phone number must contain digits only. Enter again: ");
                continue;
            }

            // Kiểm tra số điện thoại có độ dài hợp lệ không (tuỳ thuộc vào yêu cầu)
            if (phone.length() < 10 || phone.length() > 11) {
                System.out.print("Phone number must be 10-11 digits long. Enter again: ");
                continue;
            }

            // Kiểm tra số điện thoại đã tồn tại hay chưa
            if (checkPhoneExists(phone, pathpass)) {
                System.out.print("Phone number already exists. Enter again: ");
                continue;
            }

            // Nếu tất cả đều hợp lệ, trả về số điện thoại
            return phone;
        }
    }

    public static boolean checkPhoneExists(String phone, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[2].trim().equals(phone)) {
                    return true; // Số điện thoại đã tồn tại
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false; // Số điện thoại chưa tồn tại
    }

    public static String CheckString() {
        while (true) {
            String bcode = sc.nextLine().trim();
            if (bcode.isEmpty()) {
                System.out.print("Enter again: ");

            } else {
                return bcode;

            }
        }
    }

    public static boolean checkBcodeExists(String bcode, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách dòng dữ liệu thành các phần tử
                String[] data = line.split(",");
                // So sánh với bcode
                if (data[0].trim().equalsIgnoreCase(bcode)) {
                    return true; // Tìm thấy bcode
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false; // Không tìm thấy bcode
    }

    public static boolean checkpcodeExists(String bcode, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách dòng dữ liệu thành các phần tử
                String[] data = line.split(",");
                // So sánh với bcode
                if (data[0].trim().equalsIgnoreCase(bcode)) {
                    return true; // Tìm thấy bcode
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false; // Không tìm thấy bcode
    }

}
