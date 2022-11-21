package user.createCorporatePage;

import user.createCorporatePage.exceptions.PageCreationFailedException;

// not needed
public class PageGeneratePresenter {

    public PageGenerateResponseModel prepareSuccessView(PageGenerateResponseModel response){
        return response;
    }

    public PageGenerateResponseModel prepareFailView(String error){
        throw new PageCreationFailedException(error);
    }


}
