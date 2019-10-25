package com.program.weather.service.repository;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Current weather repository.
 */
@Repository
public interface CurrentWeatherRepository extends JpaRepository<WeatherEntity, Long> {
    /**
     * JPA find weaather with weather ID
     *
     * @param id the id
     * @return weather id
     */
    WeatherEntity findByWeatherId(Long id);

    /**
     * JPA find weather folow user oder by date desc
     *
     * @param userEntity the user entity
     * @return List WeatherEntity
     */
    List<WeatherEntity> findAllByUserEntitiesOrderByDateDesc(UserEntity userEntity);

    /**
     * JPA find weather folow user group by city name and oder by date desc
     *
     * @param id the id
     * @return List WeatherEntity
     */
    @Query(value = "SELECT * FROM user_weather a join weatherinfo b on a.weather_id = b.weather_id where a.user_id=?1 and b.date in ( select max(date) FROM user_weather a join weatherinfo b on a.weather_id = b.weather_id where a.user_id=?1 group by b.name_city order by date desc ) group by b.name_city order by date desc", nativeQuery = true)
    List<WeatherEntity> findDateTimeByUserGroupbyDateTimeDest(long id);


    /**
     * JPA find All weather folow user
     *
     * @param userEntity the user entity
     * @return List WeatherEntity
     */
    List<WeatherEntity> findAllByUserEntities(UserEntity userEntity);
}