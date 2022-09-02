package ALP.KBEWarehouse;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private Queue mainQueue;
    public void send(Serializable serializable) {
        rabbitTemplate.convertAndSend(mainQueue.getName(), serializable);
    }
}
