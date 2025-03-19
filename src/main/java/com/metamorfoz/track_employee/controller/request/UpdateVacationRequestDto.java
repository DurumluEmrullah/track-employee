package com.metamorfoz.track_employee.controller.request;

import com.metamorfoz.track_employee.domain.enums.ApprovalStatus;
import lombok.Data;

@Data
public class UpdateVacationRequestDto {

    private int vacationId;

    private ApprovalStatus status;

}
