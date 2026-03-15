# Payment API

[![Java CI with Maven](https://github.com/hendisantika/payment-api/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/payment-api/actions/workflows/maven.yml)

An API Gateway that receives payment requests and publishes `PaymentCreated` events to a Kafka topic using the **Transactional Outbox Pattern**.

## Architecture

This project follows **Hexagonal Architecture** (Ports & Adapters):

```
┌─────────────────────────────────────────────────┐
│                  Adapter Layer                  │
│  ┌──────────────┐          ┌──────────────────┐ │
│  │  REST API    │          │  Outbox + Kafka   │ │
│  │  (Inbound)   │          │  (Outbound)       │ │
│  └──────┬───────┘          └────────▲─────────┘ │
│         │                           │           │
│  ┌──────▼───────────────────────────┴─────────┐ │
│  │             Domain Layer                    │ │
│  │  PaymentService ─► PaymentEventPublisher    │ │
│  └─────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────┘
```

### Transactional Outbox Pattern

1. Payment request received via REST API
2. `PaymentEvent` saved to the outbox table (PENDING) within a transaction
3. A scheduled job polls the outbox every 5 seconds
4. PENDING events are published to the Kafka topic `payment-created`
5. Status updated to PROCESSED or FAILED

This guarantees no event loss and transactional consistency between the database and Kafka.

## Tech Stack

- **Java 25** / **Spring Boot 4.0.3**
- **Apache Kafka** (KRaft mode, no Zookeeper)
- **H2 Database** (in-memory)
- **Spring Data JPA** / Hibernate
- **SpringDoc OpenAPI 3.0.2** (Swagger UI)
- **Lombok**
- **Maven**

## Getting Started

### Prerequisites

- Java 25+
- Maven 3.9+
- Docker & Docker Compose

### Run

```bash
# Start Kafka via Docker Compose
docker compose up -d

# Build and run
./mvnw spring-boot:run
```

The application starts on `http://localhost:8080`.

### API Documentation

Swagger UI is available at: `http://localhost:8080/swagger-ui.html`

### H2 Console

Available at `http://localhost:8080/h2-console` with:

| Property | Value |
|----------|-------|
| JDBC URL | `jdbc:h2:mem:payment_db` |
| Username | `yu71` |
| Password | `53cret` |

## API Usage

### Create Payment

```bash
POST /api/payments
Content-Type: application/json

{
  "orderId": "ORD-001",
  "amount": 150.00,
  "customerId": "CUST-001",
  "paymentMethod": "CREDIT_CARD",
  "cardNumber": "4111111111111111",
  "cardExpiry": "12/26",
  "cardCvv": "123",
  "status": "PENDING",
  "billingAddress": "123 Main St",
  "customerEmail": "customer@example.com",
  "description": "Order payment"
}
```

### Supported Payment Methods

`CREDIT_CARD` | `DEBIT_CARD` | `PIX` | `BOLETO`

## Project Structure

```
src/main/java/id/my/hendisantika/paymentapi/
├── adapter/
│   ├── dto/                  # PaymentDto with validation
│   ├── inbound/http/         # REST controller
│   └── outbound/outbox/      # Outbox entity, repository, service, publisher
├── config/                   # Spring bean configuration
└── domain/
    ├── model/                # PaymentEvent
    ├── port/                 # Use case & publisher interfaces
    └── service/              # PaymentService
```
