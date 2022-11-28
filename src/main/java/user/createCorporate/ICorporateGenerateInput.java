package user.createCorporate;

// Use Case Layer
// Input boundary that connects to the use case interactor

public interface ICorporateGenerateInput {
    CorporateGenerateResponseModel create(CorporateGenerateRequestModel requestModel);
}
