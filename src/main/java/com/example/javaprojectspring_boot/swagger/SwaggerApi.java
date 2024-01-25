package com.example.javaprojectspring_boot.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerApi {

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/auth/**")
                .group("Auth")
                .build();
    }

    @Bean
    public GroupedOpenApi chatApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/chat/**")
                .group("Chat")
                .build();
    }

    @Bean
    public GroupedOpenApi contactApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/contact/**")
                .group("Contact")
                .build();
    }

    @Bean
    public GroupedOpenApi audioApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/audio/**")
                .group("Audio")
                .build();
    }

    @Bean
    public GroupedOpenApi groupsApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/groups/**")
                .group("Groups")
                .build();
    }

    @Bean
    public GroupedOpenApi groupChatApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/groupChat/**")
                .group("GroupChat")
                .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/user/**")
                .group("User")
                .build();
    }
}
