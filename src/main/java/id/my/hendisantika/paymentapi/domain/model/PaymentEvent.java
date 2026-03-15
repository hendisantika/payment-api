package id.my.hendisantika.paymentapi.domain.model;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : payment-api
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/03/26
 * Time: 08.07
 * To change this template use File | Settings | File Templates.
 */
public class PaymentEvent {
    private String orderId;
    private BigDecimal amount;
    private String customerId;
    private String paymentMethod;
    private String cardNumber;
    private String cardExpiry;
    private String cardCvv;
    private String status;
    private String description;
    private String transactionId;
    private String billingAddress;
    private String shippingAddress;
    private String customerEmail;
    private String customerPhone;
    private String authorizationCode;
    private String eventId;
    private String timestamp;
}
