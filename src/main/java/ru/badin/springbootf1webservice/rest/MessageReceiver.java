package ru.badin.springbootf1webservice.rest;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;
import ru.badin.springbootf1webservice.DTO.CarDto;
import ru.badin.springbootf1webservice.DTO.RacerDto;
import ru.badin.springbootf1webservice.DTO.TeamDto;

@Component
public class MessageReceiver {
    private final Jackson2JsonMessageConverter messageConverter;

    public MessageReceiver(Jackson2JsonMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @RabbitListener(queues = "queueName")
    public void receiveMessage(Message message) {
        Object payload = messageConverter.fromMessage(message);
        if (payload instanceof RacerDto) {
            RacerDto racerDto = (RacerDto) payload;
            System.out.println("\nReceived Racer Message: " + racerDto.toString() + "\n");
        } else if (payload instanceof CarDto) {
            CarDto carDto = (CarDto) payload;
            System.out.println("\nReceived Car Message: " + carDto.toString() + "\n");
        } else if (payload instanceof TeamDto) {
            TeamDto teamDto = (TeamDto) payload;
            System.out.println("\nReceived Team Message: " + teamDto.toString() + "\n");
        }
    }


}
