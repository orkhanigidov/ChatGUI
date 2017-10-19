/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.server.controller;

import com.chat.server.view.ServerGUI;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author orxan
 */
public class clientThread extends Thread {

    public DataInputStream input;
    public DataOutputStream output;
    public Socket clientSocket, socket;
    public ServerGUI sgui;
    public static ArrayList clients;
    boolean signal;

    public clientThread(Socket clientSocket, ArrayList clients, ServerGUI sgui) {
        this.clientSocket = clientSocket;
        clientThread.clients = clients;
        this.sgui = sgui;
    }

    @Override
    public void run() {
        readMessages();
    }

    public void readMessages() {
        signal = true;
        String message;
        try {
            input = new DataInputStream(clientSocket.getInputStream());
            while (signal) {
                checkClients();
                message = input.readUTF();
                if (message != null) {
                    sgui.log(message);
                    sendMessages(message);
                } else {
                    break;
                }
            }
        } catch (IOException ex) {
        }
    }

    public void sendMessages(String message) {
        Iterator i = clients.iterator();
        while (i.hasNext()) {
            try {
                socket = (Socket) i.next();
                output = new DataOutputStream(socket.getOutputStream());
                output.writeUTF(message);
                output.flush();
            } catch (IOException ex) {
            }
        }
    }

    public void close() {
//        signal = false;
        try {
            output.close();
            input.close();
//            socket.close();
            clientSocket.close();
        } catch (Exception e) {
        }
    }

    public void checkClients() {
        if (!clientSocket.isConnected()) {
            clients.remove(this);
            close();
        }
    }

}
