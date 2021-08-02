package com.jojo.zhuhaibusclock.service;

/**
 * @author JoJoWu
 */
public interface MessageService {
    void pushMessage(String key, String body);

    void pushMessage(String key, String title, String body);

    void pushMessage(String body);
}
