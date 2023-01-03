package usecases.requesttobecompanyrep;

import service.jpaImp.ICompanyRepApplicationDAO;
import usecases.common.UseCaseOutputFactory;

public class CompanyRepApplicationProcessor implements ICompanyRepApplicationProcessor{

    private final ICompanyRepApplicationDAO companyRepApplicationDAO;
    private final UseCaseOutputFactory outputFactory;


    public CompanyRepApplicationProcessor(ICompanyRepApplicationDAO companyRepApplicationDAO,
                                          UseCaseOutputFactory outputFactory) {
        this.companyRepApplicationDAO = companyRepApplicationDAO;
        this.outputFactory = outputFactory;
    }

    /**
     * Instantiates a company representative application.
     *
     * @return a {@link CompanyRepApplicationProcessResult} object containing the result of the application
     * process
     */
    @Override
    public CompanyRepApplicationProcessResult instantiateApplication(CompanyRepApplicationInput input) {

        return outputFactory.createCompanyApplicationResponse();
    }
}
