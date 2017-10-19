/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.client.model;

/**
 *
 * @author orxan
 */
public class ChatMessage {

    public static String message;

    public ChatMessage(String message) {
        ChatMessage.message = message;
    }

    public String getMessage() {
        return message;
    }

}
