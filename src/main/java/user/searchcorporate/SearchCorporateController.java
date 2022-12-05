package user.searchcorporate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


    @ComponentScan("main")
    @RestController
    public class SearchCorporateController{

        private final ISearchCorporateInput interactor;

        @Autowired
        public SearchCorporateController(ISearchCorporateInput interactor) {
            this.interactor = interactor;
        }

        @GetMapping("/search")
        public ResponseEntity<SearchCorporateResponseModel> receiveSignUpForm(@RequestBody SearchCorporateRequestModel inputdata) {
            SearchCorporateResponseModel responseDS = this.interactor.search(inputdata);
            if (responseDS.getMessage().equals("success")){ // if there are results to return
                return new ResponseEntity<>(responseDS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseDS, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
    }


