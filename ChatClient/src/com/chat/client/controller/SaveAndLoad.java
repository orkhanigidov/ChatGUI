/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.client.controller;

import com.chat.client.view.ClientGUI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Orxan
 */
public class SaveAndLoad {

    public void save(String text, String file) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            bw.append(text);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable save messages");
        }
    }

    public static void load(String file, ClientGUI cgui) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            String content = "";
            while ((line = br.readLine()) != null) {
                content += line;
                content += "\n";
            }
            cgui.loadMessages(content);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable read file");
        }
    }
}
