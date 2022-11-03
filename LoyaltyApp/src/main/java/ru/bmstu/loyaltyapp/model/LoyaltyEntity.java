package ru.bmstu.loyaltyapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import ru.bmstu.loyaltyapp.dto.enums.StatusEnum;

import javax.persistence.*;

@Data
@Entity
@Table(name = "loyalties")
@Accessors(chain = true)
@NoArgsConstructor
public class LoyaltyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "username", unique = true)
    private String username;

    @NonNull
    @Column(name = "reservation_count")
    private Integer reservation_count = 0;

    @NonNull
    @Column(name = "status")
    private StatusEnum status = StatusEnum.BRONZE;

    @NonNull
    @Column(name = "discount")
    private Integer discount;
}
