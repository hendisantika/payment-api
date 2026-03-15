package id.my.hendisantika.paymentapi.adapter.outbound.outbox;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : payment-api
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 15/03/26
 * Time: 08.15
 * To change this template use File | Settings | File Templates.
 */
public interface OutboxRepository extends JpaRepository<OutboxEntity, Long> {
    List<OutboxEntity> findByStatus(OutboxStatus status);
}
