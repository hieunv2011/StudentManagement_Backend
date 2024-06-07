package com.example.student.config;


import com.example.student.entities.Role;
import com.example.student.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@CrossOrigin
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private  final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/**")
                        .permitAll()
                        .requestMatchers("api/v1/admin").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("api/v1/user").hasAnyAuthority(Role.USER.name())
                        .requestMatchers("api/v1/teacher/").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("api/v1/grade/").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("api/v1/student").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())




                        .requestMatchers("api/v1/class/").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())
                        .requestMatchers("api/v1/class/search").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())
                        .requestMatchers("api/v1/class/{id}").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())
                        .requestMatchers("api/v1/class/update/{id}").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())
                        .requestMatchers("api/v1/class/delete/{id}").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())
                        .requestMatchers("api/v1/class/{classId}/delete/{studentId}").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())
                        .requestMatchers("api/v1/class/{classId}/add/{studentId}").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())


                        .requestMatchers("api/v1/class/detail/{student_id}").hasAnyAuthority(Role.USER.name(),Role.ADMIN.name(),Role.TEACHER.name())
                        .requestMatchers("api/v1/student/detail/{class_id}").hasAnyAuthority(Role.USER.name(),Role.ADMIN.name(),Role.TEACHER.name())
                        .requestMatchers("api/v1/student/{id}").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())

                        .requestMatchers("api/v1/student/{id}/uploadImage").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())
                        .requestMatchers("api/v1/student/{id}/image").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())
                        .requestMatchers("api/v1/student/add").hasAnyAuthority(Role.ADMIN.name(),Role.TEACHER.name())


                        .requestMatchers("api/v1/grade/copyData").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("api/v1/grade/class/{clazz}").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("api/v1/grade/student/{student}").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("api/v1/grade/{clazz}/{student}/giuaky").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("api/v1/grade/{clazz}/{student}/cuoiky").hasAnyAuthority(Role.ADMIN.name())




                        .requestMatchers("api/v1/user/me").hasAnyAuthority(Role.USER.name(),Role.ADMIN.name(),Role.TEACHER.name())
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                );
        return http.build();

    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider =new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
        throws Exception{
        return config.getAuthenticationManager();
    }

}
