package ru.sber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.model.Priority;
import ru.sber.model.Task;
import ru.sber.model.User;

@Controller
public class IndexController {
    @GetMapping("/index")
    public ModelAndView showIndex(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        User user = new User();
        modelAndView.addObject(user);
        return modelAndView;
    }
}
