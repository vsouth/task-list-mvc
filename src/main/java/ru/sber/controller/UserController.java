package ru.sber.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.model.User;
import ru.sber.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/login")
    public String userAuth(@ModelAttribute @Valid User user, HttpSession session, HttpServletRequest request) {
        log.info("#auth user: " + user);
        Optional<User> existingUser = userRepository.getUserByLogin(user.getLogin());
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            session.setAttribute("user-id", existingUser.get().getId());
            return "redirect:/tasks/my";
        }
        return "redirect:/index";
    }

    @PostMapping("/register")
    public String create(@ModelAttribute @Valid User user, HttpSession session, HttpServletRequest request) {
        log.info("#register user: " + user);
        userRepository.createUser(user);
        session.setAttribute("user-id", user.getId());
        return "redirect:/tasks/my";
    }

    @GetMapping("/logout")
    public String userAuth(HttpSession session, HttpServletRequest request) {
        Object userId = request.getSession().getAttribute("user-id");
        log.info("#logout user: " + userRepository.getUserById((Integer) userId));
        request.getSession().invalidate();
        return "redirect:/index";
    }
}
