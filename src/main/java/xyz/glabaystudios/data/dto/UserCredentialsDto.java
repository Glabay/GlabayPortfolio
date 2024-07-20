package xyz.glabaystudios.data.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Glabay
 * @project GlabayPortfolio
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-11-25
 */
@Getter
@Setter
public class UserCredentialsDto {

    public String username;
    public String email;
    public String password;
    public String rePassword;
}
