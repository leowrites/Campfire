package user.createCorporate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.ICorporateGenerateDataAccess;


@Configuration
public class CorporateGenerateConfig {

    /**
     * database configuration for the PageGenerate
     * @param dataAccess: CorporateGenerateDataAccess needs to be passed in to the input boundary
     * @return interactor with parameters dataAccess and CorporateFactory passed in
     */
    @Bean
    public ICorporateGenerateInput inputConfig(ICorporateGenerateDataAccess dataAccess){
        return new CorporateGenerateInteractor(dataAccess, new CorporateFactory());
    }
}
