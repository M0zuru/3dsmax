package com.shurima.dsmax.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${video.storage.dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(uploadDir, registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        registry
                .addResourceHandler("/" + dirName + "/**",
                        "/css/**",
                        "/library/materialize/css/**",
                        "/library/materialize/js/**",
                        "/img/**")
                .addResourceLocations("file:/"+ uploadPath + "/",
                        "classpath:/static/css/",
                        "classpath:/static/library/materialize/css/",
                        "classpath:/static/library/materialize/js/",
                        "classpath:/static/img/",
                        "classpath:/static/img/default/");
    }
}
