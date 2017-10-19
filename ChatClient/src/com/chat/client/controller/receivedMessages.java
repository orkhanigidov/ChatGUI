/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.client.controller;

import com.chat.client.view.ClientGUI;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author orxan
 */
public class receivedMessages extends Thread {

    public DataInputStream input;
    public ClientGUI cgui;

    public receivedMessages(DataInputStream input, ClientGUI cgui) {
        this.input = input;
        this.cgui = cgui;
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = input.readUTF()) != null) {
                cgui.log(message);
            }
        } catch (IOException ex) {
        }
    }

}
