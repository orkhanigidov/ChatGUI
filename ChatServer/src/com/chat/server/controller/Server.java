/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.server.controller;

import com.chat.server.view.ServerGUI;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author orxan
 */
public class Server extends Thread {

    public ServerSocket serverSocket;
    public Socket clientSocket;
    public int port;
    public ArrayList clients = new ArrayList();
    public ServerGUI sgui;
    public boolean signal;

    public Server(ServerGUI sgui, int port) {
        this.port = port;
        this.sgui = sgui;
        try {
            serverSocket = new ServerSocket(port);
            sgui.log("Server started on port " + port);
        } catch (IOException ex) {
        }
    }

    @Override
    public void run() {
        signal = true;
        sgui.log("Waiting for clients...");
        try {
            while (signal) {
                clientSocket = serverSocket.accept();
                if (!signal) {
                    break;
                }
                addClient();
            }
        } catch (IOException ex) {
        }
    }

    public void addClient() {
        clients.add(clientSocket);
        clientThread thread = new clientThread(clientSocket, clients, sgui);
        thread.start();
    }

    public void diconnect() {
        signal = false;
        try {
            serverSocket.close();
            clientSocket.close();
        } catch (Exception e) {
        }
        sgui.log("Server stoped...");
    }

}
