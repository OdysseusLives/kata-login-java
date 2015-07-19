package loginkata.controllers;

import loginkata.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String showForm(User user) {
        return "form";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String checkUserInfo(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        log.info(user.toString());
        return "redirect:/results";
    }

}