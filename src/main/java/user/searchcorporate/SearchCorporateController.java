package user.searchcorporate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ServerStatus;

/**
 * This class is a REST controller for handling requests to search for corporate accounts.
 * It uses the ISearchCorporateInput interactor to process the search request and return a response.
 */
    @ComponentScan("main")
    @RestController
    public class SearchCorporateController{

        private final ISearchCorporateInput interactor;

        @Autowired
        public SearchCorporateController(ISearchCorporateInput interactor) {
            this.interactor = interactor;
        }

    /**
     * This method receives a search request and calls the `search` method on the interactor to process it.
     * If the search is successful, it returns a response with an HTTP status of OK (200).
     * If the search is not successful, it returns a response with an HTTP status of UNPROCESSABLE_ENTITY (422).*/
        @GetMapping("/search")
        public ResponseEntity<SearchCorporateResponseModel> receiveSignUpForm(@RequestBody SearchCorporateRequestModel inputdata) {
            SearchCorporateResponseModel responseDS = this.interactor.search(inputdata);
            if (responseDS.getStatus().equals(ServerStatus.SUCCESS)){ // if there are results to return
                return new ResponseEntity<>(responseDS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseDS, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
    }


