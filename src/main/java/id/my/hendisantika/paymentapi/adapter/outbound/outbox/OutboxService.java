package id.my.hendisantika.paymentapi.adapter.outbound.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.paymentapi.domain.model.PaymentEvent;
import id.my.hendisantika.paymentapi.domain.port.PaymentEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : payment-api
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/03/26
 * Time: 08.16
 * To change this template use File | Settings | File Templates.
 */
public class OutboxService implements PaymentEventPublisher {
    ObjectMapper objectMapper = new ObjectMapper();

    OutboxRepository outboxRepository;

    public OutboxService(OutboxRepository outboxRepository) {
        this.outboxRepository = outboxRepository;
    }

    @Override
    @Transactional
    public void savePaymentEvent(PaymentEvent event) {
        // Convert PaymentEvent to OutboxEntity and save to DB with PENDING status
        try {
            OutboxEntity entity = new OutboxEntity();
            entity.setAggregateId(event.getEventId());
            entity.setAggregateType("Payment");
            entity.setEventType("PaymentCreated");
            entity.setPayload(objectMapper.writeValueAsString(event));
            entity.setStatus(OutboxStatus.PENDING);
            entity.setCreatedAt(LocalDateTime.now());

            outboxRepository.save(entity);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize PaymentEvent", e);
        }
    }
}
