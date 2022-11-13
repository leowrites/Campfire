package useCases.createCorporatePage;

public interface PageGenerateOutputBoundary {
    PageGenerateResponseModel prepareSuccessView(PageGenerateResponseModel pageInfo);
    PageGenerateResponseModel prepareFailView(String error);
}
