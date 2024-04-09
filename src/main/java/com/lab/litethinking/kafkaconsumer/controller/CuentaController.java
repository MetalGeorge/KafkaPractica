package com.lab.litethinking.kafkaconsumer.controller;

import com.lab.litethinking.kafkaconsumer.dto.Cuenta;
import com.lab.litethinking.kafkaconsumer.kafka.JsonKafkaProducer;
import com.lab.litethinking.kafkaconsumer.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/cuenta")
public class CuentaController {

    private JsonKafkaProducer jsonKafkaProducer;

    private KafkaProducer kafkaProducer;


    public CuentaController(JsonKafkaProducer jsonKafkaProducer, KafkaProducer kafkaProducer) {
        this.jsonKafkaProducer = jsonKafkaProducer;
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/crearcuenta")
    public ResponseEntity<String> publish_json(@RequestBody Cuenta cuenta){;
        jsonKafkaProducer.sendMessage(cuenta);
        return ResponseEntity.ok("Message cuenta sent to the topic");
    }

    @GetMapping("/obternercuentas")
    public ResponseEntity<String> publish_json(@RequestParam("numeroCuenta") String numeroCuenta){;
        kafkaProducer.sendMessage(numeroCuenta);
        return ResponseEntity.ok("numeroCuenta Enviado");
    }


}
