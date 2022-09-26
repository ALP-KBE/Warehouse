package ALP.KBEWarehouse.RabbitMQ;

import ALP.KBEWarehouse.ComponentService;
import ALP.RabbitMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "warehouse-queue", id = "listener")
public class RabbitMQReceiver {
    @Autowired
    ComponentService componentService;

    @RabbitHandler
    public void receiver(RabbitMessage message) {
        componentService.handle(message);
    }
}
