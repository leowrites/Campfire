package user.sort;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ISortInput sortInput(){
        return new SortInteractor();
    }
}
