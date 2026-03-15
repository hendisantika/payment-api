package id.my.hendisantika.paymentapi.adapter.inbound.http;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : payment-api
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/03/26
 * Time: 08.05
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payments API", description = "Operations to recieve payments")
public class PaymentResource {

    private final CreatePaymentEventUseCase createPaymentEventUseCase;
    Logger logger = Logger.getLogger(PaymentResource.class.getName());

    public PaymentResource(CreatePaymentEventUseCase createPaymentEventUseCase) {

        this.createPaymentEventUseCase = createPaymentEventUseCase;
    }

}
