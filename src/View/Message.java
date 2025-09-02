/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Marc
 */
public class Message {

    public static void ShowSuccessMessage(String message) {
        ShowSuccessMessage(null, message);
    }

    public static void ShowSuccessMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void ShowErrorMessage(String message) {
        ShowErrorMessage(null, message);
    }

    public static void ShowErrorMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public static void ShowInfoMessage(String message) {
        ShowInfoMessage(null, message);
    }

    public static void ShowInfoMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void ShowWarningMessage(String message) {
        ShowWarningMessage(null, message);
    }

    public static void ShowWarningMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
    }

}
