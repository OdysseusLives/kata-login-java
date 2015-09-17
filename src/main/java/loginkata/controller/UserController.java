package loginkata.controller;

import loginkata.model.User;
import loginkata.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController extends WebMvcConfigurerAdapter {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(User user, Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "form";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String createUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            log.info("Creating user: " + user.toString());
            userRepository.save(user);

            model.addAttribute("newUser", user);
        }

        model.addAttribute("users", userRepository.findAll());
        return "form";
    }

}
