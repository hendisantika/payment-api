package id.my.hendisantika.paymentapi.adapter.outbound.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.paymentapi.domain.port.PaymentEventPublisher;

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
}
