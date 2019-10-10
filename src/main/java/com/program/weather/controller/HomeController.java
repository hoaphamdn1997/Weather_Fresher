package com.program.weather.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.program.weather.common.api.AdminApi;
import com.program.weather.common.api.WeatherApi;
import com.program.weather.common.utils.CommonUtil;
import com.program.weather.common.utils.Constants;
import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.dto.DetailsWeatherDTO;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.DetailsWeatherEntity;

import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherComparator;
import com.program.weather.entity.repository.CurrentWeatherRepository;
import com.program.weather.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/home-weather")
public class HomeController {

    @Autowired
    private WeatherApi weatherApi;

    @Autowired
    AdminApi adminApi;

    @Autowired
    CurrentWeatherRepository currentWeatherRepository;

    @Autowired
    UserService userService;

    /**
     * Search weather City
     *
     * @param name
     * @param modelMap
     * @return
     */
    @GetMapping("/search-city")
    public String home(@RequestParam String name, Model modelMap, Principal principal) {

        UserEntity userEntity = userService.findByUserName(principal.getName());
        List<WeatherEntity> lstWeatherByName;
        // list weather by user
        List<WeatherEntity> lstWeather = weatherApi.findAllByUserEntities(userEntity);
        lstWeatherByName = lstWeather.stream()
                .filter(curweather -> name.equalsIgnoreCase(curweather.getNameCity()))
                .collect(Collectors.toList());
        WeatherEntity weatherEntity = null;
        //If not found City
        try {
            weatherEntity = weatherApi.restJsonData(name);
        } catch (Exception e) {
            modelMap.addAttribute("isFound", true);
        }

        // lay thoi tiet hien tai cung ten Search trong db , Xy ly nut add va update
        WeatherEntity curWeather = lstWeatherByName.stream()
                .filter(weather -> CommonUtil.curTimeToString().equals(CommonUtil.formatToString(weather.getDate())))
                .findAny().orElse(null);
        if (curWeather != null) {
            modelMap.addAttribute("flag", "update");
        } else {
            modelMap.addAttribute("flag", "add");
        }

        modelMap.addAttribute("lstWeatherByUser", lstWeatherByName);
        modelMap.addAttribute("weatherSearch", weatherEntity);
        modelMap.addAttribute("name", name);
        return "forward:/";
    }

    /**
     * Detailts 5 day with City name
     *
     * @param name  Name City
     * @param model
     * @return page Detail
     */
    @GetMapping("/detailts/{name}")
    public String getWeatherDetailts(@PathVariable String name, Model model) {
        List<DetailsWeatherEntity> lstForCast = new ArrayList<DetailsWeatherEntity>();
        DetailsWeatherDTO detailsWeatherDTO = weatherApi.foreCast(name);
        for (int i = 0; i < 40; i = i + 8) {
            lstForCast.add(new DetailsWeatherEntity(
                            Constants.IMG_URL + detailsWeatherDTO.getList().get(i).getWeather().get(0).getIcon() + Constants.PNG,
                            detailsWeatherDTO.getCity().getName(),
                            CommonUtil.toCelsius(Double.parseDouble(detailsWeatherDTO.getList().get(i).getMain().getTemp_min())),
                            CommonUtil.toCelsius(Double.parseDouble(detailsWeatherDTO.getList().get(i).getMain().getTemp_max())),
                            detailsWeatherDTO.getList().get(i).getWind().getSpeed(),
                            detailsWeatherDTO.getList().get(i).getMain().getHumidity(),
                            detailsWeatherDTO.getList().get(i).getMain().getPressure(),
                            detailsWeatherDTO.getList().get(i).getWeather().get(0).getDescription(),
                            detailsWeatherDTO.getList().get(i).getDt_txt()
                    )
            );
        }
        model.addAttribute("cityName", name);
        model.addAttribute("List", lstForCast);
        model.addAttribute("timeToday", Instant.now());
        return "pageDetalts";
    }
    /**
     * Delete weather by idweather
     *
     * @param id
     */
    @GetMapping("/deleteWeather")
    public String deleteUser(@RequestParam Long id) {
        weatherApi.deleteWeather(id);
        return "redirect:/";

    }
    /**
     * Update And Insert Weather City
     *
     * @param action
     * @param name
     * @param principal
     * @return view home
     */
    @GetMapping("/save-weather")
    public String saveWeather(@RequestParam String action, @RequestParam String name, Principal principal) {
        UserEntity userEntity = userService.findByUserName(principal.getName());
        // lst weather by user
        List<WeatherEntity> lstByUser = currentWeatherRepository.findAllByUserEntitiesOrderByDateDesc(userEntity);
        List<WeatherEntity> lstByUserByCity;
        // lay ra ds city theo iduser va nameCity
        lstByUserByCity = lstByUser.stream()
                .filter(weather -> name.trim().toLowerCase().equals(weather.getNameCity().trim().toLowerCase()))
                .collect(Collectors.toList());
        // kiem tra action
        if (action.equals("Add")) {
            // kiem tra truong hop record luu toi da 1 city la 3
            if (lstByUserByCity.size() < 3) {

                insertWeather(name, userEntity);
                return "redirect:/";
            } else {
                lstByUserByCity
                        .sort((WeatherEntity w1, WeatherEntity w2) -> w1.getDate().compareTo(w2.getDate()));
                Optional<WeatherEntity> entitya = lstByUserByCity.stream().findFirst();
                currentWeatherRepository.delete(entitya.get());
                insertWeather(name, userEntity);
                return "redirect:/";
            }
        }
        lstByUserByCity
                .sort((WeatherEntity w1, WeatherEntity w2) -> w2.getDate().compareTo(w1.getDate()));
        Optional<WeatherEntity> entitya = lstByUserByCity.stream().findFirst();
        insertWeather(name, userEntity, entitya.get().getWeatherId());
        return "redirect:/";
    }

    /**
     * Function insert commom for insert and update follow user
     *
     * @param name
     * @param userEntity
     */
    public void insertWeather(String name, UserEntity userEntity) {
        WeatherEntity result = weatherApi.restJsonData(name);
        result.setUserEntities(new HashSet<UserEntity>(Arrays.asList(userEntity)));
        result.setCreateBy(userEntity.getLastName() + " " + userEntity.getFirstName());
        currentWeatherRepository.save(result);
    }

    /**
     * Function insert commom for insert and update with id weather
     *
     * @param name id
     * @param userEntity
     */
    public void insertWeather(String name, UserEntity userEntity, Long id) {
        WeatherEntity result = weatherApi.restJsonData(name);
        result.setUserEntities(new HashSet<UserEntity>(Arrays.asList(userEntity)));
        result.setCreateBy(userEntity.getLastName() + " " + userEntity.getFirstName());
        result.setWeatherId(id);
        currentWeatherRepository.save(result);
    }

}
