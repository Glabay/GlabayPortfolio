package xyz.glabaystudios.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.glabaystudios.data.dto.UserCredentialsDto;
import xyz.glabaystudios.data.user.UserProfile;
import xyz.glabaystudios.data.user.UserProfileRepository;

/**
 * @author Glabay
 * @project GlabayPortfolio
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-11-26
 */
@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserProfileRepository userProfileRepository;

    public boolean userExists(UserCredentialsDto dto) {
        return userProfileRepository.findByUsernameIgnoreCase(dto.getUsername()).isPresent();
    }

    public void save(UserProfile model) {
        userProfileRepository.save(model);
    }
}
