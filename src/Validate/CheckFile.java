/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validate;

import java.util.*;
import java.io.*;
import java.lang.*;

/**
 *
 * @author ADMIN
 */
public class CheckFile {

    public static boolean checkFilExist(String filePath) {
        File file = new File(filePath);

        // Kiểm tra xem file có tồn tại không
        if (!file.exists()) {
            try {
                // Nếu file không tồn tại, tạo file mới
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("File does not Exist!");
                    System.out.println("File created successfully at: " + filePath);
                } else {
                    System.out.println("Failed to create file at: " + filePath);
                }
                return created; // Trả về true nếu tạo thành công, false nếu không tạo được
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
                return false; // Trả về false nếu có lỗi
            }
        } else {
            System.out.println("File already exists at: " + filePath);
            return true; // Trả về true nếu file đã tồn tại
        }
    }

    public static boolean isFileEmpty(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            // Đọc dòng đầu tiên của tệp
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                return true; // Tệp rỗng hoặc chỉ chứa khoảng trắng
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false; // Tệp không rỗng
    }
}
