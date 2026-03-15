package id.my.hendisantika.paymentapi.adapter.outbound.outbox;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : payment-api
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/03/26
 * Time: 08.14
 * To change this template use File | Settings | File Templates.
 */
@Service
@EnableScheduling
public class OutboxEventPublisher {
    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OutboxEventPublisher(OutboxRepository outboxRepository,
                                KafkaTemplate<String, String> kafkaTemplate) {
        this.outboxRepository = outboxRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 5000) // runs every 5 seconds
    @Transactional
    public void processOutbox() {
        List<OutboxEntity> events =
                outboxRepository.findByStatus(OutboxStatus.PENDING);

        for (OutboxEntity event : events) {
            try {
                // Publish to Kafka and wait for ack. If successful, mark as PROCESSED. If failed, mark as FAILED.
                kafkaTemplate.send("payment-created", event.getAggregateId() ,event.getPayload()).get(); // wait for Kafka ack (important)

                event.setStatus(OutboxStatus.PROCESSED);

            } catch (Exception e) {

                event.setStatus(OutboxStatus.FAILED);
            }
        }
    }
}
