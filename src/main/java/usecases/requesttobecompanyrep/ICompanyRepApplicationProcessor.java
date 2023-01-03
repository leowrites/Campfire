package usecases.requesttobecompanyrep;

/**
 * An interface that defines the processing of a company representative application.
 */
public interface ICompanyRepApplicationProcessor {

    /**
     * Instantiates a company representative application.
     *
     * @return a {@link CompanyRepApplicationProcessResult} object containing the result of the application
     *         process
     */
    public CompanyRepApplicationProcessResult instantiateApplication(CompanyRepApplicationInput input);
}
