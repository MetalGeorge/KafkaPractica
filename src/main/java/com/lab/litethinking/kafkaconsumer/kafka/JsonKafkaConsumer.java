package com.lab.litethinking.kafkaconsumer.kafka;

import com.lab.litethinking.kafkaconsumer.dto.Cuenta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Service
public class JsonKafkaConsumer {

    @Value("${api.endpoint.report}")
    private String apiUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.example1.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload byte[] data){
        try{
            Cuenta cuenta = (Cuenta) convertBytesToObject(data);
            System.out.println("Mensaje recibido => " + cuenta.getNumeroCuenta());


        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object convertBytesToObject(byte[] data) throws IOException, ClassNotFoundException{
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        }
    }

}
