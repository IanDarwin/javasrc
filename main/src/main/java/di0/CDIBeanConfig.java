package di0;

import jakarta.enterprise.inject.Produces;
import org.springframework.context.annotation.Bean;

public class CDIBeanConfig {
    @Bean
    public Processor processor() {
        return new Process();
    }
}
