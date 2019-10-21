package com.program.weather.controller;


import com.program.weather.service.WeatherService;
import com.program.weather.service.dto.UserDTO;
import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.repository.CurrentWeatherRepository;
import com.program.weather.service.UserService;
import com.program.weather.service.converter.UserConverter;
import com.program.weather.service.dto.property.WeatherSizeApiDTO;
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
public class AccountActionController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    CurrentWeatherRepository currentWeatherRepository;

    @Autowired
    private UserConverter userConverter;

    /**
     * Check size List  --> show more
     *
     * @param principal
     * @return size list weather
     */

    @RequestMapping(value = {"/process-size"}, method = RequestMethod.GET)
    @ResponseBody
    //ResponseBody will help you convert the returned object into json string because you use any library to convert it.--> ResponseBody sẽ giúp bạn convert đối tượng trả về thành chuỗi json thì vì bạn dùng 1 thư viện nào nó để convert.
    public ResponseEntity<List<WeatherSizeApiDTO>> processSizeByCity(Principal principal) {
        //list Size weather group was declared
        List<WeatherSizeApiDTO> sizeWeatherGroup = new ArrayList<>();
        // After the user has logged in, the principal will have the principal
        UserEntity userEntity = userService.findByUserName(principal.getName());
        Long userid = userEntity.getUserId();
        //List Weather user group by Name City and Order by Date
        List<WeatherEntity> listWeather = weatherService.findWeatherByUserAndDate(userid);
        //List Weather user Oder by Date
        List<WeatherEntity> weatherEntities = weatherService.findAllByUserEntitiesOrderByDateDesc(userEntity);
        //Attach size to city
        listWeather.forEach(w -> {
            WeatherSizeApiDTO weatherSizeApiDTO = new WeatherSizeApiDTO();
            weatherSizeApiDTO.setCity(w.getNameCity());
            //The list of city matches to the list group
            weatherSizeApiDTO.setSize(weatherEntities.stream().filter(i -> i.getNameCity().equalsIgnoreCase(w.getNameCity())).collect(Collectors.toList()).size());
            sizeWeatherGroup.add(weatherSizeApiDTO);
        });
        return ResponseEntity.ok(sizeWeatherGroup);//responseEntity is the object that returns it-->responseEntity là đối tượng trả ra cho nó
    }

    @RequestMapping(value = {"/home", "/"})
    public String hello(Model model, Principal principal) {

        // After the user has logged in, the principal will have the principal
        UserEntity userEntity = userService.findByUserName(principal.getName());
        Long userid = userEntity.getUserId();
        //List weather user groud by name City and Order by Date
        List<WeatherEntity> listWeather = weatherService.findWeatherByUserAndDate(userid);
        //List Weather user Order by Date
        List<WeatherEntity> weatherEntities = weatherService.findAllByUserEntitiesOrderByDateDesc(userEntity);
        //Add values -> Attribute
        model.addAttribute("ListW", listWeather);
        model.addAttribute("listShowMore", weatherEntities);

        return "pageHome";

    }

    /**
     * page Login
     *
     * @return page login
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "pageLogin";
    }

    /**
     * Login Action
     * When User login success -> pageHome
     *
     * @return Page Home
     */
    @PostMapping(value = {"/loginA"})
    public String loginA() {
        return "redirect:/";
    }

    /**
     * Log out
     * If User Login fail --> page Login
     *
     * @return Page Login
     */
    @GetMapping("/logout")
    public String logout() {
        return "pageLogin";
    }

    /**
     * Page Registration
     * Go to the Page Registration
     *
     * @param model
     * @return PageRegistration
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "pageRegistration";

    }

    /**
     * Page Registration Action
     *
     * @param userDTO
     * @param bindingResult
     * @param model
     * @return pageLogin
     */
    @PostMapping("/registration")
    public String regisAction(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult, Model model) {
        //Get the Email
        UserEntity email = userService.findByUserEmail(userDTO.getEmail());
        //Compare entered emails With email data
        if (null != email) {
            bindingResult.rejectValue("email", "error.email", "Email is already used please enter another email");//truong truyen vao+ ten + message
        }
        //Get the Username
        UserEntity userName = userService.findByUserName(userDTO.getUserName());
        //Compare entered Username With Username data
        if (null != userName) {
            bindingResult.rejectValue("userName", "error.userName", "Username is already used ,please enter another Username");
        }
        //if the error sends a message validates ->pageRegistration
        if (bindingResult.hasErrors()) {
            return "pageRegistration";
        } else {
            //save User
            userService.saveUser(userConverter.convertUserEntity(userDTO));
            //Add message Succsess --> Attribute
            model.addAttribute("successMessage", "Successfully ^^ ");
            //come to pagaLogin
            return "pageLogin";
        }


    }

    /**
     * Come to pageBlock
     *
     * @param model
     * @return pageBlock
     */
    @RequestMapping(value = "/block")
    public String block(Model model) {
        return "pageBlock";
    }
}
