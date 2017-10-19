/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.client.controller;

import com.chat.client.model.ChatMessage;
import com.chat.client.view.ClientGUI;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author orxan
 */
public class Client {

    public Socket clientSocket;
    public String address;
    public int port;
    public DataOutputStream output;
    public DataInputStream input;
    public ClientGUI cgui;

    public Client(String address, int port, ClientGUI cgui) {
        this.address = address;
        this.port = port;
        this.cgui = cgui;
    }

    public boolean connect() {
        try {
            clientSocket = new Socket(address, port);
        } catch (IOException ex) {
            cgui.log("Unable connect to server");
            return false;
        }

        cgui.log("Hello, " + chatLogin.user + " welcome to chat room.");

        try {
            input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            return false;
        }

        try {
            output.writeUTF(chatLogin.user + " connected to chat room.");
        } catch (IOException ex) {
            return false;
        }

        new receivedMessages(input, cgui).start();
        return true;
    }

    public void disconnect() {
        try {
            cgui.log("Client disconnected...");
            output.writeUTF(chatLogin.user + " leave out the chat room.");
            output.flush();
            try {
                output.close();
                input.close();
                clientSocket.close();
            } catch (IOException ex) {
                ex.getMessage();
            }
        } catch (IOException ex) {
        }
    }

    public void sendMessage(ChatMessage msg) {
        String message;
        try {
            message = chatLogin.user + " >> " + msg.getMessage();
            output.writeUTF(message);
            output.flush();
        } catch (IOException ex) {
        }
    }

}
