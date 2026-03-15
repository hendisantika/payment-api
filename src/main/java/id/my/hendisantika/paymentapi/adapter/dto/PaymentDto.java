package id.my.hendisantika.paymentapi.adapter.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : payment-api
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/03/26
 * Time: 08.04
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class PaymentDto {

    @NotBlank(message = "Order ID is required")
    private String orderId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Digits(integer = 12, fraction = 2, message = "Invalid amount format")
    private BigDecimal amount;

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotBlank(message = "Payment method is required")
    @Pattern(regexp = "CREDIT_CARD|DEBIT_CARD|PIX|BOLETO",
            message = "Invalid payment method")
    private String paymentMethod;

    @Size(min = 13, max = 19, message = "Invalid card number")
    @Pattern(regexp = "\\d+", message = "Card number must contain only digits")
    private String cardNumber;

    @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9]{2}",
            message = "Card expiry must be in MM/YY format")
    private String cardExpiry;

    @Pattern(regexp = "\\d{3,4}", message = "Invalid CVV")
    private String cardCvv;

    @Pattern(regexp = "PENDING|AUTHORIZED|DECLINED|CANCELLED",
            message = "Invalid status")
    private String status;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @Size(max = 50, message = "Transaction ID too long")
    private String transactionId;

    @NotBlank(message = "Billing address is required")
    private String billingAddress;

    private String shippingAddress;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String customerEmail;

    @Pattern(regexp = "\\+?[0-9]{10,15}",
            message = "Invalid phone number")
    private String customerPhone;

    @Size(max = 20, message = "Authorization code too long")
    private String authorizationCode;
}
