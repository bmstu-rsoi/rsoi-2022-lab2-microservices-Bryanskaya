package ru.bmstu.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bmstu.reservationapp.model.HotelsEntity;

@Repository
public interface HotelsRepository extends JpaRepository<HotelsEntity, Integer> {
}
