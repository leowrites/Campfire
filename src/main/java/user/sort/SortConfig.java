package user.sort;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SortConfig {

    @Bean
    public ISortInput sortInput(){
        return new SortInteractor();
    }
}
