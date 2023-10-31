package ru.badin.springbootf1webservice.rest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.badin.springbootf1webservice.DTO.CarDto;
import ru.badin.springbootf1webservice.DTO.RacerDto;
import ru.badin.springbootf1webservice.DTO.TeamDto;

@Component
public class MessageReceiver {


    @RabbitListener(queues = "queueName")
    public void receiveRacerMessage(RacerDto racerDTO) {
        System.out.println("\nReceived Racer Message: " + racerDTO.toString() + "\n");
    }

    @RabbitListener(queues = "queueName")
    public void receiveCarMessage(CarDto carDTO) {
        System.out.println("\nReceived Car Message: " + carDTO.toString() + "\n");
    }

    @RabbitListener(queues = "queueName")
    public void receiveTeamMessage(TeamDto teamDTO) {
        System.out.println("\nReceived Team Message: " + teamDTO.toString() + "\n");
    }
}
