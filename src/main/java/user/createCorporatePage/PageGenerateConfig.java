package user.createCorporatePage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.IPageGenerateDataAccess;

@Configuration
public class PageGenerateConfig {
    @Bean
    public IPageGenerateInput inputConfig(IPageGenerateDataAccess dataAccess){
        return new PageGenerateInteractor(dataAccess, new CorporatePageFactory());
    }
}
