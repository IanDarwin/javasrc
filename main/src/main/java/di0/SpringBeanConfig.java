package di0;

import jakarta.enterprise.inject.Produces;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {
    @Bean
    public Processor processor() {
        return new Process();
    }
}
