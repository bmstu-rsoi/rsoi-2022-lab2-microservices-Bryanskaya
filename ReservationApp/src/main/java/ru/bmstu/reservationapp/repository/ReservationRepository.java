package ru.bmstu.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bmstu.reservationapp.model.ReservationEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
    @Query(value = "SELECT * FROM reservations WHERE username = ?1",
            nativeQuery = true)
    List<ReservationEntity> getReservationsByUsername(String username);

    @Query(value = "SELECT * FROM reservations WHERE username = ?1 and reservationUid = ?2",
            nativeQuery = true)
    ReservationEntity getReservationsByUsernameReservationUid(String username, UUID reservationUid);
}
