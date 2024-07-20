package xyz.glabaystudios.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Glabay
 * @project GlabayPortfolio
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-11-24
 */
@Getter
@Setter
@ConfigurationProperties("spring.datasource")
public class DatabaseConfiguration {

    private String url;
    private String driverClassName;
    private String username;
    private String password;
}
