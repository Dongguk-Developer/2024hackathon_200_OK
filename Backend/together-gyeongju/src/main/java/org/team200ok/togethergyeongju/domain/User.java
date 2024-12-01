package org.team200ok.togethergyeongju.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.team200ok.togethergyeongju.constant.Gender;
import org.team200ok.togethergyeongju.constant.JobCategory;
import org.team200ok.togethergyeongju.constant.SnsType;
import org.team200ok.togethergyeongju.dto.auth.oauth.userInfo.OAuthUserInfoDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class User implements OAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String snsId;

    @Column(nullable = false, length = 50)
    private SnsType snsType;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 255)
    private String profileImage;

    @Column(length = 255)
    private String email;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private JobCategory jobCategory;

    @Column(nullable = false)
    private Gender gender;

    private String authority;


    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> authority);
    }

    @Override
    public String getName() {
        return name;
    }

    public static User from(OAuthUserInfoDto dto, int age, Gender gender, JobCategory jobCategory){
        return User.builder()
                .snsId(dto.getSnsId())
                .snsType(dto.getSnsType())
                .name(dto.getName())
                .email(dto.getEmail())
                .age(age)
                .jobCategory(jobCategory)
                .gender(gender)
                .build();
    }
}