package id.my.hendisantika.paymentapi.config;

import id.my.hendisantika.paymentapi.adapter.outbound.outbox.OutboxRepository;
import id.my.hendisantika.paymentapi.adapter.outbound.outbox.OutboxService;
import id.my.hendisantika.paymentapi.domain.port.CreatePaymentEventUseCase;
import id.my.hendisantika.paymentapi.domain.port.PaymentEventPublisher;
import id.my.hendisantika.paymentapi.domain.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : payment-api
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/03/26
 * Time: 08.12
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class PaymentConfiguration {

    @Bean
    public CreatePaymentEventUseCase createPaymentEventUseCase(PaymentEventPublisher publisher) {
        return new PaymentService(publisher);
    }

    @Bean
    public PaymentEventPublisher paymentEventPublisher(OutboxRepository outboxRepository) {
        return new OutboxService(outboxRepository);
    }
}
