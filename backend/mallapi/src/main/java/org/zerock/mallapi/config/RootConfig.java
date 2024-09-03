package org.zerock.mallapi.config;
//DTO와 엔티티 사이의 매핑(처리 수월하도록)
import org.springframework.context.annotation.Configuration;// 설정파일 역할

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies; //매핑의 엄격도 설정
import org.springframework.context.annotation.Bean; //의존성 주입

@Configuration
public class RootConfig {
    
    @Bean
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)// private필드에도 접근 가능
                .setMatchingStrategy(MatchingStrategies.LOOSE); //필드 이름이 완전히 일치 않을 때 유용, 유연 매핑
        
        return modelMapper;
    }
}
