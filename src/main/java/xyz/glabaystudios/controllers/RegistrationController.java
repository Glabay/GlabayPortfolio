package xyz.glabaystudios.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.glabaystudios.data.dto.UserCredentialsDto;
import xyz.glabaystudios.data.user.UserProfile;
import xyz.glabaystudios.services.RegistrationService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Glabay
 * @project GlabayPortfolio
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-11-26
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/newuser")
    public String registerNewUser(@ModelAttribute("newUser") UserCredentialsDto userCredentials) {
        if (Objects.isNull(userCredentials))
            return "redirect:/register?noCreds";
        if (registrationService.userExists(userCredentials))
            return "redirect:/register?userExists";
        if (!Objects.equals(userCredentials.getPassword(), userCredentials.getRePassword()))
            return "redirect:/register?passMissMatch";
        var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        var model = new UserProfile();
            model.setUsername(userCredentials.getUsername());
            model.setEmail(userCredentials.getEmail());
            model.setEncryptedPassword(new BCryptPasswordEncoder().encode(userCredentials.getPassword()));
            model.setCreatedOn(dtf.format(LocalDateTime.now()));
            model.setLastUpdated(model.getCreatedOn());
        registrationService.save(model);
        return "redirect:/index";
    }
}
