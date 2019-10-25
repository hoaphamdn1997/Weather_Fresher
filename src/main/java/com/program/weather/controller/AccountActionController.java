package com.program.weather.controller;


import com.program.weather.service.WeatherService;
import com.program.weather.service.dto.UserDTO;
import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.service.repository.CurrentWeatherRepository;
import com.program.weather.service.UserService;
import com.program.weather.service.mapper.UserConverter;
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

/**
 * The type Account action controller.
 */
@Controller
public class AccountActionController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    /**
     * The Current weather repository.
     */
    @Autowired
    CurrentWeatherRepository currentWeatherRepository;

    @Autowired
    private UserConverter userConverter;

    /**
     * Check size List  --> show more
     * if size > 1 show button show more in page
     * <p>
     * Annotation @RequestMapping is used to map requests to the class or method that handles the request.
     *
     * @param principal login have principal
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

    /**
     * "/home", "/" that is URL go to page Home
     *
     * @param model     the Model to progress add attribute for web
     * @param principal when user login have princical
     * @return pageHome string
     */
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
     * /login-->URL page Login
     *
     * @return page login
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "pageLogin";
    }

    /**
     * /loginA -->URL Login Action
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
     * @param model the model
     * @return PageRegistration string
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "pageRegistration";
    }

    /**
     * /registration Url--> Page Registration Action
     *
     * @param userDTO       the user dto
     * @param bindingResult //"BindingResult". This is Spring's object holding the result of validation and binding and containing the possible errors//“BindingResult”. Đây là đối tượng của Spring giữ kết quả của việc xác nhận và ràng buộc và chứa các lỗi có thể đã xảy ra
     * @param model         the model
     * @return pageLogin //when success --> pageLogin
     * @Valid: this is an Annotation in spring mvc used to bind the object or Parameters are bound to perform form validation.
     */
    @PostMapping("/registration")
    public String regisAction(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult, Model model) {
        //use jpa find email on database
        UserEntity email = userService.findByUserEmail(userDTO.getEmail());
        //Compare entered emails With email data
        if (null != email) {
            bindingResult.rejectValue("email", "error.email", "Email is already used please enter another email");//truong truyen vao+ ten + message
        }
        //use jpa find username on database
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
     * @param model the model
     * @return pageBlock string
     */
    @RequestMapping(value = "/block")
    public String block(Model model) {
        return "pageBlock";
    }
}
