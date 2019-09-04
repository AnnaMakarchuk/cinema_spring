package org.study.cinema.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.entity.UserRole;
import org.study.cinema.entity.enums.Gender;

@Controller
public class RegisteredUserController {

    @GetMapping("/cabinet")
    public String showUserCabinet(Model model) {
        UserRole userRole = UserRole.builder()
                .userRole("administrator")
                .build();
        RegisteredUserDto user = RegisteredUserDto.builder()
                .userId(1)
                .userName("Alisa")
                .userSurname("Alisenko")
                .gender(Gender.FEMALE)
                .userRole(userRole)
                .userLogin("ali")
                .userEMailAddress("a@i.ua")
                .build();


        model.addAttribute("user", user);
        return "cabinet";
    }

    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

}
