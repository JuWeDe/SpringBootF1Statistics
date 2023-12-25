package ru.badin.springbootf1webservice.rest;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.DTO.RacerDto;
import ru.badin.springbootf1webservice.Services.RacerServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/racers")
public class RacerController {
    private final RacerServiceImpl racerService;
    private final RabbitTemplate rabbitTemplate;
    private SimpMessagingTemplate messagingTemplate;

    public RacerController(RacerServiceImpl racerService, RabbitTemplate rabbitTemplate, SimpMessagingTemplate messagingTemplate) {
        this.racerService = racerService;
        this.rabbitTemplate = rabbitTemplate;
        this.messagingTemplate = messagingTemplate;
    }


    @GetMapping("/hal")
    public ResponseEntity<Map<String, Object>> getAllRacersWithHAL(@RequestParam(defaultValue = "0") int index, @RequestParam(defaultValue = "10") int count) {
        return ResponseEntity.ok(racerService.getAllRacersHal(index, count));
    }

    @GetMapping
    public ResponseEntity<List<RacerDto>> getAllRacers() {
        List<RacerDto> racers = racerService.getAllRacers();
        return new ResponseEntity<>(racers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RacerDto> createRacer(@RequestBody RacerDto racerDto) {
        racerService.createRacer(racerDto);
        System.out.println("Sending message: " + racerDto.getName());
        messagingTemplate.convertAndSend("/topic/racerCreated", "Новый гонщик создан: " + racerDto.getName());
        System.out.println("Message: " + racerDto.getName() + " sent");
        rabbitTemplate.convertAndSend("queueName", racerDto);
        return new ResponseEntity<>(racerDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RacerDto> getRacerById(@PathVariable Long id) {
        RacerDto racerDto = racerService.getRacerById(id);
        if (racerDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(racerDto);
    }

    @PutMapping("/{id}")
    public void updateRacer(@PathVariable Long id, @RequestBody RacerDto racerDto) {
        racerService.updateRacer(id, racerDto);
    }


    @DeleteMapping("/{id}")
    public void deleteRacer(@PathVariable Long id) {
        racerService.deleteRacer(id);
    }
}