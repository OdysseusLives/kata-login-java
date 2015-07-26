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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController extends WebMvcConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String showForm(User user, Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "form";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String checkUserInfo(@Valid User user, BindingResult bindingResult, Model model) {
        if(userRepository.exists(user.getUsername())) {
            bindingResult.rejectValue("username", "error.username", "username already exists");
        }

        if (bindingResult.hasErrors()) {
            return "form";
        }

        log.info("Creating user: " + user.toString());
        userRepository.save(user);

        return "redirect:/results";
    }

}