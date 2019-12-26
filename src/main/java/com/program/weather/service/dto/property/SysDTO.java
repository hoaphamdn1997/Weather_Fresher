package com.program.weather.service.dto.property;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Sys dto.
 */
@Data
@NoArgsConstructor
public class SysDTO {
    private String type;
    private String id;
    private String message;
    private String country;
    private String sunrise;
    private String sunset;

}
