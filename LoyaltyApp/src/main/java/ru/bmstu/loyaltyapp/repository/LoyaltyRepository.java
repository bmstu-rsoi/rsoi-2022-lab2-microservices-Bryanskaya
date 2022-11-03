package ru.bmstu.loyaltyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bmstu.loyaltyapp.model.LoyaltyEntity;

public interface LoyaltyRepository extends JpaRepository<LoyaltyEntity, Integer> {
    @Query(value = "SELECT status FROM loyalty WHERE username = ?1",
        nativeQuery = true)
    String getStatusByUsername(String username);
}
