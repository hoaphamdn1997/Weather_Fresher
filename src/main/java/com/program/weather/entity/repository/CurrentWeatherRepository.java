package com.program.weather.entity.repository;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public interface CurrentWeatherRepository extends JpaRepository<WeatherEntity, Long>{
	WeatherEntity findByWeatherId(Long id);
	WeatherEntity findByNameCity(String nameCity);
	Boolean existsByNameCity(String nameCity);
	List<WeatherEntity> findAllByUserEntitiesOrderByDateDesc(UserEntity userEntity);
	List<WeatherEntity> findAllByDate(Timestamp ts);
	Long countAllByNameCity(String nameCity);

	@Query(value = "SELECT * FROM weather.user_weather a join weather.weatherinfo b on a.weather_id = b.weather_id where a.user_id=?1 and b.date in ( select max(date) FROM weather.user_weather a join weather.weatherinfo b on a.weather_id = b.weather_id where a.user_id=?1 group by b.name_city order by date desc ) group by b.name_city order by date desc", nativeQuery = true)
	List<WeatherEntity>findDateTimeByUserGroupbyDateTimeDest(long id);
	
	
}
