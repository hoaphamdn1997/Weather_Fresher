package com.program.weather.service.dto;

import com.program.weather.service.dto.property.CityDTO;
import com.program.weather.service.dto.property.ListDetailDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Details weather dto.
 */
@Data
@NoArgsConstructor
public class DetailsWeatherDTO {

    /**
     * The List.
     */
    List<ListDetailDTO> list = new ArrayList<ListDetailDTO>();

    private CityDTO city;

}
