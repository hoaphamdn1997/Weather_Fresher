package com.program.weather.service.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ListDetailDTO {

    private Instant dt_txt;
    private MainDTO main;
    private List<WeatherDTO> weather = new ArrayList<WeatherDTO>();
    CloudsDTO clouds;
    WindDTO wind;

    @JsonProperty("dt")
    public Instant getDt_txt() {
        return dt_txt;
    }
}
