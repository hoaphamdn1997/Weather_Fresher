package com.program.weather.controller;


import com.program.weather.common.api.AdminApi;
import com.program.weather.common.api.WeatherApi;
import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.repository.CurrentWeatherRepository;
import com.program.weather.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherApi weatherApi;

    @Autowired
    AdminApi adminApi;

    @Autowired
    CurrentWeatherRepository currentWeatherRepository;

    /**
     * Check size List  --> show more
     * @param principal
     * @return
     */

    @RequestMapping(value = {"/process-size"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<WeatherSizeApiDTO>> processSizeByCity(Principal principal) {

        List<WeatherSizeApiDTO> sizeWeatherGroup = new ArrayList<>();

        // Sau khi user login thanh cong se co principal
        UserEntity userEntity = userService.findByUserName(principal.getName());
        Long userid = userEntity.getUserId();

        List<WeatherEntity> listWeather = weatherApi.findWeatherUserandDate(userid);
        List<WeatherEntity> weatherEntities = weatherApi.findAllByUserEntities(userEntity);


        listWeather.forEach(w -> {
            WeatherSizeApiDTO weatherSizeApiDTO = new WeatherSizeApiDTO();
            weatherSizeApiDTO.setCity(w.getNameCity());
            weatherSizeApiDTO.setSize(weatherEntities.stream().filter(i -> i.getNameCity().equalsIgnoreCase(w.getNameCity())).collect(Collectors.toList()).size());

            sizeWeatherGroup.add(weatherSizeApiDTO);
        });

        return ResponseEntity.ok(sizeWeatherGroup);
    }

    @RequestMapping(value = {"/home", "/"})
    public String hello(Model model, Principal principal) {

        // Sau khi user login thanh cong se co principal
        UserEntity userEntity = userService.findByUserName(principal.getName());
        Long userid = userEntity.getUserId();

        List<WeatherEntity> listWeather = weatherApi.findWeatherUserandDate(userid);
        //chua oder by  kia cha =)))
        List<WeatherEntity> weatherEntities = weatherApi.findAllByUserEntities(userEntity);

        model.addAttribute("ListW", listWeather);
        model.addAttribute("listShowMore", weatherEntities);

        return "pageHome";

    }

    /**
     * page Login
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "pageLogin";
    }

    /**
     * Login Action
     *
     * @return
     */
    @PostMapping(value = {"/loginA"})
    public String loginA() {

        return "redirect:/";
    }

    /**
     * Log out
     *
     * @return
     */
    @GetMapping("/logout")
    public String logout() {
        return "pageLogin";
    }

    /**
     * Page Registration
     *
     * @param model
     * @return
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userDTO", new UserEntity());
        return "pageRegistration";

    }

    /**
     * Page Registration Action
     *
     * @param userEntity
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/registration")
    public String regisAction(@Valid @ModelAttribute("userDTO") UserEntity userEntity, BindingResult bindingResult, Model model) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + userEntity.getUserName());

        UserEntity userName = userService.findByUserName(userEntity.getUserName());
        UserEntity email = userService.findByUserEmail(userEntity.getEmail());

        if (null != email) {
            bindingResult.rejectValue("email", "error.email", "Email Be Use");//truong truyen vao+ ten + message
        }
        if (userName != null) {
            bindingResult.rejectValue("userName", "error.userName", "User name be use");
        }

        if (bindingResult.hasErrors()) {
            return "pageRegistration";
        } else {
            userService.saveUser(userEntity);
            model.addAttribute("successMessage", "Successfully ^^ ");
            model.addAttribute("userDTO", new UserEntity());
            return "pageLogin";
        }


    }
    @RequestMapping(value = "/block")
    public String block(Model model)
    {
        return "pageBlock";
    }
}

class WeatherSizeApiDTO {
    private String city;
    private Integer size;

    public void setCity(String city) {
        this.city = city;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public String getCity() {
        return city;
    }
}