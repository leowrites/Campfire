package usecases.common;

import org.springframework.stereotype.Component;
import usecases.requesttobecompanyrep.CompanyRepApplicationProcessResult;

@Component
public class UseCaseOutputFactory {
    public CompanyRepApplicationProcessResult createCompanyApplicationResponse() {
        return new CompanyRepApplicationProcessResult();
    }
}
