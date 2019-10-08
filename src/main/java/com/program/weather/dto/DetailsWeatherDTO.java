package com.program.weather.dto;

import com.program.weather.dto.property.CityDTO;
import com.program.weather.dto.property.ListDetailDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DetailsWeatherDTO {

    List<ListDetailDTO> list = new ArrayList<ListDetailDTO>();
    private CityDTO city;

    public List<ListDetailDTO> getList() {
        return list;
    }

    public void setList(List<ListDetailDTO> list) {
        this.list = list;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }
}
