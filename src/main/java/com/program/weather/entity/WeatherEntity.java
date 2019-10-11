package com.program.weather.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "weatherinfo")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_id")
    private Long weatherId;

    @Column(name = "icon")
    private String icon;

    @Column(name = "name_city")
    private String nameCity;

    @Column(name = "id_city")
    private String idCity;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "temp")
    private int temp;

    @Column(name = "description")
    private String description;

    @Column(name = "wind")
    private String wind;

    @Column(name = "humidity")
    private String humidity;

    @Column(name = "pressure")
    private String pressure;

    @Column(name = "create_by")
    private String createBy;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_weather", joinColumns = @JoinColumn(name = "weather_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> userEntities;


}
