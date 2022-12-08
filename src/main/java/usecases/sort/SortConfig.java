package usecases.sort;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** A configuration class for Spring that gets a sortInteractor. It is connected to the Autowired
 * constructor in the SortController class.
 */
@Configuration
public class SortConfig {

    /** Retrieves a new SortInteractor, as searched for by the Autowired SortController constructor.
     * @return a new sort interactor.
     */
    @Bean
    public ISortInput sortInput(){
        return new SortInteractor();
    }
}
