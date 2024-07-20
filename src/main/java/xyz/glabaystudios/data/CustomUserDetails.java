package xyz.glabaystudios.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.glabaystudios.data.user.UserProfile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Glabay
 * @project GlabayPortfolio
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-11-25
 */
public class CustomUserDetails implements UserDetails {

    private final UserProfile userProfile;

    public CustomUserDetails(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<SimpleGrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        var roleDeveloper = new SimpleGrantedAuthority("ROLE_DEVELOPER");
        var roleJrDeveloper = new SimpleGrantedAuthority("ROLE_JUNIOR_DEVELOPER");
        var roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
        var roleOwner = new SimpleGrantedAuthority("ROLE_OWNER");

        if (Objects.equals("glabay", userProfile.getUsername().toLowerCase())) {
            authorities.add(roleDeveloper);
            authorities.add(roleAdmin);
            authorities.add(roleOwner);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return userProfile.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return userProfile.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
