package t.education.starter.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import t.education.starter.interceptor.StarterRestTemplate;
import t.education.starter.filter.LoggingFilter;
import t.education.starter.handler.GlobalExceptionHandler;
import t.education.starter.properties.StarterProperties;


@AutoConfiguration
@EnableConfigurationProperties(StarterProperties.class)
@ConditionalOnProperty(prefix = "starter", value = "enabled", havingValue = "true")
public class StarterConfig {

    @Bean
    @ConditionalOnMissingBean
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
    }

    @Bean
    @ConditionalOnExpression("${starter.handlerEnabled:false}")
    public GlobalExceptionHandler handler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public StarterRestTemplate starterRestTemplate()
    {
        return new StarterRestTemplate();
    }
}
