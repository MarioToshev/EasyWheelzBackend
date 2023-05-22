package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.CarEntity;
import com.example.easywheelz.persistance.entities.ReservationEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
@AllArgsConstructor
public class CarSpecifications {


    public  static Specification<CarEntity> withAvailabilityDateRange(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {
            Join<ReservationEntity, CarEntity> availabilityJoin = root.join("id");

            Predicate startDatePredicate = criteriaBuilder.greaterThanOrEqualTo(availabilityJoin.get("pickUpDate"), startDate);
            Predicate endDatePredicate = criteriaBuilder.lessThanOrEqualTo(availabilityJoin.get("returnDate"), endDate);

            return criteriaBuilder.and(startDatePredicate, endDatePredicate);
        };
    }
    public  static Specification<CarEntity> withBrand(String brand) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("brand"), brand);
    }
}
