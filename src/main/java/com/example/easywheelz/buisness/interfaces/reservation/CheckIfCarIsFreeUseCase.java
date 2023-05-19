package com.example.easywheelz.buisness.interfaces.reservation;

import java.time.LocalDate;

public interface CheckIfCarIsFreeUseCase {

    boolean checkIfCarIsFree(long carId, LocalDate startDate, LocalDate endDate);
}
