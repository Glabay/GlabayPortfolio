package xyz.glabaystudios.data.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Glabay
 * @project GlabayPortfolio
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-11-25
 */
@Getter
@Setter
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long uid;

    private String email;
    private String username;
    private String encryptedPassword;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String createdOn;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String lastUpdated;
}
