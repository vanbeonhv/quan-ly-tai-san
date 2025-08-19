/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Marc
 */
public class Message {

    public static void ShowSuccessMessage(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message);
    }

    public static void ShowErrorMessage(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

}
