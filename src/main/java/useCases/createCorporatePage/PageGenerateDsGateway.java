package useCases.createCorporatePage;

public interface PageGenerateDsGateway {
    boolean existsByPageLabel(String identifier);
    void save(PageGenerateDsRequestModel requestModel);
}
