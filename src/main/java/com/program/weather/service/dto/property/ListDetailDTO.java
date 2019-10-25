package com.program.weather.service.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * The type List detail dto.
 */
@Data
@NoArgsConstructor
public class ListDetailDTO {

    private Instant dt_txt;
    private MainDTO main;
    private List<WeatherDTO> weather = new ArrayList<WeatherDTO>();
    /**
     * The Clouds.
     */
    CloudsDTO clouds;
    /**
     * The Wind.
     */
    WindDTO wind;

    /**
     * Gets dt txt.
     *
     * @return the dt txt
     */
    @JsonProperty("dt")
    public Instant getDt_txt() {
        return dt_txt;
    }
}
