package user.createCorporatePage;

import entity.Page;
import entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.IPageGenerateDataAccess;
import service.PageGenerateDataAccess;

@Configuration
public class PageGenerateConfig {
    @Bean
    public IPageGenerateInput inputConfig(IPageGenerateDataAccess dataAccess){
        return new PageGenerateInteractor(dataAccess, new PageFactory() {
            @Override
            public Page create(String pageName, User owner) {
                return null;
            }
        });
    }
}
