package xyz.glabaystudios.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.glabaystudios.data.dto.UserCredentialsDto;

/**
 * @author Glabay
 * @project GlabayPortfolio
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-11-25
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class SitemapController {

    @GetMapping({"/", "/home", "/index"})
    public String getHomePage(HttpServletRequest request) {
        return "index";
    }

    @GetMapping("/osby")
    public String getOsbyPage(HttpServletRequest request) {
        return "osby";
    }

    @GetMapping("/runiverse")
    public String getRuniversePage(HttpServletRequest request) {
        return "runiverse";
    }

    @GetMapping("/irpg")
    public String getIrpgPage(HttpServletRequest request) {
        return "irpg";
    }

    @GetMapping("/penguins")
    public String getPenguinPage(HttpServletRequest request) {
        return "penguins";
    }

    @GetMapping("/register")
    public String getRegistrationPage(HttpServletRequest request, Model model) {
        model.addAttribute("newUser", new UserCredentialsDto());
        return "register";
    }
}
