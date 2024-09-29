package zerobase.weather.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(title = "날씨 일기 프로젝트 :)",
                description = "날씨 일기를 CRUD 할 수 있는 벡엔드 API 입니다",
                version = "2.0"))

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi diaryApi() {
        return GroupedOpenApi.builder()
                .group("diary-api") // API 그룹 이름 지정
                .packagesToScan("zerobase.weather") // 특정 컨트롤러만 포함
                .build();
    }
}


