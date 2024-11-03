package com.inncontrol.communications.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findByReceiver(Long receiver);
    List<Message> findBySender(Long sender);
    List<Message> findByReceiverAndStatus(Long receiver, MessageStatus status);
    List<Message> findBySenderAndStatus(Long sender, MessageStatus status);
    List<Message> findByReceiverAndSender(Long receiver, Long sender);
    List<Message> findByReceiverAndSenderAndStatus(Long receiver, Long sender, MessageStatus status);

}
