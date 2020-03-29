package kz.danke.test.task.service.impl;

import kz.danke.test.task.model.Message;
import kz.danke.test.task.repository.MessageRepository;
import kz.danke.test.task.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
