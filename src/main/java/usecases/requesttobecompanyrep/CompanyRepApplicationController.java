package usecases.requesttobecompanyrep;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class CompanyRepApplicationController {
    private final ModelMapper modelMapper;
    private final ICompanyRepApplicationProcessor processor;

    @Autowired
    public CompanyRepApplicationController(ModelMapper modelMapper, ICompanyRepApplicationProcessor processor) {
        this.modelMapper = modelMapper;
        this.processor = processor;
    }

    @PostMapping("/reps/apply")
    @PreAuthorize("hasRole('ROLE_AUTHENTICATED_USER')")
    public ResponseEntity<CompanyRepApplicationResponseDto> applyToBeRep(
            @Valid @RequestBody CompanyRepApplicationRequestDto requestDto
    ) {
        CompanyRepApplicationInput input = modelMapper.map(requestDto, CompanyRepApplicationInput.class);
        CompanyRepApplicationProcessResult result = processor.instantiateApplication(input);
        CompanyRepApplicationResponseDto responseDto = modelMapper.map(result, CompanyRepApplicationResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
