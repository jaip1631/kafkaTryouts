package kafkatryout.controller;

import kafkatryout.kafka.ProducerManager;
import kafkatryout.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

  @Autowired
  private ProducerManager producerManager;

  @RequestMapping(value = "/send", method = RequestMethod.POST)
  public String sendMessage(@RequestBody final KafkaRequest req) {
    producerManager.send(req.getTopic(), req.getValue());
    return "OK";
  }
}
