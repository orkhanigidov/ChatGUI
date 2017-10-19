/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.client.controller;

import com.chat.client.view.ClientGUI;
import com.chat.client.view.Login;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author orxan
 */
public class chatLogin {

    public static String user;

    public static void login(String username, String password) {
        boolean check = false;
        try {
            FileReader fr = new FileReader(new File("D:\\Registed.txt"));
            BufferedReader br = new BufferedReader(fr);

            String find = username.concat("/").concat(password);
            String line;

            while ((line = br.readLine()) != null) {
                if (find.equals(line)) {
                    check = true;
                }
            }

            if (check == true) {
                user = username;
                new ClientGUI().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password.\nTry again...");
                new Login().setVisible(true);
            }
        } catch (IOException ex) {
        }
    }

}
