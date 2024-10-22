package com.ggg.kotlin.hospital_java.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig :WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 허용
            .allowedOrigins("http://localhost:3000") // 특정 도메인만 허용
            .allowedOrigins("http://192.168.35.210:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 메소드
            .allowedHeaders("*") // 모든 헤더 허용
            .allowCredentials(true) // 쿠키 전송 허용
    }

}