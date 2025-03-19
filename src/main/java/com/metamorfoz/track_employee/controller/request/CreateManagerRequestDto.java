package com.metamorfoz.track_employee.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateManagerRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;
}
