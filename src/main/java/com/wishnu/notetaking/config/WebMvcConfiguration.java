package com.wishnu.notetaking.config;

import com.wishnu.notetaking.initializer.ResponseLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setIncludeClientInfo(true);

        return filter;
    }

    @Bean
    public ResponseLoggingFilter responseLoggingFilter() {
        ResponseLoggingFilter filter = new ResponseLoggingFilter();
        filter.setMaxPayloadLength(10000);
        return filter;
    }
}
