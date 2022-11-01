package ru.bmstu.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bmstu.reservationapp.model.HotelsEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelsEntity, Integer> {
    @Query(value = "SELECT * FROM hotels WHERE id = ?1",
        nativeQuery = true)
    HotelsEntity getHotelById(Integer hotelId);
}
