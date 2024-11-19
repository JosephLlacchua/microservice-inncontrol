package com.inncontrol.communications.application;

import com.inncontrol.communications.domain.Message;
import com.inncontrol.communications.domain.MessageRepository;
import com.inncontrol.communications.domain.MessageStatus;
import com.inncontrol.communications.dto.MessageDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public Message createMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setSender(messageDto.getSender());
        message.setReceiver(messageDto.getReceiver());
        message.setStatus(MessageStatus.valueOf(messageDto.getStatus()));
        message.setContent(messageDto.getContent());
        return  messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> getMessagesByReceiver(Long receiver) {
        return messageRepository.findByReceiver(receiver);
    }
    public List<Message> getMessagesBySender(Long sender) {
        return messageRepository.findBySender(sender);
    }


    @Transactional
    public Optional<Message> updateMessage(Long id, MessageDto messageDto) {
        return messageRepository.findById(id).map(message -> {
            message.setSender(messageDto.getSender());
            message.setReceiver(messageDto.getReceiver());
            message.setStatus(MessageStatus.valueOf(messageDto.getStatus()));
            message.setContent(messageDto.getContent());
            return messageRepository.save(message);
        });
    }

    @Transactional
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

}
