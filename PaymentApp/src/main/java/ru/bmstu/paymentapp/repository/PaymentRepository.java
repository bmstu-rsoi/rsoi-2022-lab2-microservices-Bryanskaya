package ru.bmstu.paymentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bmstu.paymentapp.model.PaymentEntity;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
    @Query(value = "SELECT * FROM payments WHERE payment_uid = ?1")
    PaymentEntity getPaymentByUid(UUID paymentUid);
}
