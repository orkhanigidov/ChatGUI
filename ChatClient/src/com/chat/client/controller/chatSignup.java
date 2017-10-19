/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.client.controller;

import com.chat.client.view.Signup;
import com.chat.client.view.Login;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author orxan
 */
public class chatSignup {

    public void enterValues(String name, String username, String email, String password, String re_password, String gender) {
        if (fieldCheck(name, username, email, password, re_password, gender)
                && passwordCheck(password, re_password)
                && usernameCheck(username)) {
            writeValues(name, username, email, password, re_password, gender);
        } else {
            new Signup().setVisible(true);
        }
    }

    public void writeValues(String name, String username, String email, String password, String re_password, String gender) {
        try {
            FileWriter fw = new FileWriter(new File("D:\\Registed.txt"), true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.append("---------------");
            bw.newLine();
            bw.append("Name: " + name);
            bw.newLine();
            bw.append("Email: " + email);
            bw.newLine();
            bw.append("Username: " + username);
            bw.newLine();
            bw.append(username + "/" + password);
            bw.newLine();
            bw.append("Gender: " + gender);
            bw.newLine();
            bw.close();
            JOptionPane.showMessageDialog(null, "Registration successful");
            new Login().setVisible(true);
        } catch (IOException ex) {
        }
    }

    public boolean usernameCheck(String username) {
        boolean check = true;
        try {
            FileReader fr = new FileReader(new File("D:\\Registed.txt"));
            BufferedReader br = new BufferedReader(fr);

            String find = ("Username: ").concat(username);
            String line;

            while ((line = br.readLine()) != null) {
                if (find.equals(line)) {
                    check = false;
                }
            }

            if (check == false) {
                JOptionPane.showMessageDialog(null, "This username already used, please retype another");
            } else {
                check = true;
            }
        } catch (IOException ex) {
        }
        return check;
    }

    public boolean passwordCheck(String pasword, String re_password) {
        boolean check;
        if (!pasword.equals(re_password)) {
            check = false;
            JOptionPane.showMessageDialog(null, "Password does not match");
        } else if (pasword.length() < 5) {
            check = false;
            JOptionPane.showMessageDialog(null, "Password length less than 5");
        } else {
            check = true;
        }
        return check;
    }

    public boolean fieldCheck(String name, String username, String email, String password, String re_password, String gender) {
        boolean check;
        if (name.equals("") || username.equals("") || email.equals("") || password.equals("") || re_password.equals("") || gender.equals("")) {
            check = false;
            JOptionPane.showMessageDialog(null, "Fill all fields");
        } else {
            check = true;
        }
        return check;
    }
}
