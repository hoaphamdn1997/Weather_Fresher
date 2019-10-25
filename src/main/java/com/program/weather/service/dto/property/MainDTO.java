package com.program.weather.service.dto.property;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Main dto.
 */
@Data
@NoArgsConstructor
public class MainDTO {

    private int temp;
    private String pressure;
    private String humidity;
    private String temp_min;
    private String temp_max;


}
