package com.chao.ssmvue.core.config;

import com.chao.ssmvue.core.shiro.filter.ShiroUnAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 * 因为加入了shiro 所以正好去用过滤器去进行跨域
 * @see ShiroUnAuthorizationFilter
 *
 */
//@Configuration
public class CORSConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://192.168.1.47:8081")
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                        .maxAge(3600)
                        .allowCredentials(true);
            }
        };
    }

}
