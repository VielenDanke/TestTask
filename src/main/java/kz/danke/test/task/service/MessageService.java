package kz.danke.test.task.service;

import kz.danke.test.task.model.Message;

import java.util.List;

public interface MessageService {

    Message save(Message message);

    List<Message> findAll();

    void deleteAll();
}
