package ru.job4j.forum.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.RegistrationService;

@Controller
public class RegController {

    private final PasswordEncoder passwordEncoder;
    private final RegistrationService registrationService;

    public RegController(PasswordEncoder passwordEncoder, RegistrationService registrationService) {
        this.passwordEncoder = passwordEncoder;
        this.registrationService = registrationService;
    }

    @PostMapping("/reg")
    public String regSave(Model model, @ModelAttribute User user) {
        String toReturn;
        if (!registrationService.existsUserByUsername(user.getUsername())) {
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setAuthority(registrationService.findUserByAuthority("ROLE_USER"));
            registrationService.saveUser(user);
            toReturn = "redirect:/login";
        } else {
            model.addAttribute("errorMessage", "User with this username already exists");
            toReturn = "reg";
        }
        return toReturn;
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
