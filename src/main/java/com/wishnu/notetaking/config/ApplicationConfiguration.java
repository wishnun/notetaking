package com.wishnu.notetaking.config;

import com.wishnu.notetaking.NotetakingApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class},
        scanBasePackageClasses = NotetakingApplication.class)
@Import({OpenApiConfiguration.class })
public class ApplicationConfiguration {
}
