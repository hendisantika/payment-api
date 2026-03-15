package id.my.hendisantika.paymentapi.domain.port;

import id.my.hendisantika.paymentapi.domain.model.PaymentEvent;

/**
 * Created by IntelliJ IDEA.
 * Project : payment-api
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/03/26
 * Time: 08.09
 * To change this template use File | Settings | File Templates.
 */
public interface CreatePaymentEventUseCase {
    void createPaymentEvent(PaymentEvent paymentEvent);
}
