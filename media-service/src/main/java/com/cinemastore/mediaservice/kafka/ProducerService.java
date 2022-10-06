package com.cinemastore.mediaservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceSavingSuccess(String id) {
        System.out.println("Producing the message about saving image with id: " + id);
        kafkaTemplate.send("message", "Image successfully saved " + id);
    }
}
