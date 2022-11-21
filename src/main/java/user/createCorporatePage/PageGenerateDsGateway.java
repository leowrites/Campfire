package user.createCorporatePage;

public interface PageGenerateDsGateway {
    boolean existsByPageLabel(String identifier);
    void save(PageGenerateDsRequestModel requestModel);
}
