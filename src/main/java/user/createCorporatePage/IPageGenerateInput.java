package user.createCorporatePage;

// Use Case Layer
// Input boundary that connects to the use case interactor

public interface IPageGenerateInput {
    PageGenerateResponseModel create(PageGenerateRequestModel requestModel);
}
