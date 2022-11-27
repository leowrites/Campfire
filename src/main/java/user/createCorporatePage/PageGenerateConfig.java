package user.createCorporatePage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.IPageGenerateDataAccess;


@Configuration
public class PageGenerateConfig {

    /**
     * database configuration for the PageGenerate
     * @param dataAccess: PageGenerateDataAccess needs to be passed in to the input boundary
     * @return interactor with parameters dataAccess and PageFactory passed in
     */
    @Bean
    public IPageGenerateInput inputConfig(IPageGenerateDataAccess dataAccess){
        return new PageGenerateInteractor(dataAccess, new PageFactory());
    }
}
