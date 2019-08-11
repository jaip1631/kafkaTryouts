package kafkatryout.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class ProducerManager {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void send(String topic, String data) {
    ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);

    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
      @Override
      public void onFailure(Throwable ex) {
        log.error("Failed to send kafka message [{}] to queue [{}]. Exception [{}]", data, topic, ex);
      }

      @Override
      public void onSuccess(SendResult<String, String> result) {
        log.info("Successfully sent message [{}] to queue [{}]. ProducerRecord [{}]", data, topic, result);
      }
    });
  }
}
