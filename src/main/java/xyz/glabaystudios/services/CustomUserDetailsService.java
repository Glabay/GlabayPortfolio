package xyz.glabaystudios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.glabaystudios.data.CustomUserDetails;
import xyz.glabaystudios.data.user.UserProfileRepository;

/**
 * @author Glabay
 * @project GlabayPortfolio
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-11-25
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var profile = userProfileRepository.findByUsernameIgnoreCase(username);
        if (profile.isPresent())
            return new CustomUserDetails(profile.get());
        throw new UsernameNotFoundException("User not found.");
    }
}
